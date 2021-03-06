%Reglas

miembro(X, [X|_]).
miembro(X, [_|Z]) :- miembro(X,Z).

and(true,true).
and(false,_):-false.
and(_,false):-false.

last_element([X],X).
last_element([_|T],Y):- last_element(T,Y).

list_butlast([X|Xs], Ys) :- list_butlast_prev(Xs, Ys, X).

list_butlast_prev([], [], _).
list_butlast_prev([X1|Xs], [X0|Ys], X0) :- list_butlast_prev(Xs, Ys, X1).

insertaFinal(X,L,Z) :- L=[], Z is [X].
insertaFinal(X,[L|Lr],Z) :- insertaFinal(X,Lr,Z1), Z is [L|Z1].

concatenar([],L,L).
concatenar([X|M],L,[X|Z]):-concatenar(M,L,Z).

reverse([], Zs, Zs).
reverse([X|Xs], Ys, Zs):- reverse(Xs, [X|Ys], Zs).

first_element([X|_], X).

agregar(X,L1,[X|L1]).

cola([_|T],T).

presentacion(L,X):- ( L = [] -> reverse(X,[],Y), write(Y)
                    ; cola(L,P), P = [] -> first_element(L,C), agregar([C,[]],X,Q), reverse(Q,[],Y), write(Y)
                    ; aux_presentacion(L,X) ).

aux_presentacion(L,X):- first_element(L,Y),cola(L,R), first_element(R,O), agregar([Y,O],X,Q),presentacion(R,Q).


split_to_list(X):- split_string(X, " ", "\s", L),write(L).
