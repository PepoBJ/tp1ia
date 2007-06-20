agente(3,2,cero).
%agente(X, Y, S): El objeto agente, eSta en la celda X, Y en la Situacion S
%Vivo(S): indica que el agente eSta vivo.
%visitado(X,Y, S): indica que la celda X, Y fue viSitada
%alimento(X,Y,S): indica que haY comida en la celda X, Y.
%enemigo(X,Y,S):indica que haY enemigo en la celda X, Y.
%nada(X,Y,S):indica que haY nada en la celda X, Y.


%ReglaS Diagn�SticaS
%percepcion([celdaIzq, celdaDer, celArr, celAbajo, energia], S)

energia(E,S) :- percepcion([_,_,_,_,E], S).

alimento(X,Y,S) :- percepcion([_,_,_,1,_], S), agente(I, J, S), abajo(X,Y,I,J), !.
alimento(X,Y,S) :- percepcion([_,_,1,_,_], S), agente(I, J, S), arriba(X,Y,I,J), !.
alimento(X,Y,S) :- percepcion([_,1,_,_,_], S), agente(I, J, S), derecha(X,Y,I,J), !.
alimento(X,Y,S) :- percepcion([1,_,_,_,_], S), agente(I, J, S), izquierda(X,Y,I,J), !.

enemigo(X,Y,S) :- percepcion([_,_,_,2,_], S), agente(I, J, S), abajo(X,Y,I,J), !.
enemigo(X,Y,S) :- percepcion([_,_,2,_,_], S), agente(I, J, S), arriba(X,Y,I,J), !.
enemigo(X,Y,S) :- percepcion([_,2,_,_,_], S), agente(I, J, S), derecha(X,Y,I,J), !.
enemigo(X,Y,S) :- percepcion([2,_,_,_,_], S), agente(I, J, S), izquierda(X,Y,I,J), !.

nada(X,Y,S) :- percepcion([_,_,_,0,_], S), agente(I, J, S), abajo(X,Y,I,J), !.
nada(X,Y,S) :- percepcion([_,_,0,_,_], S), agente(I, J, S), arriba(X,Y,I,J), !.
nada(X,Y,S) :- percepcion([_,0,_,_,_], S), agente(I, J, S), derecha(X,Y,I,J), !.
nada(X,Y,S) :- percepcion([0,_,_,_,_], S), agente(I, J, S), izquierda(X,Y,I,J), !.

%ReglaS CAUSALES

arriba(I, J, X, Y) :- Y = J, previo(X,I).
abajo(I, J, X, Y) :- Y = J, proximo(X,I).
derecha(I, J, X, Y) :- X = I, proximo(Y, J).
izquierda(I, J, X, Y) :- X = I, previo(Y, J).

vivo(S) :- energia(E, S) , (E > 0).

proximo(C,P) :- P is C + 1, C < 4, !.
proximo(4,1).

previo(C,P) :- P is C - 1, C > 1, !.
previo(1,4).

%Valor DE LAS ACCIONES

%%EXCELENTE COMER Y PELEAR
excelente(pelear,S) :- agente(X,Y,S), enemigo(X,Y,S), energia(E, S), E > 5, !.
excelente(comer,S) :- agente(X,Y,S), alimento(X,Y,S).

%MUY BUENO IR A DONDE HAY COMIDA Y ENEMIGOS
muyBueno(irDerecha,S) :- agente(X,Y,S), derecha(I,J,X,Y), alimento(I,J,S), !.
muyBueno(irIzquierda,S) :- agente(X,Y,S),izquierda(I,J,X,Y), alimento(I,J,S), !.
muyBueno(irArriba,S) :- agente(X,Y,S) , arriba(I,J,X,Y), alimento(I,J,S), !.
muyBueno(irAbajo,S) :- agente(X,Y,S) ,abajo(I,J,X,Y), alimento(I,J,S), !.
muyBueno(irDerecha,S) :- agente(X,Y,S), derecha(I,J,X,Y), enemigo(I,J,S), !.
muyBueno(irIzquierda,S) :- agente(X,Y,S),izquierda(I,J,X,Y), enemigo(I,J,S), !.
muyBueno(irArriba,S) :- agente(X,Y,S) , arriba(I,J,X,Y), enemigo(I,J,S), !.
muyBueno(irAbajo,S) :- agente(X,Y,S) ,abajo(I,J,X,Y), enemigo(I,J,S), !.

%BUENO ir a una celda NO VISITADA
bueno(irDerecha,S) :- agente(X,Y,S),derecha(I,J,X,Y), nada(I,J,S), not(visitado(I,J,S)), !.
bueno(irIzquierda,S) :- agente(X,Y,S),izquierda(I,J,X,Y), nada(I,J,S), not(visitado(I,J,S)), !.
bueno(irArriba,S) :- agente(X,Y,S) ,arriba(I,J,X,Y),  nada(I,J,S) , not(visitado(I,J,S)), !.
bueno(irAbajo,S) :- agente(X,Y,S) ,abajo(I,J,X,Y),  nada(I,J,S) , not(visitado(I,J,S)), !.

