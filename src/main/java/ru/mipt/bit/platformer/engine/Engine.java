package ru.mipt.bit.platformer.engine;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.MoveChecker;
import ru.mipt.bit.platformer.abstractions.Tank;
import ru.mipt.bit.platformer.abstractions.Bullet;
import ru.mipt.bit.platformer.ai.CommandCenter;
import ru.mipt.bit.platformer.graphics.HpToggle;
import ru.mipt.bit.platformer.levelbuilder.Level;

import java.util.List;

//refactored class for moving tank
public class Engine {

    private final Level level;
    private final CommandCenter commandCenter;
    private final MoveChecker moveChecker;

    public Engine(Level level, CommandCenter commandCenter, HpToggle showHp) {
        this.level = level;
        this.moveChecker = new MoveChecker(level.getTank(), showHp);
        this.commandCenter = commandCenter;
    }

    private float getDeltaTime() {
        return Gdx.graphics.getDeltaTime();
    }

    public void doCalculations() {
        moveChecker.checkMoves().doCommand();
	   if (level.getAiTanks().size() > 0) {
	       commandCenter.generateCommand().doCommand();
	   }
        level.getTank().processMovementProgress(getDeltaTime());
            for (Tank tank : level.getAiTanks()) {
                tank.processMovementProgress(getDeltaTime());
            }
            for (Bullet bullet : level.getBullets()) {
            bullet.processMovementProgress(getDeltaTime());
        }
        }

    }
