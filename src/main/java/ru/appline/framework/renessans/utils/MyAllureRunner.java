package ru.appline.framework.renessans.utils;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;

public class MyAllureRunner extends BlockJUnit4ClassRunner {

    private MyAllureListener myAllureListener;

    public MyAllureRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
        myAllureListener = new MyAllureListener();
    }

    public void run(final RunNotifier notifier) {
        notifier.addListener(myAllureListener);
        super.run(notifier);
    }
}
