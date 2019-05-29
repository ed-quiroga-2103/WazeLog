:-include('Dijkstra_Prolog.pl').

/*
Regla routes
Determina la ruta mas corta para ir de un destino a otro
Entrada: lista
Salida: ruta mas corta o de lo contrario su respectivo mensaje de error
*/

routes([]).
routes([A | R]):- dijk(A,R), routes(R).
dijk(B, [C |_]):- go(B,C).
dijk(_, []).
