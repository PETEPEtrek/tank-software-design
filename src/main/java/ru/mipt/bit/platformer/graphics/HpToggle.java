package ru.mipt.bit.platformer.graphics;

import ru.mipt.bit.platformer.abstractions.Toggle;

public class HpToggle implements Toggle {
    boolean showHp;
    @Override
    public boolean getNum() {
        return showHp;
    }

    @Override
    public void changeToggle() {
        showHp = !showHp;
    }
}