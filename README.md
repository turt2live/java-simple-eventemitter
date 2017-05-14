# java-simple-eventemitter
A library similar to how EventEmitter works in NodeJS.

# Usage

```java
import io.t2l.events.EventEmitterImpl;

public class SampleClass extends EventEmitterImpl {
    public void doSomething() {
        this.emit("myEvent", "Hello World!");
    }
}

// Example usage elsewhere
public static void main(String[] args) {
    SampleClass myObject = new SampleClass();
    myObject.on("myEvent", str -> System.out.println(str));
    
    myObject.doSomething();
}

```

### Using interfaces

If your implementation is behind an interface, something like the following would be suitable:

```java
import io.t2l.events.EventEmitter;
import io.t2l.events.EventEmitterImpl;

public interface MyInterface extends EventEmitter {
    void doSomething();
}

public class MyImplementation extends EventEmitterImpl implements MyInterface{
    @Override
    public void doSomething() {
        this.emit("myEvent", "Hello World!");
    }
}

```

Code using the `MyInterface` interface can still call the `on` method with the `EventEmitter` interface.