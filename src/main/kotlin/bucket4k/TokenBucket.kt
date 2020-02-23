package com.company.dmitry.throttling

import bucket4k.ThrottlingSettings

interface TokenBucket {

    fun tryGetTokens(count: Int, currMillis: Long, settings: ThrottlingSettings): Boolean

}