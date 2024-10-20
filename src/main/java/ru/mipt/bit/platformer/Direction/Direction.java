package ru.mipt.bit.platformer.Direction;

import com.badlogic.gdx.math.GridPoint2;

public enum Direction {
    UP {
        public GridPoint2 getChangeVector() { return new GridPoint2(0, 1); }
        public float getRotation() { return 90f; }
    },
    RIGHT {
        public GridPoint2 getChangeVector() { return new GridPoint2(1, 0); }
        public float getRotation() { return 0f; }
    },
    DOWN {
        public GridPoint2 getChangeVector() { return new GridPoint2(0, -1); }
        public float getRotation() { return -90f; }
    },
    LEFT {
        public GridPoint2 getChangeVector() { return new GridPoint2(-1, 0); }
        public float getRotation() { return -180f; }
    };

    public abstract GridPoint2 getChangeVector();
    public abstract float getRotation();
}