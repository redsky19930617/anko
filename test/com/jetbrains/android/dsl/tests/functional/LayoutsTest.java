package com.jetbrains.android.dsl.tests.functional;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.jetbrains.android.dsl.*;

import java.io.File;

public class LayoutsTest extends BaseFunctionalTest {
    private final String testDataFile = "com/jetbrains/android/dsl/tests/functional/LayoutsTest.kt";

    @Override
    @BeforeMethod
    public void setUp() throws Exception {
        super.setUp();
    }

    protected void initSettings(BaseGeneratorProps settings) {
        settings.setGenerateLayoutParamsHelperClasses(true);
    }

    @Test
    public void testLayouts() throws Exception {
        runFunctionalTest(testDataFile, Subsystem.LAYOUTS);
    }
}

