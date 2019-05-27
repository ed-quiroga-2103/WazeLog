:-include('Prolog_Test.pl').
:-include('Reglas.pl').

run:- write("Ingrese la oracion:"),nl,read_line_to_codes(user_input,Cs), atom_codes(A, Cs), atomic_list_concat(L, ' ', A), validacion(L).

validacion(L):- ( oracion(L)-> true
              ; validacion_aux(L) ).

validacion_aux(T):- ( list_butlast(T,S),reverse(S,[],X),concatenar([cartago],X,P),reverse(P,[],E), oracion(E) -> validacion_aux2(T)
                     ; write('No entendi, podria volverlo a escribir siendo un poco mas especifico si no es mucha molestia') ).

validacion_aux2(T):- ( last_element(T,X),lugar([X]) -> write('no se que pasa')
                     ; write('El lugar indicado no se encuentra en la base de datos') ).


obtener_ubicacion(T,L):- ( oracion(T),last_element(T,X),lugar([X]) -> last_element(T,L)
                         ; validacion_aux(T) ).
