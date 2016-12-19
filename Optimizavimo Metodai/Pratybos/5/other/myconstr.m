function x = myconstr(x0,H,n)
x = x0;
for i = 1:n
c = grudfun1(x);
A=grudcons1(x);
b=-cons1(x);
d = qp(H,c,A,b);
a = fmin(’Z’,0,2,[],x,d);
x = x + a*d;
end
