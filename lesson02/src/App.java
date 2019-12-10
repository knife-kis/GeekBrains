import java.util.Arrays;

/**
 * @author Maksim Tarnovskiy
 */
public class App {

    public static void main(String[] args) throws MyArraySizeExeption {
        int[][] array = new int[4][4];
        int[][] array2 = new int[2][2];
        int[][] array3 = new int[4][3];
        addArray(array);
        addArray(array2);
        addArray(array3);
    }
    static void addArray(int[][] array){
        try {
            if (array.length == 4 && array[0].length == 4) {
                for (int i = 0; i < array.length; i++) {
                    for (int j = 0; j < array.length; j++) {
                        array[i][j] = array.length;
                    }
                }
            } else throw new MyArraySizeExeption();
            System.out.println(Arrays.deepToString(array));
        } catch (MyArraySizeExeption e){
            System.out.println("Вы ввели неверную размерность массива, вы ввели: [" + array.length + "][" + array[0].length + "]" );
        }
    }
}
