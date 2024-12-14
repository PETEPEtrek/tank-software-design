package ru.mipt.bit.platformer.ai.commands;

import ru.mipt.bit.platformer.abstractions.Tank;
import ru.mipt.bit.platformer.Direction.Direction;

import org.junit.Test;
import org.mockito.Mockito;

public class CommandsTest {

    @Test
    public void doMoveRightCommand() {
        Tank tank = Mockito.mock(Tank.class);
        MoveRightCommand command = new MoveRightCommand(tank);
        command.doCommand();
        Mockito.verify(tank, Mockito.times(1)).move(Direction.RIGHT);
    }

    @Test
    public void doMoveLeftCommand() {
        Tank tank = Mockito.mock(Tank.class);
        MoveLeftCommand command = new MoveLeftCommand(tank);
        command.doCommand();
        Mockito.verify(tank, Mockito.times(1)).move(Direction.LEFT);
    }

    @Test
    public void doMoveUpCommand() {
        Tank tank = Mockito.mock(Tank.class);
        MoveUpCommand command = new MoveUpCommand(tank);
        command.doCommand();
        Mockito.verify(tank, Mockito.times(1)).move(Direction.UP);
    }

    @Test
    public void doMoveDownCommand() {
        Tank tank = Mockito.mock(Tank.class);
        MoveDownCommand command = new MoveDownCommand(tank);
        command.doCommand();
        Mockito.verify(tank, Mockito.times(1)).move(Direction.DOWN);
    }

}