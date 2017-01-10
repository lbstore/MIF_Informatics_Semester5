import math
from libs.crypt.lib import Table
import numpy
class Matrix(Table):

    def __init__(self, precision=None):

        super().__init__()
        self.nullValue = 0
        self.precision = precision
    def applyPrecision(self, precision=None):
        if precision is None:
            if self.precision is None:
                return None
            else:
                precision = self.precision

        for line in self.lines:
            for i in range(0,line.__len__()):
                line[i] = "{0:."+str(precision)+"}".format(line[i])

    def reverseSign(self):
        for i in range(0,self.getCellCount()):
            self.setFromTopLeft(i,self.getFromTopLeft(i)*-1)

class Math:
    math = math
    np = numpy
    @staticmethod
    def multiplicativeInverse(number: int, modulus: int) -> int:
        for i in range(0,modulus):
            ans = i * number % modulus
            if ans == 1:
                return i
            else:
                i += 1
        return None

    @staticmethod
    def __prepareMatrix(m1) -> Matrix:
        ans = Matrix()
        for subarray in m1.tolist():
            ans.addLine(subarray)
        return ans

    @staticmethod
    def matrixAddition(m1:Matrix, m2:Matrix) -> Matrix:
        M1 = Math.np.matrix(m1.lines)
        M2 = Math.np.matrix(m2.lines)
        return Math.__prepareMatrix(M1+M2)

    @staticmethod
    def matrixProduct(m1:Matrix, number) -> Matrix:
        def m(x):
            return x*number
        m1.applyToEachElement(m)
        return m1

    @staticmethod
    def matrixMultiplication(m1:Matrix, m2:Matrix) -> Matrix:
        M1 = Math.np.matrix(m1.lines)
        M2 = Math.np.matrix(m2.lines)
        return Math.__prepareMatrix(M1 * M2)

    @staticmethod
    def matrixInverse(m1:Matrix) -> Matrix:
        return Math.__prepareMatrix(Math.np.linalg.inv(Math.np.matrix(m1.lines)))

    @staticmethod
    def matrixTranspose(m1:Matrix) ->Matrix:
        m2 = m1.__copy__()
        m2.rotateClockwise(1)
        m2.flip(True)
        return m2

    @staticmethod
    def matrixMinor(m1: Matrix,removeLine:int,removeColumn:int) -> Matrix:
        minor = m1.__copy__()
        minor.removeColumn(removeColumn)
        minor.removeLine(removeLine)
        return minor

    @staticmethod
    def matrixDeterminant(m1:Matrix)->float:
        ans = 0
        m = m1.__copy__()
        if m.getLineCount() * m.getColumnCount() == 1:
            return m.getFromTopLeft(0)
        elif m.getLineCount() * m.getColumnCount() == 4:
        # 2*2 matrix
            ans += m.get(0,0)*m.get(1,1) - m.get(0,1)*m.get(1,0)
        else:
            firstLine = m.lines[0].copy()
            multiplier = 1
            for index in range(0,len(firstLine)):
                ans +=firstLine[index]*Math.matrixDeterminant(Math.matrixMinor(m,0,index))*multiplier
                multiplier*=-1

        return ans

    @staticmethod
    def matrixModularInverse(m1:Matrix,modulus:int)->Matrix:
        det = round(Math.np.linalg.det(m1.lines))
        new = Math.__prepareMatrix(Math.np.linalg.inv(m1.lines) * det)
        # print(det)
        # print(new)
        if det<0:
            det*=-1
            new.reverseSign()
        mi = Math.multiplicativeInverse(det, modulus)
        # print(mi)
        new = Math.matrixProduct(new,mi)
        for i in range(0,new.getCellCount()):
            new.setFromTopLeft(i,round(new.getFromTopLeft(i) % modulus))

        return new

    @staticmethod
    def IdentityMatrix(matrixPower:int) -> Matrix:
        m = Matrix()
        m.nullValue = ' '
        for i in range(0,matrixPower):
            m.set(i,i,1)
        m.equalize()
        m.applyNullValue(0)
        print(m.nullValue)
        return m

# Stack overflow solution
def generalizedEuclidianAlgorithm(a, b):
    if b > a:
        #print a, b
        return generalizedEuclidianAlgorithm(b,a);
    elif b == 0:
        return (1, 0);
    else:
        #print a,b
        (x, y) = generalizedEuclidianAlgorithm(b, a % b);
        return (y, x - (a / b) * y)

def inversemodp(a, p):
    a = a % p
    if (a == 0):
        print("a is 0 mod p")
        return 0
    (x,y) = generalizedEuclidianAlgorithm(p, a % p);
    return y % p

def identitymatrix(n):
    return [[int(x == y) for x in range(0, n)] for y in range(0, n)]

def inversematrix(matrix, q):
    n = len(matrix)
    A = Math.np.matrix([[ matrix[j, i] for i in range(0,n)] for j in range(0, n)])
    Ainv = Math.np.matrix(identitymatrix(n))
    for i in range(0, n):
        factor = inversemodp(A[i,i], q)
        A[i] = A[i] * factor % q
        Ainv[i] = Ainv[i] * factor % q
        for j in range(0, n):
            if (i != j):
                factor = A[j, i]
                A[j] = (A[j] - factor * A[i]) % q
                Ainv[j] = (Ainv[j] - factor * Ainv[i]) % q
                # print A, Ainv
                # print i, j, factor
    return Ainv