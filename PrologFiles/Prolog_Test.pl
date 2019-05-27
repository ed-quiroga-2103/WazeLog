%Reglas Gramaticales
oracion(O):- sintagma_nominal(SN), sintagma_verbal(SV), append(SN,SV,O).
oracion(O):- sintagma_nominal(O).
oracion(O):- sintagma_verbal(O).
oracion(O):- respuesta(O).

respuesta(R):- afirmacion(R).
respuesta(R):- negacion(R).

sintagma_nominal(SN):- nombre(SN).
sintagma_nominal(SN):- saludo(SAL),sustantivo(S),append(SAL,S,SN).
sintagma_nominal(SN):- saludo(SAL),nombre(N),append(SAL,N,SN).
sintagma_nominal(SN):- articulo(A), nombre(N), append(A,N,SN).
sintagma_nominal(SN):- articulo(A), sustantivo(S), append(A,S,SN).
sintagma_nominal(SN):- lugar(SN).
sintagma_nominal(SN):- sustantivo(SN).
sintagma_nominal(SN):- preposicion(P),lugar(L),append(P,L,SN).
sintagma_nominal(SN):- expresion(E),preposicion(P),append(E,P,SN).


sintagma_verbal(SV):- verbo(V), sintagma_nominal(SN), append(V,SN,SV).
sintagma_verbal(SV):- verbo(V), sintagma_verbal2(SV2), append(V,SV2,SV).
sintagma_verbal(SV):- verbo_pronomial(VP),sintagma_verbal2(SV2),append(VP,SV2,SV).
sintagma_verbal(SV):- verbo(SV).

sintagma_verbal2(SV2):- indefinido(I), sintagma_verbal3(SV3), append(I,SV3,SV2).
sintagma_verbal2(SV2):- verbo(V),sintagma_nominal(SN),append(V,SN,SV2).

sintagma_verbal3(SV3):- verbo(V), sintagma_nominal(SN), append(V,SN,SV3).

%Vocabulario
articulo([el]).
articulo([la]).
articulo([los]).
articulo([las]).
articulo([un]).
articulo([una]).
articulo([unos]).
articulo([unas]).
articulo([al]). %contractos
articulo([del]). %contractos
articulo([lo]). %neutro

nombre([oscar]).
nombre([andres]).
nombre([barrios]).
nombre([dilan]).
nombre([racso]).

verbo([estoy]).
verbo([tengo]).
verbo([pasar]).
verbo([gustaria]).
verbo([ubica]).
verbo([utilizar]).

saludo([hola]).
saludo([buenosdias]).
saludo([buenastardes]).
saludo([buenasnoches]).

adjetivo([rico]).

determinante([este]).
determinante([aquel]).
determinante([ese]).
determinante([aquellos]).
determinante([esta]).
determinante([esas]).

lugar([cartago]).
lugar([tresrios]).
lugar([sanjose]).
lugar([musgoverde]).
lugar([corralillo]).
lugar([pacayas]).
lugar([cervantes]).
lugar([pacayas]).
lugar([paraiso]).
lugar([juanvinas]).
lugar([turrialba]).
lugar([cachi]).
lugar([orosi]).

preposicion([a]).
preposicion([ante]).
preposicion([bajo]).
preposicion([con]).
preposicion([de]).
preposicion([desde]).
preposicion([durante]).
preposicion([en]).
preposicion([entre]).
preposicion([excepto]).
preposicion([hacia]).
preposicion([hasta]).
preposicion([mediante]).
preposicion([para]).
preposicion([por]).
preposicion([salvo]).
preposicion([segun]).
preposicion([sin]).
preposicion([sobre]).
preposicion([tras]).

sustantivo([wazelog]).
sustantivo([automercado]).
sustantivo([supermercado]).
sustantivo([parque]).
sustantivo([motel]).
sustantivo([farmacia]).
sustantivo([restaurantes]).
sustantivo([muelle]).
sustantivo([macdonald]).
sustantivo([tacobell]).
sustantivo([burgerking]).
sustantivo([moe]).
sustantivo([lelos]).
sustantivo([lacali]).
sustantivo([boule]).

indefinido([que]).

verbo_pronomial([me]).
verbo_pronomial([se]).

afirmacion([si]).
negacion([no]).

expresion([muchasgracias]).