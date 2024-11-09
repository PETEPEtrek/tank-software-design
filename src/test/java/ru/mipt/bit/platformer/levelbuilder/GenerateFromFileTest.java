package ru.mipt.bit.platformer.generator;

import org.junit.Test;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.abstractions.Tank;
import ru.mipt.bit.platformer.abstractions.Tree;
import ru.mipt.bit.platformer.levelbuilder.generation.GenerateFromFile;
import ru.mipt.bit.platformer.engine.Engine;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

public class GenerateFromFileTest {

    @Test
    public void getEngine() {
        GenerateFromFile generatorFromFile = new GenerateFromFile ("src/test/resources/level_for_test.txt");
        Engine engine = generatorFromFile.getEngine();
        assertNotNull(engine);
    }

    @Test
    public void getTank() {
        GenerateFromFile  generatorFromFile = new GenerateFromFile ("src/test/resources/level_for_test.txt");
        GridPoint2 expected = new GridPoint2(0, 1);
        Tank tank = generatorFromFile.getTank();
        assertEquals(expected, tank.getPlayerCoordinates());
    }

    @Test
    public void getTrees() {
        GenerateFromFile  generatorFromFile = new GenerateFromFile ("src/test/resources/level_for_test.txt");

        List<GridPoint2> expected = new ArrayList<>();
        expected.add(new GridPoint2(2, 2));
        expected.add(new GridPoint2(0, 0));
        expected.add(new GridPoint2(1, 0));

        List<Tree> trees = generatorFromFile.getTrees();

        assertEquals(expected, trees.stream().map(Tree::getCoordinates).collect(Collectors.toList()));
    }
}