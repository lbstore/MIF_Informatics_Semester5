getBin = lambda x, n: x >= 0 and str(bin(x))[2:].zfill(n) or "-" + str(bin(x))[3:].zfill(n)
abc_h='ABCDEFGHIJKLMNOP' # h-funcijos reikšmių abėcėlė
def e(m,k): #Kriptosistema
    f=lambda u,v,w:u^((v*v+77*w)%256)
    c=[m[1],f(m[0],m[1],k[0])]
    return [c[1],f(c[0],c[1],k[1])]

def code(m): 
    e=getBin(m[0],8)+getBin(m[1],8)
    h=''
    for i in range(0,4):
        h+=abc_h[int(e[4*i:4*i+4],2)]
    return h

def decod(s): #h-funkcijos reikšmė į bitus
    bt=lambda r: bin(16^r)[3:]
    b=''
    for c in s:
        if c in abc_h:
            b+=bt(abc_h.index(c))
    return b
def hf(h0,r,s): # h-funkcija
    h=[ord(h0[0]),ord(h0[1])]
    k=[ord(r[0]),ord(r[1])]
    l=len(s)
    if l%2==1:
         s=s+s[0]
         l+=1
    for i in range(0,l//2):
        m=[ord(s[2*i])^h[0],ord(s[2*i+1])^h[1]]
        h=e(m,k)
    return code(h)

def convertToInt(b):
    sk = 0
    for x in range(len(b)):
        if b[x] == '1':
            sk += (2**(len(b)-1-x))
    return sk

def convertToWord(s):
    b = ''
    for x in range(15):
        sk = (2 ** (15 - x))
        bi = s // sk
        s -= bi * sk
        if bi == 0:
            b += '0'
        else:
            b += '1'
    #b.reverse()
    return b


h0='ru'
r='ru'
s='vand'
s11='vanduo'
s2='rokvai'
h1=hf(h0,r,s)
h2 = hf(h0,r,s2)
h11 = hf(h0,r,s11)
d1 = int(decod(h1),2)
d2 = int(decod(h2),2)
d11 = int(decod(h11),2)

s = '0111010101101111'
d = int(s, 2)

print(d1,d2,d)
x = d1^d^d2
print(x)
print(chr(int(convertToWord(x)[:8],2)),chr(int(convertToWord(x)[8:],2)))

u = d1^d11^d
print(chr(int(convertToWord(u)[:8],2)),chr(int(convertToWord(u)[8:],2)))

