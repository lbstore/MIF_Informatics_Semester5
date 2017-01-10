
from libs.crypt import IOlib
from libs.crypt import cipher
from libs.crypt.IOlib import InputFormatter, stringReplace
from libs.crypt.Math import Math, Matrix, inversematrix
from libs.crypt.lib import *


# string0 ='''DGIIV LTRIL POAUO GIARP SŽRSS
# ĄAJIA BIITT AŽEBR AĄUIS ALIPP
# ATLAP IEUON PRLRS VOAOĮ JIIJT
# VDĮAN AAARO PTUIR O'''
# string1 = '''NATTN AJTEI TSETA SDĄDO YLŽOR
# ŲUUKA ĖREIS DĄNVA RNADE JNNIŠ
# IEILN OIDOA DTUEJ IDEIT IJRIA
# IOMER AIKIJ TIAAD OKKDT UDĘKP
# RSKIE ĄLOTU IDĖRI JLIIA ARNSA
# GULTN PKEBA AKKNA DĘĖTD ĖRASE IAAIE I'''
# string2 = '''RABLĄ JRPYP UUAVI IPAIJ TVAML
# AKRGI RVLĖE IILKA SIEIA LOĖTĄ
# GIŠLA IBSSG MTUKA APOĖT SAIŽP
# IPKJS DEJPY TTSVE IGITU AAĖIS
# IENAA SIEMP GGOIK EAASA ŠORAM
# UĄTRĮ YA'''
# string3 = '''PIPYO DOUAK MORŽA ĖIOED ŠVRAS
# UEŽSV SUAKJ SŽTPŽ IAŲŠĮ EČEES
# VŽČBL TLNTĖ RIGOI SIŠAP NEVSP
# JMTYĖ ESAEK NPASV IULJO EISDM
# IUŽME AIDLA ETAOA ATIVI RIAĮV
# AAAEB RK'''
# string4 = '''LUATTUKSSUĖVORIS
# VGERALID IUODOIŽR
# PENINOEL EIKOŽVNI
# MNOUOKGA IVSNRIEE
# AEŠKIŠTO ŪTURKŲBN
# RŲETGŠIP AMASŠEJU
# '''
# string5 = '''
# VUHČŠ LATČL UEŠĖL ERŠBU RUHZU
# RČŠUČ UDĖŠV PRČOH URČHL UDŠRP
# YNČHI UZERH RLHUP GHLRH ČIČŠI
# RERPŠ RIURU IUĖOM LUŽĖP ČMPR'''
# string6='''ONCUŪ ŲAŽUĮ FUĮŪĮ ĘATŽĖ FĘĖBŽ
# ĮŽFVN OĘŠRT OŽCĘR TŽAŽT RĘBDĘ
# VNĮUR TŽAŽT RĘCFN HŪĘCĮ FPJŪZ
# RTŽHC FTŽŠŪ NUMND T'''
# string7='''LĄDĖĘ CŠRDŽ VZCDN RVŪTZ CĖĄŽL
# FOCŪD ĄDZŪĄ ĖZOTZ DMĄNŠ TAĄDL
# ŽZĖCN TEVZĄ AVJDC ZĘCŪC ZJDCŠ
# VEVĮD ŽŪSTE V'''




# fullList = None
# fullList = cyphers.Fence.bruteForce(preparedText,3,20,False)
# IOlib.printListToFile("output",fullList)


# d = open("dazninisLt","r")
# List = d.readlines()
# array = dictionaryExtract(List,cmp_to_key(InputFormatter.cmpByAlphabetStr(InputFormatter.alphabets.get("lt"))))
# IOlib.printListToFile("new",array)



#
# dictionary = IOlib.readListFromFile("dazninisLt")
# InputFormatter.addDictionary("LT",dictionary)
# print(dictionary)
# array = ArrayList()
# message = InputFormatter.removeByAlphabet(string7,"ltC")
# for a in range(0,32):
#     for b in range(0,32):
#         try:
#             result = cipher.Affine.decryptSingle(message,InputFormatter.alphabets.get("ltC"),a,b)
#             # print(result)
#             if InputFormatter.stringContainsWord(result,dictionary,5):
#                 array.append("alpha="+str(a)+" beta="+str(b)+": "+result)
#         except Exception:
#             pass
#
# for w in array:
#     print(w)


