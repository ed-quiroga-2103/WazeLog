%Reglas

/*
Regla miembro
Determina si un elemento se encuentra dentro del otro elemento
Entrada: elemento que se desea verificar y la lista en la cual se desea buscar
Salida: true si es miembro, false si no lo es
*/

miembro(X, [X|_]).
miembro(X, [_|Z]) :- miembro(X,Z).

/*
Regla and
Determina valida la verasidad de los hechos expuestos
Entrada: 2 hechos
Salida: true si ambos hechos se cumplen, false si no
*/

and(true,true).
and(false,_):-false.
and(_,false):-false.

/*
Regla last_element
Determina el ultimo elemento de una lista
Entrada: lista y variable
Salida: ultimo elemento de la lista
*/

last_element([X],X).
last_element([_|T],Y):- last_element(T,Y).

/*
Regla list_butlast
Determina elimina el ultimo elemento de una lista
Entrada: lista y varible
Salida: lista sin el ultimo elemento
*/

list_butlast([X|Xs], Ys) :- list_butlast_prev(Xs, Ys, X).

list_butlast_prev([], [], _).
list_butlast_prev([X1|Xs], [X0|Ys], X0) :- list_butlast_prev(Xs, Ys, X1).

/*
Regla insertaFinal
Inserta un elemento al final de una lista
Entrada: elemento que se desea agregar y la lista que se planea agregar el elemento
Salida: lista con el elemento al final
*/

insertaFinal(X,L,Z) :- L=[], Z is [X].
insertaFinal(X,[L|Lr],Z) :- insertaFinal(X,Lr,Z1), Z is [L|Z1].

/*
Regla concatenar
Concatena dos listas
Entrada: dos listas y una variable
Salida: variable que contiene las dos listas concatenadas
*/

concatenar([],L,L).
concatenar([X|M],L,[X|Z]):-concatenar(M,L,Z).

/*
Regla reverse
Invierte una lista
Entrada: la lista que se desea invertir, una lista vacia, una variable
Salida: varible que contiene la lista invertida
*/

reverse([], Zs, Zs).
reverse([X|Xs], Ys, Zs):- reverse(Xs, [X|Ys], Zs).

/*
Regla first_element
Determina el primer elemento de una lista
Entrada: una lista y una variable
Salida: variable que contiene el primer elemento de una lista
*/

first_element([X|_], X).

/*
Regla agregar
Agrega un elemento a una lista
Entrada: elemento que se desea agregar, lista y una variable
Salida: variable que contiene la lista con el nuevo elemento
*/

agregar(X,L1,[X|L1]).

/*
Regla cola
Determina la cola de una lista
Entrada: lista y una variable
Salida: variable que contiene la cola de la lista
*/

cola([_|T],T).

/*
Regla presentacion
Divide una lista en sublistas de pares ordenados
Entrada: lista y variable
Salida: variable que contiene una lista con sublistas que a su vez contienen pares ordenados
*/

presentacion(L,X):- ( L = [] -> reverse(X,[],Y), write(Y)
                    ; cola(L,P), P = [] -> first_element(L,C), agregar([C,[]],X,Q), reverse(Q,[],Y), write(Y)
                    ; aux_presentacion(L,X) ).

aux_presentacion(L,X):- first_element(L,Y),cola(L,R), first_element(R,O), agregar([Y,O],X,Q),presentacion(R,Q).

/*
Regla split_to_list
Agrega a una lista un string separado por comas
Entrada: string y variable
Salida: variable que contiene la lista con el string
*/

split_to_list(X):- split_string(X, " ", "\s", L),write(L).
