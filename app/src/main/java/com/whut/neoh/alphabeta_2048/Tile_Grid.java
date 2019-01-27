package com.whut.neoh.alphabeta_2048;

import java.sql.Array;
import java.util.ArrayList;

public class Tile_Grid {
    public Tile[][] field;          //瓦片状态
    public Tile[][] undoField;      //回退
    public Tile[][] bufferField;

    public Tile_Grid(int sizeY, int sizeX) {
        field = new Tile[sizeY][sizeX];
        undoField = new Tile[sizeY][sizeX];
        bufferField = new Tile[sizeY][sizeX];
        clearGrid();
        clearUndoGrid();
    }


    public Cell selectRandomAvailableCell() {
        ArrayList<Cell> cells = getAvailableCells();
        if (cells.size() > 0) {
            return cells.get((int) Math.floor(Math.random() * cells.size()));
        }
        return null;
    }

    //获取field中不为空的cell列表
    public ArrayList<Cell> getNotAvailableCells() {
        ArrayList<Cell> cells = new ArrayList<Cell>();
        if (field == null || field[0] == null)
            return null;
        for (int y = 0; y < field.length; y++)
            for (int x = 0; x < field[0].length; x++)
                if (field[y][x] != null)
                    cells.add(new Cell(y, x));
        return cells;
    }

    //获取field中为空的cell列表
    public ArrayList<Cell> getAvailableCells() {
        ArrayList<Cell> cells = new ArrayList<Cell>();
        if (field == null || field[0] == null)
            return null;
        for (int y = 0; y < field.length; y++)
            for (int x = 0; x < field[0].length; x++)
                if (field[y][x] == null)
                    cells.add(new Cell(y, x));
        return cells;
    }

    //指定位置能放置新瓦片
    public boolean isCellAvailable(int y, int x) {
        return !isTileExistAt(y, x);
    }

    //指定位置能放置新瓦片
    public boolean isCellAvailable(Cell cell) {
        return !isTileExistAt(cell);
    }

    //指定位置是否存在瓦片
    public boolean isTileExistAt(int y, int x) {
        return (getTileAt(y, x) != null);
    }

    //指定位置是否存在瓦片
    public boolean isTileExistAt(Cell cell) {
        return (getTileAt(cell) != null);
    }

    //获取指定位置的瓦片
    public Tile getTileAt(int y, int x) {
        if (isCellWithinBounds(y, x))
            return field[y][x];
        return null;
    }

    //获取指定位置的瓦片
    public Tile getTileAt(Cell cell) {
        if (cell != null && isCellWithinBounds(cell))
            return field[cell.getY()][cell.getX()];
        return null;
    }

    //合法性检查
    public boolean isCellWithinBounds(Cell cell) {
        if (field == null || field[0] == null)
            return false;
        int y = cell.getY();
        int x = cell.getX();
        return (0 <= y && y < field.length) && (0 <= x && x < field[0].length);
    }

    //合法性检查
    public boolean isCellWithinBounds(int y, int x) {
        if (field == null || field[0] == null)
            return false;
        return (0 <= y && y < field.length) && (0 <= x && x < field[0].length);
    }


    public void insertTile(Tile tile) {
        field[tile.getY()][tile.getX()] = tile;
    }

    public void removeTile(Tile tile) {
        field[tile.getY()][tile.getX()] = null;
    }

    //bufferField -> undoField
    public void saveGrid() {
        if (bufferField == null || bufferField[0] == null)
            return;
        int sizeY = bufferField.length;
        int sizeX = bufferField[0].length;
        for (int y = 0; y < sizeY; y++)
            for (int x = 0; x < sizeX; x++)
                if (bufferField[y][x] == null)
                    undoField[y][x] = null;
                else
                    undoField[y][x] = new Tile(y, x, bufferField[y][x].getValue());
    }

    //field -> bufferfield
    public void preSaveGrid() {
        if (field == null || field[0] == null)
            return;
        int sizeY = field.length;
        int sizeX = field[0].length;
        for (int y = 0; y < sizeY; y++)
            for (int x = 0; x < sizeX; x++)
                if (field[y][x] == null)
                    bufferField[y][x] = null;
                else
                    bufferField[y][x] = new Tile(y, x, field[y][x].getValue());
    }

    //用缓存恢复网格状态    undofield -> field
    public void revertGrid() {
        if (undoField == null || undoField[0] == null)
            return;
        int sizeY = undoField.length;
        int sizeX = undoField[0].length;
        for (int y = 0; y < sizeY; y++)
            for (int x = 0; x < sizeX; x++) {
                if (undoField[y][x] == null)
                    field[y][x] = null;
                else
                    field[y][x] = new Tile(y, x, undoField[y][x].getValue());

            }

        // undoField[y][x] = null;
    }

    //清理网格内卡片
    public void clearGrid() {
        if (field == null || field[0] == null)
            return;
        int sizeY = field.length;
        int sizeX = field[0].length;
        for (int y = 0; y < sizeY; y++)
            for (int x = 0; x < sizeX; x++)
                field[y][x] = null;
    }

    //清理记忆的卡片缓存
    public void clearUndoGrid() {
        if (undoField == null || undoField[0] == null)
            return;
        int sizeY = undoField.length;
        int sizeX = undoField[0].length;
        for (int y = 0; y < sizeY; y++)
            for (int x = 0; x < sizeX; x++)
                undoField[y][x] = null;
    }
}
