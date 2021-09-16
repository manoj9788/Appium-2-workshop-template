package com.appium.advacedUsecases;


import com.appium.base.BaseTest;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SendSMSTest extends BaseTest {

    @Test
    public void sendSMSandClipBoard() throws InterruptedException {
        ((AndroidDriver) driver).sendSMS("555-555-5555", "Your message here!");
        Thread.sleep(5000);
        ((AndroidDriver<MobileElement>) driver).openNotifications();
        Thread.sleep(5000);
        String textFromSMS = driver.findElementById("android:id/message_text").getText();
        Assert.assertNotNull(textFromSMS);
    }
}
