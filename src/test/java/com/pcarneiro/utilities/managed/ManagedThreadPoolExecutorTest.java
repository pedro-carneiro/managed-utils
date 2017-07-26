package com.pcarneiro.utilities.managed;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.Test;

public class ManagedThreadPoolExecutorTest {

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void shouldFailOnInvalidArgument() throws Exception {
    new ManagedThreadPoolExecutor(null);
  }

  @Test
  public void shouldVerifyAttributes() throws Exception {
    ThreadPoolExecutor executor = new ThreadPoolExecutor(
      1, 2, 50, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1)
    );
    try {
      ManagedThreadPoolExecutor subject = new ManagedThreadPoolExecutor(executor);

      assertEquals(subject.getActiveCount(), executor.getActiveCount());
      assertEquals(subject.getCompletedTaskCount(), executor.getCompletedTaskCount());
      assertEquals(subject.getTaskCount(), executor.getTaskCount());
      assertEquals(subject.getPoolSize(), executor.getPoolSize());
      assertEquals(subject.getCorePoolSize(), executor.getCorePoolSize());
      assertEquals(subject.getLargestPoolSize(), executor.getLargestPoolSize());
      assertEquals(subject.getMaximumPoolSize(), executor.getMaximumPoolSize());
      assertEquals(subject.getQueueSize(), executor.getQueue().size());
    } finally {
      executor.shutdown();
    }
  }
}
