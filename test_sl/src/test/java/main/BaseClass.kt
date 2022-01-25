package main

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileBy
import io.appium.java_client.MobileDriver
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.ios.IOSDriver
import io.appium.java_client.remote.AndroidMobileCapabilityType
import io.appium.java_client.remote.MobileCapabilityType
import org.openqa.selenium.html5.Location
import org.openqa.selenium.remote.DesiredCapabilities
import org.testng.annotations.*
import utils.appPath
import java.net.URL
import java.util.concurrent.TimeUnit

open class BaseClass {
    lateinit var driver: AppiumDriver<MobileElement>

    @BeforeSuite
    @Parameters(
        value = ["paramPlatformName", "paramPlatformVersion", "paramDeviceName", "paramUDID", "paramTypeTest", "paramTimeToDelay"]
    )

    fun setupDriver(
        paramPlatformVersion: String, paramDeviceName: String,
        paramPlatformName: String, paramTimeToDelay: Long, paramUDID: String,
    ) {

        val url = URL("http://127.0.0.1:4723/wd/hub")
        val caps = DesiredCapabilities()

        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, paramPlatformName) //название платформы
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, paramPlatformVersion) //версия ОС
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, paramDeviceName) //имя устройства
        //caps.setCapability(MobileCapabilityType.NO_RESET, true)//нет переустановки приложения после каждой сессии
        caps.setCapability(
            MobileCapabilityType.NEW_COMMAND_TIMEOUT,
            "7200"
        )//увеличение времени таймера для выполнения всех тестовых классов
        caps.setCapability(MobileCapabilityType.UDID, paramUDID)//подключение к реал девайсу
        //caps.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true)// для пропуска алертов

        when (paramPlatformName) {
            "Android" -> {
                caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "ru.sportmaster.app.handh.dev")
                caps.setCapability(
                    AndroidMobileCapabilityType.APP_ACTIVITY,
                    "ru.sportmaster.app.presentation.start.StartActivity"
                )
                caps.setCapability(MobileCapabilityType.APP, appPath.fullAppLocalPathAndroid)//путь до приложения

                driver = AndroidDriver(url, caps)
            }
            "IOS" -> {
                caps.setCapability(MobileCapabilityType.APP, appPath.fullLocalAppLocalPathIOS)//путь до приложения
                driver = IOSDriver(url, caps)
            }
        }
        val location = Location(55.052146, 82.920077, 1.0)
        driver.setLocation(location)
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS) // время драйвера для поиска элемента на экране
        println("Драйвер установлен")
        //проверка наличия онбординга на экране + прохождение до главного экрана, минуя авторизацию, если онбординг найден
        driver.closeApp() // для ios 15+ не работает
        driver.resetApp() // для ios 15+
    }

    @AfterSuite
    fun end() {
        println("Тест завершён")
        driver.quit()
    }

    @BeforeClass
    fun beforeClass() {
        //заново инициализировать драйвер
        //закрыть приложение
    }

    @AfterClass
    fun afterClass() {
        //закрыть сессию драйвера
        driver.quit()
    }

    @BeforeMethod
    fun beforeMethod() {
        //запустить приложение
        driver.launchApp()
        TimeUnit.SECONDS.sleep(2)
    }

    @AfterMethod
    fun afterMethod() {
        //закрыть приложение
    }

}