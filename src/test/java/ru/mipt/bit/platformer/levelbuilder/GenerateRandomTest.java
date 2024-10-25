package ru.mipt.bit.platformer.generator;

import org.junit.Test;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.abstractions.Tank;
import ru.mipt.bit.platformer.abstractions.Tree;
import ru.mipt.bit.platformer.levelbuilder.generation.GenerateRandom;
import ru.mipt.bit.platformer.engine.Engine;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

public class GenerateRandomTest {

    @Test
    public void getEngine() {
        GenerateRandom generatorRandom = new GenerateRandom(7, 5, 3, 4);
        Engine engine = generatorRandom.getEngine();
        assertNotNull(engine);
    }

    @Test
    public void getTank() {
        GenerateRandom generatorRandom = new GenerateRandom(7, 5, 3, 4);
        List<Tree> trees = generatorRandom.getTrees();
        assertEquals(trees.size(), 3);
    }

    @Test
    public void getTrees() {
        GenerateRandom generatorRandom = new GenerateRandom(7, 5, 3, 4);
        Tank tank = generatorRandom.getTank();
        assertNotNull(tank);
    }
}