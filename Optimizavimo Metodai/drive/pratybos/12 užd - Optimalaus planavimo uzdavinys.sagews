︠0bb9db37-ea87-4c3f-8680-e56db30a3360︠
def tiesioginis(a, b, c0, c, c1, d1, c2, d2):
    p=MixedIntegerLinearProgram( maximization=True )
    p.set_objective(  c0*p[1] + c*p[2] )
    p.add_constraint( c1*p[1]+d1*p[2], max=a )
    p.add_constraint( c2*p[1]+d2*p[2], max=b )
    p.add_constraint( p[1], min=0 )
    p.add_constraint( p[2], min=0 )
    temp = p.solve()
    return [p.get_values(p[1]), p.get_values(p[2]), p.solve()]

def dualusis(a, b, c0, c, c1, d1, c2, d2):
    u=MixedIntegerLinearProgram( maximization=True )
    u.set_objective(a*u[1] + b*u[2])
    u.add_constraint( c1 * u[1] + c2 * u[2], max=c0)
    u.add_constraint(d1 * u[1] + d2 * u[2], max=c)
    u.add_constraint( u[1], min=0 )
    u.add_constraint( u[2], min=0 )
    temp = u.solve()
    return [u.get_values(u[1]), u.get_values(u[2]), u.solve()]
i = 9
#a = 120; b = 400; c0 = 30; c = 35; c1 = 3; d1 = 5; c2 = 14; d2 = 12
a = 426; b = 526; c0 = 30+2*i; c = 35+i; c1 = 12; d1 = 25; c2 = 41; d2 = 21

f = tiesioginis(a, b, c0, c, c1, d1, c2, d2)
g = dualusis(a, b, c0, c, c1, d1, c2, d2)
print 'f(x*) =', f[2]
print "x*1 = "+str(f[0])
print "x*2 = "+str(f[1])
print 'g(x*) =', g[2]
print "g*1 = "+str(g[0])
print "g*2 = "+str(g[1])
resursas = g[0]
a2 = a
while (resursas == g[0]):
    a2 -= 1
    g2 = dualusis(a2, b, c0, c, c1, d1, c2, d2)
    resursas = g2[0]
print 'a (', a2,

a2 = a
resursas = g[0]
while (resursas == g[0]):
    a2 += 1
    g2 = dualusis(a2, b, c0, c, c1, d1, c2, d2)
    resursas = g2[0]
print a2, ')'
print g2[2]

b2 = b
laikas = g[1]
while (laikas == g[1]):
    b2 -= 1
    g2 = dualusis(a, b2, c0, c, c1, d1, c2, d2)
    laikas = g2[1]
print 'b (', b2,

b2 = b
laikas = g[1]
while (laikas == g[1]):
    b2 += 1
    g2 = dualusis(a, b2, c0, c, c1, d1, c2, d2)
    laikas = g2[1]
print b2, ')'
print g2[2]


︡ec3bf1e7-14f7-4ca5-b7d3-fabe38c9f504︡{"stdout":"f(x*) = 895.948253558\n"}︡{"stdout":"x*1 = 5.43855109961\n"}︡{"stdout":"x*2 = 14.4294954722\n"}︡{"stdout":"g(x*) = 895.948253558\n"}︡{"stdout":"g*1 = 1.0297542044\n"}︡{"stdout":"g*2 = 0.869340232859\n"}︡{"stdout":"a ( 153"}︡{"stdout":" 627 )\n"}︡{"stdout":"1103.52\n"}︡{"stdout":"b ( 357"}︡{"stdout":" 1456 )\n"}︡{"stdout":"1704.58536585\n"}︡
︠b2c672e4-0394-4e34-a5a6-1238bad8f0dc︠
︡d138d8f8-2a9c-44aa-bb95-e0ce5d6279d4︡
︠b3297af5-9f47-444c-a322-1e40f08c4d14︠
︡52d816d5-af75-408d-a70d-3c7c717bbc37︡
︠c93277a2-e229-4393-82c6-117dd7e6f3a2︠









