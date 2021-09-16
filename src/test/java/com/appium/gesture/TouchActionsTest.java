package com.appium.gesture;


import com.appium.base.BaseTest;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class TouchActionsTest extends BaseTest {

    @Test
    public void doubleTap() throws InterruptedException {
        login();
        Thread.sleep(5000);
        driver.findElementByAccessibilityId("doubleTap").click();
        MobileElement element = (MobileElement) new WebDriverWait(driver, 30).
                until(elementToBeClickable(MobileBy.AccessibilityId("doubleTapMe")));
        Thread.sleep(1000);
        Map<String, Object> args = new HashMap<>();
        args.put("element", element.getId());
        driver.executeScript("mobile: doubleTap", args);
    }


    @Test
    public void longPress() throws InterruptedException {
        login();
        Thread.sleep(5000);
        driver.findElementByAccessibilityId("longPress").click();
        MobileElement longpress = (MobileElement) new WebDriverWait(driver, 30).
                until(elementToBeClickable(MobileBy.AccessibilityId("longpress")));
        Map<String, Object> args = new HashMap<>();
        args.put("element", longpress.getId());
        args.put("duration", 1.5);
        driver.executeScript("mobile: touchAndHold", args);
    }

    @Test
    public void verticalSwipeTest() throws InterruptedException {
        login();
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("verticalSwipe")));
        driver.findElementByAccessibilityId("verticalSwipe").click();
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("listview")));
        Map<String, Object> args = new HashMap<>();
        args.put("direction", "down");
        driver.executeScript("mobile: scroll", args);
    }

    @Test
    public void androidScrollTest() throws InterruptedException {
        login();
        driver.findElementByAccessibilityId("dragAndDrop").click();
        MobileElement dragMe = (MobileElement) new WebDriverWait(driver, 30)
                .until(elementToBeClickable(MobileBy.AccessibilityId("dragMe")));
        final Point dropzone = driver.findElementByAccessibilityId("dropzone").getCenter();
        driver.executeScript("mobile: dragGesture", ImmutableMap.of(
                "elementId", dragMe.getId(),
                "endX", dropzone.getX(),
                "endY", dropzone.getY()
        ));
    }
}
