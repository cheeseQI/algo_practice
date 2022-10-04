package com.myAlgorithms.array;

public class findXMatrix {
    // Given a matrix that contains only 1s and 0s, find the largest X shape which contains only 1s, with the same arm lengths and the four arms joining at the central point.
    // Return the arm length of the largest X shape
    //  {1, 1, 1, 0},
    //  {0, 1, 1, 0},
    //  {1, 0, 1, 0} }
    // the center of the largest X is (1,1)
    public int[] largest(int[][] matrix) {
        //high level
        //track the largest leg in all four directions
        //take the min for each starting spot
        int N = matrix.length;
        int M = matrix[0].length;
        if (N == 0 || M == 0) return new int[] {0, 0};
        int[][]lRUp = leftRightUp(matrix, N, M); // 扫描的同时求，然后再合并左上和右上到中心的转移
        int[][]lRDown = leftRightDown(matrix,N,M); // 同理对 左下 右下
        return merge(lRUp, lRDown, N, M);
    }
    private int[] merge(int[][] leftUp, int[][] rightDown, int N, int M){
        int largestLength = 0;
        int res[] = new int[] {0, 0};
        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                leftUp[i][j] = Math.min(leftUp[i][j],rightDown[i][j]); // 只能拿到对角线最短的/木桶效应
                if (leftUp[i][j] > largestLength) {
                    largestLength = leftUp[i][j];
                    res[0] = i;
                    res[1] = j;
                }
            }
        }
        return res;
    }

    private int[][] leftRightUp(int[][] matrix, int N, int M){
        int[][] left = new int[N][M];
        int[][] right = new int[N][M];
        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                if (matrix[i][j] == 1){
                    left[i][j] = getNumber(left, i - 1, j - 1, N, M) + 1;
                    right[i][j] = getNumber(right, i - 1, j + 1, N, M) + 1;
                }
            }
        }
        merge(left,right, N, M);
        return left;
    }

    private int[][] leftRightDown(int[][] matrix, int N, int M){
        int[][] right = new int[N][M];
        int[][] left = new int[N][M];
        for(int i = N - 1; i >= 0; i--){
            for(int j = M - 1; j >=0; j--){
                if (matrix[i][j] == 1){
                    right[i][j] = getNumber(right, i + 1, j + 1, N, M) + 1;
                    left[i][j] = getNumber(left, i + 1, j - 1, N, M) + 1;
                }
            }
        }
        merge(right, left, N, M);
        return right;
    }

    private int getNumber(int[][] input, int x, int y, int N, int M){
        if (x < 0 || x >= N || y < 0 || y >= M) return 0;
        return input[x][y];
    }
}
