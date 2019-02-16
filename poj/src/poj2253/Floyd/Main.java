package poj2253.Floyd;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int cases = 1;
        while (true) {
            /*
             * 此段程序负责输入，输入n值确定总的石块个数后，再分别输入每个石块的坐标。
             * 最后，把问题看做每个石块都互相连通的无向图，定义path二维数组，负责
             * 记录每个石块之间的距离，构成图
             */
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

            /*
             * 使用类Floyd算法解决该问题，与Floyd算法不同的是if语句里的判
             * 断条件，比较的是从i直接到j的跳跃距离和从i到k再从k到j的跳跃
             * 距离。取较短的作为i到j的跳跃距离，并记录，作为下一阶段的决策。
             */
            for (int k = 0; k < n; k++)  
                for (int i = 0; i < n; i++)
                    for (int j = 0; j < n; j++)
                        if (path[i][k] < path[i][j] && path[k][j] < path[i][j]) 
                            if (path[i][k] < path[k][j]) //�Ƚ�i��k��k��j�ľ��룬ȡ�ϳ�������Ծ����
                                path[i][j] = path[j][i] = path[k][j]; 
                            else
                                path[i][j] = path[j][i] = path[i][k];
            
            System.out.println("Scenario #" + cases++);
            System.out.printf("Frog Distance = %.3f\n\n" ,path[0][1]);
        }
        scan.close();
    }
}

//定义保存输入石块坐标的类
class Point {
    int x;
    int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}