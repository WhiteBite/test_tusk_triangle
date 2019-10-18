package com.company;


import java.io.IOException;

class Triangle {

    private Point[] points;
    private double[] side;

    Triangle(String str) throws IOException {
        points = new Point[3];
        side = new double[3];
        String delimeter = " "; // Разделитель
        String[] subStr = str.split(delimeter); // Разбить строку str.
        int countPoints = 0;
        if (subStr.length != 6) {
            throw new IOException("Not valid row! Invalid number of coordinates -> " + str);
        }
        for (int i = 0; i < subStr.length; i = i + 2) {
            if (!isDigit(subStr[i]) || !isDigit(subStr[i + 1])) {
                throw new IOException("Not valid row! Invalid coordinates");
            }
            points[countPoints] = new Point(Double.parseDouble(subStr[i]), Double.parseDouble(subStr[i + 1]));
            countPoints++;
        }
        sider();
    }

    //Вычисление длины стороны
    private static double lenSide(Point A, Point B) {
        return Math.sqrt(Math.pow(A.getX() - B.getX(), 2) + Math.pow(A.getY() - B.getY(), 2));
    }

    //Расчёт сторон
    private void sider() {
        side[0] = lenSide(points[0], points[1]);  //AB
        side[1] = lenSide(points[1], points[2]); //BC
        side[2] = lenSide(points[0], points[2]);  //AC
    }

    //Проверка на равноберденность
    private boolean isosceles() {
        return side[0] == side[1] || side[1] == side[2] || side[2] == side[0];
    }

    //периметр
    public double perimeter() {
        return side[0] + side[1] + side[2];
    }

    //площадь треугольника по формуле Герона
    public double area() {
        double p = perimeter() / 2;
        return Math.sqrt(p * (p - side[0]) * (p - side[1]) * (p - side[2]));
    }

    // проверка, на валидность числа
    private static boolean isDigit(String s) throws NumberFormatException {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //Проверяет на равнобедренность и вычисляет площадь
    double myHandler() {
        if (isosceles())
            return area();
        return 0;
    }
}