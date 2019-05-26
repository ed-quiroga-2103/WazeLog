:-include('Prolog_Test.pl').
:-include('Base_de_datos.pl').

run:- write("Escriba perro:"),nl,read_line_to_codes(user_input,Cs), atom_codes(A, Cs), atomic_list_concat(L, ' ', A), test(L).

miembro(X, [X|_]).
miembro(X, [_|Z]) :- miembro(X,Z).

and(true,true).
and(false,_):-false.
and(_,false):-false.

last_element([X],X).
last_element([_|T],Y):- last_element(T,Y).

test(L):- ( oracion(L)-> write('La oracion es correcta')
              ; write('No entiendo, por favor podria ser un poco más especifico') ).

ubicacion([cartago]).
ubicacion([liberia]).
ubicacion([sancarlos]).

este([supermercado]).
este([pali]).
este([farmacia]).

prueba(T) :- ( last_element(T,X),ubicacion([X])-> write('No entendi, podria volverlo a escribir siendo un poco mas especifico si no es mucha molestia')
             ; last_element(T,X),este([X])-> write('No entendi, podria volverlo a escribir siendo un poco mas especifico si no es mucha molestia')
             ; write('El lugar o ubicacion indicado no se encuentra en la base de datos') ).
