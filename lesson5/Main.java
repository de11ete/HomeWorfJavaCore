package lesson5;

import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        task1();
        task2();
    }

    private static void task1(){
        String[] header = new String[]{"Value 1","Value 2","Value 3"};
        int[] data1 = new int[]{100,200,123};
        int[] data2 = new int[]{300,400,500};
        int[] data3 = new int[]{12,55,502340};
        AppData appData = new AppData(header, data1, data2, data3, data1);
        try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream("src/main/java/file.csv"))) {
            stream.write(appData.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void task2(){
        AppData appData = new AppData();
        try (BufferedReader stream = new BufferedReader(new FileReader("src/main/java/file.csv"))){
            String line = stream.readLine();
            appData.setHeader(line.split(";"));
            line = stream.readLine();
            while (line != null){
                appData.addData(line);
                line = stream.readLine();
            }
            System.out.println(appData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}