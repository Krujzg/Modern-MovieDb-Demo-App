package com.example.core.application.common

import com.example.core.application.common.interfaces.Query
import com.example.core.domain.DataError
import com.example.core.domain.Result
import com.example.core.domain.RootError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import java.net.UnknownHostException
import java.util.concurrent.CancellationException

@Suppress("UNCHECKED_CAST")
abstract class AbstractQueryHandler<
        TQuery : Query,
        TResultValue : Any,
        TQueryResult : Result<TResultValue, RootError>> : BaseHandler<TResultValue>() {

    private var result: TQueryResult? = defaultResult as? TQueryResult

    suspend fun handle(request: TQuery): Flow<TQueryResult> {
        return flow {
            emit(Result.Loading("Loading..."))
            try {
                result = handleQuery(request)
                emit(result)
            } catch (e: UnknownHostException) {
                Timber.d("QueryHandler: $e!")
                result = createErrorResult(DataError.Network.NO_INTERNET)
                emit(result)
            } catch (e: IOException) {
                Timber.d("QueryHandler: $e!")
                result = createErrorResult(DataError.Network.NO_INTERNET)
                emit(result)
            } catch (e: HttpException) {
                Timber.d("QueryHandler: $e!")
                if (e.response()?.code() == 401) {
                    result = createErrorResult(DataError.Network.UNAUTHORIZED)
                    emit(result)
                } else {
                    emit(defaultResult())
                }
            } catch (e: Exception) {
                Timber.d("QueryHandler: $e!")
                emit(defaultResult())

                if (e is CancellationException) {
                    throw e
                }
            }
        } as Flow<TQueryResult>
    }

    private fun defaultResult(): TQueryResult {
        return (defaultResult as? TQueryResult) ?: throw IllegalStateException("Invalid default result type")
    }

    private fun createErrorResult(error: RootError): TQueryResult {
        return (Result.Error<TResultValue, RootError>(error) as? TQueryResult)
            ?: throw IllegalStateException("Invalid error result type")
    }

    protected abstract suspend fun handleQuery(request: TQuery): TQueryResult
}
