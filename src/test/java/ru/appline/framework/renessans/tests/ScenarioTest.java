package ru.appline.framework.renessans.tests;

import org.junit.Test;
import org.junit.runner.RunWith;

import ru.appline.framework.renessans.basetestclasses.BaseTest;
import ru.appline.framework.renessans.utils.MyAllureRunner;

@RunWith(MyAllureRunner.class)
public class ScenarioTest extends BaseTest {

    @Test
    public void firstTest() {
        app.getStartPage()
                .selectMenu("Вклады")
                .chooseCurrencyOfDeposit("RUB")
                .fillTheField("Сумма вклада", 300_000)
                .fillTheField("Срок", 6)
                .fillTheField("Ежемесячное пополнение", 50_000)
                .selectCheckBoxStatus("Ежемесячная капитализация", "true")
//                .selectCheckBoxStatus("Частичное снятие", "true")
//                .checkCalculationResults("Начислено %", 9000.17)
                .checkCalculationResults("Начислено %", 9132.17)
                .checkCalculationResults("Пополнение", 250_000)
                .checkCalculationResults("К снятию", 559_132.17)
        ;
    }

    @Test
    public void secondTest() {
        app.getStartPage()
                .selectMenu("Вклады")
                .chooseCurrencyOfDeposit("USD")
                .fillTheField("Сумма вклада", 500_000)
                .fillTheField("Срок", 12)
                .fillTheField("Ежемесячное пополнение", 5000)
                .selectCheckBoxStatus("Ежемесячная капитализация", "true")
//                .selectCheckBoxStatus("Частичное снятие", "true")
//                .checkCalculationResults("Начислено %", 796.12)
                .checkCalculationResults("Начислено %", 795.12)
                .checkCalculationResults("Пополнение", 55_000)
                .checkCalculationResults("К снятию", 555_796.12)
        ;
    }
}

