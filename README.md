# Managed utilities [![Build Status](https://travis-ci.org/pedro-carneiro/managed-utils.svg?branch=master)](https://travis-ci.org/pedro-carneiro/managed-utils) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.pcarneiro.utilities/managed-utils/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.pcarneiro.utilities/managed-utils)
This project holds some utility classes that expose bean properties as JMX attributes. First and foremost, it holds classes that expose the queue sizes of some common thread pool implementations, since I was unable to find this done anywhere else.

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

Several other dependency managers are supported. Please check the [Maven Central repository page](https://maven-badges.herokuapp.com/maven-central/com.pcarneiro.utilities/managed-utils) for further details.

## Usage
Wrap your thread pool implementation with one of the provided classes.

Example (for Spring based XML configuration):
```
<bean id="my.managed.executor" class="com.pcarneiro.utilities.managed.ManagedThreadPoolExecutor"
      c:executor-ref="my.executor"/>
```

Then, expose the bean through JMX:
```
<bean id="exporter" class="org.springframework.jmx.export.MBeanExporter">
  <property name="beans">
    <map>
      <entry key="bean:name=myManagedExecutor" value-ref="my.managed.executor"/>
    </map>
  </property>
</bean>
```
