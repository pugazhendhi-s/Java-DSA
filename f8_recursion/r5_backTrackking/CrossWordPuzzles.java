package com.f8_recursion.r5_backTrackking;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class CrossWordPuzzles {
    public static void main(String[] args) {
        char[][] board = {
                {'+', '-', '+', '+', '+' , '+', '+', '+', '+', '+'},
                {'+', '-', '+', '+', '+' , '+', '+', '+', '+', '+'},
                {'+', '-', '+', '+', '+' , '+', '+', '+', '+', '+'},
                {'+', '-', '-', '-', '-' , '-', '+', '+', '+', '+'},
                {'+', '-', '+', '+', '+' , '-', '+', '+', '+', '+'},
                {'+', '-', '+', '+', '+' , '-', '+', '+', '+', '+'},
                {'+', '+', '+', '+', '+' , '-', '+', '+', '+', '+'},
                {'+', '+', '-', '-', '-' , '-', '-', '-', '+', '+'},
                {'+', '+', '+', '+', '+' , '-', '+', '+', '+', '+'},
                {'+', '+', '+', '+', '+' , '-', '+', '+', '+', '+'},
        };
        String sentence = "LONDON;DELHI;ICELAND;ANKARA";
        String[] words = sentence.split(";");
        solve(board, words, 0);
        /*
         char[][] board = new char[10][10];
        for (int row = 0; row < board.length; row++) {
            String word = sc.next();
            board[row] = word.toCharArray();
        }
        int n = sc.nextInt();
        String[] words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = sc.next();
        }
        solve(board, words, 0);
         */
    }
    private static void display(char[][] board){
        for (char[] row : board) {
            for (char col : row) {
                System.out.print(col+" ");
            }
            System.out.println();
        }
    }
    private static void solve(char[][] board, String[] words, int wdIndex) {
        if (wdIndex == words.length){
            display(board);
            return;
        }
        String word = words[wdIndex];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                if(board[row][col] == '-' || board[row][col] == word.charAt(0)){
                    if(canPlaceWordHorizontally(board, word, row, col)){
                        boolean[] placed = placeWordHorizontally(board, word, row, col);
                        solve(board, words, wdIndex+1);
                        unplaceWordHorizontally(board, placed, row, col);
                    }
                    if(canPlaceWordVertically(board, word, row, col)){
                        boolean[] placed = placeWordVertically(board, word, row, col);
                        solve(board, words, wdIndex+1);
                        unplaceWordVertically(board, placed, row, col);
                    }
                }
            }
        }
    }

    private static boolean canPlaceWordHorizontally(char[][] board, String word, int row, int col) {
        if(col-1 >= 0 && board[row][col-1] != '+')
            return false; // before the word start, char must be '+' and after word ,'+' must be present
        else if(col + word.length() < board[0].length && board[row][col + word.length()] != '+')
            return false;
        for (int c = 0; c < word.length(); c++) {
            if(c + col >= board.length)
                return false; // word is larger
            if(board[row][col + c] != '-' && board[row][col + c] != word.charAt(c))
                return false;
        }
        return true;
    }
    private static boolean[] placeWordHorizontally(char[][] board, String word, int row, int col) {
        boolean[] placed = new boolean[word.length()];
        for (int c = 0; c < word.length(); c++) {
            if(board[row][col + c] == '-'){
                board[row][col + c] = word.charAt(c);
                placed[c] = true;
            }
        }
        return placed;
    }
    private static void unplaceWordHorizontally(char[][] board, boolean[] placed, int row, int col) {
        for (int c = 0; c < placed.length; c++) {
            if (placed[c])
                board[row][col + c] = '-';
        }
    }

    // Vertical
    private static boolean canPlaceWordVertically(char[][] board, String word, int row, int col) {
        if(row-1 >= 0 && board[row-1][col] != '+')
            return false; // before the word start, char must be '+' and till word length, there must be no '+'
        else if(row + word.length() < board.length && board[row + word.length()][col] != '+')
            return false;
        for (int r = 0; r < word.length(); r++) {
            if(r + row >= board.length)
                return false; // word is larger
            if(board[row + r][col] != '-' && board[row + r][col] != word.charAt(r))
                return false; // char must be '-' or word.char(r), else false
        }
        return true;
    }
    private static boolean[] placeWordVertically(char[][] board, String word, int row, int col) {
        boolean[] placed = new boolean[word.length()];
        for (int r = 0; r < word.length(); r++) {
            if(board[row + r][col] == '-' && !placed[r]){
                board[row + r][col] = word.charAt(r);
                placed[r] = true;
            }
        }
        return placed;
    }
    private static void unplaceWordVertically(char[][] board, boolean[] placed, int row, int col) {
        for (int r = 0; r < placed.length; r++) {
            if (placed[r])
                board[row + r][col] = '-';
        }
    }

    static class HackerRank{
        private static final Scanner sc = new Scanner(System.in);
        private static final List<String> list = new ArrayList<>();

        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            List<String> crossword = IntStream.range(0, 10).mapToObj(i -> {
                        try {
                            return bufferedReader.readLine();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    })
                    .collect(toList());

            String words = bufferedReader.readLine();

            List<String> result = crosswordPuzzle(crossword, words);

            bufferedWriter.write(
                    String.join("\n", result)
                            + "\n"
            );

            bufferedReader.close();
            bufferedWriter.close();
        }

        public static List<String> crosswordPuzzle(List<String> crossword, String sentence) {

            char[][] board = new char[10][10];
            for(int i=0; i < crossword.size(); i++){
                board[i] = crossword.get(i).toCharArray();
            }
            String[] words = sentence.split(";");
            solve(board, words, 0);
            return list;
        }
        public static void append(char[][] board){
            for (char[] row : board) {
                StringBuilder sb = new StringBuilder();
                for (char col : row) {
                    sb.append(col);
                }
                list.add(sb.toString());
            }
        }
        private static void solve(char[][] board, String[] words, int wdIndex) {
            if (wdIndex == words.length){
                append(board);
                return;
            }
            String word = words[wdIndex];
            for (int row = 0; row < board.length; row++) {
                for (int col = 0; col < board.length; col++) {
                    if(board[row][col] == '-' || board[row][col] == word.charAt(0)){
                        if(canPlaceWordHorizontally(board, word, row, col)){
                            boolean[] placed = placeWordHorizontally(board, word, row, col);
                            solve(board, words, wdIndex+1);
                            unplaceWordHorizontally(board, placed, row, col);
                        }
                        if(canPlaceWordVertically(board, word, row, col)){
                            boolean[] placed = placeWordVertically(board, word, row, col);
                            solve(board, words, wdIndex+1);
                            unplaceWordVertically(board, placed, row, col);
                        }
                    }
                }
            }
        }
        // Horizontal
        private static boolean canPlaceWordHorizontally(char[][] board, String word, int row, int col) {
            if(col-1 >= 0 && board[row][col-1] != '+')
                return false; // before the word start, char must be '+' and till word length, there must be no '+'
            else if(col + word.length() < board[0].length && board[row][col + word.length()] != '+')
                return false;
            for (int c = 0; c < word.length(); c++) {
                if(c + col >= board.length)
                    return false; // word is larger
                if(board[row][col + c] != '-' && board[row][col + c] != word.charAt(c))
                    return false;
            }
            return true;
        }
        private static boolean[] placeWordHorizontally(char[][] board, String word, int row, int col) {
            boolean[] placed = new boolean[word.length()];
            for (int c = 0; c < word.length(); c++) {
                if(board[row][col + c] == '-' && !placed[c]){
                    board[row][col + c] = word.charAt(c);
                    placed[c] = true;
                }
            }
            return placed;
        }
        private static void unplaceWordHorizontally(char[][] board, boolean[] placed, int row, int col) {
            for (int c = 0; c < placed.length; c++) {
                if (placed[c])
                    board[row][col + c] = '-';
            }
        }

        // Vertical
        private static boolean canPlaceWordVertically(char[][] board, String word, int row, int col) {
            if(row-1 >= 0 && board[row-1][col] != '+')
                return false; // before the word start, char must be '+' and till word length, there must be no '+'
            else if(row + word.length() < board.length && board[row + word.length()][col] != '+')
                return false;
            for (int r = 0; r < word.length(); r++) {
                if(r + row >= board.length)
                    return false; // word is larger
                if(board[row + r][col] != '-' && board[row + r][col] != word.charAt(r))
                    return false; // char must be '-' or word.char(r), else false
            }
            return true;
        }
        private static boolean[] placeWordVertically(char[][] board, String word, int row, int col) {
            boolean[] placed = new boolean[word.length()];
            for (int r = 0; r < word.length(); r++) {
                if(board[row + r][col] == '-' && !placed[r]){
                    board[row + r][col] = word.charAt(r);
                    placed[r] = true;
                }
            }
            return placed;
        }
        private static void unplaceWordVertically(char[][] board, boolean[] placed, int row, int col) {
            for (int r = 0; r < placed.length; r++) {
                if (placed[r])
                    board[row + r][col] = '-';
            }
        }
    }
}
