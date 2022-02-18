package com.livin.starwars.common

fun String.getIdFromUrl():String{
    val splittedIds = this.split("/")
    return splittedIds[splittedIds.size - 2]
}