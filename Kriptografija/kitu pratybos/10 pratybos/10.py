from fractions import gcd
import copy

Kv = [176805, 33311, 66622, 189823, 32631, 14371, 119277, 23528]
w1 = 1290
c = [170463, 156092, 137832, 432561, 304127, 137832, 66622, 432561, 156092, 123461, 327655, 242738, 156092, 123461, 66622, 423404, 137832, 251841, 123461, 432561]
p = 218775
s = 55963;
#12208 41378 55963 85133 99718 128888
Kp = [];
Cx = [];

    
def calculate():
        for i in range(len(Cx)):
                check = 0;
                awnser = [];
                binaris = [0,0,0,0,0,0,0,0]
                size = Cx[i]
                coppy = copy.copy(Kp)
                for j in range(len(Kp)):
                    if coppy != []:
                        h = max(coppy)
                        indexas = Kp.index(h)
                        if (size - Kp[indexas] >= 0):
                                for lem in range(len(Kp)):
                                    if size - Kp[lem] == 0:
                                        awnser.append(lem)
                                        check = 1
                                        size = 0;
                                size = size - Kp[indexas]
                                if(check == 0):        
                                    for k in range(len(Kp)):
                                        if Kp[k] == h:
                                            awnser.append(Kp.index(h))                                
                                    coppy.remove(max(coppy))
                                else:
                                    coppy[:] = []
                        else :
                                coppy.remove(max(coppy))
                                
                for k in range(len(awnser)):
                        binaris[awnser[k]] = 1;
                print("Binaris ",binaris)

def second():
        for i in range(len(Kv)):
                Kp.append((Kv[i] * s) % p )

def decypher():
        for i in range(len(c)):
                Cx.append((c[i] * s) % p)

#with Dbd you find Greates common divisor
def dbd():
        for s in range(99719, 300000):
                if gcd(s,p) == 1:
                        print(s)
                        if w1 == (Kv[0]*s) % p:
                                print('result is')
                                print(s)
                                break
#dbd()
second()
decypher()
calculate()
#print("C ",Cx)
print("KP ",Kp)
#print("KV ",Kv)
