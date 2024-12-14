package ru.mipt.bit.platformer.ai.commands;
import ru.mipt.bit.platformer.ai.IAbstraction;
import ru.mipt.bit.platformer.ai.ICommand;
import ru.mipt.bit.platformer.abstractions.Toggle;
public class ToggleFactory implements IFactory {
    public ToggleFactory() {
    }

    @Override
    public ICommand makeCommand(Integer action, IAbstraction object) {
        return new ToggleCommand((Toggle) object);
    }
}