#
# string8='''ĄOŪŠP VĘŪYI HYSĖF GVNPT BPČČN
# ROKŠĄ ĄDDET MĘĖVN ĄDZNV FFPLU
# HLMMR ĖMPJF PUDVJ ŠHŽMK FGUVE
# RČČMŲ UEYKJ EFIGT '''
#
# string9='''
# TSOJE VSTMB LNVRT LNKEH LLAJV
# SMUEG KDVRV BVKCE LTVCT KMČRD
# ČTRUT LLIZE SLCSY TTATE ZZNAT
# LANNR KLAIČ MČMJČ TNBGZ ZBNYC
# '''

#  raktas=[1, 14, 19, 11]
# preparedText = InputFormatter.removeByAlphabet(string8,"ltC")
# arrayList = IOlib.generateIndexList(4,4)
# print(preparedText)
# print(InputFormatter.alphabets.get("ltC"))
# array = [1, 11, 14, 19]
# # array = [4, 5, 19, 23]
# # array = [0, 7, 9, 15]
# ar = ArrayList()
# for l in arrayList:
#     m2 = Matrix()
#     m2.addLine([array[l[0]],array[l[1]]])
#     m2.addLine([array[l[2]],array[l[3]]])
#
#     try:
#         m3 = Math.matrixModularInverse(m2,32)
#
#         ans = cipher.Hill.singleCypher(preparedText,m3,InputFormatter.alphabets.get("ltC"))
#         ar.append(ans)
#     except Exception as e:
#         print(str(e))
#
#
# for a in ar:
#     print(a)
# pre = InputFormatter.removeByAlphabet(string9,"slt")
# print(cipher.Bifid.decryptSingle(pre,"LAIKAS",InputFormatter.alphabets.get("slt"),5))
# setup ='''
# from libs.crypt.Math import Math, Matrix
# m = Matrix()
# m.addLine([2,3,23,1,5])
# m.addLine([10,2,5,1,6])
# m.addLine([3,6,9,3,7])
# m.addLine([15,11,1,6,8])
# m.addLine([15,11,1,6,9])
# '''
# print()
# print(timeit.timeit("Math.np.linalg.det(m.lines)",setup, number=100))
# print(timeit.timeit("Math.matrixDeterminant(m)",setup, number=100))
# print(cipher.Vigenere.crypt("ATTACKATDAWN","LEMON",InputFormatter.alphabets.get("en"),True))
# cipherText = "LXFOPVEFRNHR"
# print(cipher.Vigenere.crypt(cipherText,"LEMON",InputFormatter.alphabets.get("en"),False))

# print(cipher.Vigenere.crypt(InputFormatter.removeByAlphabet(string10,"ltC"),'LIESAS',InputFormatter.alphabets.get('ltC'),False))
# string10 ='''ĄBBIT VBOPS UGSCA RSCRM KŽŠOV
# MVSAC FŪAAO KDMSV MVČDU ĮESĄJ
# MEAČH HVKOE HCMSS YHDIF AYČMŪ
# SMCLŠ SSUĘV IAĘAR VŽRHĮ KSZŲC
# MSĖDE CPŪVL YEOED RKSLC LEVCA
# CCITI AYVLE ĮĖĄĘI VCTLF KMSEĮ
# RFYLŽ GHREĮ OYČEŽ SRCLE RHVHG
# ŠVLTL DĄŪCS CGBEL KCLER ŽDZEB
# MVLVČ DOGŲI LCMŽM CLEMK IGŲMŪ
# SIFLŽ DCNHC ĮEGTĮ MŲNKO FMCIG
# OYŪŠA ŪŽVĖA MĮEĄB ŠUFOG DRŪSF
# CAAVY OĖVIŪ COCĖD UĮIEL ŠŪŽMC
# LEPČV SĘDOK ISGBY FĘYZŪ IĖĮYG
# AŪŠSK LŽHRI ŠČEKS RŽBN'''

