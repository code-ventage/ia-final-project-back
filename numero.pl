numero(N, OpenList, FinalHole):-
    (
        append(OpenList1, ['millones' | Hole], OpenList)
    ->  millones(N, OpenList1, Hole, FinalHole)
    ;
        (
            append(OpenList2, [un , millon | Hole], OpenList)
        ->  millon_especial(N, OpenList2, Hole, FinalHole)
        ;
            numero_millares(N, OpenList, FinalHole)
        )
    ).

millon_especial(N, OpenList, Hole, FinalHole):-
    OpenList = [],
    Hole = FinalHole,
    N is 1000000.

millon_especial(N, OpenList, Hole, FinalHole):-
    OpenList = [],
    numero_millares(N1, Hole, FinalHole),
    N is 1000000 + N1.

millones(N, OpenList, Hole, FinalHole):-
    numero_millares(N1, OpenList, FinalHole),
    numero_millares(N2, Hole, FinalHole),
    N is N1 * 1000000 + N2.

millones(N, OpenList, Hole, FinalHole):-
    Hole = [],
    numero_millares(N1, OpenList, FinalHole),
    N is N1 * 1000000.

numero_millares(N, OpenList, FinalHole):-
    (append(OpenList1, [mil | Hole], OpenList)
    -> millares(N, OpenList1, Hole, FinalHole)
    ;
    numero_centenas(N, OpenList, FinalHole)).
    
millares(N, OpenList, Hole, FinalHole):-
    numero_centenas(N1, OpenList, FinalHole),
    numero_centenas(N2, Hole, FinalHole),
    N is N1 * 1000 + N2.

millares(N, OpenList, Hole, FinalHole):-
    OpenList = [],
    numero_centenas(N2, Hole, FinalHole),
    N is 1000 + N2.

millares(N, OpenList, Hole, FinalHole):-
    Hole = [],
    numero_centenas(N1, OpenList, FinalHole),
    N is N1 * 1000.

millares(N, OpenList, Hole, FinalHole):-
    OpenList = [],
    Hole = FinalHole,
    N is 1000.

numero_centenas(N)--> centenas(N),!.
numero_centenas(N)--> decenas(N),!.
numero_centenas(N)--> casos_especiales(N),!.

centenas(N)--> cent(D), decenas(N1), {N is D * 100 + N1}.

decenas(N)--> digito(N),[_],!,error().
decenas(N)--> digito(N).
decenas(N)--> teen(N),[_],!,error().
decenas(N)--> teen(N).
decenas(N) --> tens(T),[y], digito(N1),[_],!,error(), {N is T+N1}.
decenas(N) --> tens(T),[y],!, digito(N1), {N is T+N1}.
decenas(N)--> tens(N),[_],!,error().
decenas(N)--> tens(N).


error(L,R):-fail.

casos_especiales(0)--> ([cero]|[cien]|[doscientos]|[trescientos]|[cuatrocientos]|[quinientos]|
                        [seiscientos]|[setecientos]|[ochocientos]|[novecientos])
                        ,[_],!,error().

casos_especiales(0)  --> [cero].
casos_especiales(100)--> [cien].
casos_especiales(200)--> [doscientos].
casos_especiales(300)--> [trescientos].
casos_especiales(400)--> [cuatrocientos].
casos_especiales(500)--> [quinientos].
casos_especiales(600)--> [seiscientos].
casos_especiales(700)--> [setecientos].
casos_especiales(800)--> [ochocientos].
casos_especiales(900)--> [novecientos].

digito(1) --> [uno]. 
digito(2) --> [dos]. 
digito(3) --> [tres].   
digito(4) --> [cuatro].    
digito(5) --> [cinco].    
digito(6) --> [seis].      
digito(7) --> [siete].
digito(8) --> [ocho].     
digito(9) --> [nueve]. 
teen(10) --> [diez].
teen(11) --> [once].
teen(12) --> [doce].
teen(13) --> [trece].
teen(14) --> [catorce].
teen(15) --> [quince].
teen(16) --> [dieciseis].
teen(17) --> [diecisiete].
teen(18) --> [dieciocho].
teen(19) --> [diecinueve].

tens(20) --> [veinte].
tens(30) --> [treinta].
tens(40) --> [cuarenta].
tens(50) --> [cincuenta].
tens(60) --> [sesenta].
tens(70) --> [setenta].
tens(80) --> [ochenta].
tens(90) --> [noventa].


cent(1) --> [ciento].
cent(2) --> [doscientos].
cent(3) --> [trescientos].
cent(4) --> [cuatrocientos].
cent(5) --> [quinientos].
cent(6) --> [sieiscientos].
cent(7) --> [setecientos].
cent(8) --> [ochocientos].
cent(9) --> [novecientos].