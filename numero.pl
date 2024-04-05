numero_millones(N, OpenList, FinalHole):-
    (
        append(MillarMillones, [millones | Millares], OpenList)
    ->
        millones(N, MillarMillones, Millares, FinalHole)
    ;
        (
            append([un, millon], Millares, OpenList)
        ->
            Millares \= [],
            numero_millares(N1, Millares, FinalHole),
            N is 1000000 + N1
        ;
            Millares = [],
            N is 1000000
        ) 
    ), !.

millones(N, MillarMillones, Millares, FinalHole):-
    MillarMillones \= [],
    Millares \= [],
    numero_millares(N1, MillarMillones, FinalHole),
    numero_millares(N2, Millares, FinalHole),
    N is N1 * 1000000 + N2.

millones(N, MillarMillones, Millares, FinalHole):-
    MillarMillones = [],
    Millares \= [],
    numero_millares(N2, Millares, FinalHole),
    N is 1000000 + N2.

millones(N, MillarMillones, Millares, FinalHole):-
    MillarMillones \= [],
    Millares = [],
    numero_millares(N1, Millares, FinalHole),
    N is N1 * 1000000.

numero_millares(N, OpenList, FinalHole):-
    (
        append(CentenasMillares, [mil | Centenas], OpenList)
    ->
        (
            centenas_millares_especiales(X, Y, []),
            append(CentenasMillaresL, Y, CentenasMillares)
        ->
            append(CentenasMillaresL, X, CentenasMillaresR),
            millares(N, CentenasMillaresR, Centenas, FinalHole)
        ;
            (
                centenas_millares_especiales(X, _, []),
                append(_, X, CentenasMillares)
            ->
                false
            ;
                millares(N, CentenasMillares, Centenas, FinalHole)
            )
        )
    ;
        numero_centenas(N, OpenList, FinalHole)
    ), !.

millares(N, CentenasMillares, Centenas, FinalHole):-
    CentenasMillares \= [],
    Centenas \= [],
    numero_centenas(N1, CentenasMillares, FinalHole),
    numero_centenas(N2, Centenas, FinalHole),
    N is N1 * 1000 + N2.

millares(N, CentenasMillares, Centenas, FinalHole):-
    CentenasMillares \= [], 
    Centenas = [],
    numero_centenas(N1, CentenasMillares, FinalHole),
    N is N1 * 1000.

millares(N, CentenasMillares, Centenas, FinalHole):-
    CentenasMillares = [],
    Centenas \= [],
    numero_centenas(N2, Centenas, FinalHole),
    N is 1000 + N2.

millares(N, CentenasMillares, Centenas, FinalHole):-
    CentenasMillares = FinalHole,
    Centenas = FinalHole,
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
teen(20) --> [veinte].
teen(21) --> [veintiuno].
teen(22) --> [veintidos].
teen(23) --> [veintitres].
teen(24) --> [veinticuatro].
teen(25) --> [veinticinco].
teen(26) --> [veintiseis].
teen(27) --> [veintisiete].
teen(28) --> [veintiocho].
teen(29) --> [veintinueve].

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

centenas_millares_especiales([uno]) --> [un].
centenas_millares_especiales([y, uno]) --> [y, un].
centenas_millares_especiales([veintiuno]) --> [veintiun].