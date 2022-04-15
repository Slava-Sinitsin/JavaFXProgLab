package Lab1Package;

import java.util.ArrayList;

public abstract class BaseAI extends Thread {
    protected final ArrayList<Transport> transportArr;
    protected volatile boolean running = true;
    protected volatile boolean paused = false;
    protected final Object pauseLock = new Object();

    public BaseAI(ArrayList<Transport> transportArr) {
        this.transportArr = transportArr;
    }

    public void myWait() {
        paused = true;
    }

    public void myNotify() {
        synchronized (pauseLock) {
            paused = false;
            pauseLock.notifyAll();
        }
    }

    public void mySleep(int millis) {
        try {
            sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean check() {
        synchronized (pauseLock) {
            if (paused) {
                try {
                    pauseLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return running;
    }
}