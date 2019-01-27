package com.whut.neoh.alphabeta_2048;

public class Cell {
    private int y;
    private int x;

    public Cell(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }


    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

}
