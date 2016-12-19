function [x,fval,exitflag,output] = optfunction()
    total = 0;
    v = [1 1 1]; #startinis taškas
    #v  = [0.1, 0, 0.2]; #studento nr taškas
    for i = 1:-0.001:0.0
          [x,fval,exitflag,output] = fminunc(@goal,[v(1) v(2) v(3) i]);
           if(0.4 < x(1) && x(1)< 0.429)
            i
            x
            fval
            output
            total = total+1;
           endif
    end
    total
end
