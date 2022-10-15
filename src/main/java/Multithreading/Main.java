package Multithreading;

public class Main {
    public static void main(String[] args) {
        runOneThread();

        System.out.println();

        runTwoThread();
    }

    private static final int SIZE = 60000000;
    private static final int HALF_SIZE = SIZE / 2;

    public static float[] newArrayValuesFirst(float[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

        return arr;
    }

    public static float[] newArrayValuesSecond(float[] arr) {
        for (int i = arr.length, j = arr.length; i < arr.length * 2; i++) {
            arr[i - j] = (float) (arr[i - j] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        return arr;
    }

    public static void runOneThread() {
        float[] array = new float[SIZE];

        for (int i = 0; i < array.length; i++) {array[i] = 1.0f;}

        long a = System.currentTimeMillis();

        newArrayValuesFirst(array);

        System.out.printf("First method ends with : %d", System.currentTimeMillis() - a);
        System.out.printf("%nLast array value of first method : %f", array[array.length - 1]);
    }

    public static void runTwoThread() {
        float[] array = new float[SIZE];
        float[] f_array = new float[HALF_SIZE];
        float[] s_array = new float[HALF_SIZE];

        for (int i = 0; i < array.length; i++) { array[i] = 1.0f; }

        long a = System.currentTimeMillis();

        System.arraycopy(array, 0, f_array, 0, HALF_SIZE);
        System.arraycopy(array, HALF_SIZE, s_array, 0, HALF_SIZE);

        Thread thread1 = new Thread(() -> {
            float[] f_a = newArrayValuesSecond(f_array);
        });

        Thread thread2 = new Thread(() -> {
            float[] s_a = newArrayValuesSecond(s_array);
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.arraycopy(f_array, 0, array, 0, HALF_SIZE);
        System.arraycopy(s_array, 0, array, HALF_SIZE, HALF_SIZE);

        System.out.printf("Second method ends with : %d", System.currentTimeMillis() - a);
        System.out.printf("%nLast array value of second method : %f", array[array.length - 1]);
    }
}
