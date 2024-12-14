package ru.mipt.bit.platformer.ai.commands;
import ru.mipt.bit.platformer.ai.IAbstraction;
import ru.mipt.bit.platformer.ai.ICommand;
public interface IFactory{
    ICommand makeCommand(Integer action, IAbstraction object);
}