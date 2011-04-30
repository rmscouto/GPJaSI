son(b,a).
male(a).

father(A,B) :- son(B,A) , male(A).
