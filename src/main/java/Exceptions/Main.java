package Exceptions;

public class Main {
    public static void main(String[] args)
    {
        String[][] array = new String[][] { {"1", "1", "give me get away", "1"}, {"1", "2", "1", "1"}, {"1", "1", "3", "1"}, {"1", "1", "1", "4"} };
        String[][] array_2 = new String[][]{ {"1", "2", "3", "4"}, {"1", "2", "4"}, {"1", "2", "3", "4"}, {"1", "2", "3", "4"} };
        String[][] array_3 = new String[][]{ {"1", "2", "3", "4"}, {"1", "2", "3", "4"}, {"1", "2", "3", "4"} };

        try
        {
            try
            {
                int result = checkExceptions(array);

                System.out.println(result);
            }
            catch (MyArraySizeException e)
            {
                System.out.println(e);
            }
        }
        catch (MyArrayDataException e)
        {
            System.out.println("Invalid array values!");
            System.out.printf("Error in cell [%d, %d]", e.i, e.j);
        }
    }

    public static int checkExceptions(String[][] arr) throws MyArraySizeException, MyArrayDataException
    {
        int sum = 0;

        // Check Rows Count
        if (arr.length != 4)
        {
            throw new MyArraySizeException("Wrong number of rows!");
        }

        // Check Columns Count
        for (int i = 0; i < arr.length; i++)
        {
            if (arr[i].length != 4)
            {
                throw new MyArraySizeException(String.format("Wrong number of columns in row %d!", i));
            }

            for (int j = 0; j < arr[i].length; j++)
            {
                try
                {
                    sum += Integer.parseInt(arr[i][j]);
                }
                catch (NumberFormatException e)
                {
                    throw new MyArrayDataException(i, j);
                }
            }
        }

        return sum;
    }
}
