
A='abcdefghijklmnopqrstuvwxyz'
def i_skaiciu(text):
    t=''
    for r in text:
        if r in A:
            ind=A.index(r)+1
            if ind<10: t=t+'0'+str(ind)
            else: t=t+str(ind)
    return int(t,10)  

def i_teksta(M):
    n=M
    text=''
    while n>0:
        ind=n%100
        ind=ind-1
        if (ind>=0) & (ind<len(A)):
            text+=A[ind]
            n=(n-ind+1)//100
        else:
            text+='?'
            n=(n-ind+1)//100            
    return text[::-1]


def powermod(a, m, n):
    assert m >= 0;
    assert n >= 1;
    ans = 1
    apow = a
    while m != 0:
        if m%2 != 0:
            ans = (ans * apow) % n            
        apow = (apow * apow) % n              
        m /= 2   
    return ans % n
def gcd(a, b):                                        # (1)
    """
    Returns the greatest commond divisor of a and b.
    Input:
        a -- an integer
        b -- an integer
    Output:
        an integer, the gcd of a and b
    Examples:
    >>> gcd(97,100)
    1
    >>> gcd(97 * 10**15, 19**20 * 97**2)              # (2)
    97L
    """
    if a < 0:  a = -a
    if b < 0:  b = -b
    if a == 0: return b
    if b == 0: return a
    while b != 0: 
        (a, b) = (b, a%b)
    return a
def extended_gcd(aa, bb):
    lastremainder, remainder = abs(aa), abs(bb)
    x, lastx, y, lasty = 0, 1, 1, 0
    while remainder:
        lastremainder, (quotient, remainder) = remainder, divmod(lastremainder, remainder)
        x, lastx = lastx - quotient*x, x
        y, lasty = lasty - quotient*y, y
    return lastremainder, lastx * (-1 if aa < 0 else 1), lasty * (-1 if bb < 0 else 1)
 
def modinv(a, m):
	g, x, y = extended_gcd(a, m)
	if g != 1:
		raise ValueError
	return x % m


n = 2104903025234833471775486482272866481730934732974913;
na = 2104903025234833471775486482272866481730934732974913;
e = 131128777013;
ea = 60180661145;
d = 1971741675466598231173762517132398846963020217286525;
c = 696694583882797655156995004155095940834409199314769;
ca = 748828167974112530841739419803363780008514217329424;
newC = 382671220060022187265594160627251126366431547175100;
N = (e*d) - 1;
while True:
    if ( N % 2 == 0):
        N = N//2;
    else:
        t = N;
        break;
ap = 3;
a = pow(ap,t,n)
#print(a);
i = 1;
while True:
    a_new=pow(a,2,n)    
    if a_new == 1:
        break;
    else:
        a = a_new;
        break;
pa = gcd(a-1,n)
qa = n//pa;
fi = (pa-1)*(qa-1)
dn = modinv(ea,fi)
N = pow (c,d,n);
print(i_teksta(N))
M = pow(ca,dn,n);
print(i_teksta(M));
u = pow(newC,(pa+1)//4,pa)
v = pow(newC,(qa+1)//4,qa);
a = modinv(qa,pa)
b = modinv(pa,qa)
m1 = (u*a*qa+v*b*pa) % n
m2 = (u*a*qa-v*b*pa) % n
m3 = (-u*a*qa+v*b*pa) % n
m4 = (-u*a*qa-v*b*pa) % n
print(i_teksta(m1))
print(i_teksta(m2))
print(i_teksta(m3))
print(i_teksta(m4))
