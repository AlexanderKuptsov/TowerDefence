package Helpers;

public class MyThread extends Thread {

    public MyThread() {
        super();
    }

    @Override
    public void run() {
        Sound.playSound("res\\sounds\\switch23.wav").join();
    }
}