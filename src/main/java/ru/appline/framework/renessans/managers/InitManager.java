package ru.appline.framework.renessans.managers;

import java.util.concurrent.TimeUnit;

import static ru.appline.framework.renessans.utils.PropertyConstants.*;
import static ru.appline.framework.renessans.managers.DriverManager.getDriver;
import static ru.appline.framework.renessans.managers.DriverManager.quitDriver;

public class InitManager {

    public static PropertyManager properties = PropertyManager.getPropertyManager();

    public static void initFramework() {
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().pageLoadTimeout(Integer.parseInt(properties.getProperty(PAGE_LOAD_TIMEOUT)), TimeUnit.SECONDS);
        getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(properties.getProperty(IMPLICITY_WAIT)), TimeUnit.SECONDS);
        getDriver().get(properties.getProperty(APP_URL));
    }

    public static void quitFramework() {
        quitDriver();
    }
}
