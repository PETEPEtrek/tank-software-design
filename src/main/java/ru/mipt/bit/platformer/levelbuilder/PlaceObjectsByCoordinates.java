package ru.mipt.bit.platformer.levelbuilder;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Direction.Direction;
import ru.mipt.bit.platformer.abstractions.Tank;
import ru.mipt.bit.platformer.abstractions.Tree;
import ru.mipt.bit.platformer.collisions.FindCollisions;

import static ru.mipt.bit.platformer.util.GdxGameUtils.incrementedY;

import java.util.ArrayList;
import java.util.List;

public class PlaceObjectsByCoordinates {
    private final List<GridPoint2> tankCoordinatesList;
    private final List<GridPoint2> treeCoordinatesList;
    private final FindCollisions collisionFinder;

    private final List<Tree> trees = new ArrayList<>();
    private Tank tank;

    public PlaceObjectsByCoordinates(List<GridPoint2> tankCoordinatesList,
                                     List<GridPoint2> treeCoordinatesList,
                                     List<GridPoint2> bordersList,
                                     FindCollisions collisionFinder) {
        this.tankCoordinatesList = tankCoordinatesList;
        this.treeCoordinatesList = treeCoordinatesList;
        this.collisionFinder = collisionFinder;

        createTank();
        createTrees();
        createBorders(bordersList);

    }

    private void createTank() {
        tank = new Tank(Direction.UP, collisionFinder, 1f, 0.4f, tankCoordinatesList.get(0), incrementedY(tankCoordinatesList.get(0)));

        collisionFinder.addCollidable(tank);

    }

    private void createTrees() {
        for (GridPoint2 treeCoordinates : treeCoordinatesList) {
            trees.add(new Tree(treeCoordinates, 0f));
            collisionFinder.addCollidable(trees.get(trees.size() - 1));
        }
    }

    private void createBorders(List<GridPoint2> bordersList) {
        List<Tree> borders = new ArrayList<>();

        for (GridPoint2 bordersCoordinates : bordersList) {
            borders.add(new Tree(bordersCoordinates, 0f));
            collisionFinder.addCollidable(borders.get(borders.size() - 1));
        }
    }

    public Tank getTank() {
        return tank;
    }

    public List<Tree> getTrees() {
        return trees;
    }
}
