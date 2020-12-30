# elog4j

This is the embryo of a new log framework for Java. Why? Because structured logging is not easily supported with 
any legacy frameworks. This is becuase they are fundamentally designed around log "lines" of text, and whereas you 
can easily format a structured message as text, parse a text line as a structured message is a lot harder. 

## TLDR;

```
Logger log = LogManager.getLogger(getClass());
log.debug("Test message", e -> e.label("type", "user").put("age", 46).put("name", "Fungrim"));
```

## TLDR; Redux

```
// create manager and set as static - "global"
LogManager manager = LogManager.builder(ClockService.systemUtc())
  .global()
  .build();

// route a category
manager.route(LogManager.class.getPackage())
  .at(LogLevel.INFO)
  .with(e -> e.label("elog4j", "internal"))
  .extractSource();
  .to(ConsoleSink.getDefaultInstance());
  
// route everything else
manager.routeUnmatched()
  .at(LogLevel.INFO)
  .with(e -> e.label("elog4j", "unmatched"));
  .to(ConsoleSink.getDefaultInstance());
```
