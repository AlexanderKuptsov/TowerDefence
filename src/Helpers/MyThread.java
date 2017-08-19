package Helpers;

import Data.ResourceLoader;

public class MyThread extends Thread {

    private String name;

    public MyThread() {
        super();
        //System.out.println("new Thread " + name);
    }

    @Override
    public void run() {
        ResourceLoader resources = new ResourceLoader();
        System.out.println("Loading completed!");
    }
}