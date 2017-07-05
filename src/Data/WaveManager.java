package Data;

import Helpers.Clock;

import java.util.concurrent.CopyOnWriteArrayList;

import static Helpers.Artist.modifyCash;

/**
 * Created by shurik on 02.05.2017.
 */
public class WaveManager {

    private float timeBetweenEnemies, difficultyMulti, currentDifMulti;
    private int waveNumber, enemiesPerWave;
    private Enemy[] enemyTypes;
    private Wave currentWave;

    public WaveManager(Enemy[] enemyTypes, float timeBetweenEnemies, int enemiesPerWave, float difficultyMulti) {
        this.enemyTypes = enemyTypes;
        this.timeBetweenEnemies = timeBetweenEnemies;
        this.enemiesPerWave = enemiesPerWave;
        this.waveNumber = 0;
        this.difficultyMulti = difficultyMulti;
        currentDifMulti = 1f;
        this.currentWave = null;
        newWave();
    }

    public void update() {
        if (!currentWave.isCompleted()) currentWave.update();
        else {
            enemiesPerWave += 3;
            modifyCash(5 + waveNumber / 2);
            newWave();
        }
    }

    private void newWave() {
        if (waveNumber != 0) currentWave.getEnemies().clear();
        waveNumber++;
        currentWave = new Wave(enemyTypes, timeBetweenEnemies, enemiesPerWave, currentDifMulti, waveNumber);
        currentDifMulti = difficultyMulti;
    }

    public void restartEnemies() {
        currentWave.getEnemies().clear();
        waveNumber = 0;
    }

    public Wave getCurrentWave() {
        return currentWave;
    }

    public int getWaveNumber() {
        return waveNumber;
    }
}
