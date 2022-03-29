package lesson2;

public class Main {
    public static int arraySum(String[][] twoDimensionalArray) throws MyArraySizeException, MyArrayDataException {
        if (twoDimensionalArray.length != 4) {
            throw new MyArraySizeException("Длина массива != 4");
        }

        for (String[] array : twoDimensionalArray) {
            if (array.length != 4) {
                throw new MyArraySizeException("Высота массива не равна 4");
            }
        }

        int sum = 0;
        for (int i = 0; i < twoDimensionalArray.length; i++) {
            for (int j = 0; j < twoDimensionalArray[i].length; j++) {
                try {
                    sum += Integer.parseInt(twoDimensionalArray[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Неверные данные в элементе:" + i  + "," + j);
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {

        String[][] successArray =
                {{"1", "2", "3", "4"}, {"0", "1", "2", "3", "4"}, {"1", "2", "3", "4"}, {"1", "2", "3", "4"}};

        Integer[][] array = new Integer[4][];
        array[0] = new Integer[5];
        array[0] = new Integer[4];
        array[0] = new Integer[4];

        try {
            System.out.println(arraySum(successArray));
        } catch (MyArraySizeException e) {
            e.printStackTrace();
        } catch (MyArrayDataException e) {
            e.printStackTrace();
        }

        String[][] invalidDataArray =
                {{"2", "2", "2", "2"}, {"3", "2", "o", "3"}, {"x", "1", "1", "4"}, {"3", "4", "3", "4"}};

        try {
            System.out.println(arraySum(invalidDataArray));
        } catch (MyArraySizeException e) {
            e.printStackTrace();
        } catch (MyArrayDataException e) {
            e.printStackTrace();
        }
    }
}
