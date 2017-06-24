package Helpers;

import Graphics.Tile;
import Graphics.TileGrid;
import Graphics.TileType;

import java.io.*;
import java.util.Scanner;

import static Graphics.TileType.Dirt;

/**
 * Created by shurik on 08.05.2017.
 */
public enum LevelManager {
    INSTANCE;

    public void saveMap(String mapName, TileGrid grid) {
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

    public TileGrid loadMap(String mapName) {
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

    // test
    public TileGrid getText(String mapName) {
        TileGrid grid = new TileGrid();
        Scanner in = null;
        try {
            in = new Scanner(new File(mapName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (in != null) {
            StringBuilder str = new StringBuilder();
            while (in.hasNextLine()) {
                str.append(in.nextLine());
            }
            for (int i = 0; i < grid.getTileWide(); i++) {
                for (int j = 0; j < grid.getTileHigh(); j++) {
                    grid.setTile(i, j, getTileType(
                            str.substring(i * grid.getTileHigh() + j, i * grid.getTileHigh() + j + 1)));
                }
            }
        }
        return grid;
    }
    //

    public TileGrid setMap(String mapName) { // only for .jar
        TileGrid grid = new TileGrid();
        String data;
        switch (mapName) {
            case "res\\maps\\newMarvelousMap1":
                data = "223331400000055222331111111105322233335000105332222235040155533324233000105553322211111102555333214444402511100211111102010100225500102010111111111102010000005055002011110555005505000010055500555000010045500502504010045500552504010055504452504010550554452550010500055552555010022222542555010024442222";
                break;
            case "res\\maps\\newMarvelousMap2":
                data = "555551222125555555501222122205552111222111205522122202221255522122002021225222122052021255220105552221022220105055201022222102025001025522102222021225522102220021225552105520021222222102525001222202102555201522200102255201552222122005221052222122205521055202122222221055502111111111055552555555555555";
                break;
            case "res\\maps\\newMarvelousMap3":
                data = "221555522222212511500550422212510511111042112010012221002122010012221042122010012221504122011112221551122442222111051552422422100001455222411104401405222210000001055110010111111055410010100000005410010101110005410010101010005410210141010005112210111010455222210000010455222011111114455222000000004445";
                break;
            default:
                data = "223331400000055222331111111105322233335000105332222235040155533324233000105553322211111102555333214444402511100211111102010100225500102010111111111102010000005055002011110555005505000010055500555000010045500502504010045500552504010055504452504010550554452550010500055552555010022222542555010024442222";
                break;
        }

        for (int i = 0; i < grid.getTileWide(); i++) {
            for (int j = 0; j < grid.getTileHigh(); j++) {
                grid.setTile(i, j, getTileType(
                        data.substring(i * grid.getTileHigh() + j, i * grid.getTileHigh() + j + 1)));
            }
        }
        return grid;
    }


    public TileType getTileType(String ID) {
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

    public String getTileID(Tile t) {
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
