package com.appium.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSOptions;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.net.ServerSocket;
import java.time.Duration;

public class BaseTest {
    private AppiumDriverLocalService service;
    public AppiumDriver<MobileElement> driver;
    public WebDriverWait wait;

    @BeforeClass
    public void beforeClass() throws Exception {
        service = AppiumDriverLocalService.buildService(
                new AppiumServiceBuilder().usingAnyFreePort().withArgument(GeneralServerFlag.BASEPATH, "/wd/hub")
                        .withArgument(GeneralServerFlag.RELAXED_SECURITY));
        service.start();

        if (service == null || !service.isRunning()) {
            throw new RuntimeException("An appium server node is not started!");
        }
       androidCaps();
        //iosCaps();

    }

    @AfterClass
    public void afterClass() {
        if (driver != null) {
            driver.quit();
        }
        if (service != null) {
            service.stop();
        }
    }

    private void androidCaps() throws IOException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        capabilities.setCapability(IOSMobileCapabilityType.LAUNCH_TIMEOUT, 900000);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
        capabilities.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, new ServerSocket(0).getLocalPort());
        capabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "/apps/VodQA.apk");
        driver = new AndroidDriver<>(service.getUrl(), capabilities);
        wait = new WebDriverWait(driver, 30);
    }

    private void iosCaps() throws IOException {
       /* DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13.3");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 11");
        capabilities.setCapability(IOSMobileCapabilityType.WDA_LOCAL_PORT, new ServerSocket(0).getLocalPort());

        //sometimes environment has performance problems
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
        capabilities.setCapability(IOSMobileCapabilityType.LAUNCH_TIMEOUT, 700000);
        capabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "/apps/vodqa.zip");
        */
        IOSOptions iosOptions = new IOSOptions();
        iosOptions.setApp(System.getProperty("user.dir") + "/apps/vodqa.zip")
                .setAutomationName(AutomationName.IOS_XCUI_TEST)
                .setDeviceName("iPhone 11")
                .setNewCommandTimeout(Duration.ofSeconds(60))
                .setPlatformVersion("13.3")
                .setCapability(IOSMobileCapabilityType.LAUNCH_TIMEOUT, 700000);

        driver = new IOSDriver<>(service.getUrl(), iosOptions);
        wait = new WebDriverWait(driver, 30);
    }

    public void login() {
        System.out.print( "My SessionId: "+driver.getSessionId());
        wait.until(ExpectedConditions.
                elementToBeClickable(MobileBy.AccessibilityId("login"))).click();
    }
}
