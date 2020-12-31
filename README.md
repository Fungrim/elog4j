# elog4j

This is the embryo of a new log framework for Java. Why? Because structured logging is not easily supported with 
any legacy frameworks. This is becuase they are fundamentally designed around log "lines" of text, and whereas you 
can easily format a structured message as text, parse a text line as a structured message is a lot harder. 

## TLDR;

```java
Logger log = LogManager.getLogger(getClass());
log.debug("Test message", e -> e.label("type", "user")
  .prop("age", 46)
  .prop("name", "Fungrim"));
```

## TLDR; Properties vs Labels

* Label: For storage solutions, such as Google Operations, where labels are a first class citizen these will be used. You should use labels as categories, types and cohorts, to use for analysis, not for specifik values on entities. For other storages, such as Elastic, the labels will be part of the complete payload. 

* Property: Properties are part of the structured payload. which is a simplified data structure supporting strings, numbers, boolean and nested arrays (but not an array at the top level). 

## TLDR; Config

```java
// create manager
LogManager manager = LogManager.builder(ClockService.systemUtc())
  .global() // set as the static "global" manager
  .build();

// route a category
manager.route(LogManager.class.getPackage())
  .at(LogLevel.INFO) // category threshold
  .with(e -> e.label("category", "internal")) // label all as 'internal' 
  .extractSourceLocation(); // include source class, method and line if possible
  .to(ConsoleSink.getDefaultInstance()); 
  
// route everything else
manager.routeUnmatched()
  .at(LogLevel.ERROR) // category threshold
  .with(e -> e.label("category", "unmatched")); // label all as 'unmatched' 
  .to(ConsoleSink.getDefaultInstance());
```

## TLDR; Decorators, Filters and Sinks

* Decorator: A decorator works on an `LogEvent.Builder` and is able to modify the event. This is a functional interface used while logging. 

* Filter: A filter inspects a created `LogEvent` and either pass it on or drops it. 

* Sink: A sink receives a list of events "sinks" it to a storage. There are "wrapper" sinks for batching and asynchronous logging. 

## TLDR; Batches and Threads

```java
// new thread, the batch
Sink target = ConsoleSink.getDefaultInstance(); // end target
Sink batchSink = BatchSink.wrap(target, 256); // batches of 256
Sinc asyncSink = AsyncSink.wrap(batchSink); // sinle thread handoff

manager.route(MyClass.class.getPackage())
  .at(LogLevel.INFO) // category threshold
  .to(asyncSink); 
```
