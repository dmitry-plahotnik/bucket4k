package bucket4k

open class DistributeThrottlingSettings(burst: Long, refillTimeSec: Long, val syncPeriodMillis: Long)
    : ThrottlingSettings(burst, refillTimeSec)