package Data;

import Enemies.Enemy;
import Helpers.Clock;
import UI.UI;
import org.newdawn.slick.TrueTypeFont;

import java.awt.*;

import static Helpers.Artist.*;

/**
 * Created by shurik on 02.05.2017.
 */
public class WaveManager {

    private float timeBeforeStart, timeBetweenEnemies, difficultyMulti, currentDifMulti;
    private int waveNumber, enemiesPerWave;
    private Enemy[] enemyTypes;
    private Wave currentWave;
    private UI waveManagerUI;

    private final int COUNT_X = WIDTH / 2 - TILE_SIZE / 3;
    private final int COUNT_GO__X = WIDTH / 2 - (int) (TILE_SIZE * 0.75f);
    private final int COUNT_Y = TILE_SIZE;

    public WaveManager(Enemy[] enemyTypes, float timeBetweenEnemies, int enemiesPerWave, float difficultyMulti) {
        this.enemyTypes = enemyTypes;
        this.timeBeforeStart = 5f;
        this.timeBetweenEnemies = timeBetweenEnemies;
        this.enemiesPerWave = enemiesPerWave;
        this.waveNumber = 0;
        this.difficultyMulti = difficultyMulti;
        currentDifMulti = 1f;
        this.currentWave = null;
        this.waveManagerUI = new UI();
        waveManagerUI.setFont(new TrueTypeFont(new Font("Algerian", Font.BOLD, 66), true));
        newWave();
    }

    public void update() {
        if (timeBeforeStart >= 0) {
            if (timeBeforeStart > 1)
                waveManagerUI.drawString(COUNT_X, COUNT_Y, String.valueOf(Math.round(timeBeforeStart)));
            else
                waveManagerUI.drawString(COUNT_GO__X, COUNT_Y, "GO");

            timeBeforeStart -= Clock.INSTANCE.delta();
        } else if (!currentWave.isCompleted()) currentWave.update();
        else {
            enemiesPerWave += 3;
            modifyCash(5 + waveNumber / 2);
            newWave();
        }
    }

    private void newWave() {
        if (waveNumber != 0) currentWave.getEnemies().clear();
        waveNumber++;
        timeBeforeStart=5f;
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
