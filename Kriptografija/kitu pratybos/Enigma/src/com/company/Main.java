package com.company;

public class Main {

    static String ABC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static int n = 26;
    static int k1 = 1;
    static int k2 = 11;

    static int[] L1={5, 3, 2, 0, 17, 10, 8, 24, 20, 11, 1, 12, 9, 22, 16, 6, 25, 4, 18, 21, 7, 13, 15, 23, 19, 14};
    static int[] L2 = {20, 3, 24, 18, 8, 5, 15, 4, 7, 11, 0, 13, 9, 22, 12, 23, 10, 1, 19, 21, 17, 16, 2, 25, 6, 14};
    static int[] s={2, 4, 0, 6, 1, 11, 3, 8, 7, 13, 16, 5, 15, 9, 18, 12, 10, 19, 14, 17, 25, 22, 21, 24, 23, 20};

    static String c = "PUEFFOEBVKDMXLNSSHIMPFLQV"+
"TMOVWIRSBLITOCQKLMLJTNBKT"+
"TGYBHPRVJQAFNXMCODWAGVBHL"+
"FZKEVBHHKUXWKMERFITZGDKYT"+
"XJUUIHANJIEGIMRVIIFPXXMOB"+
"WDIRJGFTVUDEMZEVAAZGHHKGU"+
"MZIADUXDGHPBQOSGBCJADXMRL"+
"SLKKYZAWISSUGIONQEWMWSMWA"+
"EHQNBUVKLUXNPLJKGTSNZQJFQ"+
"GVJYZIQJAMTAODECZJFRIFUWZ"+
"WSMXOPKMDFGBFKEZWSYIHPSZX"+
"JUOFJMVLGGYKRGCQBOYRKYZCY"+
"XSTHEBHACVJINXVXEWNJDZQZH"+
"YIDCWLXKSHIZW";


    static char EnigmaDecode(int ci, int i){
        int m1 = i % n;
        int m2 = i / n;
        int ro1 = (ci + m2+k2) % n;
        int lem2 = 0;
        for (int j = 0; j < 26; j++){
            if (L2[j] == ro1)
                lem2 = j;
        }
        int m2k2negative = -m2 -k2;
        int m2k2positive = m2 + k2;
        int ro2 = (lem2 + m2k2negative) % n;
        if (ro2 < 0)
            ro2 = ((lem2%n)+n-(m2k2positive % n))%n;
        int ro3 = (ro2+m1+k1)%n;
        int lem1 = 0;
        for(int j = 0; j < 26; j++){
            if (L1[j] == ro3)
                lem1 = j;
        }
        int m1k1negative = -m1-k1;
        int m1k1positive =  m1+k1;
        int ro4 = (lem1 + m1k1negative)%n;
        if (ro4 < 0)
            ro4 = ((lem1 % n) + n - (m1k1positive % n))%n;
        return ABC.charAt(ro4);
    }


    static char EnigmaDecodeK1(int ci, int i, int k22){
        int m1 = i % n;
        int m2 = i / n;
        int ro1 = (ci + m2+k22) % n;
        int lem2 = 0;
        for (int j = 0; j < 26; j++){
            if (L2[j] == ro1)
                lem2 = j;
        }
        int m2k2negative = -m2 -k22;
        int m2k2positive = m2 + k22;
        int ro2 = (lem2 + m2k2negative) % n;
        if (ro2 < 0)
            ro2 = ((lem2%n)+n-(m2k2positive % n))%n;
        int ro3 = (ro2+m1+k1)%n;
        int lem1 = 0;
        for(int j = 0; j < 26; j++){
            if (L1[j] == ro3)
                lem1 = j;
        }
        int m1k1negative = -m1-k1;
        int m1k1positive =  m1+k1;
        int ro4 = (lem1 + m1k1negative)%n;
        if (ro4 < 0)
            ro4 = ((lem1 % n) + n - (m1k1positive % n))%n;
        return ABC.charAt(ro4);
    }

    static char EnigmaDecodeReflection(int ci, int i){
        int m1 = i % n;
        int m2 = i / n;
        int ro1 = (ci + m1+k1) % n;
        int lem1 = 0;
        for (int j = 0; j < 26; j++){
            if (L1[j] == ro1)
                lem1 = L1[j];
        }
        int m1k1negative = -m1 -k1;
        int m1k1positive = m1 + k1;
        int ro2 = (lem1 + m1k1negative) % n;
        if (ro2 < 0)
            ro2 = ((lem1%n)+n-(m1k1positive % n))%n;
        int ro3 = (ro2+m2+k2)%n;
        int lem2 = 0;
        for(int j = 0; j < 26; j++){
            if (L2[j] == ro3)
                lem2 = L2[j];
        }
        int m2k2negative = -m2-k2;
        int m2k2positive =  m2+k2;
        int ro4 = (lem2 + m2k2negative)%n;
        if (ro4 < 0)
            ro4 = ((lem2 % n) + n - (m2k2positive % n))%n;
        //System.out.println(ro4);
        return EnigmaDecode(ro4, i);
    }

    public static void main(String[] args) {
        String answer = "";
        String answer2 = "";
        int cLength = c.length();
        for(int i = 0; i < cLength; i++) {
           for (int j = 0; j < 26; j++) {
               if (ABC.charAt(j) == c.charAt(i)) {
                   answer = answer + EnigmaDecode(j, i);
               }
           }
        }
        for(int k = 0; k < 26; k++) {
            for (int i = 0; i < cLength; i++) {
                for (int j = 0; j < 26; j++) {
                    if (ABC.charAt(j) == c.charAt(i)) {
                        answer2 = answer2 + EnigmaDecodeK1(j, i, k);
                    }
                }
            }
            if(answer2.charAt(0) == 'T')
                System.out.println("k: "+k+" "+ answer2);

            answer2 = "";
        }
        //System.out.println(answer);
    }
}
