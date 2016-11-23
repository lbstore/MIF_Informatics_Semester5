︠b01ae0cf-2d93-4eb8-a75b-183ab77f6729s︠
var("y z")
z = (1 -2*x*y)/(2* (x + y))
fgoal = x * y * z
#finit = 2*x*y + 2*x*z + 2*y*z -1 = 0
fgoal

ans = minimize(-fgoal,[1,1])
print ans
︡a3501572-14b1-4318-a69d-e0bc5181a14c︡{"stdout":"(y, z)\n"}︡{"stdout":"-1/2*(2*x*y - 1)*x*y/(x + y)\n"}︡{"stdout":"Optimization terminated successfully.\n         Current function value: -0.068041\n         Iterations: 4\n         Function evaluations: 5\n         Gradient evaluations: 5\n"}︡{"stdout":"(0.4082372629230197, 0.4082372629230197)\n"}︡{"done":true}︡
