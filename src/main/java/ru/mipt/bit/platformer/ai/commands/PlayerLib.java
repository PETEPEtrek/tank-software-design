package ru.mipt.bit.platformer.ai.commands;
import static com.badlogic.gdx.Input.Keys.*;
import java.util.Map;
import java.util.HashMap;


public class PlayerLib implements ICommandLib {
    public PlayerLib() {
    }

    public Map<Integer, IFactory> getCommandLib() {
    	ShootFactory shootFactory = new ShootFactory();
        ToggleFactory toggleFactory = new ToggleFactory();
        MovingFactory movingFactory = new MovingFactory();
        Map<Integer, IFactory> factoryMap = new HashMap<>();
        factoryMap.put(UP, movingFactory);
        factoryMap.put(LEFT, movingFactory);
        factoryMap.put(DOWN, movingFactory);
        factoryMap.put(RIGHT, movingFactory);
        factoryMap.put(W, movingFactory);
        factoryMap.put(A, movingFactory);
        factoryMap.put(S, movingFactory);
        factoryMap.put(D, movingFactory);
        factoryMap.put(L, toggleFactory);
        factoryMap.put(SPACE, shootFactory);
        return factoryMap;
    }
}