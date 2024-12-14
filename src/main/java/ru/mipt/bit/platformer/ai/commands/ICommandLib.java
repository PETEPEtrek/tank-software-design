package ru.mipt.bit.platformer.ai.commands;
import java.util.Map;

public interface ICommandLib {
    Map<Integer, IFactory> getCommandLib();
}