package lesson5;

import java.util.ArrayList;
import java.util.Arrays;

public class AppData {
    private String[] header;
    private int[][] data;

    public AppData() {
    }

    public AppData(String[] header, int[] ... data) {
        this.header = header;
        this.data = data;
    }

    public String[] getHeader() {
        return header;
    }

    public void setHeader(String[] header) {
        this.header = header;
    }

    public int[][] getData() {
        return data;
    }

    public void addData(int[] line) {
        int[][] newData;
        if (data == null) {
            newData = new int[1][];
        } else {
            newData = Arrays.copyOf(data, data.length + 1);
        }
        newData[newData.length - 1] = line;
        this.data = newData;
    }

    public void addData(String line) {
        String[] parts = line.split(";");
        int[] numbers = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            numbers[i] = Integer.parseInt(parts[i]);
        }
        addData(numbers);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String s : header) {
            sb.append(s + ";");
        }
        sb.setLength(sb.length()-1);
        sb.append("\n");
        for (int[] line : data) {
            for (int i : line) {
                sb.append(i + ";");
            }
            sb.setLength(sb.length()-1);
            sb.append("\n");
        }
        return sb.toString();
    }
}