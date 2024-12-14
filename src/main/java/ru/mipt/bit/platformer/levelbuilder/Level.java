package ru.mipt.bit.platformer.levelbuilder;

import ru.mipt.bit.platformer.abstractions.Bullet;
import ru.mipt.bit.platformer.abstractions.Tank;
import ru.mipt.bit.platformer.abstractions.Tree;
import ru.mipt.bit.platformer.collisions.FindCollisions;
import ru.mipt.bit.platformer.abstractions.Collidability;
import ru.mipt.bit.platformer.ai.IAbstraction;


import ru.mipt.bit.platformer.eventmanager.EventListener;
import ru.mipt.bit.platformer.eventmanager.EventPublisher;
import ru.mipt.bit.platformer.eventmanager.Events;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CopyOnWriteArrayList;

import java.util.*;

public class Level implements EventPublisher {
    private final Map<Events, List<EventListener>> listeners = new HashMap<>();

    private Tank tank = null;
    private final Queue<Bullet> bullets = new ConcurrentLinkedDeque<>();
    private final List<Tree> trees = new CopyOnWriteArrayList<>();
    private final List<Tank> aiTanks = new CopyOnWriteArrayList<>();
    private final List<IAbstraction> abstractions = new CopyOnWriteArrayList<>();
    private final List<Collidability> collidableObjects = new CopyOnWriteArrayList<>();

    public Level(List<Events> eventTypes) {
        eventTypes.forEach(event -> listeners.put(event, new ArrayList<>()));
    }

    public void addTank(Tank tank) {
        this.tank = tank;
    }

    public void addAiTanks(List<Tank> aiTanks) {
        this.aiTanks.addAll(aiTanks);
    }

    public void addTrees(List<Tree> trees) {
        this.trees.addAll(trees);
    }

    public void addAbstraction(List<IAbstraction> abstractions) {
        this.abstractions.addAll(abstractions);
    }

    public List<IAbstraction> getAbstractions() {
        return abstractions;
    }

    public Tank getTank() {
        return tank;
    }

    public List<Tree> getTrees() {
        return trees;
    }

    public List<Tank> getAiTanks() {
        return aiTanks;
    }

    public void registerTankDestruction(Tank curTank) {
        if (tank != curTank) {
            aiTanks.remove(curTank);
        }
        notify(Events.DELETE_TANK, curTank);
    }

    public void registerBulletDestruction(Bullet bullet) {
        bullets.remove(bullet);
        notify(Events.DELETE_BULLET, bullet);
    }

    public void registerBulletCreation(Bullet bullet) {
        bullets.add(bullet);
        notify(Events.CREATE_BULLET, bullet);
    }


    public Queue<Bullet> getBullets() {
        return bullets;
    }

    @Override
    public void subscribe(Events event, EventListener eventListener) {
        List<EventListener> eventListeners = listeners.get(event);
        eventListeners.add(eventListener);
    }

    @Override
    public void unsubscribe(Events event, EventListener eventListener) {
        List<EventListener> eventListeners = listeners.get(event);
        eventListeners.remove(eventListener);
    }

    @Override
    public void notify(Events event, Object object) {
        List<EventListener> eventListeners = listeners.get(event);
        for (EventListener listener : eventListeners) {
            listener.update(event, object);
        }
    }
}
