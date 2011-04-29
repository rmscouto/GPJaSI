subclass(b,a).
calls(a,b).
calls(z,l).
father(A,B) :- subclass(B,A) , calls(A,B).
