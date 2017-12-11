package com.pcarneiro.utilities.managed;

import static org.testng.Assert.assertEquals;

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.testng.annotations.Test;

public class ManagedThreadPoolTaskSchedulerTest {

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void shouldFailOnInvalidArgument() {
    new ManagedThreadPoolTaskScheduler(null);
  }

  @Test
  public void shouldVerifyAttributes() {
    ThreadPoolTaskScheduler executor = new ThreadPoolTaskScheduler();
    try {
      executor.initialize();
      ManagedThreadPoolTaskScheduler subject = new ManagedThreadPoolTaskScheduler(executor);

      assertEquals(subject.getActiveCount(), executor.getActiveCount());
      assertEquals(subject.getCompletedTaskCount(),
        executor.getScheduledThreadPoolExecutor().getCompletedTaskCount());
      assertEquals(subject.getTaskCount(),
        executor.getScheduledThreadPoolExecutor().getTaskCount());
      assertEquals(subject.getPoolSize(), executor.getPoolSize());
      assertEquals(subject.getCorePoolSize(),
        executor.getScheduledThreadPoolExecutor().getCorePoolSize());
      assertEquals(subject.getLargestPoolSize(),
        executor.getScheduledThreadPoolExecutor().getLargestPoolSize());
      assertEquals(subject.getMaximumPoolSize(),
        executor.getScheduledThreadPoolExecutor().getMaximumPoolSize());
      assertEquals(subject.getQueueSize(),
        executor.getScheduledThreadPoolExecutor().getQueue().size());
    } finally {
      executor.shutdown();
    }
  }
}
