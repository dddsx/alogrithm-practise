package poj2253.Test;


import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int cases = 1;
        while (true) {
            int n;
            if ((n = scan.nextInt()) <= 0) {
                break;
            }
            scan.nextLine();
            Point[] points = new Point[n];
            double[][] path = new double[n][n];
            for (int i = 0; i < n; i++) {
                points[i] = new Point(scan.nextInt(), scan.nextInt());
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    double x = points[i].x - points[j].x;
                    double y = points[i].y - points[j].y;
                    path[i][j] = Math.sqrt(x * x + y * y);
                }
            }
            for (int k = 0; k < n; k++) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (path[i][k] < path[i][j] && path[k][j] < path[i][j])
                            if (path[i][k] < path[k][j])
                                path[i][j] = path[k][j];
                            else
                                path[i][j] = path[i][k];
                    }
                }
                for (int x = 0; x < n; x++) {
                    for (int y = 0; y < n; y++) {
                        if (path[x][y]<0xFFFFF)
                            System.out.printf("%.3f\t",path[x][y]);
                        else
                            System.out.print("∞" + "   ");
                    }
                    System.out.println();
                }
                System.out.println();
            }
            System.out.println("Scenario #" + cases++);
            System.out.printf("Frog Distance = %.3f\n\n", path[0][1]);
        }
    }
}
class Point {
    int x;
    int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
