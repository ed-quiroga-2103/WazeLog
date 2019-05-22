%Reglas Gramaticales
oracion(O):- sintagma_nominal(SN), sintagma_verbal(SV), append(SN,SV,O).
oracion(O):- sintagma_nominal(O).
oracion(O):- sintagma_verbal(O).
oracion(O):- respuesta(O).

respuesta(R):- afirmacion(R).
respuesta(R):- negacion(R).

sintagma_nominal(SN):- nombre(SN).
sintagma_nominal(SN):- saludo(SAL),sustantivo(S),append(SAL,S,SN).
sintagma_nominal(SN):- articulo(A), nombre(N), append(A,N,SN).
sintagma_nominal(SN):- articulo(A), sustantivo(S), append(A,S,SN).
sintagma_nominal(SN):- lugar(SN).
sintagma_nominal(SN):- sustantivo(SN).
sintagma_nominal(SN):- preposicion(P),lugar(L),append(P,L,SN).
sintagma_nominal(SN):- expresion(E),preposicion(P),append(E,P,SN).


sintagma_verbal(SV):- verbo(V), sintagma_nominal(SN), append(V,SN,SV).
sintagma_verbal(SV):- verbo(V), sintagma_verbal2(SV2), append(V,SV2,SV).
sintagma_verbal2(SV2):- indefinido(I), sintagma_verbal3(SV3), append(I,SV3,SV2).
sintagma_verbal3(SV3):- verbo(V), sintagma_nominal(SN), append(V,SN,SV3).
sintagma_verbal(SV):- verbo(SV).
sintagma_verbal(SV):- verbo_pronomial(VP),sintagma_verbal2(SV2),append(VP,SV2,SV).
sintagma_verbal2(SV2):- verbo(V),sintagma_nominal(SN),append(V,SN,SV2).

%Vocabulario
articulo([el]).
articulo([al]).
nombre([gato]). nombre([perro]). nombre([pescado]). nombre([carne]).
verbo([estoy]).
verbo([tengo]).
verbo([pasar]).
verbo([gustaria]).
verbo([ubica]).
verbo([utilizar]).
saludo([hola]).
nombre([racso]).
adjetivo([rico]).
determinante([mala]).
lugar([cartago]).
lugar([tresrios]).
lugar([sanjose]).
preposicion([en]).
preposicion([por]).
sustantivo([wazelog]).
sustantivo([automercado]).
indefinido([que]).
sustantivo([supermercado]).
verbo_pronomial([me]).
verbo_pronomial([se]).
afirmacion([si]).
negacion([no]).
expresion([muchasgracias]).
