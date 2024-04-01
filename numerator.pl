% auxiliary predicate
len([],0).
len([_|X],L):-len(X,L1),L is L1 + 1 .

append(List1, List2, List1AndList2,Cad,CadR):-append(List1, List2, List1AndList2).

reverse([], []).
reverse([X], List):-
    reverse([], List1),
    append(List1, [X], List).
reverse([L | R], List):-
    reverse(R, List1),
    append(List1, [L], List), !.

delete_zero_left([], []).

delete_zero_left([X], List):-
    append([X], [], List).
delete_zero_left([L | R], List):-
    (
        L = 0
    ->
        delete_zero_left(R, List)
    ;
        append([L | R], [], List)
    ), !.

to_array(N, List):-
    (
        N >= 10
    ->
        N1 is N mod 10,
        N2 is N // 10,
        to_array(N2, List1),
        append(List1, [N1], List)
    ;
        append([N], [], List)
    ).


% initial predicate to call the GCL   
numeral(N, Number):-
    to_array(Number, List),
    numeral(M, List, []),
    atomic_list_concat(M, ' ', N).

numeral(N, LRec, RRec):-
    numeral_millones(N, LRec, RRec).

numeral_millones(N, LRec1, RRec):-
    delete_zero_left(LRec1, LRec),
    len(LRec, N1),
    (
        N1 > 6
    ->
        reverse(LRec, RLRec),
        append([U, D, C, UM, DM, CM], R, RLRec),
        (
            [U, D, C, UM, DM, CM] \= [0, 0, 0, 0, 0, 0]
        ->
            reverse([U, D, C, UM, DM, CM], M),
            numeral_millares(NC, M, []),
            R \= [0],
            (   
                R = [1]
            ->
                append([un, millon], NC, N)
            ;   
                reverse(R, RR),
                numeral_millares(NM, RR, []),
                (
                    append(X, [uno], NM)
                ->
                    append(X, [un], NMF),
                    append(NMF, [millones], FNM)
                ;   
                    append(NM, [millones], FNM)
                ),
                
                append(FNM, NC, N)
            )
        ;
            R \= [0],
            (   
                R = [1]
            ->
                append([un, millon], [], N)
            ;   
                reverse(R, RR),
                numeral_millares(NM, RR, []),
                (
                    append(X, [uno], NM)
                ->
                    append(X, [un], NMF),
                    append(NMF, [millones], FNM)
                ;   
                    append(NM, [millones], FNM)
                ),
                N = FNM
            )    
        )
    ;
        numeral_millares(N, LRec, RRec)
    ), !.

numeral_millares(N, LRec1, RRec):-
    delete_zero_left(LRec1, LRec),
    len(LRec, N1),
    N1 < 7,
    (
        N1 > 3
    ->
        reverse(LRec, RLRec),
        append([U, D, C], R, RLRec),
        (
            [U, D, C] \= [0, 0, 0]
        ->
            reverse([U, D, C], M),
            numeral_centenas(NC, M, []),
            R \= [0],
            (   
                R = [1]
            ->
                append([mil], NC, N)
            ;   
                reverse(R, RR),
                numeral_centenas(NM, RR, []),
                (
                    centenas_millares_especiales(X, Y, []),
                    append(Z, Y, NM)
                ->
                    append(Z, X, NMR),
                    append(NMR, [mil], FNM),
                    append(FNM, NC, N)
                ;
                    append(NM, [mil], FNM),
                    append(FNM, NC, N)
                )
            )
        ;
            R \= [0],
            (   
                R = [1]
            ->
                append([mil], [], N)
            ;   
                reverse(R, RR),
                numeral_centenas(NM, RR, []),
                (
                    centenas_millares_especiales(X, Y, []),
                    append(Z, Y, NM)
                ->
                    append(Z, X, FNM),
                    append(FNM, [mil], N)
                ;
                    append(NM, [mil], FNM),
                    append(FNM, [], N)
                )
            )    
        )
    ;
        numeral_centenas(N, LRec, RRec)
    ), !.

    
