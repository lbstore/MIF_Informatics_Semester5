function [ result ] = goal(x)
  result = -x(1)*x(2)*x(3) + 1/x(4)*(2*x(1)*x(2) + 2*x(1)*x(3) + 2*x(2)*x(3) - 1)^2;
end
