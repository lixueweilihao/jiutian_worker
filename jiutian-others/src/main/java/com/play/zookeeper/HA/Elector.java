package com.play.zookeeper.HA;
public interface Elector {
    boolean wins();

    default void waitForWin() throws InterruptedException {
        while (!wins()) {
            Thread.sleep(50L);
        }
    }
}
