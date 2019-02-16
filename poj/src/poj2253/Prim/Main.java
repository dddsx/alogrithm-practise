package poj2253.Prim;
import java.util.Scanner;

public class Main {
	static int MAX = 0xFFFFFFF;
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

			//开始进行算法设计
			double min,jump = 0;  //min记录生成树连通到下一个结点的最短距离，其中最大
			//的min就是青蛙所需的最小跳跃距离jump

			int minIndex;               //记录生成树连通到下一个结点的索引
			double[] lowCost = new double[n];    // 存储每个生成树外的节点到生成树的最小距离
			int[] pre = new int[n];                          // 存储寻找下一个结点时所参照的前驱节点
			boolean[] choice = new boolean[n];    // 存储每个石块的信息，已加入生成树后记为true
			for(int i=0;i<n;i++){
				choice[i] = false;
			}

			choice[0] = true;                //首先选择第0个石块加入生成树
			for (int i = 1; i < n; i++) {
				lowCost[i] = path[0][i];   //其他石块到生成树的距离就是到第0个石块的距离
				pre[i] = 0;                      //记录这些石块参照的前驱石块
			}

			while (!choice[1]) {           //当第二个青蛙所在的石块没有加入到生成树时，循环继续
				min = MAX;
				minIndex = 0;             // 记录当前lowCost里面最小权值的下标
				for (int i = 1; i < n; i++) {
					if (choice[i] == false && lowCost[i] != 0 && lowCost[i] < min) {
						min = lowCost[i];
						minIndex = i;
					}
				}
				choice[minIndex] = true;  //将石块加入生成树
				if (min > jump) {              //此跳若大于目前最大需要的跳跃距离，记录
					jump = min;
				}
				//System.out.println(pre[minIndex] + "->"+minIndex);

				/*
				 * 由于有新的石块加入生成树,对于每个生成树外的石块，更新它们到生成树的最短距离。
				 */
				for (int i = 1; i < n; i++) {
					if (lowCost[i]!=0 && lowCost[i] > path[minIndex][i]) {
						lowCost[i] = path[minIndex][i];
						pre[i] = minIndex;
					}
				}
			}
			System.out.println("Scenario #" + cases++);
			System.out.printf("Frog Distance = %.3f\n\n", jump);
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