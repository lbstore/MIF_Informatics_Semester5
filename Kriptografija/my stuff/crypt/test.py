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

h0='ss'
r='ab'
s='vucccsss'
h=hf(h0,r,s)
print(h, decod(h))