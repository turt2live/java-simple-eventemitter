package io.t2l.events;

import java.util.function.Consumer;

public class TestHandler<T> implements Consumer<T> {

    public int callCount = 0;
    public T lastCallResult = null;

    @Override
    public void accept(T o) {
        callCount++;
        lastCallResult = o;
    }
}
