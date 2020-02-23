package bucket4k

import com.company.dmitry.throttling.TokenBucket
import java.util.concurrent.TimeUnit

open class SimpleTokenBucket(var tokens: Long, var lastUpdateTimeNano: Long): TokenBucket {

    constructor(): this(Long.MAX_VALUE, 0)

    override fun tryGetTokens(count: Int, currNanos: Long, settings: ThrottlingSettings) : Boolean {
        refill(currNanos, settings)
        if (tokens >= count) {
            tokens -= count
            return true
        }
        return false
    }

    private fun refill(currNanos: Long, settings: ThrottlingSettings) {
        val oneTokenRefill = TimeUnit.MILLISECONDS.toNanos(settings.refillTimeMillis) / settings.maxTokens
        val timeFrame = currNanos - lastUpdateTimeNano
        val newTokens = timeFrame/oneTokenRefill
        tokens = minOf(tokens + newTokens, settings.maxTokens)
        lastUpdateTimeNano = currNanos - (timeFrame % oneTokenRefill)
    }

    fun copyState(bucket: SimpleTokenBucket) {
        tokens = bucket.tokens
        lastUpdateTimeNano = bucket.lastUpdateTimeNano
    }
}