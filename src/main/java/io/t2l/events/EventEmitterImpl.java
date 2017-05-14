package io.t2l.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Represents a class that is capable of emitting events
 */
public class EventEmitterImpl implements EventEmitter {

    private Map<String, ArrayList<Consumer<Object>>> handlers = new HashMap<>();

    protected void emit(String event, Object eventData) {
        if (!handlers.containsKey(event)) return; // nothing to do

        handlers.get(event).forEach(h -> {
            try {
                h.accept(eventData);
            } catch (Exception e) {
                if (!event.equals("error"))
                    this.emit("error", e);
                // else consume
            }
        });
    }

    public <T> void on(String event, Consumer<T> handler) {
        if (!handlers.containsKey(event))
            handlers.put(event, new ArrayList<>());

        //noinspection unchecked
        handlers.get(event).add(e -> handler.accept((T) e));
    }
}
