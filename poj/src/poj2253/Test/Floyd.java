package poj2253.Test;


public class Floyd {
    private final static int M = 0xFFFFF;
    public static void floyd(int[][] dist) {
        int i, j, k;
        int n = dist.length;
        for (k = 0; k < n; k++) {
            for (i = 0; i < n; i++) {
                for (j = 0; j < n; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j])
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < n; y++) {
                    if (dist[x][y]<M)
                        System.out.print(dist[x][y] + "   ");
                    else
                        System.out.print("��" + "   ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        //int[][] dist = {{0, 9, M, 2}, {9, 0, 1, M}, {M, 1, 0, 3}, {2, M, 3, 0}};
        int[][] dist = {
                {0,1,M,M,5},
                {1,0,2,M,M},
                {M,2,0,3,M},
                {M,M,3,0,4},
                {5,M,M,4,0}
        };
        floyd(dist);
    }
}

