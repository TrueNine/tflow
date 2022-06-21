package io.tn.core;

public class Optimization {

    public static void oplz(Runnable run, long control) {
        try {
            Thread.sleep(control);
            run.run();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
