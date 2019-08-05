package snake;

import static java.lang.Math.abs;

public enum Direction {
    UP(0),
    RIGHT(1),
    DOWN(2),
    LEFT(3);

    private final int directionCode;

    public int directionCode() {
        return directionCode;
    }

    Direction(int directionCode) {
        this.directionCode = directionCode;
    }

    public boolean compatibleWith (Direction newDirection) {
        if (abs(this.directionCode - newDirection.directionCode()) == 1 ||
                abs(this.directionCode - newDirection.directionCode()) == 3) {
            return true;
        } else {return false;}
    }

}
