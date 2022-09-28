package AdvancedOOP;

public class Track extends Obstacles {
    public enum Tracks {
        SLOW(500), NORMAL(700), FAST(1000);

        public int distance;

        Tracks(int distance){
            this.distance = distance;
        }
    }

    public Tracks t;

    public Track(Tracks t){
        this.t = t;
    }

    @Override
    public boolean hindrance(Contestants c){
        return c.run(t);
    }
}
