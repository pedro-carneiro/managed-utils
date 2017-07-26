package com.pcarneiro.utilities.managed;

import static org.testng.Assert.assertEquals;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.testng.annotations.Test;

public class ManagedThreadPoolTaskExecutorTest {

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void shouldFailOnInvalidArgument() throws Exception {
    new ManagedThreadPoolTaskExecutor(null);
  }

  @Test
  public void shouldVerifyAttributes() throws Exception {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    try {
      executor.initialize();
      ManagedThreadPoolTaskExecutor subject = new ManagedThreadPoolTaskExecutor(executor);

      assertEquals(subject.getActiveCount(), executor.getActiveCount());
      assertEquals(subject.getCompletedTaskCount(),
        executor.getThreadPoolExecutor().getCompletedTaskCount());
      assertEquals(subject.getTaskCount(), executor.getThreadPoolExecutor().getTaskCount());
      assertEquals(subject.getPoolSize(), executor.getPoolSize());
      assertEquals(subject.getCorePoolSize(), executor.getThreadPoolExecutor().getCorePoolSize());
      assertEquals(subject.getLargestPoolSize(),
        executor.getThreadPoolExecutor().getLargestPoolSize());
      assertEquals(subject.getMaximumPoolSize(),
        executor.getThreadPoolExecutor().getMaximumPoolSize());
      assertEquals(subject.getQueueSize(), executor.getThreadPoolExecutor().getQueue().size());
    } finally {
      executor.shutdown();
    }
  }
}
