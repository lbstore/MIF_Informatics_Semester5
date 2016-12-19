clear all;
x0 = [0,0,0];
x1 = [1,1,1];
xs = [0.1,0,0.2]
xb = [0.1,0.09475,0.2];

#f(x0), g(x0),- h(x0)

#f(x1), g(x1), -h(x1)

#f(xs), g(xs), -h(xs)

#[X, OBJ, INFO, ITER, NF, LAMBDA] =sqp (x0, @f, @g, @h);
 %neranda, matyt negali is
%tokio pradinio tasko apskaiciuoti(0, 0, 0)nepalankus taskas
#[X, OBJ, INFO, ITER, NF, LAMBDA] = sqp(x1, @f, @g, @h)
[X, OBJ, INFO, ITER, NF, LAMBDA] = sqp(xb, @f, @g, @h)
  %NF -  tikslo funkcijos kvietimas
