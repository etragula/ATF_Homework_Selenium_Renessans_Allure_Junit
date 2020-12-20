package ru.appline.framework.renessans.managers;

import ru.appline.framework.renessans.pages.DepositPage;
import ru.appline.framework.renessans.pages.StartPage;

public class PageManager {

    private static PageManager pageManager;

    StartPage startPage;
    DepositPage depositPage;

    private PageManager () {
    }

    public static PageManager getPageManager() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }

    public StartPage getStartPage() {
        if (startPage == null) {
            startPage = new StartPage();
        }
        return startPage;
    }

    public DepositPage getDepositPage() {
        if (depositPage == null) {
            depositPage = new DepositPage();
        }
        return depositPage;
    }
}
