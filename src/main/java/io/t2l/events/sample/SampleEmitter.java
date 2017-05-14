package io.t2l.events.sample;

import io.t2l.events.EventEmitterImpl;

import java.util.function.Consumer;

/**
 * A sample class demonstrating how to use the EventEmitter
 */
public class SampleEmitter extends EventEmitterImpl {
    public static void main(String[] args) {
        SampleEmitter testEmitter = new SampleEmitter();

        // Register our handlers
        testEmitter.on("StringEvent", e -> System.out.println(e.toString()));
        testEmitter.<ObjectEvent>on("ObjectEvent", e -> System.out.println(e.getNumber()));
        testEmitter.onObjectEvent(e -> System.out.println("From custom on() method: " + e.getNumber()));

        // Invoke some events
        testEmitter.doSomething();
        testEmitter.doSomethingElse();
    }

    /**
     * Sample invoker of the "StringEvent" event
     */
    public void doSomething() {
        this.emit("StringEvent", "Hello World!!");
    }

    /**
     * Sample invoker of the "ObjectEvent" event
     */
    public void doSomethingElse() {
        this.emit("ObjectEvent", new ObjectEvent(47));
    }

    /**
     * Sample of how to specify your own on() method for code clarity
     * @param handler the handler to send to the super on()
     */
    public void onObjectEvent(Consumer<ObjectEvent> handler) {
        this.on("ObjectEvent", handler);
    }

    /**
     * A sample event class
     */
    public class ObjectEvent {

        private int i;

        ObjectEvent(int i) {
            this.i = i;
        }

        /**
         * Gets the number stored in this event
         * @return the stored number
         */
        public int getNumber() {
            return i;
        }
    }
}
