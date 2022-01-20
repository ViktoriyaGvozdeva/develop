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
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10") //версия ОС
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "HUAWEI P20 Pro") //имя устройства
        //caps.setCapability(MobileCapabilityType.NO_RESET, true)//нет переустановки приложения после каждой сессии
        caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "7200")//увеличение времени таймера для выполнения всех тестовых классов
        caps.setCapability(MobileCapabilityType.APP, "/Users/viktoriyag/Documents/app/sportmaster-4.0.13.5684_dev_beta.apk")//путь до приложения
        caps.setCapability(MobileCapabilityType.UDID, "WCR7N19111005423")//подключение к реал девайсу

        caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "ru.sportmaster.app.handh.dev")
        caps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "ru.sportmaster.app.presentation.start.StartActivity")

        driver = AndroidDriver(url, caps) // установка драйвера и приложения на Android устройство
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS) // время драйвера для поиска элемента на экране
    }

    @AfterSuite
    fun end() {
        driver.quit()
    }

    @Test

    //авторизация при первом старте приложения
    fun testOne() {
        TimeUnit.SECONDS.sleep(1)

        //сплеш/тап на крестик закрытия
        lateinit var element: MobileElement //создаём объект MobileElement
        element = driver.findElement(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.ImageButton"))//поиск элемента по локатору
        element.click() //клик по элементу

        //ввод телефона в поле авторизации
        lateinit var element2: MobileElement //создаём объект MobileElement
        element2 = driver.findElement(MobileBy.id("ru.sportmaster.app.handh.dev:id/editTextPhone"))//поиск элемента по локатору
        element2.sendKeys("999 999 99 67")//ввод текста в поле
        TimeUnit.SECONDS.sleep(2)

        //тап "Получить код"
        lateinit var element3: MobileElement
        element3 = driver.findElement(MobileBy.id("ru.sportmaster.app.handh.dev:id/buttonGetCode"))
        element3.click()

        //ввод кода sms
        lateinit var element4: MobileElement
        element4 = driver.findElement(MobileBy.id("ru.sportmaster.app.handh.dev:id/pinCodeEditText"))
        element4.sendKeys("1111")
        TimeUnit.SECONDS.sleep(5)

        //тап "Использовать геопозицию во время использования приложения"
        lateinit var element5: MobileElement
        element5 = driver.findElement(MobileBy.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button"))
        element5.click()

        //тап "Другой город"
        lateinit var element6: MobileElement
        element6 = driver.findElement(MobileBy.id("android:id/button2"))
        element6.click()

        //тап на затемнённый экран
        lateinit var element7: MobileElement
        element7 = driver.findElement(MobileBy.id("ru.sportmaster.app.handh.dev:id/qsgCitySearch"))
        element7.click()

        //выбор города Москва
        lateinit var element8: MobileElement
        element8 = driver.findElement(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ViewFlipper/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]"))
        element8.click()

        //тап "Профиль"
        lateinit var element9: MobileElement
        element9 = driver.findElement(MobileBy.id("ru.sportmaster.app.handh.dev:id/profile_graph"))
        element9.click()
        TimeUnit.SECONDS.sleep(10)

    }
}