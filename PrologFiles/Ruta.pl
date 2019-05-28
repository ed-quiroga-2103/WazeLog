:-include('Dijkstra_Prolog.pl').

routes([]).
routes([A | R]):- dijk(A,R), routes(R).
dijk(B, [C |_]):- go(B,C).
dijk(_, []).
