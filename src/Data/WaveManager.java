package Data;

import static Helpers.Artist.modifyCash;

/**
 * Created by shurik on 02.05.2017.
 */
public class WaveManager {

    private float timeSinceLastWave, timeBetweenEnemies;
    private int waveNumber, enemiesPerWave;
    private Enemy[] enemyTypes;
    private Wave currentWave;

    public WaveManager(Enemy[] enemyTypes, float timeBetweenEnemies, int enemiesPerWave) {
        this.enemyTypes = enemyTypes;
        this.timeBetweenEnemies = timeBetweenEnemies;
        this.enemiesPerWave = enemiesPerWave;
        this.timeSinceLastWave = 0;
        this.waveNumber = 0;

        this.currentWave = null;
        newWave();
    }

    public void update() {
        if (!currentWave.isCompleted()) currentWave.update();
        else {
            enemiesPerWave += 2;
           // enemyTypes.setHealth(enemyTypes.getHealth() * 1.2f);
            modifyCash(5 * waveNumber);
            newWave();
        }
    }

    private void newWave() {
        if (waveNumber != 0) currentWave.getEnemies().clear();
        currentWave = new Wave(enemyTypes, timeBetweenEnemies, enemiesPerWave);
        waveNumber++;
        System.out.println("Wave " + waveNumber);
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
