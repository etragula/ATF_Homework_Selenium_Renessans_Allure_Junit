package ru.appline.framework.renessans.pages;

import org.junit.Assert;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class StartPage extends BasePage {

    @FindBy(xpath = "//div[@class='service__title-text']")
    List<WebElement> services;

    @Step("Выбор меню \"{nameOfMenuItem}\")")
    public DepositPage selectMenu(String nameOfMenuItem) {
        if (nameOfMenuItem.equals("")) {
            Assert.fail("Введите название категории меню для выбора.");
        }
        for (WebElement menuItem : services) {
            if (menuItem.getText().equalsIgnoreCase("Вклады")) {
                wait.until(ExpectedConditions.elementToBeClickable(menuItem));
                action.moveToElement(menuItem).click().build().perform();
                return app.getDepositPage();
            }
        }
        Assert.fail("Меню \"" + nameOfMenuItem + "\" не было найдено на стартовой странице!");
        return app.getDepositPage();
    }

}
