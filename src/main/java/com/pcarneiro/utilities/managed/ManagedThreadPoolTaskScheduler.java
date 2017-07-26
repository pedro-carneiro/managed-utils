package com.pcarneiro.utilities.managed;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@ManagedResource(description = "ThreadPoolExecutor attributes")
public class ManagedThreadPoolTaskScheduler {

  private final ThreadPoolTaskScheduler executor;

  /**
   * Constructor.
   *
   * @param executor A non-<code>null</code> instance of a {@link ThreadPoolTaskScheduler}.
   */
  public ManagedThreadPoolTaskScheduler(ThreadPoolTaskScheduler executor) {
    if (executor == null) {
      throw new IllegalArgumentException("executor is mandatory!");
    }
    this.executor = executor;
  }

  @ManagedAttribute(description = "Number of threads that are actively executing tasks.")
  public int getActiveCount() {
    return executor.getActiveCount();
  }

  @ManagedAttribute(description = "Total number of tasks that have completed execution.")
  public long getCompletedTaskCount() {
    return executor.getScheduledThreadPoolExecutor().getCompletedTaskCount();
  }

  @ManagedAttribute(description = "Total number of tasks "
      + "that have ever been scheduled for execution.")
  public long getTaskCount() {
    return executor.getScheduledThreadPoolExecutor().getTaskCount();
  }

  @ManagedAttribute(description = "Number of threads in the pool.")
  public int getPoolSize() {
    return executor.getPoolSize();
  }

  @ManagedAttribute(description = "Core number of threads.")
  public int getCorePoolSize() {
    return executor.getScheduledThreadPoolExecutor().getCorePoolSize();
  }

  @ManagedAttribute(description = "Largest number of threads "
      + "that have ever simultaneously been in the pool.")
  public int getLargestPoolSize() {
    return executor.getScheduledThreadPoolExecutor().getLargestPoolSize();
  }

  @ManagedAttribute(description = "Maximum allowed number of threads.")
  public int getMaximumPoolSize() {
    return executor.getScheduledThreadPoolExecutor().getMaximumPoolSize();
  }

  @ManagedAttribute(description = "Number of tasks queued for execution.")
  public int getQueueSize() {
    return executor.getScheduledThreadPoolExecutor().getQueue().size();
  }
}
