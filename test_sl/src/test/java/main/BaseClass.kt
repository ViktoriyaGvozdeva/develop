package main

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileBy
import io.appium.java_client.MobileDriver
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.remote.AndroidMobileCapabilityType
import io.appium.java_client.remote.MobileCapabilityType
import org.openqa.selenium.remote.DesiredCapabilities
import org.testng.annotations.AfterSuite
import org.testng.annotations.BeforeSuite
import org.testng.annotations.Test
import utils.appPath
import java.net.URL
import java.util.concurrent.TimeUnit

open class BaseClass {
    lateinit var driver: AppiumDriver<MobileElement>

    @BeforeSuite
    fun setupDriver() {

        val url = URL("http://127.0.0.1:4723/wd/hub")
        val caps = DesiredCapabilities()

        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android") //название платформы
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10") //версия ОС
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "HUAWEI P20 Pro") //имя устройства
        //caps.setCapability(MobileCapabilityType.NO_RESET, true)//нет переустановки приложения после каждой сессии
        caps.setCapability(
            MobileCapabilityType.NEW_COMMAND_TIMEOUT,
            "7200"
        )//увеличение времени таймера для выполнения всех тестовых классов
        caps.setCapability(MobileCapabilityType.APP, appPath.fullAppLocalPathAndroid)//путь до приложения
        caps.setCapability(MobileCapabilityType.UDID, "WCR7N19111005423")//подключение к реал девайсу

        caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "ru.sportmaster.app.handh.dev")
        caps.setCapability(
            AndroidMobileCapabilityType.APP_ACTIVITY,
            "ru.sportmaster.app.presentation.start.StartActivity"
        )

        driver = AndroidDriver(url, caps) // установка драйвера и приложения на Android устройство
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS) // время драйвера для поиска элемента на экране
    }

    @AfterSuite
    fun end() {
        driver.quit()
    }


}