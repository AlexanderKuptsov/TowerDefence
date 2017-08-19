package Data;

import Helpers.Sound;
import org.newdawn.slick.opengl.Texture;

import java.util.HashMap;
import java.util.Map;

import static Helpers.Artist.quickLoad;

public class ResourceLoader {

    public static final Map<String, Texture> UI_TEXTURES = new HashMap<String, Texture>(25);
    public static final Map<String, Texture> TERRAIN_TEXTURES = new HashMap<String, Texture>(15);
    public static final Map<String, Texture> ENEMIES_TEXTURES = new HashMap<String, Texture>(20);
    public static final Map<String, Texture> TOWERS_TEXTURES = new HashMap<String, Texture>(15);
    public static final Map<String, Texture> PROJECTILE_TEXTURES = new HashMap<String, Texture>(8);
    public static final Map<String, Texture> EFFECTS_TEXTURES = new HashMap<String, Texture>(5);
    public static final Map<String, Sound> SOUNDS_PACK = new HashMap<String, Sound>(15);

    // Sounds !!!!!!!!!!

    public ResourceLoader() {
        // Loading
        load_UI_Textures();
        load_Sounds_Pack();
        load_Terrain_Textures();
        load_Enemies_Textures();
        load_Towers_Textures();
        load_Projectiles_Texture();
        load_Effects_Textures();
    }

    private void load_UI_Textures() {
        addTo_UI_TEXTURES("mainMenu");
        addTo_UI_TEXTURES("play");
        addTo_UI_TEXTURES("editor");
        addTo_UI_TEXTURES("quit");
        addTo_UI_TEXTURES("Levels");
        addTo_UI_TEXTURES("1");
        addTo_UI_TEXTURES("2");
        addTo_UI_TEXTURES("3");
        addTo_UI_TEXTURES("menu");
        addTo_UI_TEXTURES("menuBackground");
        addTo_UI_TEXTURES("menuBackground2");
        addTo_UI_TEXTURES("menuBackgroundEditor");
        addTo_UI_TEXTURES("cancel");
        addTo_UI_TEXTURES("cancelActive");
        addTo_UI_TEXTURES("healthBackGround");
        addTo_UI_TEXTURES("healthBorder");
        addTo_UI_TEXTURES("healthForeGround");
        addTo_UI_TEXTURES("sell");
        addTo_UI_TEXTURES("upgrade");
        addTo_UI_TEXTURES("settings");
        addTo_UI_TEXTURES("Next");
        addTo_UI_TEXTURES("Restart"); // 22
    }

    private void load_Terrain_Textures() {
        addTo_TERRAIN_TEXTURES("grass");
        addTo_TERRAIN_TEXTURES("grassHD");
        addTo_TERRAIN_TEXTURES("dirt");
        addTo_TERRAIN_TEXTURES("dirtHD");
        addTo_TERRAIN_TEXTURES("water");
        addTo_TERRAIN_TEXTURES("waterHD");
        addTo_TERRAIN_TEXTURES("stonesHD");
        addTo_TERRAIN_TEXTURES("bush");
        addTo_TERRAIN_TEXTURES("sand");
        addTo_TERRAIN_TEXTURES("sandHD");
        addTo_TERRAIN_TEXTURES("sandNew");
        addTo_TERRAIN_TEXTURES("NULL");  // 12
    }

    private void load_Enemies_Textures() {
        addTo_ENEMIES_TEXTURES("tankNavyRight");
        addTo_ENEMIES_TEXTURES("tankNavyUp");
        addTo_ENEMIES_TEXTURES("tankNavyDown");
        addTo_ENEMIES_TEXTURES("tankNavyLeft");

        addTo_ENEMIES_TEXTURES("tank1Right");
        addTo_ENEMIES_TEXTURES("tank1Up");
        addTo_ENEMIES_TEXTURES("tank1Down");
        addTo_ENEMIES_TEXTURES("tank1Left");

        addTo_ENEMIES_TEXTURES("planeRight");
        addTo_ENEMIES_TEXTURES("planeUp");
        addTo_ENEMIES_TEXTURES("planeDown");
        addTo_ENEMIES_TEXTURES("planeLeft");

        addTo_ENEMIES_TEXTURES("soldierRight");
        addTo_ENEMIES_TEXTURES("soldierUp");
        addTo_ENEMIES_TEXTURES("soldierDown");
        addTo_ENEMIES_TEXTURES("soldierLeft");

        addTo_ENEMIES_TEXTURES("UFO");
        addTo_ENEMIES_TEXTURES("UFO2");  // 18
    }

