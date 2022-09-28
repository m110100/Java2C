package AdvancedOOP;

public class Cat implements Contestants{
    private String name;
    private int max_distance;
    private int max_height;

    public Cat(String name, int max_distance, int max_height){
        this.name = name;
        this.max_distance = max_distance;
        this.max_height = max_height;
    }

    @Override
    public boolean run(Track.Tracks t) {
        if (t.distance <= max_distance) {
            System.out.printf("Cat %s successfully ran%n", name);
            return true;
        }
        else {
            System.out.printf("Cat %s couldn't run%n", name);
            return false;
        }
    }

    @Override
    public boolean jump(Wall.Walls w) {
        if (w.height <= max_height) {
            System.out.printf("Cat %s successfully jumped%n", name);
            return true;
        }
        else {
            System.out.printf("Cat %s couldn't jump%n", name);
            return false;
        }
    }
}
