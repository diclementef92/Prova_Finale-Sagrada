package it.polimi.ingsw.model.cards.publics;

import org.junit.Test;

public class AllPublicsTest {

    /**
     * Method used for testing all public cards classes
     */
    @Test
    public void allPublicsTests(){
        ColorDiagonalsTest colorDiagonals = new ColorDiagonalsTest();
        ColorVarietyTest colorVariety = new ColorVarietyTest();
        ColumnColorVarietyTest columnColorVariety = new ColumnColorVarietyTest();
        ColumnShadeVarietyTest columnShadeVariety = new ColumnShadeVarietyTest();
        DeepShadesTest deepShadesTest = new DeepShadesTest();
        LightShadesTest lightShadesTest = new LightShadesTest();
        MediumShadesTest mediumShadesTest = new MediumShadesTest();
        RowColorVarietyTest rowColorVarietyTest = new RowColorVarietyTest();
        RowShadeVarietyTest rowShadeVarietyTest = new RowShadeVarietyTest();
        ShadeVarietyTest shadeVarietyTest = new ShadeVarietyTest();
        ///
        colorDiagonals.returnScoreTest();
        colorVariety.returnScore();
        columnColorVariety.returnScoreTest_case1();
        columnColorVariety.returnScoreTest_case2();
        columnShadeVariety.returnScoreTest_case1();
        deepShadesTest.returnScore();
        lightShadesTest.returnScore();
        mediumShadesTest.returnScore();
        rowColorVarietyTest.returnScoreTest_case1();
        rowColorVarietyTest.returnScoreTest_case2();
        rowShadeVarietyTest.returnScore();
        shadeVarietyTest.returnScore();
    }
}