    private void load_Towers_Textures() {
        addTo_TOWERS_TEXTURES("cannonBase");
        addTo_TOWERS_TEXTURES("cannonBaseBlue");
        addTo_TOWERS_TEXTURES("cannonGun");
        addTo_TOWERS_TEXTURES("cannonGunBlue");
        addTo_TOWERS_TEXTURES("flameThrowerFull");
        addTo_TOWERS_TEXTURES("range");
        addTo_TOWERS_TEXTURES("towerBase");
        addTo_TOWERS_TEXTURES("towerBase2");
        addTo_TOWERS_TEXTURES("towerIceFull");
        addTo_TOWERS_TEXTURES("towerMortalFull");
        addTo_TOWERS_TEXTURES("towerMortalGun");
        addTo_TOWERS_TEXTURES("towerPurpleFull");
        addTo_TOWERS_TEXTURES("towerPurpleGun");  // 13
    }

    private void load_Projectiles_Texture() {
        addTo_PROJECTILE_TEXTURES("bullet");
        addTo_PROJECTILE_TEXTURES("fire");
        addTo_PROJECTILE_TEXTURES("iceCube");
        addTo_PROJECTILE_TEXTURES("missile");
        addTo_PROJECTILE_TEXTURES("rocket");
        addTo_PROJECTILE_TEXTURES("shuriken"); // 6
    }

    private void load_Effects_Textures() {
        addTo_EFFECTS_TEXTURES("Blood");
    }

    private void load_Sounds_Pack() {
        addTo_SOUNDS_PACK("click1.wav");
        addTo_SOUNDS_PACK("blade.wav");
        addTo_SOUNDS_PACK("boom1.wav");
        addTo_SOUNDS_PACK("boom2.wav");
        addTo_SOUNDS_PACK("Bottle_Break.wav");
        addTo_SOUNDS_PACK("fire.aif");
        addTo_SOUNDS_PACK("lose1.wav");
        addTo_SOUNDS_PACK("money.wav");
        addTo_SOUNDS_PACK("spell1.aif");
        addTo_SOUNDS_PACK("switch23.wav");
        addTo_SOUNDS_PACK("upgrade.wav");  // 11
    }

    // Helpers
    private void addTextureToMap(Map<String, Texture> map, String textureName) {
        map.put(textureName, quickLoad(textureName));
    }

    private void addTo_UI_TEXTURES(String textureName) {
        UI_TEXTURES.put(textureName, quickLoad("UI\\" + textureName));
    }

    private void addTo_TERRAIN_TEXTURES(String textureName) {
        TERRAIN_TEXTURES.put(textureName, quickLoad("Terrain\\" + textureName));
    }

    private void addTo_ENEMIES_TEXTURES(String textureName) {
        ENEMIES_TEXTURES.put(textureName, quickLoad("Enemies\\" + textureName));
    }

    private void addTo_PROJECTILE_TEXTURES(String textureName) {
        PROJECTILE_TEXTURES.put(textureName, quickLoad("Projectiles\\" + textureName));
    }

    private void addTo_TOWERS_TEXTURES(String textureName) {
        TOWERS_TEXTURES.put(textureName, quickLoad("Towers\\" + textureName));
    }

    private void addTo_EFFECTS_TEXTURES(String textureName) {
        EFFECTS_TEXTURES.put(textureName, quickLoad("Effects\\" + textureName));
    }

    private void addTo_SOUNDS_PACK(String soundName) {
        SOUNDS_PACK.put(soundName, new Sound(soundName));
    }
}
