package com.pcarneiro.utilities.managed;

import com.google.common.cache.Cache;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;

@ManagedResource(description = "Cache attributes")
public class ManagedCache {

  private final Cache cache;

  /**
   * Constructor.
   *
   * @param cache A non-<code>null</code> instance of a {@link Cache}.
   */
  public ManagedCache(Cache cache) {
    if (cache == null) {
      throw new IllegalArgumentException("cache is mandatory!");
    }

    this.cache = cache;
  }

  /**
   * Returns the number of times {@link Cache} lookup methods have returned either a cached or
   * uncached value. This is defined as {@code hitCount + missCount}.
   */
  @ManagedAttribute(description = "Number of times lookup methods have returned either "
      + "a cached or uncached value (hitCount + missCount)")
  public long requestCount() {
    return cache.stats().requestCount();
  }

  /**
   * Returns the number of times {@link Cache} lookup methods have returned a cached value.
   */
  @ManagedAttribute(description = "Number of times lookup methods have returned a cached value")
  public long hitCount() {
    return cache.stats().hitCount();
  }

  /**
   * Returns the ratio of cache requests which were hits. This is defined as {@code hitCount /
   * requestCount}, or {@code 1.0} when {@code requestCount == 0}. Note that {@code hitRate +
   * missRate =~ 1.0}.
   */
  @ManagedAttribute(description = "Ratio of cache requests which were hits")
  public double hitRate() {
    return cache.stats().hitRate();
  }

  /**
   * Returns the number of times {@link Cache} lookup methods have returned an uncached (newly
   * loaded) value, or null. Multiple concurrent calls to {@link Cache} lookup methods on an absent
   * value can result in multiple misses, all returning the results of a single cache load
   * operation.
   */
  @ManagedAttribute(description = "Number of times lookup methods have returned a uncached value")
  public long missCount() {
    return cache.stats().missCount();
  }

  /**
   * Returns the ratio of cache requests which were misses. This is defined as {@code missCount /
   * requestCount}, or {@code 0.0} when {@code requestCount == 0}. Note that {@code hitRate +
   * missRate =~ 1.0}. Cache misses include all requests which weren't cache hits, including
   * requests which resulted in either successful or failed loading attempts, and requests which
   * waited for other threads to finish loading. It is thus the case that {@code missCount &gt;=
   * loadSuccessCount + loadExceptionCount}. Multiple concurrent misses for the same key will result
   * in a single load operation.
   */
  @ManagedAttribute(description = "Ratio of cache requests which were misses")
  public double missRate() {
    return cache.stats().missRate();
  }

  /**
   * Returns the total number of times that {@link Cache} lookup methods attempted to load new
   * values. This includes both successful load operations, as well as those that threw exceptions.
   * This is defined as {@code loadSuccessCount + loadExceptionCount}.
   */
  @ManagedAttribute(description = "Number of times lookup methods "
      + "have attempted to load new values")
  public long loadCount() {
    return cache.stats().loadCount();
  }

  /**
   * Returns the number of times {@link Cache} lookup methods have successfully loaded a new value.
   * This is usually incremented in conjunction with {@link #missCount}, though {@code missCount} is
   * also incremented when an exception is encountered during cache loading (see {@link
   * #loadExceptionCount}). Multiple concurrent misses for the same key will result in a single load
   * operation. This may be incremented not in conjunction with {@code missCount} if the load occurs
   * as a result of a refresh or if the cache loader returned more items than was requested. {@code
   * missCount} may also be incremented not in conjunction with this (nor {@link
   * #loadExceptionCount}) on calls to {@code getIfPresent}.
   */
  @ManagedAttribute(description = "Number of times lookup methods "
      + "have successfully loaded new values")
  public long loadSuccessCount() {
    return cache.stats().loadSuccessCount();
  }

  /**
   * Returns the number of times {@link Cache} lookup methods threw an exception while loading a new
   * value. This is usually incremented in conjunction with {@code missCount}, though {@code
   * missCount} is also incremented when cache loading completes successfully (see {@link
   * #loadSuccessCount}). Multiple concurrent misses for the same key will result in a single load
   * operation. This may be incremented not in conjunction with {@code missCount} if the load occurs
   * as a result of a refresh or if the cache loader returned more items than was requested. {@code
   * missCount} may also be incremented not in conjunction with this (nor {@link #loadSuccessCount})
   * on calls to {@code getIfPresent}.
   */
  @ManagedAttribute(description = "Number of times lookup methods "
      + "threw exceptions while loading new values")
  public long loadExceptionCount() {
    return cache.stats().loadExceptionCount();
  }

  /**
   * Returns the ratio of cache loading attempts which threw exceptions. This is defined as {@code
   * loadExceptionCount / (loadSuccessCount + loadExceptionCount)}, or {@code 0.0} when {@code
   * loadSuccessCount + loadExceptionCount == 0}.
   */
  @ManagedAttribute(description = "Ratio of cache loading attempts which threw exceptions")
  public double loadExceptionRate() {
    return cache.stats().loadExceptionCount();
  }

  /**
   * Returns the total number of nanoseconds the cache has spent loading new values. This can be
   * used to calculate the miss penalty. This value is increased every time {@code loadSuccessCount}
   * or {@code loadExceptionCount} is incremented.
   */
  @ManagedAttribute(description = "Total number of nanoseconds the cache spent loading new values")
  public long totalLoadTime() {
    return cache.stats().totalLoadTime();
  }

  /**
   * Returns the average time spent loading new values. This is defined as {@code totalLoadTime /
   * (loadSuccessCount + loadExceptionCount)}.
   */
  @ManagedAttribute(description = "Average time spent loading new values")
  public double averageLoadPenalty() {
    return cache.stats().averageLoadPenalty();
  }

  /**
   * Returns the number of times an entry has been evicted. This count does not include manual
   * {@linkplain Cache#invalidate invalidations}.
   */
  @ManagedAttribute(description = "Number of times cache entries have been evicted")
  public long evictionCount() {
    return cache.stats().evictionCount();
  }
}
