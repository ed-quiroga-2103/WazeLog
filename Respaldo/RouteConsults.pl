conexion(sanjose,cartago).
conexion(cartago,sanjose).
conexion(cartago, heredia).
conexion(heredia,cartago).
conexion(heredia,alajuela).
conexion(alajuela,heredia).
conexion(alajuela,guanacaste).
conexion(guanacaste, alajuela).

camino(Inicio, Destino):-
    conexion(Inicio, Destino).
camino(Inicio, Destino):-
    conexion(Inicio, Intermedio),
    write(Intermedio), nl,
    camino(Intermedio, Destino).

consulta:-
    write('destino?'),
    read(Destino),

    write('punto de partida?'),
    read(Partida),

    write('su ruta es: \n'),
    camino(Partida, Destino).
