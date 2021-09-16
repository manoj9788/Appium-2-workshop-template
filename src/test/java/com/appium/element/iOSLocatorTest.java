package com.appium.element;


import com.appium.base.BaseTest;
import io.appium.java_client.MobileBy;
import org.testng.annotations.Test;

public class iOSLocatorTest extends BaseTest {

    @Test
    public void classChainTest() {
        login();
        //
        //label == "Chained View"
       // driver.findElement(MobileBy.iOSNsPredicateString("name IN {'chained VieW','Demos wheel picker color'} AND visible == 1")).click();
        driver.findElement(MobileBy.iOSNsPredicateString("name IN {'chained VieW','Chained View'}")).click();
       // driver.findElement(MobileBy.iOSNsPredicateString("name BEGINSWITH 'Chained'")).click();

        //String text = driver.findElement(MobileBy.iOSClassChain("XCUIElementTypeWindow/**/XCUIElementTypeStaticText[2]")).getText();
        //Assert.assertEquals(text,"Native View Demo");
    }
}
