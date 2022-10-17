package MultithreadingSecondPart;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    static Semaphore semaphore;

    public Tunnel(int length) {
        this.length = length;

        this.description = String.format("Tunnel %d meters", length);
    }

    static {
        semaphore = new Semaphore(Main.CARS_COUNT / 2);
    }

    @Override
    public void go(Car c ) {
        try {
            System.out.printf("%s getting ready for the stage: %s%n", c.getName(), description);

            semaphore.acquire();

            System.out.printf("%s started the stage: %s%n", c.getName(), description);

            Thread.sleep(length / c.getSpeed() * 1000L);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            semaphore.release();

            System.out.printf("%s finished the stage: %s%n", c.getName(), description);
        }
    }
}
