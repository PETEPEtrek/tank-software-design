package ru.mipt.bit.platformer.levelbuilder.generation;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.abstractions.Tank;
import ru.mipt.bit.platformer.abstractions.Tree;
import ru.mipt.bit.platformer.levelbuilder.ILevelBuilder;
import ru.mipt.bit.platformer.levelbuilder.PlaceObjectsByCoordinates;
import ru.mipt.bit.platformer.engine.Engine;
import ru.mipt.bit.platformer.collisions.FindCollisions;

//class for random generation of level
public class GenerateRandom implements ILevelBuilder{
    private final Engine engine;
    private final Tank tank;
    private final List<Tree> trees;
    private final List<Tank> aiTanks;

    public GenerateRandom(int width, int height, int treesNum, int aiTankNum) {
        FindCollisions collisionFinder = new FindCollisions(new ArrayList<>());
        List<GridPoint2> treeCoordinatesList = new ArrayList<>(generateRandomCoordinates(treesNum, width, height));
        List<GridPoint2> tankCoordinatesList = new ArrayList<>(generateRandomCoordinates(aiTankNum, width, height));
        List<GridPoint2> levelBorders = new ArrayList<>(createBorderCoordinates(width, height));

        PlaceObjectsByCoordinates root = new PlaceObjectsByCoordinates(tankCoordinatesList,
                treeCoordinatesList,
                levelBorders, 
                collisionFinder);
        
        tank = root.getTank();
        aiTanks = root.getAiTanks();
        trees = root.getTrees();
        engine = new Engine(tank, aiTanks);

    }


    private Set<GridPoint2> createBorderCoordinates(int width, int height) {
        Set<GridPoint2> borders = new HashSet<>();

        for (int j = 0; j < width; j++) {
            borders.add(new GridPoint2(j, height));
            borders.add(new GridPoint2(j, -1));
        }

        for (int i = 0; i < height; i++) {
            borders.add(new GridPoint2(width, i));
            borders.add(new GridPoint2(-1, i));
        }
        
        return borders;
    }

    private Set<GridPoint2> generateRandomCoordinates(int num, int maxX, int maxY) {
        Set<GridPoint2> randomCoordinates = new HashSet<>();

        for (int i = 0; i < num; i++) {
            randomCoordinates.add(new GridPoint2((int) (Math.random() * (maxX - 1)), (int) (Math.random() * (maxY - 1))));
        }
        return randomCoordinates;
    }

    public Engine getEngine() {
        return engine;
    }

    @Override
    public List<Tree> getTrees() {
        return trees;
    }

    @Override
    public Tank getTank() {
        return tank;
    }

    @Override
    public List<Tank> getAiTanks() {
        return aiTanks;
    }
}