package ru.mipt.bit.platformer.ai.commands;

import ru.mipt.bit.platformer.ai.ICommand;
import ru.mipt.bit.platformer.Direction.Direction;
import ru.mipt.bit.platformer.abstractions.Tank;

public class ShootCommand implements ICommand {
    private final Tank tank;

    public ShootCommand(Tank tank) {
        this.tank = tank;
    }

    @Override
    public void doCommand() {
        tank.shoot();
    }
}