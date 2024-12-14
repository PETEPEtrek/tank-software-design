package ru.mipt.bit.platformer.ai.commands;

import ru.mipt.bit.platformer.ai.ICommand;
import ru.mipt.bit.platformer.Direction.Direction;
import ru.mipt.bit.platformer.abstractions.Tank;

public class ShootCommand implements ICommand {
    private final IShoot shooter;

    public ShootCommand(IShoot shooter) {
        this.shooter = shooter;
    }

    @Override
    public void execute() {
        shooter.shoot();
    }
}