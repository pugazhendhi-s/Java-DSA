package com.f9_queueStack.stack.leetCode.easy;

public class FloodFill {

    public static void main(String[] args) {

    }

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int color = image[sr][sc];
        if (color != newColor) {
            dfs(image, sr, sc, color, newColor);
        }
        return image;
    }

    private void dfs(int[][] image, int row, int col, int color, int newColor) {
        if (image[row][col] == color) {
            image[row][col] = newColor;
            if (row >= 1)
                dfs(image, row - 1, col, color, newColor);
            if (col >= 1)
                dfs(image, row, col - 1, color, newColor);
            if (row + 1 < image.length)
                dfs(image, row + 1, col, color, newColor);
            if (col + 1 < image[0].length)
                dfs(image, row, col + 1, color, newColor);
        }
    }
}
