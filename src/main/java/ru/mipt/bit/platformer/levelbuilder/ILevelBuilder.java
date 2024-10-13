package ru.mipt.bit.platformer.levelbuilder;

import ru.mipt.bit.platformer.abstractions.Tank;
import ru.mipt.bit.platformer.abstractions.Tree;
import ru.mipt.bit.platformer.engine.Engine;

import java.util.List;

//interface for building levels
public interface ILevelBuilder {
    Tank getTank();
    
    List<Tree> getTrees();

    Engine getEngine();
}
