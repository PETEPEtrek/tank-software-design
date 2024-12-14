package ru.mipt.bit.platformer.engine;

import  ru.mipt.bit.platformer.graphics.Renderer;
import ru.mipt.bit.platformer.abstractions.Tank;
import ru.mipt.bit.platformer.abstractions.Bullet;
import ru.mipt.bit.platformer.ai.commands.IPersona;
import ru.mipt.bit.platformer.graphics.HpToggle;
import ru.mipt.bit.platformer.levelbuilder.Level;
import ru.mipt.bit.platformer.ai.IAbstraction;

import java.util.List;

//refactored class for moving tank
public class Engine {

    private final Level level;
    private List<IPersona> personas;
    
    public Engine(Level level, List<IPersona> personas, HpToggle showHp) {
        this.level = level;
        this.personas = personas;
    }

    public void doCalculations(float deltaTime) {
        for (IPersona persona : personas) {
            persona.chooseNextComand().execute();
        }

       for (IAbstraction abstraction: level.getAbstractions()) {
            abstraction.processMovementProgress(deltaTime);
       }

    }
}
