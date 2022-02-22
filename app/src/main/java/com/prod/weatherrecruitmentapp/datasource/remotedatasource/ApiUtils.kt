package com.prod.weatherrecruitmentapp.datasource.remotedatasource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

/**
 * Created by Piotr Jaszczurowski on 22.02.2022
 */

fun<T> result(call: suspend ()-> Response<T?>): Flow<ResponseData<T?>> = flow {
    try {
        val call = call()
        call.let {
            if(call.isSuccessful){
                emit(ResponseData.Succes(it.body()))
            }
            else{
                call.errorBody()?.let {error ->
                    error.close()
                    emit(ResponseData.Error(error.string()))
                }
            }
        }
    }catch (t:Throwable){
        t.printStackTrace()
        emit(ResponseData.Error(t.message.toString()))
    }
}