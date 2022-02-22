package com.prod.weatherrecruitmentapp.datasource.remotedatasource

/**
 * Created by Piotr Jaszczurowski on 22.02.2022
 */
sealed class ResponseData<out T>{
    data class Succes<out R>(val data :R?): ResponseData<R>()
    data class Error(val errorMessage:String): ResponseData<Nothing>()
}
