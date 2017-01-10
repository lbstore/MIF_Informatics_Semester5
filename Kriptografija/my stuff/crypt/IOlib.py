

import re
from libs.crypt import Math
from libs.crypt.lib import ArrayList, cmp_to_key


class InputFormatter:
    alphabets = dict()
    dictionaries = dict()
    @staticmethod
    def init():
        InputFormatter.addAlphabet("en", "ABCDEFGHIJKLMNOPQRSTUVWXYZ")
        InputFormatter.addAlphabet("ltC", "AĄBCČDEĘĖFGHIĮYJKLMNOPRSŠTUŲŪVZŽ")
        InputFormatter.addAlphabet("lt", "aąbcčdeęėfghiįyjklmnoprsštuųūvzž")
        InputFormatter.addAlphabet("slt", "ABCČDEFGHIYJKLMNOPRSTUVZŽ")

    @staticmethod
    def getAlphabet(key):
        return InputFormatter.alphabets.get(key)

    @staticmethod
    def addAlphabet(key, alph):
        InputFormatter.alphabets.setdefault(key, alph)

    @staticmethod
    def addDictionary(key, dictionary):
        InputFormatter.dictionaries.setdefault(key,dictionary)

    @staticmethod
    def stringContainsWord(text:str,dictionary:list,wordLen=None):
        found = False
        for word in dictionary:
            if wordLen is not None:
                if len(word)>= wordLen:
                    if word in text:
                        found = True
            else:
                if word in text:
                    found = True
            if found:
                print("Found:"+word)
                return True
        return False


    @staticmethod
    def cmpByAlphabet(alphabet):
        line = ArrayList()
        line.populateFromString(alphabet)

        def cmpItems(a, b):
            index1 = line.indexOf(a)
            index2 = line.indexOf(b)
            ans = 0
            if index1 < index2:
                ans = -1
            elif index1 > index2:
                ans = 1
            return ans

        return cmpItems

    @staticmethod
    def cmpByAlphabetStr(alphabet):
        line = ArrayList()
        line.populateFromString(alphabet)

        def cmpItems(a:str, b:str):
            minLen = min(len(a),len(b))
            for i in range(0,minLen):
                index1 = line.indexOf(a[i])
                index2 = line.indexOf(b[i])
                if index1 < index2:
                    return -1
                elif index1 > index2:
                    return 1

            return 0

        return cmpItems


    @staticmethod
    def removeWithRegex(text, regex):
        return re.sub(regex, "", text)

    @staticmethod
    def removeWhiteSpace(text):
        p = re.compile(r"^\s+", re.MULTILINE)
        return p.sub("", text)

    @staticmethod
    def removeByAlphabet(text, key):
        result = ""
        alphabet = InputFormatter.alphabets.get(key)
        if alphabet is not None:
            for char in text:
                if char in alphabet:
                    result += char
        else:

            print("alphabet is None")
        return result


