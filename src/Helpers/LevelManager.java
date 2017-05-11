package Helpers;

import Graphics.Tile;
import Graphics.TileGrid;
import Graphics.TileType;

import java.io.*;

import static Graphics.TileType.Dirt;

/**
 * Created by shurik on 08.05.2017.
 */
public class LevelManager {

    public static void saveMap(String mapName, TileGrid grid) {
        StringBuilder mapDate = new StringBuilder();
        for (int i = 0; i < grid.getTileWide(); i++) {
            for (int j = 0; j < grid.getTileHigh(); j++) {
                mapDate.append(getTileID(grid.getTile(i, j)));
            }
        }
        try {
            File file = new File(mapName);
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(mapDate.toString());
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static TileGrid loadMap(String mapName) {
        TileGrid grid = new TileGrid();

        try {
            BufferedReader br = new BufferedReader(new FileReader(mapName));
            String data = br.readLine();
            for (int i = 0; i < grid.getTileWide(); i++) {
                for (int j = 0; j < grid.getTileHigh(); j++) {
                    grid.setTile(i, j, getTileType(
                            data.substring(i * grid.getTileHigh() + j, i * grid.getTileHigh() + j + 1)));
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return grid;
    }

    public static TileType getTileType(String ID) {
        TileType type = TileType.NULL;
        switch (ID) {
            case "0":
                type = TileType.Grass;
                break;
            case "1":
                type = TileType.Dirt;
                break;
            case "2":
                type = TileType.Water;
                break;
            case "3":
                type = TileType.Sand;
                break;
            case "4":
                type = TileType.Bush;
                break;
            case "5":
                type = TileType.Stones;
                break;
            case "-1":
                type = TileType.NULL;
                break;
        }
        return type;
    }

    public static String getTileID(Tile t) {
        String ID = "E";
        switch (t.getType()) {
            case Grass:
                ID = "0";
                break;
            case Dirt:
                ID = "1";
                break;
            case Water:
                ID = "2";
                break;
            case Sand:
                ID = "3";
                break;
            case Bush:
                ID = "4";
                break;
            case Stones:
                ID = "5";
                break;
            case NULL:
                ID = "-1";
                break;
        }
        return ID;
    }
}
