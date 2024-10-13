package ru.mipt.bit.platformer.levelbuilder.generation;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.abstractions.Tank;
import ru.mipt.bit.platformer.abstractions.Tree;
import ru.mipt.bit.platformer.levelbuilder.ILevelBuilder;
import ru.mipt.bit.platformer.levelbuilder.PlaceObjectsByCoordinates;
import ru.mipt.bit.platformer.engine.Engine;
import ru.mipt.bit.platformer.collisions.FindCollisions;

import java.io.FileReader;
import java.util.*;

//class for reading from file
public class GenerateFromFile implements ILevelBuilder {
    private final Engine engine;
    private final Tank tank;
    private final List<Tree> trees;

    public GenerateFromFile(String file) {
        FindCollisions collisionFinder = new FindCollisions(new ArrayList<>());
        List<GridPoint2> tankCoordinates = new ArrayList<>(getSymbolCoordinates("X", file));
        List<GridPoint2> treeCoordinates = new ArrayList<>(getSymbolCoordinates("T", file));
        List<GridPoint2> borders = new ArrayList<>(getBordersFromFile(file));

        PlaceObjectsByCoordinates root = new PlaceObjectsByCoordinates(tankCoordinates,
                treeCoordinates,
                borders, 
                collisionFinder);

        tank = root.getTank();
        trees = root.getTrees();
        engine = new Engine(tank);

    }

    private Set<GridPoint2> getSymbolCoordinates(String symbol, String file) {
        List<List<String>> symbolMap = new ArrayList<>();
        getSymbolMapFromFile(symbolMap, file);


        Collections.reverse(symbolMap);
        Set<GridPoint2> coordinates = new HashSet<>();

        for (int i = 0; i < symbolMap.size(); i++) {
            for (int j = 0; j < symbolMap.get(i).size(); j++) {

                if (symbolMap.get(i).get(j).equals(symbol)) {
                    coordinates.add(new GridPoint2(j, i));
                }

            }
        }
        return coordinates;
    }

    private Set<GridPoint2> getBordersFromFile(String file) {
        List<List<String>> symbolMap = new ArrayList<>();
        getSymbolMapFromFile(symbolMap, file);

        Set<GridPoint2> borders = new HashSet<>();

        int height = symbolMap.size();
        int width = symbolMap.get(0).size();

        for (int i = 0; i < height; i++) {
            borders.add(new GridPoint2(width, i));
            borders.add(new GridPoint2(-1, i));
        }

        for (int j = 0; j < width; j++) {
            borders.add(new GridPoint2(j, height));
            borders.add(new GridPoint2(j, -1));
        }
        return borders;
    }

    private void getSymbolMapFromFile(List<List<String>> symbolMap, String file) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileReader(file));
        } catch (Throwable ex) {
            System.out.println(ex.getMessage());
        }

        if (scanner != null) {
            while (scanner.hasNextLine()) {
                symbolMap.add(Arrays.asList(scanner.nextLine().split("")));
            }
        }
    }

    public Engine getEngine() {
        return engine;
    }

    @Override
    public Tank getTank() {
        return tank;
    }

    @Override
    public List<Tree> getTrees() {
        return trees;
    }

}