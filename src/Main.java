import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        int[] sourceData = {0, 5, 10, 0, 11, 14, 13, 4, 11, 8, 8, 7, 1, 4, 12, 11};
        int countStep=0, lengthCycle = 0;
        List<int[]> lstData = new ArrayList<>();
        lstData.add(sourceData);

        while(true) {
            int[] curData = sourceData.clone();
            int indexMaxValue =  findIndexMaxNumber(curData);
            int saveNum = curData[indexMaxValue];
            curData[indexMaxValue] = 0;

            //целое от деления
            int plusNum = saveNum/curData.length;
            //остаток от деления
            int plusOstatok = saveNum%curData.length;
            //распределение числа с правой стороны
            for (int i = indexMaxValue + 1; i < curData.length; i++) {
                if (plusNum>0) curData[i] += plusNum;
                if (plusOstatok>0) {
                    curData[i] += 1;
                    plusOstatok--;
                }
            }
            //распределение числа с левой стороны
            for (int i = 0; i <= indexMaxValue; i++) {
                if (plusNum>0) curData[i] += plusNum;
                if (plusOstatok>0) {
                    curData[i] += 1;
                    plusOstatok--;
                }
            }

            //сравнение полученного массива с другими, полученными ранее
            boolean exit=false;
            for(int i=0; i <lstData.size();i++){
                if (compareArrays(lstData.get(i), curData)) {
                    countStep = lstData.size();
                    lengthCycle =  lstData.size()- i;
                    exit = true;
                }

            }

            lstData.add(curData);
            sourceData = curData;
            if (exit) break;
        }

        //Вывод результата
        for(int[] array:lstData){
            printArray(array);
        }
        System.out.println("Количество шагов = " + countStep);
        System.out.println("Длина цикла  = " + lengthCycle);
    }

    //функция для сравнения массивов на равенство
    public static boolean compareArrays(int[] array1, int[] array2){
        for(int i=0; i < array1.length;i++){
            if(array1[i] != array2[i]) return false;
        }
        return true;
    }

    //функция поиска индекса элемента, имеющего максимальное значение в массиве
    public static int findIndexMaxNumber(int[] array){
        int maxIndex= 0;
        for (int i=1;i<array.length;i++){
            if (array[maxIndex] < array[i]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    //функция печати массива в заданном виде
    public static void printArray(int[] array){
        for(int i=0;i <array.length;i++) {
            System.out.print(array[i] + " ");
        }
        System.out.print("\n");
    }
}
