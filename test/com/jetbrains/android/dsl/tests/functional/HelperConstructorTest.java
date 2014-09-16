package com.jetbrains.android.dsl.tests.functional;

import com.jetbrains.android.dsl.BaseGeneratorProps;
import com.jetbrains.android.dsl.Subsystem;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

public class HelperConstructorTest extends BaseFunctionalTest {
    private final String testDataFile = "com/jetbrains/android/dsl/tests/functional/HelperConstructorTest.kt";

    @Override
    @BeforeMethod
    public void setUp() throws Exception {
        super.setUp();
    }

    protected void initSettings(BaseGeneratorProps settings) {
        settings.setGenerateViewHelperConstructors(true);
    }

    @Test
    public void testHelperConstructors() throws Exception {
        runFunctionalTest(testDataFile, Subsystem.VIEWS);
    }
}
