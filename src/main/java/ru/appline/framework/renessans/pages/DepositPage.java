package ru.appline.framework.renessans.pages;

import org.junit.Assert;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static ru.appline.framework.renessans.managers.DriverManager.getDriver;

public class DepositPage extends BasePage {

    @FindBy(xpath = "//input[@name='calc-currency']")
    List<WebElement> listOfCurrencies;

    @FindBy(xpath = "//input[@name = 'amount']")
    WebElement amountOfDeposit;

    @FindBy(xpath = "//input[@name = 'replenish']")
    WebElement monthReplenish;

    @FindBy(xpath = "//select[@name = 'period']")
    WebElement periodOfDeposit;

    @FindBy(xpath = "//input[@name = 'capitalization']")
    WebElement monthCapitalization;

    @FindBy(xpath = "//input[@name = 'partial_out']")
    WebElement partiallyWithdraw;

    @FindBy(xpath = "//span[@class = 'js-calc-earned']")
    WebElement accruedInterest;

    @FindBy(xpath = "//span[@class = 'js-calc-replenish']")
    WebElement sumReplenishResult;

    @FindBy(xpath = "//span[@class = 'js-calc-result']")
    WebElement totalSumToWithdraw;

    @Step("Выбор валюты депозита: {nameOfCurrency}.")
    public DepositPage chooseCurrencyOfDeposit(String nameOfCurrency) {
        checkPageIsOpened();
        if (nameOfCurrency.equals("")) {
            Assert.fail("Введите название валюты для выбора (USD | RUB).");
        }
        for (WebElement currency : listOfCurrencies) {
            if (currency.getAttribute("value").equalsIgnoreCase(nameOfCurrency)) {
                action.moveToElement(currency).click().build().perform();
                return this;
            }
        }
        Assert.fail("Валюта \"" + nameOfCurrency + "\" не была найдена в списке! (USD | RUB)");
        return this;
    }

    @Step("Проверка заполнения поля \"{fieldName}\" значением \"{value}\".")
    public DepositPage fillTheField(String fieldName, int value) {
        if (fieldName.equals("") || value < 0) {
            Assert.fail("Выберете поле (\"Срок\", \"Ежемесячное пополнение\", \"Сумма вклада\") и полож-ое значение.");
        }
        switch (fieldName) {
            case "Сумма вклада":
                amountOfDeposit.clear();
                action.moveToElement(amountOfDeposit).click().sendKeys("" + value).build().perform();
                Assert.assertEquals("Значение поля \"" + fieldName + "\" не соответсвует значению\"" + value + "\"!",
                        value, fromStringToInteger(amountOfDeposit.getAttribute("value")));
                return this;
            case "Ежемесячное пополнение":
                monthReplenish.clear();
                action.moveToElement(monthReplenish).click().sendKeys("" + value).build().perform();
                Assert.assertEquals("Значение поля \"" + fieldName + "\" не соответсвует значению\"" + value + "\"!",
                        value, fromStringToInteger(monthReplenish.getAttribute("value")));
                return this;
            case "Срок":
                Select select = new Select(periodOfDeposit);
                select.selectByValue("" + value);
                Assert.assertEquals("Значение поля \"" + fieldName + "\" не соответсвует значению\"" + value + "\"!",
                        value, fromStringToInteger(select.getFirstSelectedOption().getAttribute("value")));
                return this;
        }
        Assert.fail("Поле \"" + fieldName + "\" не было найдено в списке (\"Срок\", \"Ежемесячное пополнение\", \"Сумма вклада\")!");
        return this;
    }

    @Step("Проверка чек-бокса \"{whatFieldToCheck}\" с необходимым статусом \"{value}\".")
    public DepositPage selectCheckBoxStatus(String whatFieldToCheck, String value) {
        if (whatFieldToCheck.equals("")) {
            Assert.fail("Бокс \"" + whatFieldToCheck + "\" не был найден в списке (\"Ежемесячная капитализация\", \"Частичное снятие\")!");
        }
        switch (whatFieldToCheck) {
            case "Ежемесячная капитализация":
                if (!monthCapitalization.isSelected() && value.equals("true") ||
                        monthCapitalization.isSelected() && value.equals("false")) {
                    action.moveToElement(monthCapitalization).click().build().perform();
                }
                Assert.assertEquals("Значение поля \"" + whatFieldToCheck + "\" не соответсвует поданному значению!",
                        monthCapitalization.isSelected(), Boolean.valueOf(value));
                return this;
            case "Частичное снятие":
                if (!partiallyWithdraw.isSelected() && value.equals("true") ||
                        partiallyWithdraw.isSelected() && value.equals("false")) {
                    action.moveToElement(partiallyWithdraw).click().build().perform();
                }
                Assert.assertEquals("Значение поля \"" + whatFieldToCheck + "\" не соответсвует поданному значению!",
                        partiallyWithdraw.isSelected(), Boolean.valueOf(value));
                return this;
        }
        Assert.fail("Бокс \"" + whatFieldToCheck + "\" не был найден в списке (\"Ежемесячная капитализация\", \"Частичное снятие\")!");
        return this;
    }

    @Step("Проверка значений поля \"{whatFieldToCheck}\"")
    public DepositPage checkCalculationResults(String whatFieldToCheck, double value) {
        sleepForInterval(750);
        if (whatFieldToCheck.equals("") || value < 0) {
            Assert.fail("Выберете поле  (\"Начислено %\", \"Пополнение\", \"К снятию\") и полож-ое значение.");
        }
        switch (whatFieldToCheck) {
            case "Начислено %":
                Assert.assertEquals(value, Double.parseDouble(accruedInterest.getText()
                        .replace(",", ".")
                        .replace(" ", "")), 9e-10);
                return this;
            case "Пополнение":
                Assert.assertEquals(value, Double.parseDouble(sumReplenishResult.getText()
                        .replace(",", ".")
                        .replace(" ", "")), 9e-10);
                return this;
            case "К снятию":
                Assert.assertEquals(value, Double.parseDouble(totalSumToWithdraw.getText()
                        .replace(",", ".")
                        .replace(" ", "")), 9e-10);
                return this;
        }
        Assert.fail("Поле \"" + whatFieldToCheck + "\" не было найдено в списке (\"Начислено %\", \"Пополнение\", \"К снятию\")!");
        return this;
    }

    private void checkPageIsOpened() {
        Assert.assertEquals("Заголовок \"Вклады\" отсутствует/не соответствует требуемому",
                "Вклады", getDriver().getTitle());
    }
}
