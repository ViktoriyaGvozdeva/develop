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
import java.net.URL
import java.util.concurrent.TimeUnit

class BaseClass {
    lateinit var driver: AppiumDriver<MobileElement>

    @BeforeSuite
    fun setupDriver() {


        val url = URL("http://127.0.0.1:4723/wd/hub")
        val caps = DesiredCapabilities()


        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android") //название платформы
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.0") //версия ОС
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 5") //имя устройства
        caps.setCapability(MobileCapabilityType.NO_RESET, true)//не сбрасывать приложение в 0 перед новым запуском
        caps.setCapability(
            MobileCapabilityType.NEW_COMMAND_TIMEOUT,
            "7200"
        )//увеличение времени таймера для выполнения всех тестовых классов
        caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "ru.sportmaster.app.handh.dev")
        caps.setCapability(
            AndroidMobileCapabilityType.APP_ACTIVITY,
            "ru.sportmaster.app.presentation.start.StartActivity"
        )//путь до приложения
        caps.setCapability(
            MobileCapabilityType.APP,
            "/Users/viktoriyag/Documents/app/sportmaster-4.0.13.5605_dev_beta.apk"
        )//путь до приложения


        // caps.setCapability(MobileCapabilityType.UDID, "")//подключение к реал девайсу

        driver = AndroidDriver(url, caps) // установка драйвера и приложения на Android устройство
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS) // веремя драйвера для поиска элемента на экране
    }

    @AfterSuite
    fun end() {
        driver.quit()
    }

    @Test
    fun testOne() {
        TimeUnit.SECONDS.sleep(1)

        lateinit var  element: MobileElement //создаём объект MobileElement
        element = driver.findElement(MobileBy.xpath(""))//поиск элемента по локатору
        element.click() //клик по элементу

        TimeUnit.SECONDS.sleep(5)
    }
}