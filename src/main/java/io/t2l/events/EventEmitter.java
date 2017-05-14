package io.t2l.events;

import java.util.function.Consumer;

/**
 * Represents a class that is capable of emitting events
 */
public interface EventEmitter {
    /**
     * Registers a handler for a specific event
     *
     * @param event   the event to listen to, case sensitive
     * @param handler the handler to process the event
     * @param <T>     the type of event expected
     */
    <T> void on(String event, Consumer<T> handler);
}
