package ru.appline.framework.renessans.basetestclasses;

import org.junit.AfterClass;
import org.junit.Before;

import ru.appline.framework.renessans.managers.PageManager;

import static ru.appline.framework.renessans.managers.InitManager.initFramework;
import static ru.appline.framework.renessans.managers.InitManager.quitFramework;

public class BaseTest {

    protected PageManager app = PageManager.getPageManager();

    @Before
    public void doBefore() {
        initFramework();
    }

    @AfterClass
    public static void doAfter() {
        quitFramework();
    }
}
