package main

import constructor_classes.locatorsTypes
import io.appium.java_client.MobileBy
import io.appium.java_client.MobileElement
import io.appium.java_client.touch.WaitOptions
import io.appium.java_client.touch.offset.PointOption
import org.springframework.expression.TypeLocator
import org.testng.AssertJUnit
import utils.PlatformTouchAction
import java.time.Duration
import java.util.concurrent.TimeUnit
import javax.xml.soap.Text

open class TestMedhods : BaseClass() {

    fun clickToElement(locatorType: String, locator: String) {

        lateinit var element: MobileElement //создаём объект MobileElement
        when (locatorType) {
            locatorsTypes.androidId -> element = driver.findElement(MobileBy.id(locator))
            locatorsTypes.androidXPath -> element = driver.findElement(MobileBy.xpath(locator))
        }

        element.click() //клик по элементу
        TimeUnit.SECONDS.sleep(1)
    }

    fun inputTextInField(locatorType: String, locator: String, inputText: String) {
        lateinit var element: MobileElement
        when (locatorType) {
            locatorsTypes.androidId -> element = driver.findElement(MobileBy.id(locator))
            locatorsTypes.androidXPath -> element = driver.findElement(MobileBy.xpath(locator))
        }
        element.sendKeys(inputText)
        TimeUnit.SECONDS.sleep(1)
    }

    //свайп по координатам
    fun swipeOnScreen(
        startCordX: Int,
        startCordY: Int,
        moveCordX: Int,
        moveCordY: Int,
    ) {
        PlatformTouchAction(driver)
            .longPress(PointOption.point(startCordX, startCordY))
            .moveTo(PointOption.point(moveCordX, moveCordY))
            .release()
            .perform()
    }

    //тап по координатам
    fun tapByCoordinates(
        cordX: Int,
        cordY: Int,
    ) {
        PlatformTouchAction(driver)
            .tap(PointOption.point(cordX, cordY))
            .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
            .perform()
    }

    //проверка доступности элемента
    fun checkAvailableElement(locatorType: String, locator: String) {
        var checkAvailableElement = false // создаём объект типа Boolean
        when (locatorType) {
            "id" -> checkAvailableElement = driver.findElement(MobileBy.id(locator)).isEnabled
            "AccessibilityId" -> checkAvailableElement = driver.findElement(MobileBy.AccessibilityId(locator)).isEnabled
            "xpath" -> checkAvailableElement = driver.findElement(MobileBy.xpath(locator)).isEnabled
        }//у поиска происходит обращение к атрибуту и возврат true или false

        AssertJUnit.assertTrue(checkAvailableElement)//проверка значения checkAvailableElement
    }

    //проверка текста в элементе
    fun checkTextInElement(locatorType: String, locator: String, text: String) {
        lateinit var element: MobileElement
        var elementAttribute = "" // создаем объект с пустой строкой
        when (locatorType) {
            "id" -> element = driver.findElement(MobileBy.id(locator))
            "AccessibilityId" -> element = driver.findElement(MobileBy.AccessibilityId(locator))
            "xpath" -> element = driver.findElement(MobileBy.xpath(locator))
        }
        elementAttribute =
            element.getAttribute("text") // в найденном элементе мы находим текст по атрибуту и передаем его в elementAttribute

        AssertJUnit.assertEquals(
            elementAttribute,
            text
        ) // сравнение текста, взятого из атрибута элемента, с текстом, который мы передали в функцию. Данная функция возвращает true или false
    }

    //очищение поля
    fun cleanField (locatorType: String, locator: String) {
        lateinit var element: MobileElement
        when (locatorType) {
                "id" -> element = driver.findElement(MobileBy.id(locator))
                "AccessibilityId" -> element = driver.findElement(MobileBy.AccessibilityId(locator))
                "xpath" -> element = driver.findElement(MobileBy.xpath(locator))
        }
        element.clear() // очищение поля
    }

}