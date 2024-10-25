package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;


import ru.mipt.bit.platformer.levelbuilder.generation.GenerateFromFile;
import ru.mipt.bit.platformer.levelbuilder.generation.GenerateRandom;
import ru.mipt.bit.platformer.levelbuilder.ILevelBuilder;
import ru.mipt.bit.platformer.levelbuilder.RendererBuilder;
import ru.mipt.bit.platformer.graphics.Renderer;
import ru.mipt.bit.platformer.engine.Engine;



public class GameDesktopLauncher implements ApplicationListener {

    private RendererBuilder rendererBuilder;
    private Engine engine;
    private Renderer renderer;

    @Override
    public void create() {
        ILevelBuilder levelBuilder = new GenerateFromFile("src/main/resources/level.txt");
        //ILevelBuilder levelBuilder = new GenerateRandom(7, 6, 3);
        rendererBuilder = new RendererBuilder("level.tmx", "images/tank_blue.png", "images/greenTree.png");
        engine = levelBuilder.getEngine();
        renderer = rendererBuilder.generateRenderer(levelBuilder);
    }

    @Override
    public void render() {
        engine.doCalculations();
        renderer.doRender();
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
        // dispose of all the native resources (classes which implement com.badlogic.gdx.utils.Disposable)
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