def t32():
    string11 = '''UEBĄI ĮYĘIE HOŲSR VILMM CMŪSĘ
    ĖIĘTI PŽŠĄB CĖEEL BŪJŪU ĖRVHE
    ŠHHKH IŽĄDH IUŪLL SĖKŠĖ ĄDĖUC
    ČMTJC AĄŠVD ŲIRFV ČUŲŠC IHMCG
    ĖFĄEE EBCŲP ĘRFCI AŽĄŠS JVŠLC
    ŪVDMĖ ČCYEŠ EĄĮFC ŠJAĮĄ ZŪLŠL
    OVŠNI ĮĄRIR IOŪVD ĘŠFYŪ ĖĄDVE
    YŲVZS ĄVŠFC CMELL FSJMČ ĖIOFV
    EČŪSJ VRVBĄ OHIČK CFEĄY IĖDEV
    DCCPA BLCOŲ MLĘAŪ ĄVCEŠ GYUCY
    ŠŪJPM LDYGM ŽROEF HIBTĘ PŠYLB
    CFEŪV MYDEZ EECĮC URILD ELGMG
    DEHĄI YIAŲH LŽČUĄ LČHDE ENMKY
    EŠNEO FŠIČA IPŽŠS CSYVĄ VŽŪCV
    DĘŠĘF ŲAĖŪS ZEŽRB LSAFD ĄVHŠM
    ĘŪKPV ĄĘIĘP MDD '''
    keyStart = "PEI"
    preparedText = InputFormatter.removeByAlphabet(string11,"ltC")
    dictionaryNotFiltered = IOlib.readListFromFile("dazninisLt")
    filtered = IOlib.DictionaryActions.formatToWordsOfMinSize(dictionaryNotFiltered,6)
    length = preparedText.__len__()
    array = IOlib.IndexGenerator.IndexArrayWithIncrements(32,3)
    limit = int(array.getPossibleVariationCount())
    vinegereTable = cipher.Vigenere.createVinegereTable(InputFormatter.getAlphabet("ltC"))
    result = ArrayList()
    for i in range(0,limit):
        if i % 1000 == 0:
            print(i)
        array.increment()
        if array.containsIndexPair():
            continue
        key = keyStart + array.returnValueByAlphabet(InputFormatter.getAlphabet("ltC"))
        value = cipher.Vigenere.cryptMassive(preparedText, key, vinegereTable, InputFormatter.getAlphabet("ltC"), length)
        if IOlib.DictionaryActions.stringHasWords(value,filtered):
            result.append(value)
            print(value)


    IOlib.printListToFile("output",result)

def t33():
    string12 = '''ĖVŽFE YĮŽŪV VCBLM MAĘKV ŽLMKK
    VVSTĮ GCŲFN SFLMF ŠKRCŲ FAIĄL
    TCTHĘ DŪŽIK LVŪĘY OZNYO URGDŠ
    VZĄKA IFIĮK GVFRS ĘGĄŽŪ KHĖAK
    ACAVT RAYDV TBUĖŪ ČKLSĘ KVŽNO
    YKĘSC DCCVU PAĖOV UROYD SŪJAC
    CDVGV DAĖŽV PVECM YIEČČ AOMVF
    IŽVIĄ ŪLVRR HCDŠF NCGLŪ ŽIHĄD
    KFNCG CEOTL DSŪYA TKVŽN UFDMS
    VIĘLČ IOEOČ BMSCC ŽLMNE CAVEP
    IFFIŠ FLCŽD TŠJVŪ ČIJIĖ ŪAEND
    MBSŽŠ SHĄVE NIYDL KVLIŪ ĘŠFNC
    GĖEMS ĘKVGF AOŪCA OTLET PLPVE
    EIKKC ĘFMJT LĘGBĮ SKKČG FŲFRG
    ĄMRCK ĘŽDTC DĘMCS SĮDŪV UŪČČU
    OIHĘĖ ESLŽĘ EINPL ĘŠEOT ĮČČUJ
    IGŪDC FEGRG ULDSE EUJIĖ ŪAUKO
    FRGĄK UĘEVV RIČŽD VGKSŪ GAOIŠ
    DĘENA EL '''
    preparedText = InputFormatter.removeByAlphabet(string12,"ltC")
    abc = InputFormatter.getAlphabet("ltC")
    dictionary = IOlib.readListFromFile("dazninisLt")
    filtered = IOlib.DictionaryActions.formatToWordsSize(dictionary,5,6)
    length = preparedText.__len__()
    result = ArrayList()
    table = cipher.Vigenere.createVinegereTable(abc)
    i = 0
    print(filtered.__len__())
    for word in filtered:
        i+=1
        if i %100 == 0:
            print(i)
        try:
            cypherText = cipher.Vigenere.cryptMassive(preparedText,word,table,abc,length)
            if IOlib.DictionaryActions.stringHasWords(cypherText,filtered):
                result.append(word+": "+cypherText)
        except Exception:
            pass
    IOlib.printListToFile("output",result)

