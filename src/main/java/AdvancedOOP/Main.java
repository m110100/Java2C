package AdvancedOOP;

public class Main {
    public static void main(String[] args) {
        // Препятствия
        Obstacles[] obstacles = new Obstacles[] {
                new Wall(Wall.Walls.HIGH),
                new Track(Track.Tracks.NORMAL),
                new Wall(Wall.Walls.HIGH),
                new Track(Track.Tracks.FAST),
                new Wall(Wall.Walls.SMALL),
                new Track(Track.Tracks.SLOW),
                new Wall(Wall.Walls.HIGH),
                new Track(Track.Tracks.FAST)
        };

        Contestants[] contestants = new Contestants[] {
                new Human("Alex", 700, 10),
                new Robot("Jarvis", 4000, 60),
                new Cat("Snowflake", 300, 40),
                new Human("Tony", 1000, 15)
        };

        for (Contestants c : contestants) {
            for (Obstacles o: obstacles) {
                if(!o.hindrance(c)) break;
            }
        }
    }
}
