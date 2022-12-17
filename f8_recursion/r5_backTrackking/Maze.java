package com.f8_recursion.r5_backTrackking;

import java.util.ArrayList;
import java.util.Arrays;

public class Maze {

    public static void main(String[] args) {
        allPathsNumbers();
    }
    // Right, Down directions are allowed
    static int pathsRDCount(int r, int c){
        if(r == 1 || c == 1)
            return 1;
        int down  = pathsRDCount(r-1, c);
        int right = pathsRDCount(r,c-1);
        return down + right;
    }
    static ArrayList<String> pathsRD(String p, int r, int c){
        if(r == 1 && c == 1) {
            ArrayList<String> list = new ArrayList<>();
            list.add(p);
            return list;
        }
        ArrayList<String> list = new ArrayList<>();
        if(r > 1)
            list.addAll(pathsRD(p+"D" ,r-1, c));  // Down
        if(c > 1)
            list.addAll(pathsRD(p+"R", r,c-1)); // Right
        return list;
    }

    // Right, Down and diagonal directions are allowed
    static ArrayList<String> pathsDiagonal(String p, int r, int c){
        if(r == 1 && c == 1) {
            ArrayList<String> list = new ArrayList<>();
            list.add(p);
            return list;
        }
        ArrayList<String> list = new ArrayList<>();
        if(r > 1 && c > 1)
            list.addAll(pathsDiagonal(p+"D", r-1, c-1)); // diagonal
        if(r > 1)
            list.addAll(pathsDiagonal(p+"V" ,r-1, c));  // vertical
        if(c > 1)
            list.addAll(pathsDiagonal(p+"H", r,c-1)); // horizontal
        return list;
    }

    // Right, Down directions are allowed and contains some restricted box
    static void pathsRestrictions(){
        boolean[][] board = { // false represent u can't go on that.
                {true, true, true},
                {true, true, true},
                {true, true, true}
        };
        ArrayList<String> list = pathsRestriction("", board, 0, 0);
        System.out.println(list);
    }

    static ArrayList<String> pathsRestriction(String p, boolean[][] maze, int r, int c){
        if(r == maze.length-1 && c == maze[0].length-1) {
            ArrayList<String> list = new ArrayList<>();
            list.add(p);
            return list;
        }
        ArrayList<String> list = new ArrayList<>();
        if(!maze[r][c])
            return list;
        if(r < maze.length-1)
            list.addAll(pathsRestriction(p+"D", maze,r+1, c));  // Down
        if(c < maze[0].length-1)
            list.addAll(pathsRestriction(p+"R", maze, r,c+1)); // Right
        return list;
    }

    // All paths - Backtracking applied
    static void allPaths(){
        boolean[][] board = { // false represent u can't go on that.
                {true, true, true},
                {false, true , true},
                {true, true, true}
        };
        allPaths("", board, 0, 0);
    }
    static void allPaths(String p, boolean[][] maze, int r, int c){
        if(r == maze.length-1 && c == maze[0].length-1) {
            System.out.println(p);
            return;
        }
        if(!maze[r][c])
            return;
        // I am considering this block in my path
        maze[r][c] = false;  // backtracking

        if(r < maze.length-1)
            allPaths(p+"D", maze, r+1, c);  // Down
        if(c < maze[0].length-1)
            allPaths(p+"R", maze, r,c+1); // Right
        if(r > 0)
            allPaths(p+"U", maze, r-1, c); // Up
        if(c > 0)
            allPaths(p+"L", maze, r, c-1); // Left

        // this line is where the function will be over
        // so before the functions gets, also remove the changes that were made by that function

        maze[r][c] = true;  // backtracking
    }
    
    // All paths and print the matrix of that path

    static void allPathsNumbers(){
        boolean[][] board = { // false represent u can't go on that.
                {true, true, true},
                {true, true , true},
                {true, true, true}
        };
        int[][] path = new int[board.length][board[0].length];
        allPathNumbers("", board, 0, 0, path, 1);
    }
    static void allPathNumbers(String p, boolean[][] maze, int r, int c, int[][] path, int step){
        if(r == maze.length-1 && c == maze[0].length-1) {
            System.out.println("Path = "+p);
            path[r][c] = step;
            for (int[] nums : path) {
                System.out.println(Arrays.toString(nums));
            }
            System.out.println();
            return;
        }
        if(!maze[r][c])
            return;
        // I am considering this block in my path
        maze[r][c] = false;  // backtracking
        path[r][c] = step;
        if(r < maze.length-1)
            allPathNumbers(p+"D", maze,r+1, c, path, step+1);  // Down
        if(c < maze[0].length-1)
            allPathNumbers(p+"R", maze, r,c+1, path, step+1);  // Right
        if(r > 0)
            allPathNumbers(p+"U", maze,r-1, c, path, step+1); // Up
        if(c > 0)
            allPathNumbers(p+"L", maze, r,c-1, path, step+1); // Left

        // this line is where the function will be over
        // so before the functions gets, also remove the changes that were made by that function

        maze[r][c] = true;  // backtracking
        path[r][c] = 0;
    }




}