numeral_centenas(N,LRec,RRec):-len(LRec,L),number(N,1,L,LRec,RRec), !.


% principal predicate
number([cero],1,1)--> [0],!.
number(N,REC, LEN)--> [0],{LEN1 is LEN- 1},number(N,REC, LEN1),!.
number([N],REC,LEN)--> digit(N,REC,LEN),!.
number([N],REC,LEN)--> teen(N,REC,LEN),!.
number(N,REC,LEN)--> decenas(N,REC,LEN),!.
number(N,REC,LEN)--> centenas(N,REC,LEN),!.


decenas([N1,y,N2],REC,LEN)-->  dec(N1,REC,LEN),{REC1 is REC+1},digit(N2,REC1,LEN).
decenas([N1],1,2)-->  dec(N1,1,2),[0].
    

centenas([cien],1,3)-->  [1],[0],[0].
centenas([N1|N2],REC,LEN)-->  cen(N1,REC,LEN),{LEN1 is LEN-1},decenas(N2,REC,LEN1).
centenas([N1,N2],REC,LEN)-->  cen(N1,REC,LEN),{LEN1 is LEN-1},teen(N2,REC,LEN1).
centenas([N1,N2],REC,LEN)-->  cen(N1,REC,LEN),[0],{LEN1 is LEN-2},digit(N2,REC,LEN1).
centenas([N1],1,3)-->  cen(N1,1,3),[0],[0].

% Primitive Cases
digit(uno,X,X)-->[1].
digit(dos,X,X)-->[2].
digit(tres,X,X)-->[3].
digit(cuatro,X,X)-->[4].
digit(cinco,X,X)-->[5].
digit(seis,X,X)-->[6].
digit(siete,X,X)-->[7].
digit(ocho,X,X)-->[8].
digit(nueve,X,X)-->[9].

teen(diez,_,2)-->[1],[0].
teen(once,_,2)-->[1],[1].
teen(doce,_,2)-->[1],[2].
teen(trece,_,2)-->[1],[3].
teen(catorce,_,2)-->[1],[4].
teen(quince,_,2)-->[1],[5].
teen(dieciseis,_,2)-->[1],[6].
teen(diecisiete,_,2)-->[1],[7].
teen(dieciocho,_,2)-->[1],[8].
teen(diecinueve,_,2)-->[1],[9].

teen(veinte,_,2)-->[2], [0].
teen(veintiuno,_,2)-->[2], [1].
teen(veintidos,_,2)-->[2], [2].
teen(veintitres,_,2)-->[2], [3].
teen(veinticuatro,_,2)-->[2], [4].
teen(veinticinco,_,2)-->[2], [5].
teen(veintiseis,_,2)-->[2], [6].
teen(veintisiete,_,2)-->[2], [7].
teen(veintiocho,_,2)-->[2], [8].
teen(veintinueve,_,2)-->[2], [9].

dec(treinta,1,2)-->[3].
dec(cuarenta,1,2)-->[4].
dec(cincuenta,1,2)-->[5].
dec(sesenta,1,2)-->[6].
dec(setenta,1,2)-->[7].
dec(ochenta,1,2)-->[8].
dec(noventa,1,2)-->[9].

cen(ciento,1,3)-->[1].
cen(doscientos,1,3)-->[2].
cen(trescientos,1,3)-->[3].
cen(cuatrocientos,1,3)-->[4].
cen(quinientos,1,3)-->[5].
cen(seiscientos,1,3)-->[6].
cen(setecientos,1,3)-->[7].
cen(ochocientos,1,3)-->[8].
cen(novecientos,1,3)-->[9].

centenas_millares_especiales([un]) --> [uno].
centenas_millares_especiales([y, un]) --> [y, uno].
centenas_millares_especiales([veintiun]) --> [veintiuno].