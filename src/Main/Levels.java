package Main;

/**
 * Created by shurik on 15.07.2017.
 */
public enum Levels {

    LVL1(1, 0, 5, 90, 5, 3.3f, 8, 1.275f),
    LVL2(2, 0, 5, 100, 5, 2, 9, 1.35f),
    LVL3(3, 0, 2, 115, 8, 1.25f, 15, 1.535f);

    private int lvlNum, startedPlaceX, startedPlaceY, startedMoney, startedLives, enemiesPerWave;
    private float timeBetweenEnemies, difficultyMulti;

    Levels(int lvlNum, int startedPlaceX, int startedPlaceY, int startedMoney, int startedLives,
           float timeBetweenEnemies, int enemiesPerWave, float difficultyMulti) {

        this.lvlNum = lvlNum;
        this.startedPlaceX = startedPlaceX;
        this.startedPlaceY = startedPlaceY;
        this.startedMoney = startedMoney;
        this.startedLives = startedLives;
        this.timeBetweenEnemies = timeBetweenEnemies;
        this.enemiesPerWave = enemiesPerWave;
        this.difficultyMulti = difficultyMulti;

    }

    public int getLvlNum() {
        return lvlNum;
    }

    public int getStartedPlaceX() {
        return startedPlaceX;
    }

    public int getStartedPlaceY() {
        return startedPlaceY;
    }

    public int getStartedMoney() {
        return startedMoney;
    }

    public int getStartedLives() {
        return startedLives;
    }

    public int getEnemiesPerWave() {
        return enemiesPerWave;
    }

    public float getTimeBetweenEnemies() {
        return timeBetweenEnemies;
    }

    public float getDifficultyMulti() {
        return difficultyMulti;
    }

    public String getMapName() {
        return "res\\maps\\newMarvelousMap" + lvlNum;
    }
}
