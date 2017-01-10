package com.company;



/*public class Main {
    static class MyResult {
        private int first;
        private int second;

        public MyResult(int first, int second) {
            this.first = first;
            this.second = second;
        }

        public int getFirst() {
            return first;
        }

        public int getSecond() {
            return second;
        }
    };

    static int [] k = {161, 245, 150};
    static int c[][] = {{41, 190}, {84, 237}, {51, 165}, {82, 253}, {39, 163}, {79, 240}, {54, 178}, {83, 232}, {52, 171},
            {93, 243}, {37, 169}, {75, 244}, {60, 172}, {75, 237}, {34, 166}, {68, 239}, {63, 172}, {94, 251}, {59, 174},
            {88, 252}, {38, 178}, {80, 224}, {55, 161}, {68, 226}, {40, 171}, {93, 255}, {60, 188}, {93, 253}, {36, 182}, 
            {70, 241}, {36, 189}, {89, 249}, {47, 174}, {64, 238}, {33, 161}, {81, 226}, {56, 160}, {87, 227}, {58, 172}, 
            {92, 231}, {33, 164}, {69, 224}, {41, 174}};

    static int []iv = {91, 241};

    static int f(int m, int k) {
        return  (m&k)^((k%16)|m);
    }

    static MyResult feistel(int a, int b){
        int L0 = a;
        int R0 = b;
        int L1 = R0;
        int R1 = (L0 ^ f(R0, k[0]));
        int L2 = R1;
        int R2 = (L1 ^ f(R1, k[1]));
        int L3 = R2;
        int R3 = (L2 ^ f(R2,k[2]));
        int L4 = R3;
        int R4 = L3;
        a = L4;
        b = R4;
        return new MyResult(a, b);
    }

    public static void main(String[] args) {
        String result = "";
        /*for (int i = 0; i < 64; i++) {
            int m = i;
            int key = k[0];
            int cr = f(m,key);
            MyResult mr = feistel(cr,cr);
            int a = mr.getFirst() ^ c[i][0];
            int b = mr.getSecond() ^ c[i][1];
            result = result + String.valueOf((char) a) + String.valueOf((char) b);
        }*/
       /* int answer[][] = new int[43][2];
        //String result = "";
        for (int i = 0; i < 43; i++) {
            int a =answer[i][0]^c[i][0];
            int b = answer[i][1]^c[i][1];
            result = result + String.valueOf((char) a) + String.valueOf((char) b);
            iv[0] = c[i][0];
            iv[1] = c[i][1];
        }
        System.out.println(result);

    }
}
*/
//package com.company;
public class Main {

    static int [] k = {101, 20, 215};
    static int c[][] = {{139, 190}, {100, 184}, {162, 85}, {195, 237}, {41, 165}, {243, 20}, {132, 231}, {109, 254}, {160, 6},
            {195, 179}, {49, 254}, {236, 94}, {132, 190}, {125, 167}, {189, 68}, {222, 239}, {45, 187}, {229, 26}, {155, 228},
            {120, 231}, {161, 14}, {210, 167}, {32, 254}, {232, 86}, {147, 171}, {121, 174}, {188, 70}, {212, 230}, {53, 178},
            {237, 16}, {134, 225}, {99, 234}, {170, 0}, {202, 162}, {58, 232}, {224, 76}, {138, 160}, {105, 167}, {173, 70},
            {197, 255}, {62, 177}, {244, 2}, {158, 231}, {107, 234}, {162, 1}, {213, 164}, {53, 232}, {228, 70}, {138, 165},
            {113, 177}, {185, 83}, {195, 244}, {35, 168}, {234, 15}, {143, 228}, {118, 233}, {168, 18}, {192, 168}, {59, 249},
            {254, 73}, {132, 186}, {120, 160}, {180, 73}, {215, 233}, {43, 163}, {243, 8}, {156, 242}, {110, 244}, {176, 14},
            {204, 180}, {54, 247}, {225, 91}, {151, 177}, {99, 189}, {191, 87}, {217, 249}, {62, 163}, {255, 3}, {129, 235},
            {113, 226}, {163, 3}, {195, 169}, {44, 243}, {241, 73}, {139, 183}, {109, 185}, {185, 80}, {222, 252}, {42, 182},
            {226, 28}, {136, 238}, {96, 230}, {170, 4}, {209, 183}, {59, 243}, {229, 95}, {133, 182}, {114, 180}, {179, 84},
            {192, 252}, {63, 165}, {225, 30}, {148, 254}, {93, 151}};

    static int []iv = {248, 73};

    static int f(int m, int k) {
        return (m ^ k) & ((k/16)|m);
    }
    static int f2(int m, int k) {
        return (m&k)|((k%16)^m);
    }

    public static void main(String[] args) {
        int answer[][] = new int[104][2];
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
            int a = answre[i][0] ^ iv[0];
            int b = answer[i][1] ^ iv[1];
            result = result + String.valueOf((char) a) + String.valueOf((char) b);
            iv[0] = c[i][0];
            iv[1] = c[i][1];
        }
        System.out.println(result);

    }
}
