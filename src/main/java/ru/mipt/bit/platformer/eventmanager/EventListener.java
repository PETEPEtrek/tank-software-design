package ru.mipt.bit.platformer.eventmanager;

public interface EventListener {
    void update(Events event, Object object);
}
