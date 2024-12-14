package ru.mipt.bit.platformer.ai.commands;
import ru.mipt.bit.platformer.ai.IAbstraction;
import ru.mipt.bit.platformer.ai.ICommand;

import ru.mipt.bit.platformer.ai.commands.IShoot;

public class ShootFactory implements IFactory {
    public ShootFactory() {}

    @Override
    public ICommand makeCommand(Integer action, IAbstraction object) {
        return new ShootCommand((IShoot) object);
    }
}