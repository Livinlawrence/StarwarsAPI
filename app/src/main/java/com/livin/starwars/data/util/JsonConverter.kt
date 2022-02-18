package com.livin.starwars.data.util

import java.lang.reflect.Type

interface JsonConverter {

    fun<T> fromJson(jsonString:String,type:Type):T

    fun<T> toJson(obj:T, type: Type):String
}