:-include('Prolog_Test.pl').
:-include('Reglas.pl').
:-include('Ruta.pl').
:-include('BaseConocimientos.pl').

/*
Regla run
Inicia el programa
Entrada:
Salida: La ruta más corta para llegar al destino deseado
*/

run:- write('Bienvenido a WazeLog la mejor lógica de llegar a su destino. Porfavor indíqueme donde se encuentra.'),nl,read_line_to_codes(user_input,Cs), atom_codes(A, Cs), atomic_list_concat(L, ' ', A), aux_run(L,[]).

aux_run(O,Z):- ( oracion(O) -> obtener_ubicacion(O,W), concatenar([W],Z,U), write('Muy bien, ¿Cual es su destino?'),nl,read_line_to_codes(user_input,Cs), atom_codes(A, Cs), atomic_list_concat(L, ' ', A), aux_run2(L,U)
               ; validacion_aux(O) ).

aux_run2(O,Z):- ( oracion(O) -> obtener_ubicacion(O,W), concatenar([W],Z,U), write('Excelente, ¿Tiene algún destino intermedio?'),nl,read_line_to_codes(user_input,Cs), atom_codes(A, Cs), atomic_list_concat(L, ' ', A), intermedios(L,U)
               ; validacion_aux(O) ).

/*
Regla validacion
Determina la validez de la oracion
Entrada: lista con el input separado por comas
Salida: true si la oracion es correcta, o en caso contrarios su respectivo mensaje de error
*/


validacion(L):- ( oracion(L)-> true
              ; validacion_aux(L) ).

validacion_aux(T):- ( list_butlast(T,S),reverse(S,[],X),concatenar([cartago],X,P),reverse(P,[],E), oracion(E) -> validacion_aux2(T)
                     ; write('No entendi, podria volverlo a escribir siendo un poco mas especifico si no es mucha molestia') ).

validacion_aux2(T):- ( last_element(T,X),lugar([X]) -> write('Disculpe, no entendi su respuesta')
                     ; write('El lugar indicado no se encuentra en la base de datos') ).

/*
Regla obtener_ubicacion
Obtiene la ubicacion de la oracion
Entrada: oracion y variable
Salida: variable que contiene la ubicacion de la oracion
*/

obtener_ubicacion(T,L):- ( oracion(T),last_element(T,X),lugar([X]) -> last_element(T,L)
                         ; validacion_aux(T) ).

/*
Regla intermedios
Loops que se encarga de los destinos intermedios
Entrada: oracion y variable
Salida: variable con la lista de todos los destinos intermedios
*/

intermedios(T,Y):- ( T = [si]-> intermedios_aux2(Y)
                   ; T = [no]-> reverse(Y,[],X), modificacion(X,W)
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

/*
Regla modificaion
Se encarga de modificarla la lista para que su contenido siga la secuencia: origen,destinos intermedios,destino
Entrada: lista y variable
Salida: lista modificada con su respectiva secuencia
*/

modificacion(L,Y):- first_element(L,X),concatenar([X],Y,Z), aux_modificacion(L,Z).

aux_modificacion(L,Z):- cola(L,X), cola(X,T), reverse(T,[],R), concatenar(R,Z,U),aux_modificacion2(L,U).

aux_modificacion2(L,Z):- cola(L,X), first_element(X,T), concatenar([T],Z,U), reverse(U,[],R), routes(R).
