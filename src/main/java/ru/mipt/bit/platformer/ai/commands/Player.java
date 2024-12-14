package ru.mipt.bit.platformer.ai.commands;
import java.util.Map;
import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.ai.IAbstraction;
import ru.mipt.bit.platformer.ai.ICommand;

public class Player implements IPersona {
	private final IAbstraction object;
    private final Map<Integer, IFactory> factoriesLib;

    public Player(Map<Integer, IFactory> factoriesLib, IAbstraction object) {
        this.object = object;
        this.factoriesLib = factoriesLib;
    }
    @Override
    public ICommand chooseNextComand() {
        for (var elem : factoriesLib.entrySet()) {
            if (Gdx.input.isKeyPressed(elem.getKey())) {
                return elem.getValue().makeCommand(elem.getKey(), object);
            }
        }
        return null;
    }
}