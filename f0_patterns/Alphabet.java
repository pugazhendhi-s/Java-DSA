package com.f0_patterns;

public class Alphabet {

    public static void main() {
        gateWay();
    }
    static void gateWay(){
        /*
        ABCDEFEDCBA
        ABCDE EDCBA
        ABCD   DCBA
        ABC     CBA
        AB       BA
        A         A
        */
        int n = 6;
        for (int row = 0; row < n; row++) {
            int totCol = n-1-row;
            int totSpace = n-1 - totCol;
            for (int col = 0; col <= totCol; col++) {
                System.out.print((char)('A'+ col));
            }
            for (int sp = 0; sp < totSpace; sp++) {
                System.out.print("  ");
            }
            for (int col = 0; col <= totCol; col++) {
                if(row == 0){
                    int ch = 'A'+n-1-col;
                    System.out.print((char)(ch));
                }
                else {
                    int ch = 'A' + n-1 - (row + col);
                    System.out.print((char)(ch));
                }
            }
            System.out.println();
        }
    }
    static void verticalHalfDiamond(){
        /*
         * A
         * B D
         * C F I
         * D H L P
         * E J O T Y
         * F L R X
         * G N U
         * H P
         * I
         */
        int size = 5;
        int n = 2*size;
        for (int row = 0; row < n; row++) {
            int totCol = row < size ? row : n - row -2;
            int ch = row+'A';
            for (int col = 0; col <= totCol; col++) {
                System.out.print((char)(ch)+" ");
                ch += row+1;
            }
            System.out.println();
        }
    }
    static void reverseVerticalHard() {
        /*
         * A
         * B I
         * C H J
         * D G K N
         * E F L M O
         */
        int n = 5; int memory = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col <= row; col++) {
                if(col % 2 == 0) {
                    int totArea = col * n + row;
                    int skippedArea = col * (col -1)/2;
                    int ch = totArea - skippedArea - col;
                    System.out.print((char)(ch + 65)+" ");
                }
                else {
                    int totArea = col * n + n - row - 1;
                    int skippedArea = col * (col -1)/2;
                    int ch = totArea - skippedArea;
                    System.out.print((char)(ch + 65) + " ");
                }
            }
            System.out.println();
        }
    }
    static void reverseVertical(){
        /*
         * A J K T U
         * B I L S V
         * C H M R W
         * D G N Q X
         * E F O P Y
         */
        int n = 5;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if(col % 2 == 0) {
                    int ch = col * n + row;
                    System.out.print((char)(ch + 65)+" ");
                }
                else {
                    int ch = col * n + n - row - 1;
                    System.out.print((char) (ch + 65) + " ");
                }
            }
            System.out.println();
        }
    }
    static void alphaNum(){
        int n = 5;
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= n; col++) {
                if(col <= row)
                    System.out.print((row) +" ");
                else
                    System.out.print( (char)(('a'- 1) + row) +" ");
            }
            System.out.println();
        }
    }
}