%MALO ir a una celda VISITADA
malo(irDerecha,S) :- agente(X,Y,S),derecha(I,J,X,Y), nada(I,J,S), visitado(I,J,S), !.
malo(irIzquierda,S) :- agente(X,Y,S),izquierda(I,J,X,Y), nada(I,J,S), visitado(I,J,S), !.
malo(irArriba,S) :- agente(X,Y,S) ,arriba(I,J,X,Y),  nada(I,J,S) , visitado(I,J,S), !.
malo(irAbajo,S) :- agente(X,Y,S) ,abajo(I,J,X,Y),  nada(I,J,S) , visitado(I,J,S), !.


%AXIOMAS DE ESTADO SUCESOR

agente(X,Y,res(irIzquierda,S)) :- agente(I,J,S), izquierda(X,Y,I,J), !.
agente(X,Y,res(irDerecha,S)) :- agente(I,J,S), derecha(X,Y,I,J), !.
agente(X,Y,res(irArriba,S)) :- agente(I,J,S), arriba(X,Y,I,J), !.
agente(X,Y,res(irAbajo,S)) :- agente(I,J,S), abajo(X,Y,I,J), !.
agente(X,Y,res(comer,S)) :- agente(X,Y,S), !.
agente(X,Y,res(pelear,S)) :- agente(X,Y,S), !.

%nada(X,Y,res(irIzquierda,S)) :- nada(I,J,S), izquierda(X,Y,I,J).
%nada(X,Y,res(irDerecha,S)) :- nada(I,J,S), derecha(X,Y,I,J).
%nada(X,Y,res(irArriba,S)) :- nada(I,J,S), arriba(X,Y,I,J).
%nada(X,Y,res(irAbajo,S)) :- nada(I,J,S), abajo(X,Y,I,J).

%No queda enemigo o comida si el agente ya mato o comio en esa celda
nada(X,Y,res(comer,S)) :- agente(X,Y,S), !.
nada(X,Y,res(pelear,S)) :- agente(X,Y,S), !.

nada(X,Y,res(_,S)) :- nada(X,Y,S), !.




alimento(X,Y,res(A,S)) :- alimento(X,Y,S), not(A=comer), !.
enemigo(X,Y,res(A,S)) :- enemigo(X,Y,S), not(A=pelear), !.

visitado(X,Y,res(_A,S)) :- agente(X,Y,S), !.
visitado(X,Y,res(_A,S)) :- visitado(X,Y,S).

siguiente_accion(terminar,S) :- objetivo(S), !.
siguiente_accion(A,S) :- excelente(A,S), !.
siguiente_accion(A,S) :- muyBueno(A,S), !.
siguiente_accion(A,S) :- bueno(A,S), !.
siguiente_accion(A,S) :- malo(A,S), !.

%Definici�n de meta

objetivo(S) :- nada(1,1,S), nada(2,1,S), nada(3,1,S), nada(4,1,S), nada(1,2,S), nada(2,2,S), nada(3,2,S), nada(4,2,S), nada(1,3,S), nada(2,3,S), nada(3,3,S), nada(4,3,S), nada(1,4,S), nada(2,4,S), nada(3,4,S), nada(4,4,S).



%ASI SON LAS PERCEPCIONES QUE SE IRAN AGREGANDO

%percepcion([2, 1, 0, 0, 50], 0 ). %situacion 0
%percepcion([0, 0, 1, 0, 45], res(irIzquierda, 0) ). %situacion 1
%percepcion([0, 0, 1, 0, 55], res(comer, res(irIzquierda, 0)) ). %situacion 2
%percepcion([1, 0, 0, 0, 50], res(irArriba, res(comer, res(irIzquierda, 0))) ). %situacion 3
%percepcion([1, 0, 0, 0, 40], res(pelear, res(irArriba, res(comer, res(irIzquierda, 0)))) ). %situacion 4
%percepcion([0, 0, 0, 0, 35], res(irIzquierda, res(pelear, res(irArriba, res(comer, res(irIzquierda, 0))))) ). %situacion 5
%percepcion([0, 0, 0, 0, 25], res(pelear, res(irIzquierda, res(pelear, res(irArriba, res(comer, res(irIzquierda, 0)))))) ). %situacion 6
%percepcion([0, 0, 0, 0, 20], res(irDerecha, res(pelear, res(irIzquierda, res(pelear, res(irArriba, res(comer, res(irIzquierda, 0))))))) ). %situacion 7
%percepcion([0, 0, 2, 0, 20], res(irDerecha, res(irDerecha, res(pelear, res(irIzquierda, res(pelear, res(irArriba, res(comer, res(irIzquierda, 0)))))))) ). %situacion 8

