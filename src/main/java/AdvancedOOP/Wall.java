package AdvancedOOP;

public class Wall extends Obstacles {
    public enum Walls {
        SMALL(10), MEDIUM(20), HIGH(30);

        public int height;

        Walls(int height){
            this.height = height;
        }
    }

    public Walls w;

    public Wall(Walls w){
        this.w = w;
    }

    @Override
    public boolean hindrance(Contestants c){
        return c.jump(w);
    }
}
