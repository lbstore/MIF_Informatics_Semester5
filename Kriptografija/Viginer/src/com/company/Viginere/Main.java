package com.company.Viginere;

import java.io.UnsupportedEncodingException;
import java.util.Locale;

public class Main {
    Locale lithuanian = new Locale("lt_LT");

    public static void main(String[] args) throws UnsupportedEncodingException {
        String[] letters;
        letters = new String[32];
        letters[0] = "AĄBCČDEĘĖFGHIĮYJKLMNOPRSŠTUŲŪVZŽ";
        letters[1] = "ĄBCČDEĘĖFGHIĮYJKLMNOPRSŠTUŲŪVZŽA";
        letters[2] = "BCČDEĘĖFGHIĮYJKLMNOPRSŠTUŲŪVZŽAĄ";
        letters[3] = "CČDEĘĖFGHIĮYJKLMNOPRSŠTUŲŪVZŽAĄB";
        letters[4] = "ČDEĘĖFGHIĮYJKLMNOPRSŠTUŲŪVZŽAĄBC";
        letters[5] = "DEĘĖFGHIĮYJKLMNOPRSŠTUŲŪVZŽAĄBCČ";
        letters[6] = "EĘĖFGHIĮYJKLMNOPRSŠTUŲŪVZŽAĄBCČD";
        letters[7] = "ĘĖFGHIĮYJKLMNOPRSŠTUŲŪVZŽAĄBCČDE";
        letters[8] = "ĖFGHIĮYJKLMNOPRSŠTUŲŪVZŽAĄBCČDEĘ";
        letters[9] = "FGHIĮYJKLMNOPRSŠTUŲŪVZŽAĄBCČDEĘĖ";
        letters[10] = "GHIĮYJKLMNOPRSŠTUŲŪVZŽAĄBCČDEĘĖF";
        letters[11] = "HIĮYJKLMNOPRSŠTUŲŪVZŽAĄBCČDEĘĖFG";
        letters[12] = "IĮYJKLMNOPRSŠTUŲŪVZŽAĄBCČDEĘĖFGH";
        letters[13] = "ĮYJKLMNOPRSŠTUŲŪVZŽAĄBCČDEĘĖFGHI";
        letters[14] = "YJKLMNOPRSŠTUŲŪVZŽAĄBCČDEĘĖFGHIĮ";
        letters[15] = "JKLMNOPRSŠTUŲŪVZŽAĄBCČDEĘĖFGHIĮY";
        letters[16] = "KLMNOPRSŠTUŲŪVZŽAĄBCČDEĘĖFGHIĮYJ";
        letters[17] = "LMNOPRSŠTUŲŪVZŽAĄBCČDEĘĖFGHIĮYJK";
        letters[18] = "MNOPRSŠTUŲŪVZŽAĄBCČDEĘĖFGHIĮYJKL";
        letters[19] = "NOPRSŠTUŲŪVZŽAĄBCČDEĘĖFGHIĮYJKLM";
        letters[20] = "OPRSŠTUŲŪVZŽAĄBCČDEĘĖFGHIĮYJKLMN";
        letters[21] = "PRSŠTUŲŪVZŽAĄBCČDEĘĖFGHIĮYJKLMNO";
        letters[22] = "RSŠTUŲŪVZŽAĄBCČDEĘĖFGHIĮYJKLMNOP";
        letters[23] = "SŠTUŲŪVZŽAĄBCČDEĘĖFGHIĮYJKLMNOPR";
        letters[24] = "ŠTUŲŪVZŽAĄBCČDEĘĖFGHIĮYJKLMNOPRS";
        letters[25] = "TUŲŪVZŽAĄBCČDEĘĖFGHIĮYJKLMNOPRSŠ";
        letters[26] = "UŲŪVZŽAĄBCČDEĘĖFGHIĮYJKLMNOPRSŠT";
        letters[27] = "ŲŪVZŽAĄBCČDEĘĖFGHIĮYJKLMNOPRSŠTU";
        letters[28] = "ŪVZŽAĄBCČDEĘĖFGHIĮYJKLMNOPRSŠTUŲ";
        letters[29] = "VZŽAĄBCČDEĘĖFGHIĮYJKLMNOPRSŠTUŲŪ";
        letters[30] = "ZŽAĄBCČDEĘĖFGHIĮYJKLMNOPRSŠTUŲŪV";
        letters[31] = "ŽAĄBCČDEĘĖFGHIĮYJKLMNOPRSŠTUŲŪVZ";

        /*String text = "ĮIMCUĮFĘHNNĄĄŠEŽBLUAEIVHŪDMKIŪSŲŠNTŽOŠHNUĄHYYARSKŪ" +
                "MYPĖVCŽŽKCKŠĄMAĮKĘEPIVIAV" +
                "TKSJYEFIHĮMŪIĖYCKMNUIVŠUF" +
                "IGFCRTLKSFEGŠSYLODOČNMTVI" +
                "GFIMNNKĘBMEDMODUKĖELZMCRH" +
                "OŪUĖAYIIŠBALKMĖTBLUACŽEKL" +
                "CEETŪFLIČIHODEČOINŠIČAGĘA" +
                "EHAKOŠKFĘRFIAŽRINŠIĖAKGMM" +
                "EAFŠŲĄŠUĮŠĄEDAŲFIAČUTVCVŽ" +
                "EĘFITSIŠMBEKIĮZCŲŽNKZĄMČN" +
                "ŠĮAŠPIGCGUEŲŪVBĖĘIĮVĄVNUĄ" +
                "FŽMYPĮFŲAĘSŲFĘSTNKHŽUGETV" +
                "VŽŽNCSCŲFIVPCŲŽNŪSVMEIMŽI" +
                "ŽYALYEUGETYŠČVĖELISŽUFFŽM" +
                "EISFŪMČNHIIŠYJMBMKĮOGFMVN" +
                "PSNIŽYBSYŠUEAC";

        String text = "KTGMKVĖDJPIFKOČVSFVMORRSG" +
                "ZOJŲIĖTĘCTMĘNGFOFYZABŽCLĮ" +
                "HFUHYŽHRAKCKĮFKHYJAKKCGDY" +
                "LSVKŽRSVHYFANISFĮHĮEĖCĮHC" +
                "IĖLMŪONLĮĮTVNŲCĮHIŲELZŪII" +
                "FHŽDCMVGMYOSHĖDONIYTNLLTS" +
                "ŪTFNIISŠAKTSYĘFVTCGCĖBĖFV" +
                "KALVVĮMYBAGĄTYOKNGNFNAŽEN" +
                "HĮEKOŽGFEFYHURAFYYĖPIHSMG" +
                "VDCFĮFYAGVMPVPYĘJACJHHMUK" +
                "SĘEĮDBOVĘKFCDCUDCVOKVIHĮJ" +
                "HĖZOOTDĮDĘDJHIGOKEKŠDIYAH" +
                "ĘĮĘLIĮŪRACJMITZVLTCIAŠLSC" +
                "RJPIVĘKFCDCUDCVBLOĮFNOOCS" +
                "ĖNIGCĮAJPĮHRAŲUHKĮNUAĖĄĘE" +
                "LPHTPTŪIHENĖYATCTYĮAŪGŽZV" +
                "LŽEĮĘSSCĖŽAOTCČYDVNVKU";

        //roko
        String text = "YNĄPĮEKVIŪĄFEFLYIĘRZCSNŪP" +
                "ŲHSCFAYKKCLZIJAESLIMĄHOYO" +
                "TVPFŽČĘURĄJYĘCTŲPKISGĮĄHB" +
                "ČCFMŠIĮGĮLYEEBĮĄSDFCEŽDKD" +
                "FĄĮOYKDMDĮSSĮŲŠJĮNCVFZŲFG" +
                "LYECĄHUĮVDRFDEFLKLMBŽVJDZ" +
                "YEAKLJFPĘUGŪDŽĄGHSCFBFŲTC" +
                "HŪŠZŪKTĖTDFIYŪDFVVĮKHŪĄCŲ" +
                "HNCGMĮMRHSGZĮIROĖŽGMĄŲYYC" +
                "KMGJEGKTFPĘUGŪTFĄŪNHYGIĄE" +
                "OOSZŠĖRLCŪRŠUŪMCFMIGIYFVM" +
                "OČGHSCFE";
        */
        String text ="LOĄCOYĄAUSHCĄAEJŲJOATNĘCH" +
                "SĮČNCĄCĄTĘDVĖYYVLCĘYDRVFZ" +
                "ĄHŠSEMSHJKEOVČCDSOKGŪTAAY" +
                "RITIPYĮDŽYVVĄŲĮEGRĄLBŽGĖU" +
                "VMPDVKLPŲŪŠĮŽDMPŠACLĮGFAĄ" +
                "CTVPANFĮĮŲAEJTCIORJGJŽALT" +
                "AJŪIGTCŲJŠZYSOPHĄUSDFBZĖĮ" +
                "DEYKCVŽŪURŠNHIHRMGNŽDGEUT" +
                "ŽŠZŠAZZČZBŲZNMYBĘSĘFĘBNĄN" +
                "MNAŽLNZĘNDNŽMŠĘJNHĘŠZŲJŪJ" +
                "ĮZLJĄCVPLAĮČEFĘMĘPAVATŪEA" +
                "ŲMDJYMŲGLBYHPATĘYVĄGTŠHLK" +
                "ŽTVHĘLLPOEZHEHOŽGVAPOKRNB" +
                "ŲZZNĄČCPSNDKCJĄTĘKRĖYMNAĮ" +
                "TUDVATRALĮTDBŠĄĮJZSYĮMFOĘ" +
                "UŽMAĮVYMEMTKUIŠMKFO";


        String key = "BALSAS";
        int length = text.length();
        System.out.println(length);
        int keyLength = key.length();
        int times;
        String fullKey = new String();
        /*
        if(length % keyLength == 0) {
            System.out.println("IF WAS TRUE");
            times = length/keyLength;
            for (int i = 0; i < times; i++){
                fullKey = fullKey + key;
            }

        }
        else {
            System.out.println("IF WAS FALSE");
            int modulus = length % keyLength;
            int integer = length/keyLength;
            for (int i = 0; i < integer; i++){
                fullKey = fullKey + key;
            }
            for (int i = 0; i < modulus; i++){
                fullKey = fullKey + key.charAt(i);
            }
        }
        System.out.println(fullKey);
*/
        if(length % keyLength == 0) {
            System.out.println("IF WAS TRUE");
            times = length/keyLength;
            for (int i = 0; i < times; i++){
                fullKey = fullKey + key;
            }

        }
        else {
            System.out.println("IF WAS FALSE");
            int modulus = length % keyLength;
            System.out.println(modulus);
            int integer = length/keyLength;
            System.out.println(integer);
            int size = 0;
            for (int i = 0; i < integer; i++){
                fullKey = fullKey + key + text;
                size++;
            }
            System.out.println(size);
            for (int i = 0; i < modulus; i++){
                fullKey = fullKey + text.charAt(size);
                size ++;
            }
        }

        //System.out.println(fullKey);




        for (int j = 0; j < length; j++){
            for (int i = 0; i < 32; i++) {
                if (letters[i].startsWith(String.valueOf(fullKey.charAt(j)))){
                    int place = letters[i].indexOf(text.charAt(j));
                    System.out.print(letters[0].charAt(place));
                }
            }
        }
    }
}
