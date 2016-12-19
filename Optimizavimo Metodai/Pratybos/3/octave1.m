c = [8; -8; 10; -10;-3;0;0]
a = [
  -1,1,2,-2,0,1,0;
   1,-1,4,-4,0,0,0;
   -1,1,0,0,1,0,1;
   -1,1,0,0,-1,0,0;
     ]
b = [2; -3; 0; -5]
lb = [];
ub = [];
vartype = "CCCCCCC";
ctype = "SSSS";
s = -1;
[xopt, fopt, status ] = glpk (c, a, b, lb, ub, ctype, vartype, s )