class IndexGenerator:

    @staticmethod
    def __go1(indexArrayList: ArrayList, current: ArrayList, left: ArrayList, i:int, maxLength:int):
        length = current.__len__()

        if not length < maxLength:
            indexArrayList.append(current)
            # Debug.print("add:"+current.__str__())
            return
        else:
            current.append(left.pop(i))
            length = left.__len__()
            # Debug.print("Cur:" + current.__str__() + " Left:" + left.__str__() + " len =" + str(length), end="")
            # Debug.print(" index:" + str(i))
            a = 0
            if a<length:
                while a < length:
                    IndexGenerator.__go1(indexArrayList,current.copy(), left.copy(), a, maxLength)
                    a += 1
            else:
                indexArrayList.append(current)
                # Debug.print("add:" + current.__str__())
                return

    @staticmethod
    def __go(indexArrayList: dict, current: ArrayList, left: ArrayList, i:int, maxLength:int):
        length = current.__len__()

        if length >= maxLength:
            indexArrayList.setdefault(current.__str__(),current)
            # Debug.print("add:"+current.__str__())
            return
        else:
            current.append(left.pop(i))
            length = left.__len__()
            # Debug.print("Cur:" + current.__str__() + " Left:" + left.__str__() + " len =" + str(length), end="")
            # Debug.print(" index:" + str(i))
            a = 0
            if a<length:
                while a < length:
                    IndexGenerator.__go(indexArrayList,current.copy(), left.copy(), a, maxLength)
                    a += 1
            else:
                indexArrayList.setdefault(current.__str__(), current)
                # Debug.print("add:" + current.__str__())
                return

    @staticmethod
    def generateIndexList(size:int,maxLength:int)->list:
        maxLength = min(size,maxLength)
        # print("MaxLen:"+str(maxLength))
        indexArrayList = dict()
        indexArray = ArrayList()

        for i in range(0, size):
            indexArray.append(i)
        for i in range(0, size):
            newArray = ArrayList()
            IndexGenerator.__go(indexArrayList, newArray.copy(), indexArray.copy(), i, maxLength)
        array = ArrayList()
        for each in indexArrayList.values():
            array.append(each)
        array.sort()
        return array

    @staticmethod
    def arrayObjectCrossProduct(array:list,objects:list):
        newArray = ArrayList()
        for ar in array:
            for ob in objects:
                newObject = list()
                try:
                    newObject.extend(ar)
                except TypeError:
                    newObject.append(ar)
                try:
                    newObject.extend(ob)
                except TypeError:
                    newObject.append(ob)

                newArray.append(newObject)

        return newArray

    @staticmethod
    def generateAllPossibleIndexes(indexCount:int,maxLength:int)->list:
        finalArray = ArrayList()

        for i in range(0,indexCount):
            finalArray.append(i)
        array = finalArray.copy()

        for i in range(0,maxLength-1):
            finalArray = IndexGenerator.arrayObjectCrossProduct(finalArray,array)

        return finalArray

    class IndexArrayWithIncrements:
        def __init__(self,indexCount:int, maxLength:int,startingValue=0):
            self.maxValue = indexCount-1
            self.maxLength = maxLength
            self.array = ArrayList()
            for i in range(0,maxLength):
                self.array.append(0)
            self.increment(startingValue)

        def __bump(self):
            currentIndex = self.maxLength-1

            while not currentIndex<0:
                if self.array[currentIndex] < self.maxValue:
                    self.array[currentIndex]+=1
                    return
                else:
                    self.array[currentIndex] = 0
                currentIndex+=-1

        def increment(self, amount=1):
            for i in range(0,amount):
                self.__bump()

        def containsIndexPair(self):
            for i in range(1,self.maxLength):
                if self.array[i-1] == self.array[i]:
                    return True
            return False

        def returnValueByAlphabet(self,alphabet:str):
            string=""
            for i in self.array:
                string+= alphabet[i]
            return string

        def getPossibleVariationCount(self):
            return Math.math.pow(self.maxValue+1,self.maxLength)


def printListToFile(filePath,array:list):
    file = open(filePath,'w')
    for s in array:
        if "\n" not in s:
            s+="\n"
        file.write(s)
    file.flush()
    file.close()

def readListFromFile(filePath)->list:
    file = open(filePath,'r')
    array = list()
    for line in file.readlines():
        if line.__len__()>1:
            array.append(stringReplace(line,'\n'))
    return array

def stringReplace(source:str,target:str,replacement="")->str:
    if target not in source:
        return source
    try:
        index = source.index(target)
        new = source[:index] + replacement + source[len(target)+index:]
    except ValueError:
        return source
    return new


class DictionaryActions:
    @staticmethod
    def formatToWordsSize(array:list,minSize:int,maxSize:int):
        result = ArrayList()
        maxSize = max(maxSize,minSize)
        for word in array:
            if minSize <= word.__len__() <= maxSize:
                result.append(word)
        return result

    @staticmethod
    def stringHasWords(string:str,dictionary:list):
        for word in dictionary:
            index = string.find(word)
            if index >=0:
                return True
        return False
    @staticmethod
    def dictionaryExtract(lines,Key=None)->list:
        array = ArrayList()
        Set = set()
        for line in lines:
            if not line[0].isupper():
                s = line
                try:
                    if "." not in s:
                        s = stringReplace(s, "dkt", ",")
                        s = stringReplace(s, "vksm", ",")
                        s = stringReplace(s, "sktv", ",")
                        s = stringReplace(s, "bdv", ",")
                        s = stringReplace(s, "prv", ",")
                        s = stringReplace(s, "jst", ",")
                        s = stringReplace(s, "įv", ",")
                        for i in s:
                            if i.isnumeric() or i == "\t" or i== "\n" or i ==" ":
                                s = stringReplace(s, i, "")
                        miniList = s.split(",")
                        for var in miniList:
                            Set.add(var)

                except ValueError:
                    print(ValueError)
                    pass

        for line in array:
            Set.add(line)

        array.clear()
        array.extend(Set.copy())
        array.sort(key=Key)
        return array
