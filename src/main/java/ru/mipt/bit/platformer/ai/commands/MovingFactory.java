package ru.mipt.bit.platformer.ai.commands;

import ru.mipt.bit.platformer.abstractions.Moving;
import ru.mipt.bit.platformer.Direction.Direction;
import ru.mipt.bit.platformer.ai.IAbstraction;
import ru.mipt.bit.platformer.ai.ICommand;
import static com.badlogic.gdx.Input.Keys.*;


public class MovingFactory implements IFactory {
    public MovingFactory() {
    }

    @Override
    public ICommand makeCommand(Integer action, IAbstraction object) {
        switch (action)  {
        	case (W):
        	case (UP):
                return new MoveCommand((Moving)object, Direction.UP);
            case (A):
            case (LEFT):
                return new MoveCommand((Moving)object, Direction.LEFT);
            case (S):
            case (DOWN):
                return new MoveCommand((Moving)object, Direction.DOWN);
            case (D):
            case  (RIGHT):
                return new MoveCommand((Moving)object, Direction.RIGHT);
        }
        return null;
    }
}