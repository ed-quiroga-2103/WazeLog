:-include('Prolog_Test.pl').
:-include('Reglas.pl').

run:- write("Escriba perro:"),nl,read_line_to_codes(user_input,Cs), atom_codes(A, Cs), atomic_list_concat(L, ' ', A), validacion(L).

validacion(L):- ( oracion(L)-> write('La oracion es correcta')
              ; validacion_aux(L) ).

validacion_aux(T) :- ( last_element(T,X),lugar([X])-> write('No entendi, podria volverlo a escribir siendo un poco mas especifico si no es mucha molestia')
             ; last_element(T,X),sustantivo([X])-> write('No entendi, podria volverlo a escribir siendo un poco mas especifico si no es mucha molestia')
             ; write('El lugar o ubicacion indicado no se encuentra en la base de datos') ).
