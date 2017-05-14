package io.t2l.events;

public class TestEmitter extends EventEmitterImpl {
    public void doEmit(String emit, Object data) {
        this.emit(emit, data);
    }
}