def t34():

    string13 = '''CBBKR ŠCPBĘ FZDŲI PEZŪG MĄKČM
    GZŠGT ĖGSŠČ FFUSF ELŠYY ŪEHLY
    DPESČ ĖTČUF KZMKA AFČĘE AĖŠŠS
    ILOIF CŪLLI UCJRI ŪOTOZ ŲKCĮF
    ZIKJV CPIEČ VOPŠH ŠIĖYČ LJIAU
    ŠAPĄL UHEYP AAČUC PDAUĖ ŪĄŠEČ
    ĖĮĮGE YĖZGZ PYBZŽ GŲĘBG HŲDŲM
    GSODL FROUR VVFAY ĘFĮPM YZHFY
    ĮKŠĖK DČĮŠZ MLČĘJ GMĖFĘ AŠBĖA '''

    key = "TRAKAI"
    preparedText = InputFormatter.removeByAlphabet(string13,"ltC")
    solvedText=preparedText[:-key.__len__()]
    cryptoText = cipher.Vigenere.crypt(preparedText,key+solvedText,InputFormatter.getAlphabet("ltC"),False)
    # print(cryptoText[len(solvedText):len(key)+len(solvedText)])
    print(cryptoText)

def t41():
    text = '''LERLT CTNZQ SAMDL JQGZO UQOPF
    CEUIC UFQHH CEFFM EANDD POAXR
    XHSXK UDVBF MSUUW DPAMJ BGQNI
    VBJUP SHUVL NRTMY SXSMU BPBZZ
    CCQBK EWYUV JXKTE SMXTH EUDPP
    MYTMG YNDXB VMRBM DMSLK UFGTR
    FSIKH VGHFC MOQQW NNEOZ KIHQY
    BIRUV DNPAB PBPSW KUGAR HGMIN
    HVMZX YHFJD TMNNI ZBRAW BRBXY
    JTOUX BFPQA DWFUJ MXAPO XXKDO
    QNJTJ XWZTS VDPFK SKOUQ BPGPC
    GNGDM NJNLY WLQFF AUUTG JSHSI
    OGQLU IDZXC PXHSM HJXYP QMGRB
    KSUWR'''
    text = InputFormatter.removeByAlphabet(text, "en")
    l1 = [10, 2, 11, 18, 8, 20, 19, 25, 23, 1, 15, 9, 14, 6, 24, 0, 17, 7, 22, 21, 4, 12, 5, 3, 16, 13]
    l2 = [14, 2, 7, 20, 18, 9, 19, 25, 23, 1, 13, 17, 22, 5, 3, 0, 24, 8, 21, 10, 11, 12, 15, 4, 6, 16]
    key = [18, 3]
    enigma = cipher.Enigma(InputFormatter.getAlphabet("en"), [l1, l2], key)
    print(enigma.decrypt(text))

