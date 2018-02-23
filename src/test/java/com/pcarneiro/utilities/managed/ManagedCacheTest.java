package com.pcarneiro.utilities.managed;

import static org.testng.Assert.*;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.testng.annotations.Test;

/**
 * @author pedrocarneiro
 */
public class ManagedCacheTest {

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void shouldFailOnInvalidArgument() {
    new ManagedCache(null);
  }

  @Test
  public void shouldVerifyAttributes() {
    Cache cache = CacheBuilder.newBuilder().recordStats().build();
    ManagedCache subject = new ManagedCache(cache);

    assertEquals(subject.requestCount(), cache.stats().requestCount());
    assertEquals(subject.hitCount(), cache.stats().hitCount());
    assertEquals(subject.hitRate(), cache.stats().hitRate());
    assertEquals(subject.missCount(), cache.stats().missCount());
    assertEquals(subject.missRate(), cache.stats().missRate());
    assertEquals(subject.loadCount(), cache.stats().loadCount());
    assertEquals(subject.loadSuccessCount(), cache.stats().loadSuccessCount());
    assertEquals(subject.loadExceptionCount(), cache.stats().loadExceptionCount());
    assertEquals(subject.loadExceptionRate(), cache.stats().loadExceptionRate());
    assertEquals(subject.totalLoadTime(), cache.stats().totalLoadTime());
    assertEquals(subject.averageLoadPenalty(), cache.stats().averageLoadPenalty());
    assertEquals(subject.evictionCount(), cache.stats().requestCount());
  }
}
