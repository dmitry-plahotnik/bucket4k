package bucket4k

object BucketCalc {

    class BucketState(val tokens: Int, val lastUpdateTimestampNano: Long)

    fun calcNewState(state: BucketState, currTimeNano: Long): BucketState {
        return BucketState(1, 2L)
    }

    fun calcState(): Long {

        return 0
    }
}