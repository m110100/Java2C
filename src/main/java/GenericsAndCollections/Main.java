package GenericsAndCollections;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//         taskOne();

//         taskTwo();

         taskThree();
    }

    public static void taskOne() {
        Integer[] intArray = {0, 1, 1, 2, 3, 5, 8};

        System.out.printf("Task One.%nInitial array : " + Arrays.toString(intArray));

        try {
            swapTwoArrayElements(intArray, 2, 5);
        }

        catch (WrongIndexesException e) {
            e.printStackTrace();
        }

        System.out.printf("%nThe result of the swapping of variables : " + Arrays.toString(intArray));
    }

    public static void taskTwo(){
        Integer[] intArray = {0, 1, 1, 2, 3, 5, 8};

        ArrayList<Integer> arraylistint = arrayToArrayList(intArray);

        System.out.println(arraylistint.getClass());
    }

    public static void taskThree() {
        Box<Apple> appleBox = new Box<>(new Apple(), new Apple(), new Apple(), new Apple(), new Apple(), new Apple());

        Box<Orange> orangeBox = new Box<>(new Orange(), new Orange(), new Orange());

        Box<Orange> orangeBox1 = new Box<>();

        orangeBox.movingFruits(orangeBox1);
        System.out.println(orangeBox);
        System.out.println(orangeBox1);

        Box<Orange> anotherOrangeBox = new Box<>(new Orange(), new Orange());

        Box<Apple> anotherAppleBox = new Box<>(new Apple(), new Apple(), new Apple());

        System.out.printf("anotherAppleBox weight : %f%n", anotherAppleBox.getWeight());

        System.out.printf("appleBox weight : %f%n", appleBox.getWeight());

        Box<Fruit> someFruitsBox = new Box<>(new Fruit(1.000f));

        System.out.printf("anotherAppleBox weight : %f%n", appleBox.getWeight());

//        System.out.printf("appleBox weight : %f%n", appleBox.getWeight());
//
//        System.out.println(anotherAppleBox.getBox());
//        System.out.printf("%nCompare anotherAppleBox with anotherOrangeBox equals : %b", anotherAppleBox.compare(anotherOrangeBox));


    }

    private static void swapTwoArrayElements(Object[] array, int fPosition, int sPosition) throws WrongIndexesException {
        if (fPosition < 0 || fPosition > array.length || sPosition < 0 || sPosition > array.length || fPosition == sPosition) {
            throw new WrongIndexesException("Invalid element indexes");
        }

        Object temp = array[fPosition];

        array[fPosition] = array[sPosition];

        array[sPosition] = temp;
    }

    private static <T> ArrayList arrayToArrayList(T[] array) {
        return new ArrayList(Arrays.asList(array));
    }
}