def t42():
    text = '''MPZZL WKHLZ WLYFJ PGQGD PKUUE
    WHLJR XGBGA YSDGJ EDNQK VUSMJ
    APNXB WAPAJ FKSVO YCVQS OQAXQ
    YZGAS OCWDO SKGBM QGRRF EGJKS
    EXQJM PFOJZ XFIUA VMSIP MUXLL
    ETZIF IUIKQ SVGCV LXDDC BGBAG
    AEUOL SPSKW YGIJJ FKOGH TJTVE
    ICMXP NABIL PZDRE ILMBV TSKIH
    NSDKT LMDVC AGERV ISLQM IGLXU
    QOYOY TFDCL WQXOK KKHRY CSZRX
    ZMXKS KTMZO TWPFA ODLJG WHCOG
    SAZKK LMNCR QQRYC YPHMK TUUYT
    CXGST FUQXL JLZFK TEELC XHCYM
    NWGNI WFTYL JOP'''
    text = InputFormatter.removeByAlphabet(text, "en")
    l1 = [20, 3, 24, 18, 8, 5, 15, 4, 7, 11, 0, 13, 9, 22, 12, 23, 10, 1, 19, 21, 17, 16, 2, 25, 6, 14]
    l2 = [8, 13, 24, 18, 9, 0, 7, 14, 10, 11, 19, 25, 4, 17, 12, 21, 15, 3, 22, 2, 20, 16, 23, 1, 6, 5]
    key =ArrayList()
    key.append(6)  # first key

    enigma = cipher.Enigma(InputFormatter.getAlphabet("en"), [l1, l2], key)
    for i in range(0,26):
        enigma.keys = [6,i]
        print("key="+str(i)+" "+enigma.decrypt(text))

def t43():
    text = '''BLXRE DVCEI GVEXZ IUVSW TVTRZ
DJPGS AQSVM FBIOV FCFUY EUSTH
NECVK BGTJM RWFRG UDKOK TMRZD
HLCFT ORUMM MCCAI ULRUA SYTSG
PDTRC HPZDB RBOJI BJFMR FKDJT
OGDLO WFEDN ZGVXD MZPZF QMDTZ
HXXIB QPQZH BIHQL GOCUF UBEEM
MCDGY VBNTI KHDKY WUAOB JBPOY
ZDZXN UDGOT YCPJS VKQJA SIWHP
DEGDG OOBNU WPQEX AIECU YHNMB
UUDHU EWEVR XDQAA ZLOOC ZIBG'''
    text = InputFormatter.removeByAlphabet(text, "en")
    l1= [14, 2, 7, 20, 18, 9, 19, 25, 23, 1, 13, 17, 22, 5, 3, 0, 24, 8, 21, 10, 11, 12, 15, 4, 6, 16]
    l2 = [5, 3, 2, 0, 17, 10, 8, 24, 20, 11, 1, 12, 9, 22, 16, 6, 25, 4, 18, 21, 7, 13, 15, 23, 19, 14]
    s = [2, 4, 0, 6, 1, 11, 3, 8, 7, 13, 16, 5, 15, 9, 18, 12, 10, 19, 14, 17, 25, 22, 21, 24, 23, 20]
    key = [9, 11]
    enigma = cipher.Enigma(InputFormatter.getAlphabet("en"), [l1, l2], key,s)
    print(enigma.reflection)
    print(enigma.decrypt(text,True))

