package com.livin.starwars.data.util

import com.google.gson.Gson
import java.lang.reflect.Type

class GsonConverter(private val gson: Gson):JsonConverter {

    override fun <T> fromJson(jsonString: String, type: Type): T {
        return gson.fromJson(jsonString,type)
    }

    override fun <T> toJson(obj: T, type: Type): String {
        return gson.toJson(obj,type)
    }
}