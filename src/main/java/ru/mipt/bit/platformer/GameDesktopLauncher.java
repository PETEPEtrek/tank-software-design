package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.levelbuilder.generation.GenerateFromFile;
import ru.mipt.bit.platformer.levelbuilder.generation.GenerateRandom;
import ru.mipt.bit.platformer.levelbuilder.Level;
import ru.mipt.bit.platformer.eventmanager.Events;
import ru.mipt.bit.platformer.levelbuilder.ILevelBuilder;
import ru.mipt.bit.platformer.levelbuilder.RendererBuilder;
import ru.mipt.bit.platformer.graphics.Renderer;
import ru.mipt.bit.platformer.engine.Engine;
import ru.mipt.bit.platformer.graphics.HpToggle;
import ru.mipt.bit.platformer.ai.commands.IPersona;
import ru.mipt.bit.platformer.ai.commands.AI;
import ru.mipt.bit.platformer.ai.commands.AILib;
import ru.mipt.bit.platformer.ai.commands.Player;
import ru.mipt.bit.platformer.ai.commands.PlayerLib;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.mipt.bit.platformer.abstractions.Tank;

import java.util.*;


public class GameDesktopLauncher implements ApplicationListener {

    private RendererBuilder rendererBuilder;
    private ClassPathXmlApplicationContext ctx;
    private Engine engine;
    private Renderer renderer;
    private HpToggle showHp;
    private final List<IPersona> personas = new ArrayList<>();

    @Override
    public void create() {
        ctx = new ClassPathXmlApplicationContext("appContext.xml");
        ILevelBuilder levelBuilder = ctx.getBean("genfile", GenerateFromFile.class);
        Level level = levelBuilder.getLevel();
        rendererBuilder = ctx.getBean("rend", RendererBuilder.class);
        showHp = rendererBuilder.getHpToggle();

        Tank playerTank = level.getTank();
        List<Tank> aiTanks = level.getAiTanks();
        personas.add(new Player(new PlayerLib().getCommandLib(), playerTank));
        for (Tank aiTank: aiTanks) {
            personas.add(new AI(new AILib().getCommandLib(), aiTank));
        }
        engine = new Engine(level, personas, showHp);
        renderer = rendererBuilder.generateRenderer(levelBuilder);
        level.subscribe(Events.DELETE_TANK, renderer);
        level.subscribe(Events.CREATE_BULLET, renderer);
        level.subscribe(Events.DELETE_BULLET, renderer);
    }

    @Override
    public void render() {
        engine.doCalculations(getDeltaTime());
        renderer.doRender();
    }

    public float getDeltaTime() {
        return Gdx.graphics.getDeltaTime();
    }


    @Override
    public void resize(int width, int height) {
        // do not react to window resizing
    }

    @Override
    public void pause() {
        // game doesn't get paused
    }

    @Override
    public void resume() {
        // game doesn't get paused
    }

    @Override
    public void dispose() {
        for (Texture texture : rendererBuilder.getTextures()) {
            texture.dispose();
        }
    }

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        // level width: 10 tiles x 128px, height: 8 tiles x 128px
        config.setWindowedMode(1280, 1024);
        new Lwjgl3Application(new GameDesktopLauncher(), config);
    }
}
