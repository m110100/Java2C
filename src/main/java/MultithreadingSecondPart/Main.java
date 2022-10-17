package MultithreadingSecondPart;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Main {
    static final int CARS_COUNT = 4;
    static final CountDownLatch countDownLatchFinish = new CountDownLatch(CARS_COUNT);
    static final CountDownLatch countDownLatchReady = new CountDownLatch(CARS_COUNT);
    static final CyclicBarrier startBarrier = new CyclicBarrier(CARS_COUNT);

    public static void main(String[] args) throws InterruptedException{
        System.out.println("IMPORTANT ANNOUNCEMENT >>> Preparing!!!");

        Race race = new Race(new Road(60), new Tunnel(80), new Road(40));

        Car[] cars = new Car[CARS_COUNT];

        for (int i = 0; i < cars.length; i++) {
            int randomSpeed = 20 + (int) (Math.random() * 10);

            cars[i] = new Car(race, randomSpeed);
        }

        for (Car car : cars) new Thread(car).start();

        countDownLatchReady.await();

        System.out.println("IMPORTANT ANNOUNCEMENT >>> The race has started!!!");

        countDownLatchFinish.await();

        System.out.println("IMPORTANT ANNOUNCEMENT >>> The race is over!!!");
    }
}
