package com.whut.neoh.alphabeta_2048;

public class Tile extends Cell {
    private int value = 0;
    private Tile[] mergedFrom = null;

    //FAC
    public Tile(int y,int x ,int value){
        super(y, x);
        this.value = value;
    }
    public Tile(Cell cell,int value) {
        super(cell.getY(),cell.getX());
        this.value = value;
    }

    //POSITION
    public void updatePosition(Cell cell){
        this.setY(cell.getY());
        this.setX(cell.getX());
    }
    public void updatePosition(int x,int y){
        this.setY(y);
        this.setX(x);
    }

    //VALUE
    public int getValue(){
        return this.value;
    }

    //

    public Tile[] getMergedFrom() {
        return mergedFrom;
    }

    public void setMergedFrom(Tile[] mergedFrom) {
        this.mergedFrom = mergedFrom;
    }
}
