package com.company;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.Scanner;

public class Intellect {
    private double max = 0;
    private String coords;
    private String inPath;

    public void setInPath(String inPath) {
        this.inPath = inPath;
    }

    public void setOutPath(String outPath) {
        this.outPath = outPath;
    }

    private String outPath;


    //Считывает и и проверяет на треугольники
    private String reader() throws IOException {
        int counter = 0;
        try (FileInputStream inputStream = new FileInputStream(inPath); Scanner sc = new Scanner(inputStream, StandardCharsets.UTF_8)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                counter++;
                try {
                    //System.out.println(line);
                    double temp = new Triangle(line).myHandler();
                    if (temp==0)
                        continue;
                   // System.out.println(temp);
                    if (temp > max) {
                        max = temp;
                        coords = line;
                    }
                } catch (IOException e) {
                    System.out.print("ROW: " + counter + " ");
                    System.out.println(e.getMessage());
                }
            }
            //sc.close();   //closing the resource is handled automatically by the try-with-resources
        } catch (FileNotFoundException e) {
            System.out.println("File not found! ");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        if (counter == 0)
            System.out.println("File is empty!");
        return coords;
    }

    //Запись строки в файл
    private void writer(String path, String str) throws IOException {
        FileWriter nFile = new FileWriter(path);
        nFile.write(str);
        nFile.close();
    }

    //Запись таблицы в файл
    private void writer(String path, String[] str) throws IOException {
        FileWriter nFile = new FileWriter(path);
        System.out.println("File with new parameters will be created!");
        for (String s : str) {
            nFile.write(s);
            nFile.write('\n');
        }
        nFile.close();
    }

    Intellect(String inPath,String outPath) {
        this.inPath = inPath;
        this.outPath = outPath;
    }
    void go() throws IOException {
        coords = reader();
        if (coords != null) {
            writer(outPath, coords);
        }
    }

    //запись сгенерированной таблицы в файл
    public void writerGen(int count) throws IOException {
        writer("in1.txt", genTriangle_table(count));
    }

    //Генерация таблицы треугольников
    private String[] genTriangle_table(int count) {
        String[] table = new String[count];
        for (int i = 0; i < count; i = i + 2) {
            table[i] = genTriangle();
            table[i + 1] = genTriangle_isosceles();
        }
        for (int i = 0; i < count; i++)
            System.out.println(table[i]);
        return table;
    }

    //Генерация случайного треугольника
    private String genTriangle() {
        Random r = new Random();
        //      x1  y1                   x2                                           y2                                              x3                                            y3
        return "0 " + "0 " + Integer.toString(r.nextInt(10)) + " " + Integer.toString(r.nextInt(10)+1) + " " + Integer.toString(r.nextInt(10)) + " " + Integer.toString(r.nextInt(10));
    }

    //Генерация равнобедренного треугольника
    private String genTriangle_isosceles() {
        Random r = new Random();
        int x2 = r.nextInt(20)+1;
        double x3 = (double) x2 /2;
        return "0 " + "0 " + Integer.toString(x2) + " 0 " + Double.toString(x3) + " " + Integer.toString(r.nextInt(20));
    }
}

