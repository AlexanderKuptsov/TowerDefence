package Graphics;

import Helpers.Artist;

import static Helpers.Artist.HEIGHT;
import static Helpers.Artist.TILE_SIZE;
import static Helpers.Artist.WIDTH;

/**
 * Created by shurik on 28.04.2017.
 */
public class TileGrid {
    public Tile[][] map;
    private int tileWide, tileHigh;

    public TileGrid() { // всё поле в траве
        this.tileWide = (WIDTH - 192) / TILE_SIZE;
        this.tileHigh = HEIGHT / TILE_SIZE;
        map = new Tile[(Artist.WIDTH - 192) / TILE_SIZE][Artist.HEIGHT / TILE_SIZE];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = new Tile(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, TileType.Grass);
            }
        }
    }

    public TileGrid(int[][] newMap) {
        this.tileWide = newMap[0].length;
        this.tileHigh = newMap.length;

        map = new Tile[tileWide][tileHigh];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                switch (newMap[j][i]) {
                    case 0:
                        map[i][j] = new Tile(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, TileType.Grass);
                        break;
                    case 1:
                        map[i][j] = new Tile(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, TileType.Dirt);
                        break;
                    case 2:
                        map[i][j] = new Tile(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, TileType.Water);
                        break;
                    case 3:
                        map[i][j] = new Tile(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, TileType.Sand);
                        break;
                    case 4:
                        map[i][j] = new Tile(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, TileType.Bush);
                        break;
                    case 5:
                        map[i][j] = new Tile(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, TileType.Stones);
                        break;
                }
            }
        }
    }

    public void setTile(int xCoord, int yCoord, TileType type) {
        map[xCoord][yCoord] = new Tile(xCoord * TILE_SIZE, yCoord * TILE_SIZE, TILE_SIZE, TILE_SIZE, type);
    }

    public Tile getTile(int xPlace, int yPlace) {
        if (xPlace < tileWide && yPlace < tileHigh && xPlace > -1 && tileHigh > -1) return map[xPlace][yPlace];
        else return new Tile(0, 0, 0, 0, TileType.NULL);
    }

    public void draw() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j].draw();
            }
        }
    }

    public int getTileWide() {
        return tileWide;
    }

    public void setTileWide(int tileWide) {
        this.tileWide = tileWide;
    }

    public int getTileHigh() {
        return tileHigh;
    }

    public void setTileHigh(int tileHigh) {
        this.tileHigh = tileHigh;
    }
}