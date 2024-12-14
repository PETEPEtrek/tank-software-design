package ru.mipt.bit.platformer.ai.commands;
import ru.mipt.bit.platformer.abstractions.Toggle;

import ru.mipt.bit.platformer.ai.ICommand;

public class ToggleCommand implements ICommand {
    private final Toggle toggle;

    public ToggleCommand(Toggle toggle) {
        this.toggle = toggle;
    }

    public void execute() {
        toggle.changeToggle();
    }
}