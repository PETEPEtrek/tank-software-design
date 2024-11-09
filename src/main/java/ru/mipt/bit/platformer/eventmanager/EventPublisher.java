package ru.mipt.bit.platformer.eventmanager;

public interface EventPublisher {
    void subscribe(Events event, EventListener eventListener);

    void unsubscribe(Events event, EventListener eventListener);

    void notify(Events event, Object object);
}
