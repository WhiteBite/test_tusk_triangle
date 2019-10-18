package com.company;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void menu() {
        System.out.println("Welcome to the test assignment for my future work. \n" +
                "This program can generate triangles and find the largest isosceles triangle.\n" +
                "To set the path, use args \'in.txt out.txt\'\n" +
                "For use console app use argument -console");
    }

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            menu();
        } else if (args[1] == "-console") {
            menu2();
        } else if (args.length == 2) {
            new Intellect(args[0],args[1]).go();
        } else {
            System.out.println("Wrong argumets");
        }
    }

    private static void menu2() throws IOException {
        System.out.println("        Open console");
        System.out.println(" gen in1.txt (file with random triangles - Enter 1 ");
        System.out.println(" manually configure the input and output file, Enter 2 \n\t(by default in.txt and out.txt) ");
        System.out.println(" To start, enter 3 ");
        Scanner scanner = new Scanner(System.in);
        int menuScan = scanner.nextInt();

        Intellect q=  new Intellect("in.txt" ," out.txt");
        if (menuScan==1){
            q.writerGen(20);
        }
        if (menuScan==2){
            System.out.println("Enter an input file name ");
            String inFile = scanner.nextLine();
            System.out.println("Enter the name of the output file");
            String outFile = scanner.nextLine();
            q.setInPath(inFile);
            q.setOutPath(outFile);
        }
        if (menuScan==3){
            q.go();
        }
    }
}
