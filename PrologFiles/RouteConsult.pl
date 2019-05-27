%ruta(sanjose,cartago).
%ruta(cartago,heredia).
%ruta(heredia,limon).

ruta(limon,heredia).
ruta(heredia,cartago).
ruta(cartago,sanjose).

consultaDestino(Destino,Partida):-ruta(Destino,Partida).
consultaDestino(Destino,Partida):-ruta(Destino,Intermedio), consultaDestino(Intermedio,Partida), write(Intermedio), write('\n').
consultaDestino(Destino,Partida):-ruta(Partida,Intermedio), consultaDestino(Intermedio,Destino), write(Intermedio), write('\n').


consulta:-
    write('cual es su destino?'),
    read(EntDest),

    write('de donde parte?'),
    read(EntPart),
    write('su ruta es: \n'),
    consultaDestino(EntDest, EntPart),
    write(EntDest).
