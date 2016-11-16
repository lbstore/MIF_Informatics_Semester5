package com.company;

public class Main {

    static int [] k = {214, 12, 214};
    static int c[][] = {{87, 16}, {77, 5}, {89, 15}, {95, 9}, {95, 9}, {79, 1}, {79, 6}, {65, 11}, {80, 19}, {76, 31}, 
            {73, 19}, {75, 1}, {75, 7}, {95, 30}, {73, 19}, {83, 28}, {81, 24}, {89, 25}, {75, 3}, {75, 3}, {88, 24}, 
            {78, 27}, {65, 3}, {91, 30}, {65, 3}, {73, 27}, {79, 24}, {85, 28}, {79, 4}, {79, 13}, {85, 24}, {79, 4}, 
            {87, 25}, {64, 27}, {75, 3}, {89, 15}, {75, 3}, {81, 25}, {83, 24}, {86, 25}, {70, 15}, {73, 19}, {95, 14}, 
            {95, 24}, {69, 0}, {83, 28}, {85, 25}, {95, 28}, {78, 3}, {85, 24}, {64, 30}, {69, 3}, {90, 3}, {78, 27}, 
            {88, 19}, {73, 31}};

    static int []iv = {248, 73};

    static int f(int m, int k) {
        return (m ^ k) & ((k/16)|m);
    }
    static int f2(int m, int k) {
       return (m&k)|((k%16)^m);
    }

    public static void main(String[] args) {
        int answer[][] = new int[46][2];
        String result2 = "";
        for (int i = 0; i < 104; i++) {
            int L0 = c[i][0];
            int R0 = c[i][1];
            int L1 = R0;
            int R1 = (L0 ^ f(R0, k[2]));
            int L2 = R1;
            int R2 = (L1 ^ f(R1, k[1]));
            int L3 = R2;
            int R3 = (L2 ^ f(R2,k[0]));
            int L4 = R3;
            int R4 = L3;
            answer[i][0] = L4;
            answer[i][1] = R4;
        }
        String result = "";
        for (int i = 0; i < 104; i++) {
            result = result + String.valueOf((char) answer[i][0]) + String.valueOf((char) answer[i][1]);
        }
        System.out.println(result);

    }
}
