package com.livin.starwars

object Utils {
    fun waitForSomeTime(waitMilli: Long) {
        val startTime = System.currentTimeMillis()
        val endTime = startTime + waitMilli
        while (System.currentTimeMillis() < endTime) {
        }
    }
}