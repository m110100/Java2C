package GenericsAndCollections;

import java.util.ArrayList;
import java.util.Arrays;

public class Box <T extends Fruit> {
    ArrayList<T> box = new ArrayList<>();

    public Box(T... fruits) {
        box = new ArrayList<>(Arrays.asList(fruits));
    }

    public ArrayList<T> getBox() {
        return box;
    }

    public void addFruit(T fruit) {
        box.add(fruit);
    }

    public float getWeight() {
        float finalWeight = 0.0f;

        for (T t : box) {
            finalWeight += t.getWeight();
        }

        return finalWeight;
    }

    public boolean compare(Box<? extends Fruit> someBox) {
        return Math.abs(getWeight() - someBox.getWeight()) < 0.0001f;
    }

    public void movingFruits(Box<T> anotherBox) {
        if (this == anotherBox) {
            return;
        }

        anotherBox.box.addAll(this.box);
        this.box.clear();
    }
}
