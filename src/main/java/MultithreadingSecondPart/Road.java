package MultithreadingSecondPart;

public class Road extends Stage {
    public Road(int length) {
        this.length = length;

        this.description = String.format("Road %d meters", length);
    }

    @Override
    public void go(Car c) {
        try {
            System.out.printf("%s started the stage: %s%n", c.getName(), description);

            Thread.sleep(length / c.getSpeed() * 1000);

            System.out.printf("%s finished the stage: %s%n", c.getName(), description);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
