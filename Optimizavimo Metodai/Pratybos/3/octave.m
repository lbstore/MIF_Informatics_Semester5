c=[3 2];
A=[2,1;1,1;1,0;1,0;0,1];
b=[100;80;40;0;0]
 
lb=[0;0]
ub=[Inf;Inf];
ctype = ["U";"U";"U";"L";"L"]; % indicates upper bound or lower bound
vtype = ["C";"C"]; % continuous variables
 
sense=-1; % maximises
[xopt,zmx]=glpk(c,A,b,lb,ub,ctype,vtype,sense)
