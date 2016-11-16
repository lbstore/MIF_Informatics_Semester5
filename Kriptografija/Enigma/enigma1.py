__author__ = 'Vik'

abc = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'
cipher = 'QNGBYIZGCVGCHYQGYGJSCYNACBRMYCWJQQRGZYKICNBLPJQYLKYBKGWKKDQTWOEHIGUMDVLMQNXEZOQWGZDJVKASFTMQIKDGFVDZVHTRZTCGXEPOIADGVMJSYETGNRJXDRHLPBHSBCEXLPHEBDMQOGFOBQQHPXODCFMRSBJKFHTWIGTHGZKHWYJPPESKCTHFBXHJVJXFZTIZRILEHXYLONRMXEHRPNFZIPHBYKTENGSTEVXZVBCABIQNTELTRTCOIYQLDWNHDMTPTSDCJMRRNMSPPSBTDDXNALVPBBUBILVHCDUUSEVEBFADLGVHUNOZYNMDEZJUWR'
ro = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 0]
k1 = 7
k2 = 4
L1 = [5, 3, 2, 0, 17, 10, 8, 24, 20, 11, 1, 12, 9, 22, 16, 6, 25, 4, 18, 21, 7, 13, 15, 23, 19, 14]
L2 = [20, 3, 24, 18, 8, 5, 15, 4, 7, 11, 0, 13, 9, 22, 12, 23, 10, 1, 19, 21, 17, 16, 2, 25, 6, 14]
s = [2, 4, 0, 6, 1, 11, 3, 8, 7, 13, 16, 5, 15, 9, 18, 12, 10, 19, 14, 17, 25, 22, 21, 24, 23, 20]

def k(keit, m, sk):
    return keit[(keit.index(sk) + m) % 26]

def lamb(l,x):
	return l.index(x)

def lambpos(l, x):
	return l[x]

def decr(c):
    mas = []
    for i in range(0, len(c)):
        m1 = i % 26
        m2 = 0
        if (i >= 26):
            m2 = (i - m1) // 26
        step1 = k(ro, m1+k1, abc.index(cipher[i]))
        step2 = lambpos(L1, step1)
        step3 = k(ro,-m1-k1, step2)
        step4 = k(ro,m2+k2, step3)
        step5 = lambpos(L2, step4)
        step6 = k(ro, -m2-k2, step5)

        ref = s.index(step6)

        step1 = k(ro ,m2+k2, ref)
        step2 = lamb(L2, step1)
        step3 = k(ro, -m2-k2, step2)
        step4 = k(ro, m1+k1, step3)
        step5 = lamb(L1, step4)
        step6 = k(ro, -m1-k1, step5)
        # print(ABC[l])
        mas.append(abc[step6])
    return mas


mas = decr(cipher)
print("".join(mas))
