package ru.mipt.bit.platformer.levelbuilder.generation;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import ru.mipt.bit.platformer.eventmanager.Events;
import ru.mipt.bit.platformer.levelbuilder.Level;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.abstractions.Tank;
import ru.mipt.bit.platformer.abstractions.Tree;
import ru.mipt.bit.platformer.levelbuilder.ILevelBuilder;
import ru.mipt.bit.platformer.levelbuilder.PlaceObjectsByCoordinates;
import ru.mipt.bit.platformer.engine.Engine;
import ru.mipt.bit.platformer.collisions.FindCollisions;
import ru.mipt.bit.platformer.graphics.HpToggle;

//class for random generation of level
public class GenerateRandom implements ILevelBuilder{
    private Level level;
    private final int width;
    private final int height;
    private final int treesNum;
    private final int aiTankNum;
    private HpToggle showHp;

    public GenerateRandom(int width, int height, int treesNum, int aiTankNum, HpToggle showHp) {
        this.width = width;
        this.height = height;
        this.treesNum = treesNum;
        this.aiTankNum = aiTankNum;
        this.showHp = showHp;

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

    

    @Override
    public Level getLevel() {
        if (level != null) return level;
        List<GridPoint2> treeCoordinates = new ArrayList<>(generateRandomCoordinates(treesNum, width, height));
        List<GridPoint2> tankCoordinates = new ArrayList<>(generateRandomCoordinates(aiTankNum, width, height));
        List<GridPoint2> levelBorders = new ArrayList<>(createBorderCoordinates(width, height));
        FindCollisions collisionFinder = new FindCollisions(new ArrayList<>());

        List<Events> events = new ArrayList<>();
        events.add(Events.CREATE_BULLET);
        events.add(Events.DELETE_BULLET);
        events.add(Events.DELETE_TANK);
        level = new Level(events);
        PlaceObjectsByCoordinates root = new PlaceObjectsByCoordinates(level, tankCoordinates,
                    treeCoordinates,
                    levelBorders, 
                    collisionFinder);


        Tank tank = root.getTank();
        List<Tank> aiTanks = root.getAiTanks();
        List<Tree> trees = root.getTrees();

        level.addTank(tank);
        level.addTrees(trees);
        level.addAiTanks(aiTanks);
        return level;
    }
}