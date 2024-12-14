package ru.mipt.bit.platformer.ai.commands;

import ru.mipt.bit.platformer.ai.ICommand;
import ru.mipt.bit.platformer.ai.IAbstraction;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class AI implements IPersona {
	private final IAbstraction object;
    private final Map<Integer, IFactory> factoriesLib;


    public AI(Map<Integer, IFactory> factoriesLib, IAbstraction object) {
    	this.object = object;
        this.factoriesLib = factoriesLib;
    }

    @Override
    public ICommand chooseNextComand() {
        List<IFactory> factories = new ArrayList<>(factoriesLib.values());
        var id = new Random().nextInt(factories.size());
        return factories.get(id).makeCommand(id, object);
    }
}