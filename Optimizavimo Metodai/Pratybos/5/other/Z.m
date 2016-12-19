function a = Z(t,x,d)
y = x + t*d;
if cons1(y) >= 0
    a = fun1(y) + 2*cons1(y);
else
    a = fun1(y);
end


function f = fun1(x)
f = 100*(x(2)-x(1)^2)^2 + (1 - x(1))^2;
function g = cons1(x)
g = x(1)^2 + x(2)^2 - 1.5;
function df = grudfun1(x)
df1 = -400*(x(2) - x(1)^2)*x(1) + 2*x(1) - 2;
df2 = 200*(x(2) - x(1)^2);
df = [df1;df2];
function dg = grudcons1(x);
dg = [2*x(1), 2*x(2)];
