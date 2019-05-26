%Reglas

miembro(X, [X|_]).
miembro(X, [_|Z]) :- miembro(X,Z).

and(true,true).
and(false,_):-false.
and(_,false):-false.

last_element([X],X).
last_element([_|T],Y):- last_element(T,Y).
