#### Classtest passed
NB: [x] passed, [ ] to do

package dice
------------------------

**DiceTest.java**
- [x] DraftDieTest : controllo valore valido dado
- [x] increaseValueTest_overMax : se val già max, non aumentato
- [x] decreaseValueTest_underMin : se val già min, non diminuito 
- [x] changeValueTest 
- [x] rollDiceTest_valueInTheRange
- [x] getColorTest  
- [x] setValueTest 

**DiceBagTest.java**

- [x] diceBagSizeTest_WhenCreatedIsfull 
- [x] shuffleDiceBagTest_numElemNotChange
- [x] diceBagSize()     : sempre maggiore di 0 
- [x] removeDieFromBag() : dimensione ridotta di uno
- [x] checkDiceLeftinTheDicebag_AtTheEnd() : considerando sia alla fine del round sia alla fine del gioco

**DraftPoolTest.java**

- [x] getSizeDraftPoolTest_AtTheBeginningOfTheRound
- [x] getSizeDraftPoolTest_AtTheEndOfTheRound 
- [x] rerollAll_validValues 

**RoundTrackTest.java**

- [x] getNumberofRoundTest_atTheBeginningOfTheGame()
- [x] insertDieTest_allDicesLeftinDraftpoolAreInTheRoundtrackFirstPosition

package windowpattern
-------------------------

**Cell**

- [x] getColorTest
- [x] getValueTest
- [x] setDiceTest
- [x] getDiceTest_cellWithoutDiceAtBeginning

**WindowPatternCard**

- [x] getNameTest
- [x] getDifficultyTest
- [x] insertDieTest_ColoredCell 
- [x] insertDieTest_NumericCell

**WindowPatternDeck**

- [x] WindowPatternDeckTest_numberOfWindows -> tot 24
- [x] getSizeWindowPatternDeckTest 

player
-----------------------

**PlayerBoard**

- [x] getColorTest
- [x] getWindowboardTest
- [x] placeDiceTest
- [x] checkValidityPlacementTest
- [x] checkValidityPlacementTest_diceInaColoredCell
- [x] checkValidityPlacementTest_diceInaOccupiedCell
- [x] checkValidityPlacementTest_diceInaNumericCell

**Player**

- [x] getPlayerNickname
- [x] getPlayerBoard
- [x] getPrivateCard
- [x] getFavorTokenAmount

publics
----------------------
- [x] returnScore -> per ogni carta obbiettivo pubblico

privates
----------------------
- [x] getColorPrivateTest
- [x] getScoreTest_AtBeginning-> 0
- [ ] getScoreTest -> carta specifica