percepcion([0,0,1,0,35],cero).
percepcion([0,2,1,0,32],res(irArriba,cero)).
percepcion([0,2,1,0,43],res(comer,res(irArriba,cero))).
percepcion([1,2,0,0,39],res(irArriba,res(comer,res(irArriba,cero)))).
percepcion([1,2,0,0,50],res(comer,res(irArriba,res(comer,res(irArriba,cero))))).
percepcion([1,0,0,0,45],res(irIzquierda,res(comer,res(irArriba,res(comer,res(irArriba,cero)))))).
percepcion([1,0,0,0,56],res(comer,res(irIzquierda,res(comer,res(irArriba,res(comer,res(irArriba,cero))))))).
percepcion([2,0,0,0,51],res(irIzquierda,res(comer,res(irIzquierda,res(comer,res(irArriba,res(comer,res(irArriba,cero)))))))).
percepcion([2,0,0,0,62],res(comer,res(irIzquierda,res(comer,res(irIzquierda,res(comer,res(irArriba,res(comer,res(irArriba,cero))))))))).
percepcion([0,0,1,2,56],res(irIzquierda,res(comer,res(irIzquierda,res(comer,res(irIzquierda,res(comer,res(irArriba,res(comer,res(irArriba,cero)))))))))).
percepcion([0,0,1,2,44],res(pelear,res(irIzquierda,res(comer,res(irIzquierda,res(comer,res(irIzquierda,res(comer,res(irArriba,res(comer,res(irArriba,cero))))))))))).
percepcion([0,0,0,0,40],res(irArriba,res(pelear,res(irIzquierda,res(comer,res(irIzquierda,res(comer,res(irIzquierda,res(comer,res(irArriba,res(comer,res(irArriba,cero)))))))))))).
percepcion([0,0,0,0,51],res(comer,res(irArriba,res(pelear,res(irIzquierda,res(comer,res(irIzquierda,res(comer,res(irIzquierda,res(comer,res(irArriba,res(comer,res(irArriba,cero))))))))))))).
percepcion([0,0,1,0,46],res(irDerecha,res(comer,res(irArriba,res(pelear,res(irIzquierda,res(comer,res(irIzquierda,res(comer,res(irIzquierda,res(comer,res(irArriba,res(comer,res(irArriba,cero)))))))))))))).
percepcion([0,0,0,0,42],res(irArriba,res(irDerecha,res(comer,res(irArriba,res(pelear,res(irIzquierda,res(comer,res(irIzquierda,res(comer,res(irIzquierda,res(comer,res(irArriba,res(comer,res(irArriba,cero))))))))))))))).
percepcion([0,0,0,0,53],res(comer,res(irArriba,res(irDerecha,res(comer,res(irArriba,res(pelear,res(irIzquierda,res(comer,res(irIzquierda,res(comer,res(irIzquierda,res(comer,res(irArriba,res(comer,res(irArriba,cero)))))))))))))))).
percepcion([0,0,0,0,48],res(irDerecha,res(comer,res(irArriba,res(irDerecha,res(comer,res(irArriba,res(pelear,res(irIzquierda,res(comer,res(irIzquierda,res(comer,res(irIzquierda,res(comer,res(irArriba,res(comer,res(irArriba,cero))))))))))))))))).
percepcion([0,0,0,0,44],res(irArriba,res(irDerecha,res(comer,res(irArriba,res(irDerecha,res(comer,res(irArriba,res(pelear,res(irIzquierda,res(comer,res(irIzquierda,res(comer,res(irIzquierda,res(comer,res(irArriba,res(comer,res(irArriba,cero)))))))))))))))))).
percepcion([2,0,0,0,40],res(irIzquierda,res(irArriba,res(irDerecha,res(comer,res(irArriba,res(irDerecha,res(comer,res(irArriba,res(pelear,res(irIzquierda,res(comer,res(irIzquierda,res(comer,res(irIzquierda,res(comer,res(irArriba,res(comer,res(irArriba,cero))))))))))))))))))).
percepcion([0,0,0,0,36],res(irIzquierda,res(irIzquierda,res(irArriba,res(irDerecha,res(comer,res(irArriba,res(irDerecha,res(comer,res(irArriba,res(pelear,res(irIzquierda,res(comer,res(irIzquierda,res(comer,res(irIzquierda,res(comer,res(irArriba,res(comer,res(irArriba,cero)))))))))))))))))))).
percepcion([0,0,0,0,28],res(pelear,res(irIzquierda,res(irIzquierda,res(irArriba,res(irDerecha,res(comer,res(irArriba,res(irDerecha,res(comer,res(irArriba,res(pelear,res(irIzquierda,res(comer,res(irIzquierda,res(comer,res(irIzquierda,res(comer,res(irArriba,res(comer,res(irArriba,cero))))))))))))))))))))).
