package com.company;

public class Main {

    static String toBinary(int i){
        String binaryString = Integer.toBinaryString(i);
        while(binaryString.length() <= 7){
            binaryString = "0" + binaryString.substring(0, binaryString.length());
        }
        //System.out.println(binaryString);
        return binaryString;
    }

//-----------------------------------------------------------------------------------------------


    static int[] convertFromStringToInt(String t){
        int a[] = new int[8];
        for (int index = t.indexOf('0'); index >= 0; index = t.indexOf('0', index + 1))
        {
            a[index] = 0;
        }
        for (int index = t.indexOf('1'); index >= 0; index = t.indexOf('1', index + 1)){
            a[index] = 1;
        }
        return a;
    }

    static int convertToInt(int [] bitArray){
        int tmp = 0;
        for(int i = 0; i < 8; i++){
            tmp += bitArray[i]*Math.pow(2,7-i);
        }
        return tmp;
    }


//-----------------------------------------------------------------------------------------------

    static int oneInt(int[] k, int[] c){
        int kNew [] = new int[8];
        int k2[] = new int[16];
        for (int i = 0; i < 8; i++){
            k2[i] = k[i];
        }
        for (int i = 0; i < 8; i++){
            int p = (c[0]*k2[i+7] + c[1]*k2[i+6] + c[2]*k2[i+5] + c[3]*k2[i+4] + c[4]*k2[i+3] + c[5]*k2[i+2] + c[6]*k2[i+1] + c[7]*k2[i])%2;
            kNew[i] = p;
            k2[i+8] = p;
        }
        return convertToInt(kNew);
    }


    public static void main(String[] args) {
        String text = "OB";
        int cypher [] = {225, 171, 212, 115, 36, 100, 148, 159, 113, 109, 228, 54, 91, 247, 237, 65, 198, 229, 241, 26,
                49, 135, 221, 244, 198, 33, 162, 209, 87, 24, 243, 198, 28, 151, 82, 31, 154, 2, 226, 233, 43, 27, 15,
                135, 96};
        int K []= new int[cypher.length];
        String binaryK[] = new String[2];
        for (int i = 0; i < text.length(); i++){
            int tmp = (int) text.charAt(i);
            K[i] = tmp ^ cypher[i];
            binaryK[i] = toBinary(K[i]);
        }
        int realC = 0;
        for (int c = 0; c < 256 ;c++){
            int tmp;
            if (toBinary(c).charAt(7) == '0'){
                continue;
            }
            tmp = oneInt(convertFromStringToInt(toBinary(K[0])),convertFromStringToInt(toBinary(c)));
            if (tmp == K[1]){
                realC = c;
            }
        }
        System.out.print("OB");
        for (int i = 2; i < cypher.length; i++){
            K[i] = oneInt(convertFromStringToInt(toBinary(K[i-1])),convertFromStringToInt(toBinary(realC)));
            char a = (char) (K[i]^cypher[i]);
            System.out.print(a);
        }

    }
}
