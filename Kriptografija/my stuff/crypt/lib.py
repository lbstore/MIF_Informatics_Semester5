# coding=utf-8
def utfPrint(text):
    print(text, "utf-8")

def formatListToString(array):
    string = ""
    for x in array:
        if x is None:
            x = " "

        string += str(x)+u","

    return "["+string[:-1] + "]"


class ArrayList(list):

    def __init__(self, data=None, nullValue=" "):
        super().__init__()
        self.nullValue = nullValue
        if data is not None:
            self.extend(data)

    def __fitsInRange(self, index:int):
        if self.__len__() <= index:
            return False
        else:
            return True

    def appendToSize(self, requiredSize):
        size = self.__len__()
        for i in range(size,requiredSize):
            self.append(self.nullValue)

    def indexOf(self, value):
        i = -1
        for val in self:
            i+=1
            if value == val:
                return i
        return -1

    def lastIndexOf(self, value):
        i = self.__len__()
        while i>=0:
            i -= 1
            if value == self[i]:
                return i
        return -1

    def insert(self, index:int, value):
        self.appendToSize(index)
        super().insert(index, value)

    def set(self,index:int, value):
        self.appendToSize(index+1)
        self[index] = value

    def populateFromString(self, text):
        for i in text:
            self.append(i)

    def get(self,index:int):
        return self[index]


class Table:
    def __init__(self, data=None):

        self.name = "Table"
        self.nullValue =" "
        self.lines = ArrayList(ArrayList(None,self.nullValue))
        if data is not None:
            self.lines.extend(data)

    def equalize(self):
        maxTableLen = 0
        for line in self.lines:
            if maxTableLen < line.__len__():
                maxTableLen = line.__len__()
        for line in self.lines:
            line.appendToSize(maxTableLen)

    def addLine(self, line=None):
        arrayList = ArrayList(line,self.nullValue)
        self.lines.append(arrayList)

    def remove(self, line, column):
        ob = self.lines[line].pop(column)
        return ob

    def get(self, line, column):
        ob = self.lines[line]
        return ob[column]

    def set(self, line: int, column: int, value):
        while line >= self.getLineCount():
            self.addLine()

        self.lines[line].set(column, value)

    def getLineCount(self):
        return self.lines.__len__()

    def getColumnCount(self):
        m = 0
        for line in self.lines:
            m = max(m,line.__len__())
        return m
    def getColumn(self,col)->list:
        column = ArrayList()
        for i in range(self.getLineCount()):
            column.append(self.get(i,col))
        return column

    def setLine(self,index:int,line:list):
        i = 0
        for value in line:
            self.set(index, i, value)
            i += 1

    def setColumn(self,index:int,column:list):
        i=0
        for value in column:
            self.set(i,index,value)
            i+=1

    def getFromTopLeft(self,index:int):
        i =0
        for line in self.lines:
            for value in line:
                if i == index:
                    return value
                i+=1

    def setFromTopLeft(self,index:int,value):
        i = 0
        for line in self.lines:
            for valIndex in range(0,line.__len__()):
                if i == index:
                    line[valIndex] = value
                    return
                i += 1

    def indexFromTopLeft(self,findMe):
        i = 0
        for line in self.lines:
            for value in line:
                if findMe == value:
                    return i
                i += 1
        return -1
    def indexFromTop(self,findMe):
        i = 0
        for line in self.lines:
            if line.indexOf(findMe) >= 0:
                return i
            i+=1
        return -1

    def indexFromLeft(self,findMe):
        for line in self.lines:
            indexOf = line.indexOf(findMe)
            if indexOf >= 0:
                return indexOf
        return -1

    def removeLine(self,index:int):
        del self.lines[index]

    def removeColumn(self,index:int):
        for line in self.lines:
            line.pop(index)

    def getCellCount(self):
        return self.getColumnCount()*self.getLineCount()

    def __copy__(self):
        table = Table()
        for i in range(0,self.getLineCount()):
            table.addLine(self.lines[i].copy())
        return table

    def __str__(self):
        string = self.name+u"\n"
        i = 0
        for line in self.lines:
            string += str(i)+":"+line.__str__()+u"\n"
            i+=1
        return string

    def printMe(self):
        print(self.name)
        for line in self.lines:
            print(line.__str__())

    def __rotateCW(self):
        table = Table()
        for i in range(0,self.getLineCount()):
            line = self.lines[i]
            table.setColumn(self.getLineCount()-i-1,line)
        self.lines = table.lines

    def __rotateCCW(self):
        table = Table()
        for i in range(0, self.getLineCount()):
            line = self.lines[i]
            line.reverse()
            table.setColumn(i, line)
        self.lines = table.lines

    def rotateClockwise(self, times=1):
        for i in range(0,times):
            self.__rotateCW()

    def rotateCounterClockwise(self, times=1):
        for i in range(0,times):
            self.__rotateCCW()

    def __flipVertical(self):
        for line in self.lines:
            line.reverse()

    def flip(self, VerticalAxis=True):
        if not VerticalAxis:
            self.rotateClockwise(2)
        self.__flipVertical()

    def applyNullValue(self,nullValue):
        for i in range(0,self.getCellCount()):
            if self.getFromTopLeft(i) == self.nullValue:
                self.setFromTopLeft(i,nullValue)
        self.nullValue = nullValue
        for line in self.lines:
            line.nullValue = nullValue

    def applyToEachElement(self,function):
        for i in range(0, self.getCellCount()):
            self.setFromTopLeft(i, function(self.getFromTopLeft(i)))

    @staticmethod
    def createTable(text, lineLength, linesAreColumns=False):
        table = Table()
        index = 0
        lineIndex = 0
        origline = ArrayList()
        for i in text:
            origline.append(i)
        line = ArrayList()
        size = origline.__len__()

        if not linesAreColumns:
            while index < size:
                if lineIndex >= lineLength:
                    table.addLine(line)
                    line = ArrayList()
                    lineIndex = 0
                line.set(lineIndex, origline[index])
                index += 1
                lineIndex += 1
            table.addLine(line)
        else:
            while table.getLineCount() < lineLength:
                table.addLine(ArrayList())
            currentIndex = 0
            while index < size:
                if lineIndex >= lineLength:
                    lineIndex = 0
                    currentIndex += 1
                table.set(lineIndex, currentIndex, origline[index])
                index += 1
                lineIndex += 1
        return table


def cmp_to_key(mycmp):
    'Convert a cmp= function into a key= function'
    class K(object):
        def __init__(self, obj, *args):
            self.obj = obj
        def __lt__(self, other):
            return mycmp(self.obj, other.obj) < 0
        def __gt__(self, other):
            return mycmp(self.obj, other.obj) > 0
        def __eq__(self, other):
            return mycmp(self.obj, other.obj) == 0
        def __le__(self, other):
            return mycmp(self.obj, other.obj) <= 0
        def __ge__(self, other):
            return mycmp(self.obj, other.obj) >= 0
        def __ne__(self, other):
            return mycmp(self.obj, other.obj) != 0
    return K

def convertToInt(b:list):
    sk = 0
    p = 0
    b.reverse()
    for i in b:
        sk+= (2**p) * i
        p+=1
    return sk


def convertToBits(s:int):
    pow = 0
    i = 0
    s+=1
    bits = list()
    while (s>pow):
        pow = 2**i
        i+=1
    # found max
    while(i>=0):
        pow = 2**i
        if(s>pow):
            s-=pow
            bits.append(1)
        else:
            bits.append(0)
        i-=1
    while bits.__len__()>1 and (bits[0]==0):
        bits.pop(0)

    return bits
