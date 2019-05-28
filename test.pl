prueba([1,2,3,4],10).

evento(brazil,2014).
evento(sudafrica,2010).
evento(alemania,2006).
evento(rusia,alemania).

preg1(P):- P = "Digite la fecha".
preg2(R1,P,B):- (evento(_,R1) -> P = "La fecha es valida", B = true; P = "La fecha no es valida", B =false).

primeroSegundo(L,P,S):-

primero(L,A,B):- A = P, primero


