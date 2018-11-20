# Managed utilities [![Build Status](https://travis-ci.org/pedro-carneiro/managed-utils.svg?branch=master)](https://travis-ci.org/pedro-carneiro/managed-utils) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.pcarneiro.utilities/managed-utils/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.pcarneiro.utilities/managed-utils) [![codecov.io](http://codecov.io/github/pedro-carneiro/managed-utils/coverage.svg?branch=master)](http://codecov.io/github/pedro-carneiro/managed-utils?branch=master)
This project holds some utility classes that expose bean properties as JMX attributes. First and
foremost, it holds classes that expose the queue sizes of some common thread pool implementations,
since I was unable to find this done anywhere else.

## Install
Import the dependency into your project.

On maven, use:
```
<dependency>
  <groupId>com.pcarneiro.utilities</groupId>
  <artifactId>managed-utils</artifactId>
  <version>${managed-utils.version}</version>
</dependency>
```

Several other dependency managers are supported. Please check the [Maven Central repository page](
https://maven-badges.herokuapp.com/maven-central/com.pcarneiro.utilities/managed-utils) for further
details.

## Usage
Wrap your thread pool implementation with one of the provided classes.

Example (for Spring based XML configuration):
```
<bean id="my.managed.executor" class="com.pcarneiro.utilities.managed.ManagedThreadPoolExecutor"
      c:executor-ref="my.executor"/>
```

Similarly, you can also wrap your Guava
[`Cache`](https://github.com/google/guava/wiki/CachesExplained) to expose statistics:
```
<bean id="my.managed.cache" class="com.pcarneiro.utilities.managed.ManagedCache"
      c:cache-ref="my.cache"/>
```

Then, expose the bean through JMX:
```
<bean id="exporter" class="org.springframework.jmx.export.MBeanExporter">
  <property name="beans">
    <map>
      <entry key="bean:name=myManagedExecutor" value-ref="my.managed.executor"/>
      <entry key="bean:name=myManagedCache" value-ref="my.managed.cache"/>
    </map>
  </property>
</bean>
```

## Motivation
Thread pools are a relevant tool, when it comes to unleashing the potential of the JVM concurrency
capabilities. When used along with a task queue, they can even be effectively used as rate limiters.

As such, it's important to monitor these queues, to evaluate how to dimension your thread pool, and
to better understand how it behaves under load.

Should you collect your metrics from managed beans, then this project may be useful to you.
