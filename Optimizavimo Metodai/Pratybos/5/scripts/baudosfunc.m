function [ result ] = baudosfunc(x)
%   b(x) = (1/r)*g(x)^2
    result = 1/x(4)*(2*x(1)*x(2) + 2*x(1)*x(3) + 2*x(2)*x(3) - 1)^2;
end

