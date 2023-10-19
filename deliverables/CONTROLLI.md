#STATO GENERALE DEL PROGETTO

===================================


DA FINIRE:
- SISTEMA CONTROLLO ORTOGONALE
- SISTEMA AGGIORNAMENTO MODEL NEL CASO DI MOSSE CHE MODIFICANO IL MODEL TRA UNO STEP E L'ALTRO


===================================

*INTERPRETER*
Sono dei controlli pre-Request


"pass"

- [ ] E' IL TUO TURNO

"placedice"

- [ ] E' IL TUO TURNO: DELETE_ACTION + returnedRequest
- [ ] HO ANCORA AZIONI PIAZZADADO: DELETE_ACTION + returnedRequest
- [ ] DRAFTPOOL NON E' VUOTA: DELETE_ACTION + returnedRequest

"usetool"

- [ ] E' IL TUO TURNO: DELETE_ACTION + returnedRequest
- [ ] HO ANCORA AZIONI TOOLCARD : DELETE_ACTION + returnedRequest

- [ ] DRAFTPOOL NON VUOTA: DELETE_ACTION + returnedRequest
- [ ] CI SONO ANCORA TOOLCARD IN GAME: DELETE_ACTION + returnedRequest
- [ ] IL COLORE DADO CORRISPONDE A QUELLO DELLA TOOLCARD: DELETE_ACTION + returnedRequest

"pass"
- [ ] E' IL TUO TURNO: DELETE_ACTION + returnedRequest


===================================


*ACTIONINTERPRETER*


Controlli per i vari tipi di azione, divisi per toolcard

TOOLCARD 1:
"action":"changeDiceValue",
- [ ] CONTROLLO SE L'ACTIONRESPONSE DICE NON E' NULL: SE E' NULL, DEVO CONTROLLLARE SE LA DRAFTPOOL NON E' VUOTA E DEVO SELEZIONARE UN DADO DALLA DRAFTPOOL

- [ ]
- [ ]
- [ ]
- [ ]
- [ ]
- [ ]


"action":"placeDice",
"actiontype": "placeWithAllRestrictions"
