%Reglas Gramaticales
oracion(O):- sintagma_nominal(SN), sintagma_verbal(SV), append(SN,SV,O).
oracion(O):- sintagma_nominal(O).
oracion(O):- sintagma_verbal(O).
oracion(O):- respuesta(O).

respuesta(R):- afirmacion(R).
respuesta(R):- negacion(R).

sintagma_nominal(SN):- saludo(SAL),nombre(N),append(SAL,N,SN).
sintagma_nominal(SN):- articulo(A), nombre(N), append(A,N,SN).
sintagma_nominal(SN):- articulo(A), sustantivo(S), append(A,S,SN).
sintagma_nominal(SN):- lugar(SN).
sintagma_nominal(SN):- nombre(SN).
sintagma_nominal(SN):- preposicion(P),lugar(L),append(P,L,SN).
sintagma_nominal(SN):- expresion(E),preposicion(P),append(E,P,SN).

sintagma_verbal(SV):- verbo(V), sintagma_nominal(SN), append(V,SN,SV).
sintagma_verbal(SV):- verbo(V), sintagma_verbal2(SV2), append(V,SV2,SV).
sintagma_verbal(SV):- verbo_auxiliar(VA),sintagma_verbal2(SV2),append(VA,SV2,SV).

sintagma_verbal2(SV2):- indefinido(I), sintagma_verbal3(SV3), append(I,SV3,SV2).
sintagma_verbal2(SV2):- verbo(V),sintagma_nominal(SN),append(V,SN,SV2).

sintagma_verbal3(SV3):- verbo(V), sintagma_nominal(SN), append(V,SN,SV3).

%Vocabulario
articulo([un]).
articulo([una]).
articulo([al]). 

verbo([estoy]).
verbo([encuentro]).
verbo([hallo]).
verbo([situo]).
verbo([localizo]).
verbo([residuo]).
verbo([voy]).
verbo([ir]).
verbo([dirigo]).
verbo([encamino]).
verbo([tengo]).
verbo([pasar]).
verbo([gustaria]).
verbo([ubica]).
verbo([utilizar]).
verbo([ir]).
verbo([llegar]).

saludo([hola]).
saludo([buenosdias]).
saludo([buenastardes]).
saludo([buenasnoches]).

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
preposicion([con]).
preposicion([de]).
preposicion([desde]).
preposicion([durante]).
preposicion([en]).
preposicion([hacia]).
preposicion([hasta]).
preposicion([para]).
preposicion([por]).

nombre([wazelog]).

sustantivo([automercado]).
sustantivo([supermercado]).
sustantivo([parque]).
sustantivo([motel]).
sustantivo([farmacia]).
sustantivo([restaurante]).
sustantivo([muelle]).

indefinido([que]).

verbo_auxiliar([me]).
verbo_auxiliar([se]).
verbo_auxiliar([quiero]).

afirmacion([si]).
negacion([no]).

expresion([muchasgracias]).
