package Graphics;

/**
 * Created by shurik on 28.04.2017.
 */
public enum TileType {

    NULL("NULL", false), Grass("grassHD", true), Dirt("dirtHD", false), Water("waterHD", false), Sand("sand", true),
    Bush("bush", false), Stones("stonesHD", false);

    String textureName;
    boolean buildable;

    TileType(String textureName, boolean buildable) {
        this.textureName = textureName;
        this.buildable = buildable;
    }

    public boolean isBuildable() {
        return buildable;
    }
}
