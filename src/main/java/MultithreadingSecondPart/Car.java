package MultithreadingSecondPart;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private Race race;
    private int speed;
    private String name;
    private static CyclicBarrier startBarrier;
    private static CountDownLatch countDownLatchReady;
    private static CountDownLatch countDownLatchFinish;

    static {
        countDownLatchReady = Main.countDownLatchReady;
        countDownLatchFinish = Main.countDownLatchFinish;

        startBarrier = Main.startBarrier;
    }

    String getName() {
        return name;
    }

    int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed) {
        this.race = race;

        this.speed = speed;

        CARS_COUNT++;

        this.name = String.format("Member No.%d%n", CARS_COUNT);
    }

    @Override
    public void run() {
        try {
            System.out.printf("%s is preparing%n", this.name);

            Thread.sleep(500 + (int) (Math.random() * 800));

            countDownLatchReady.countDown();

            System.out.printf("%s ready%n", this.name);

            startBarrier.await();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        final ArrayList<Stage> stages = race.getStages();

        for (Stage stage : stages) stage.go(this);

        countDownLatchFinish.countDown();
    }
}
