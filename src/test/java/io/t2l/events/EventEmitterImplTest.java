package io.t2l.events;

import junit.framework.TestCase;

public class EventEmitterImplTest extends TestCase {
    public void testSimpleEmit(){
        TestEmitter emitter = new TestEmitter();
        TestHandler handler = new TestHandler();

        emitter.on("string", handler);

        emitter.doEmit("string", "Hello World");

        assertEquals(1, handler.callCount);
        assertEquals("Hello World", handler.lastCallResult);
    }

    public void testMultipleHandlers(){
        TestEmitter emitter = new TestEmitter();
        TestHandler handler1 = new TestHandler();
        TestHandler handler2 = new TestHandler();

        emitter.on("string", handler1);
        emitter.on("string", handler2);

        emitter.doEmit("string", "Hello World");

        assertEquals(1, handler1.callCount);
        assertEquals(1, handler2.callCount);
        assertEquals("Hello World", handler1.lastCallResult);
        assertEquals("Hello World", handler2.lastCallResult);
    }

    public void testNoHandlers(){
        TestEmitter emitter = new TestEmitter();
        TestHandler handler = new TestHandler();

        emitter.on("string", handler);

        emitter.doEmit("something else", "Hello World");

        assertEquals(0, handler.callCount);
        assertNull(handler.lastCallResult);
    }

    public void testErrorCatcher() {
        TestEmitter emitter = new TestEmitter();
        TestHandler errorHandler = new TestHandler();

        emitter.on("throw", e -> {throw new RuntimeException("TEST THROW");});
        emitter.on("error", errorHandler);

        emitter.doEmit("throw", null);

        assertEquals(1, errorHandler.callCount);
        assertTrue(errorHandler.lastCallResult instanceof RuntimeException);
        assertEquals("TEST THROW", ((RuntimeException)errorHandler.lastCallResult).getMessage());
    }

    public void testNullEventName() {
        TestEmitter emitter = new TestEmitter();
        TestHandler handler = new TestHandler();

        emitter.on(null, handler);

        emitter.doEmit(null, "test");

        assertEquals(1, handler.callCount);
        assertEquals("test", handler.lastCallResult);
    }

    public void testNullEventData() {
        TestEmitter emitter = new TestEmitter();
        TestHandler handler = new TestHandler();

        handler.lastCallResult = "NOT NULL"; // so we can actually see if it gets called

        emitter.on("event", handler);

        emitter.doEmit("event", null);

        assertEquals(1, handler.callCount);
        assertNull(handler.lastCallResult);
    }

    public void testMultipleEmitsSameHandler() {
        TestEmitter emitter = new TestEmitter();
        TestHandler handler = new TestHandler();

        emitter.on("event", handler);

        emitter.doEmit("event", "test1");

        assertEquals(1, handler.callCount);
        assertEquals("test1", handler.lastCallResult);

        emitter.doEmit("event", "test2");

        assertEquals(2, handler.callCount);
        assertEquals("test2", handler.lastCallResult);
    }

    public void testHandlerAddedAfterEmit() {
        TestEmitter emitter = new TestEmitter();
        TestHandler handler1 = new TestHandler();
        TestHandler handler2 = new TestHandler();

        emitter.on("event", handler1);

        emitter.doEmit("event", "test1");

        assertEquals(1, handler1.callCount);
        assertEquals(0, handler2.callCount);
        assertEquals("test1", handler1.lastCallResult);
        assertNull(handler2.lastCallResult);

        emitter.on("event", handler2);

        emitter.doEmit("event", "test2");

        assertEquals(2, handler1.callCount);
        assertEquals(1, handler2.callCount);
        assertEquals("test2", handler1.lastCallResult);
        assertEquals("test2", handler2.lastCallResult);
    }

    public void testCaseMatters() {
        TestEmitter emitter = new TestEmitter();
        TestHandler lowercaseHandler = new TestHandler();
        TestHandler uppercaseHandler = new TestHandler();

        emitter.on("event", lowercaseHandler);
        emitter.on("EVENT", uppercaseHandler);

        emitter.doEmit("EVENT", "test");

        assertEquals(0, lowercaseHandler.callCount);
        assertEquals(1, uppercaseHandler.callCount);
        assertNull(lowercaseHandler.lastCallResult);
        assertEquals("test", uppercaseHandler.lastCallResult);
    }
}
