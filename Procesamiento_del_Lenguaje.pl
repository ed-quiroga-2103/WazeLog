:-include('Prolog_Test.pl').
:-include('Base_de_datos.pl').

run:- write("Escriba perro:"),nl,read_line_to_codes(user_input,Cs), atom_codes(A, Cs), atomic_list_concat(L, ' ', A), oracion(L).
