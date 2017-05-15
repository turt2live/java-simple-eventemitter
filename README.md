# java-simple-eventemitter
A library similar to how EventEmitter works in NodeJS.

# Install

Maven:

```
<repositories>
    <repository>
        <name>t2l-repo</name>
        <url>http://ci.t2l.io:8081/artifactory/gradle-dev-local/</url>
    </repository>
</repositories>
<dependencies>
    <dependency>
        <groupId>io.t2l</groupId>
        <artifactId>EventEmitter</artifactId>
        <version>1.0.0</version>
    </dependency>
</dependencies>
```

Gradle:
```
repositories {
    maven { url 'http://ci.t2l.io:8081/artifactory/gradle-dev-local/' }
}
dependencies {
    compile 'io.t2l:EventEmitter:1.0-SNAPSHOT'
}
```

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