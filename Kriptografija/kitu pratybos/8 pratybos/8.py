from __future__ import division
from collections import defaultdict

#30 uzduotis 
#1. Tekstas, prasideda raidemis MI
#Jis uzsifruotas naudojant 8 registru generuota rakto srauta.Issifruokite sifra
cipher1 = [241, 225, 136, 198, 167, 216, 92, 6, 245, 11, 76, 103, 168, 100, 33, 242, 224, 128, 196, 161, 218, 65, 10, 246, 26, 88, 101, 166, 123, 45, 250, 227, 132, 192, 175, 218, 84, 0, 243, 0, 87, 103, 164, 121, 42, 239, 237, 146, 198, 187, 220, 76, 29, 255, 5, 93, 111, 186, 121, 55, 232, 237, 138, 192, 171, 218, 86, 15, 251, 26, 86, 103, 187, 106, 61, 245, 253, 146, 216, 187, 220, 77, 14, 233, 5, 93, 116, 160, 113, 49, 252, 224, 128, 223, 175, 192, 79, 24, 233, 13, 79, 97, 167, 117, 52, 250, 244, 147, 211, 189, 199, 83, 2, 233, 30, 88, 106, 160, 125, 49]

cipher2 = [181, 27, 91, 115, 173, 4, 146, 210, 222, 120, 119, 55, 5, 168, 204, 176, 87, 179, 113, 171, 6, 143, 222, 221, 105, 99, 53, 11, 183, 192, 184, 84, 183, 117, 165, 6, 154, 212, 216, 115, 108, 55, 9, 181, 199, 173, 90, 161, 115, 177, 0, 130, 201, 212, 118, 102, 63, 23, 181, 218, 170, 90, 185, 117, 161, 6, 152, 219, 208, 105, 109, 55, 22, 166, 208, 183, 74, 161, 109, 177, 0, 131, 218, 194, 118, 102, 36, 13, 189, 220, 190, 87, 179, 106, 165, 28, 129, 204, 194, 126, 116, 49, 10, 185, 217, 184, 67, 160, 102, 183, 27, 157, 214, 194, 109, 99, 58, 13, 177, 220]
c=[]
n=0
v=0

str=" "
for i in range(len(cipher1)):
	c.append(('{0:08b}'.format(cipher1[i])))
	str+=('{0:08b}'.format(cipher1[i]))
	n+=c[i].count('0')
	v+=c[i].count('1')
c00=0
c01=0
c10=0
c11=0
for i in range(len(str)):
	if (i<len(str)):
		if((str[i-1]=='0') and (str[i]=='0')):
			c00 = c00+1
		if((str[i-1]=='0') and (str[i]=='1')):
			c01 = c01+1
		if((str[i-1]=='1') and (str[i]=='0')):
			c10 = c10+1
		if((str[i-1]=='1') and (str[i]=='1')):
			c11 = c11+1
print "bitu poros"			
print "00---",c00
print "01---",c01
print "10---",c10
print "11---",c11

print "0----------1"
print "0---",n
print "1---",v
print "is viso---", n+v


print "pavienis testas"
x1=v-n
t1 = float(pow(x1,2)/(v+n))
print float(t1)

print "bitu poru testas"
x1= pow(c00,2)
x2= pow(c01,2)
x3= pow(c10,2)
x4= pow(c11,2)
x5= pow(n,2)
x6= pow(v,2)
t1= (4/1111)*float(x1+x2+x3+x4)
t2= (2/1112)*float(x5+x6)+1
t= t1-t2
print t

print"autokoreliacijos testas"
'''
1<=d<=556
nuo i=1 iki 557
'''

d=3
count = 0
for i in range(0,len(str)-d):
	if ((str[i]) != (str[i+d-1])):
		count +=1
#print count
#1
#559
ats = (2*count-len(str)+d)/pow((len(str)-d),0.5)
print ats

	#print c[i].count('0')


#print n
#print v

#print n2
#print v2
#c
#537
#575
#t1 1.29
#t2 3.68
#c2
#588
#524

#T1 65.54
#T2 14.87



