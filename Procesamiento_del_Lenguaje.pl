:-include('Prolog_Test.pl').
:-include('Reglas.pl').

%run:- write("Ingrese la oracion:"),nl,read_line_to_codes(user_input,Cs), atom_codes(A, Cs), atomic_list_concat(L, ' ', A), validacion(L).

run:- write('Bienvenido a WazeLog la mejor lógica de llegar a su destino. Porfavor indíqueme donde se encuentra.'),nl,read_line_to_codes(user_input,Cs), atom_codes(A, Cs), atomic_list_concat(L, ' ', A), aux_run(L,[]).

aux_run(O,Z):- ( oracion(O) -> obtener_ubicacion(O,W), concatenar([W],Z,U), write('Muy bien, ¿Cual es su destino?'),nl,read_line_to_codes(user_input,Cs), atom_codes(A, Cs), atomic_list_concat(L, ' ', A), aux_run2(L,U)
               ; validacion_aux(O) ).

aux_run2(O,Z):- ( oracion(O) -> obtener_ubicacion(O,W), concatenar([W],Z,U), write('Excelente, ¿Tiene algún destino intermedio?'),nl,read_line_to_codes(user_input,Cs), atom_codes(A, Cs), atomic_list_concat(L, ' ', A), intermedios(L,U)
               ; validacion_aux(O) ).

validacion(L):- ( oracion(L)-> true
              ; validacion_aux(L) ).

validacion_aux(T):- ( list_butlast(T,S),reverse(S,[],X),concatenar([cartago],X,P),reverse(P,[],E), oracion(E) -> validacion_aux2(T)
                     ; write('No entendi, podria volverlo a escribir siendo un poco mas especifico si no es mucha molestia') ).

validacion_aux2(T):- ( last_element(T,X),lugar([X]) -> write('no se que pasa')
                     ; write('El lugar indicado no se encuentra en la base de datos') ).


obtener_ubicacion(T,L):- ( oracion(T),last_element(T,X),lugar([X]) -> last_element(T,L)
                         ; validacion_aux(T) ).

intermedios(T,Y):- ( T = [si]-> intermedios_aux2(Y)
                   ; T = [no]-> reverse(Y,[],X), modificacion(X,[])
                   ; intermedio_aux(T,Y) ).

intermedios_aux2(Y):- write('¿Cual seria?'),nl,read_line_to_codes(user_input,Cs), atom_codes(A, Cs), atomic_list_concat(L, ' ', A), intermedio_aux(L,Y).

intermedio_aux(T,Y):- ( oracion(T), last_element(T,X), sustantivo([X]) -> intermedio_aux3(X,Y)
                      ; validacion_aux(T) ).

intermedio_aux3(T,Y):- write('¿Cual ' + T ),nl,read_line_to_codes(user_input,Cs), atom_codes(A, Cs), atomic_list_concat(L, ' ', A), intermedio_aux4(L,Y).

intermedio_aux4(T,Y):- ( list_butlast(T,S),reverse(S,[],L),concatenar([cartago],L,P),reverse(P,[],E), oracion(E) -> intermedio_aux5(T,Y)
                       ; validacion_aux(T) ).

intermedio_aux5(T,Y):- last_element(T,X), write('Donde se encuentra '+ X +'?'),nl,read_line_to_codes(user_input,Cs), atom_codes(A, Cs),atomic_list_concat(L, ' ', A), intermedio_aux6(L,Y).

intermedio_aux6(T,Y):- (oracion(T), last_element(T,X), lugar([X]) -> intermedio_aux7(T,Y)
                      ; validacion_aux(T) ).

intermedio_aux7(T,Y):- last_element(T,X), lugar([X]),concatenar([X],Y,Z), write('¿Algun destino intermedio?'),nl,read_line_to_codes(user_input,Cs), atom_codes(A, Cs), atomic_list_concat(L, ' ', A), intermedios(L,Z).


modificacion(L,Y):- first_element(L,X),concatenar([X],Y,Z), aux_modificacion(L,Z).

aux_modificacion(L,Z):- cola(L,X), cola(X,T), reverse(T,[],R), concatenar(R,Z,U),aux_modificacion2(L,U).

aux_modificacion2(L,Z):- cola(L,X), first_element(X,T), concatenar([T],Z,U), reverse(U,[],R), write(R).
