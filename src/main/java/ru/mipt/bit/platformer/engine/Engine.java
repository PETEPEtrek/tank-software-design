package ru.mipt.bit.platformer.engine;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.MoveChecker;
import ru.mipt.bit.platformer.abstractions.Tank;
import ru.mipt.bit.platformer.ai.CommandCenter;

import java.util.List;

//refactored class for moving tank
public class Engine {

    private final Tank tank;
    private final List<Tank> aiTanks;
    private final CommandCenter commandCenter;
    private final MoveChecker moveChecker;

    public Engine(Tank tank, List<Tank> aiTanks) {
        this.tank = tank;
        this.aiTanks = aiTanks;
        this.moveChecker = new MoveChecker(tank);
        this.commandCenter = new CommandCenter(aiTanks);
    }

    private float getDeltaTime() {
        return Gdx.graphics.getDeltaTime();
    }

    public void doCalculations() {
        moveChecker.checkMoves().doCommand();
	if (aiTanks.size() > 0) {
	commandCenter.generateCommand().doCommand();
	}
        tank.processMovementProgress(getDeltaTime());
        for (Tank tank : aiTanks) {
            tank.processMovementProgress(getDeltaTime());
        }
    }

}
