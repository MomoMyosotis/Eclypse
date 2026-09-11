// first line

package app.helpers;
import java.util.ArrayList;
import app.modules.computer.FileSearch.FileInfo;

public class SelectionSort {

    private SelectionSort(){}

        public static void selectionsort(ArrayList<FileInfo> gioie, int start){

            for (int i = start; i < gioie.size() - 1; i++){

            int position = i;

            // trovo il massimo nella parte non ordinata
            for (int j = i + 1; j < gioie.size(); j++){
                if (gioie.get(j).getScore() > gioie.get(position).getScore()){
                    position = j;
                }
            }

            // scambio
            FileInfo temp = gioie.get(position);
            gioie.set(position, gioie.get(i));
            gioie.set(i, temp);
        }
    }
}

// last line