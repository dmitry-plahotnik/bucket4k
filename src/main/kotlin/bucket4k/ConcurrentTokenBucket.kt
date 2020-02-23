package bucket4k

import com.company.dmitry.throttling.TokenBucket
import java.util.concurrent.atomic.AtomicReference

class ConcurrentTokenBucket: TokenBucket {
    private val bucketRef: AtomicReference<SimpleTokenBucket> = AtomicReference(SimpleTokenBucket())

    override fun tryGetTokens(count: Int, currNanos: Long, settings: ThrottlingSettings): Boolean {
        val updated = SimpleTokenBucket()
        while (true) {
            val current = bucketRef.get()
            updated.copyState(current)
            val success = updated.tryGetTokens(count, currNanos, settings)
            if (bucketRef.compareAndSet(current, updated)) {
                return success
            }
        }
    }
}