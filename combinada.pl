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