def t51():
    cipherText = [[217, 225], [205, 255], [215, 239], [192, 241], [196, 245], [217, 229], [218, 253], [205, 244],
        [209, 231], [204, 245], [202, 226], [208, 231], [193, 255], [214, 231], [197, 244], [220, 255],
        [192, 245], [217, 231], [196, 241], [220, 238], [192, 245], [192, 253], [202, 238], [199, 249],
        [220, 231], [223, 244], [218, 245], [219, 250], [218, 245], [216, 240], [217, 231], [204, 231],
        [195, 244], [221, 251], [204, 231], [192, 249], [221, 225], [220, 231], [204, 255], [197, 244],
        [203, 239], [204, 249], [205, 255], [221, 229], [216, 231], [205, 255], [204, 245], [217, 254],
        [197, 250], [203, 225], [204, 245], [201, 230], [193, 241], [200, 253], [219, 252], [204, 231],
        [209, 239], [204, 249], [220, 231], [204, 231], [221, 252], [199, 242], [205, 240], [201, 251],
        [205, 224], [217, 252], [220, 253], [219, 240], [211, 240], [203, 224], [197, 240], [205, 250],
        [221, 252], [212, 224], [204, 231], [215, 224], [221, 252], [220, 231], [221, 227], [208, 231],
        [194, 245], [203, 244], [212, 224], [200, 253], [202, 231], [199, 252], [204, 243], [215, 229],
        [217, 225], [201, 249], [200, 253], [218, 245], [217, 254], [197, 250], [195, 250]]
    key = [117, 181, 169]

    def funct(m, k):
        return (m | k) ^ ((k // 16) & m)

    print(cipher.Feistel.decrypt(cipherText, key, funct))

def t52():
    cipherText = [[31, 75], [11, 75], [25, 83], [19, 85], [9, 77], [28, 84], [9, 67], [17, 67], [4, 86], [9, 65], [16, 67], [14, 78], [19, 94], [4, 84], [29, 87], [9, 72], [11, 70], [21, 65], [27, 82], [27, 85], [17, 71], [11, 78], [13, 77], [26, 76], [8, 92], [30, 76], [25, 79], [11, 79], [22, 82], [16, 70], [17, 67], [8, 78], [11, 78], [0, 82], [17, 67], [3, 74], [30, 80], [20, 68], [7, 83], [3, 72], [16, 82], [4, 86], [6, 78], [9, 69], [19, 79], [25, 80], [19, 86], [3, 72], [27, 73], [0, 66], [20, 68], [3, 78], [12, 70], [17, 67], [4, 72], [9, 77], [20, 84], [13, 65], [28, 74], [25, 79], [16, 70], [9, 80], [3, 72], [4, 72], [26, 74], [27, 86], [1, 65], [15, 76], [12, 68], [25, 81], [22, 83], [13, 73], [31, 84]]
    def funct(m, k):
        return (m ^ k) & ((k // 16) | m)

    for i in range(0,256):
        key = [i, 133]
        print(str(i)+": "+cipher.Feistel.decrypt(cipherText, key, funct))

def t61():

    def f(m,k):
        return (m ^ k) & ((k // 16) | m)
    key =[53, 75, 67]

    text = [[16, 86], [18, 90], [9, 65], [19, 71], [26, 82], [7, 67], [14, 78], [9, 65], [9, 65], [18, 71], [13, 77], [19, 71],
     [20, 80], [5, 77], [6, 81], [12, 68], [4, 95], [16, 93], [20, 65], [6, 85], [12, 79], [0, 81], [10, 69], [23, 69],
     [9, 69], [30, 86], [26, 82], [20, 65], [17, 67], [0, 89], [10, 69], [20, 65], [5, 95], [7, 65], [2, 89], [21, 85],
     [3, 89], [14, 93], [12, 74], [19, 87], [6, 85], [7, 65], [12, 79], [3, 89], [6, 92], [11, 91], [22, 68], [19, 71],
     [18, 82], [2, 65], [22, 68], [28, 82], [2, 78], [11, 69], [3, 95], [22, 68], [14, 94], [13, 79], [12, 79], [9, 77],
     [0, 82], [4, 72], [13, 69], [13, 65], [24, 87]]

    blockCipher = cipher.BlockCipher(key)
    print(blockCipher.decrypt(text,f,blockCipher.keys))

def t62():
    def f(m, k):
        return (m ^ k) & ((k // 16) | m)

    key = [53, 75, 67]
    iv = [97, 132]
    text = [[205, 98], [61, 164], [163, 88], [81, 186], [242, 99], [58, 128], [142, 124], [113, 189], [247, 84], [17, 189], [179, 110], [110, 155], [217, 100], [37, 185], [182, 87], [86, 168], [230, 120], [38, 153], [137, 106], [112, 185], [233, 91], [31, 162], [170, 105], [111, 145], [211, 116], [44, 183], [187, 70], [93, 181], [226, 119], [39, 129], [134, 116], [114, 165], [225, 72], [7, 166], [188, 117], [101, 147], [192, 121], [53, 184], [163, 76], [78, 163], [255, 99], [43, 146], [134, 96], [96, 174], [242, 75], [2, 162], [186, 112], [97, 147], [209, 106], [35, 167], [183, 80], [85, 174], [224, 121], [47, 159], [143, 104], [112, 191], [233, 89], [10, 175], [174, 102], [121, 151], [192, 113], [35, 175], [169, 66], [77, 172], [233, 112], [41, 148], [158, 109], [98, 184], [249, 89], [20, 165], [175, 115], [107, 144], [201, 110], [43, 166], [176, 83], [72, 176], [253, 97], [34, 140], [128, 102], [118, 178], [227, 85], [10, 161], [166, 100], [112, 132], [220, 100], [46, 169], [175, 69], [82, 179], [232, 116]]

    blockCipher = cipher.BlockCipherCBC(key,iv)
    print(blockCipher.decrypt(text, f, blockCipher.keys))

def t63():
    def f(m, k):
        return (m ^ k) & ((k // 16) | m)

    key = [53, 75, 67]
    iv = [97, 132]
    text = [[129, 109], [43, 184], [234, 93], [76, 177], [185, 96], [35, 145], [210, 127], [96, 164], [160, 72], [8, 167], [250, 102], [111, 153], [144, 116], [59, 161], [234, 69], [71, 161], [162, 119], [53, 130], [200, 121], [114, 188], [188, 69], [24, 176], [237, 105], [98, 137], [139, 109], [42, 190], [243, 91], [80, 191], [191, 107], [40, 141], [205, 120], [108, 189], [184, 80], [29, 165], [254, 127], [121, 129], [134, 123], [53, 169], [228, 81], [91, 188], [170, 109], [58, 132], [196, 112], [112, 166], [163, 95], [17, 186], [225, 100], [97, 133], [145, 108], [32, 189], [230, 92], [66, 172], [166, 104], [34, 158], [209, 119], [115, 162], [179, 92], [19, 166], [235, 124], [123, 143], [134, 109], [63, 167], [228, 82], [67, 191], [185, 113], [56, 134], [221, 119], [101, 170], [163, 91], [2, 178], [254, 97], [99, 153], [152, 118], [45, 175], [234, 95], [85, 178], [167, 6]]
    blockCipher = cipher.BlockCipherCFB(key,iv,12)
    print(blockCipher.decrypt(text,f,key,True))

def t64():
    def f(m,k):
        return (m ^ k) & ((k // 16) | m)

    key = [53, 75, 67]
    text = [[78, 67], [84, 86], [78, 79], [64, 64], [64, 72], [72, 83], [64, 79], [74, 71], [90, 64], [91, 69],
               [76, 76],
               [70, 75], [91, 66], [69, 65], [76, 69], [72, 65], [64, 69], [81, 85], [77, 80], [72, 81], [74, 83],
               [84, 77],
               [84, 72], [84, 78], [64, 72], [64, 91], [89, 78], [91, 89], [64, 95], [76, 90], [93, 78], [89, 79],
               [83, 76],
               [64, 78], [78, 80], [74, 80], [84, 91], [69, 69], [82, 83], [78, 73], [90, 93], [78, 73], [69, 93],
               [72, 64],
               [89, 92], [90, 65], [64, 89], [64, 89], [82, 72], [85, 73], [68, 80], [68, 76], [68, 70], [88, 86],
               [64, 80],
               [72, 81], [64, 95], [76, 90], [74, 66], [72, 95], [64, 95], [76, 82], [64, 70], [72, 68], [84, 13],
               [84, 16],
               [82, 10], [76, 7], [74, 0], [72, 12], [72, 1], [84, 17], [76, 19], [76, 4], [64, 30], [64, 25], [92, 12],
               [83, 1], [76, 5], [76, 26], [64, 18], [72, 20], [72, 6], [82, 22], [72, 17], [83, 9], [68, 15], [78, 20],
               [70, 26]]
    blockCipher = cipher.BlockCipherCRT(key)
    print(blockCipher.decrypt(text,f,0,True))