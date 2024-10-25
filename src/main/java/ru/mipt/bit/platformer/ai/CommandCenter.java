package ru.mipt.bit.platformer.ai;

import ru.mipt.bit.platformer.ai.commands.*;
import ru.mipt.bit.platformer.abstractions.Tank;

import java.util.List;

public class CommandCenter {

    private final List<Tank> aiTanks;

    public CommandCenter(List<Tank> aiTanks) {
        this.aiTanks = aiTanks;
    }

    public ICommand generateCommand() {
        int commandNum = (int) (Math.random() * 7);
        int aiTankNum = ((int) (Math.random() * 100)) % aiTanks.size();

        switch (commandNum) {
            case 0:
                return new MoveUpCommand(aiTanks.get(aiTankNum));
            case 1:
                return new MoveRightCommand(aiTanks.get(aiTankNum));
            case 2:
                return new MoveDownCommand(aiTanks.get(aiTankNum));
            case 3:
                return new MoveLeftCommand(aiTanks.get(aiTankNum));
            default:
                return new StayCommand(aiTanks.get(aiTankNum));
        }
    }
}