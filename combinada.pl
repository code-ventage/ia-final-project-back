% Predicado para convertir una lista de átomos a una lista con números convertidos a enteros
convertir_lista([], []).
convertir_lista([A|As], [N|Ns]) :-
    (
      atom_number(A, N), % Intentar convertir a número
      number(N) % Verificar si es un número válido
    ;
      N = A % Si no es un número, dejar como átomo
    ),
    convertir_lista(As, Ns).
    
combinada(N, Atom):-
    atomic_list_concat(List, ' ', Atom),
    convertir_lista(List, NList), 
    expression(N, NList, []), !.

expression(Value, OpenList, FinalHole) :-
    times(Value, OpenList, FinalHole).

expression(SumValue, OpenList, FinalHole) :-
    times(Value1, OpenList, ['+'|Hole1]),
    expression(Value2, Hole1, FinalHole),
    SumValue is Value1 + Value2.

expression(ResValue, OpenList, FinalHole) :-
    times(Value1, OpenList, ['-'|Hole1]),
    expression(Value2, Hole1, FinalHole),
    ResValue is Value1 - Value2.

times(Value, OpenList, FinalHole) :-
    element(Value, OpenList, FinalHole).

times(TimesValue, OpenList, FinalHole) :-
    element(Value1, OpenList, ['*'|Hole1]),
    times(Value2, Hole1, FinalHole),
    TimesValue is Value1 * Value2.

times(TimesValue, OpenList, FinalHole) :-
    element(Value1, OpenList, ['/'|Hole1]),
    times(Value2, Hole1, FinalHole),
    Value2 \= 0,
    TimesValue is Value1 / Value2.

element(Value, [Value|FinalHole], FinalHole) :-
    is_real(Value), !.
    
element(Value, ['('|OpenList], FinalHole) :-
    expression(Value, OpenList, [')'|FinalHole]).

is_real(Value):-
    integer(Value).

is_real(Value):-
    float(Value).