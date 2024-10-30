package com.example.core.application.common

import com.example.core.application.common.interfaces.Command
import com.example.core.domain.DataError
import com.example.core.domain.constants.NetworkResponseCode
import com.example.core.domain.Result
import com.example.core.domain.RootError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import java.net.UnknownHostException
import java.util.concurrent.CancellationException
//dummy  asd asd
@Suppress("UNCHECKED_CAST")
abstract class AbstractCommandHandler<
        TCommand : Command,
        TResponseValue : Any,
        TCommandResult : Result<TResponseValue, RootError>> : BaseHandler<TResponseValue>() {

    private var result: Result<TResponseValue, RootError>? = defaultResult as? TCommandResult

    suspend fun handle(request: TCommand): Flow<TCommandResult> {
        return flow {
            emit(Result.Loading("Loading..."))
            try {
                result = handleRequest(request)
                emit(result)
            } catch (e: UnknownHostException) {
                Timber.d("CommandHandler: $e!")
                emit(defaultResult)
            } catch (e: IOException) {
                Timber.d("CommandHandler: $e!")
                result = Result.Error(DataError.Network.NO_INTERNET)
                emit(result)
            } catch (e: HttpException) {
                Timber.d("CommandHandler: $e!")
                if (e.response()?.code() == NetworkResponseCode.UN_AUTHORIZED) {
                    result = Result.Error(DataError.Network.UNAUTHORIZED)
                    emit(result)
                } else {
                    emit(defaultResult)
                }
            } catch (e: CancellationException) {
                Timber.d("CommandHandler: $e!")
                emit(defaultResult)
                throw e
            }
        } as Flow<TCommandResult>
    }

    protected abstract suspend fun handleRequest(request: TCommand): TCommandResult
}
