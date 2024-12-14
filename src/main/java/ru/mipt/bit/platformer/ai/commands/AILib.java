package ru.mipt.bit.platformer.ai.commands;
import static com.badlogic.gdx.Input.Keys.*;
import java.util.HashMap;
import java.util.Map;

public class AILib implements ICommandLib {
    public AILib() {
    }

    public Map<Integer, IFactory> getCommandLib() {
        MovingFactory movingFactory = new MovingFactory();
        ShootFactory shootFactory = new ShootFactory();
        Map<Integer, IFactory> factoryMap = new HashMap<>();
        factoryMap.put(UP, movingFactory);
        factoryMap.put(LEFT, movingFactory);
        factoryMap.put(DOWN, movingFactory);
        factoryMap.put(RIGHT, movingFactory);
        factoryMap.put(SPACE, shootFactory);
        return factoryMap;
    }
}