%Base de Datos
arc(a,b).
arc(b,c).
arc(c,d).
arc(d,e).

connection(X,Y):- arc(X,Y).
connection(X,Y):- arc(X,Z), connection(Z,Y).
