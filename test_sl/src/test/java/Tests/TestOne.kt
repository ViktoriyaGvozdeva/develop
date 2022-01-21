package Tests

import constructor_classes.locatorsTypes
import io.appium.java_client.MobileBy
import io.appium.java_client.MobileElement
import locators.SplashScreenLocators
import main.TestMedhods
import org.testng.annotations.Test
import org.xml.sax.Locator
import java.util.concurrent.TimeUnit
import javax.xml.soap.Text

class TestOne : TestMedhods() {

    @Test

    //авторизация при первом старте приложения
    fun testOne() {
        TimeUnit.SECONDS.sleep(1)

        //сплеш/тап на крестик закрытия
        clickToElement(
            locatorType = locatorsTypes.androidXPath,
            locator = SplashScreenLocators().exitButtonSplashScreen.androidXpath
        )

        //ввод телефона в поле авторизации
        inputTextInField(
            locatorType = locatorsTypes.androidId,
            locator = "ru.sportmaster.app.handh.dev:id/editTextPhone",
            inputText = "999 999 99 67"
        )
        TimeUnit.SECONDS.sleep(2)

        //тап "Получить код"
        clickToElement(
            locatorType = locatorsTypes.androidId,
            locator = "ru.sportmaster.app.handh.dev:id/buttonGetCode"
        )

        //ввод кода sms
        inputTextInField(
            locatorType = locatorsTypes.androidId,
            locator = "ru.sportmaster.app.handh.dev:id/pinCodeEditText",
            inputText = "1111"
        )
        TimeUnit.SECONDS.sleep(5)

        //тап "Использовать геопозицию во время использования приложения"
        clickToElement(
            locatorType = locatorsTypes.androidId,
            locator = "com.android.permissioncontroller:id/permission_allow_foreground_only_button"
        )

        //тап "Другой город"
        clickToElement(
            locatorType = locatorsTypes.androidId,
            locator = "android:id/button2"
        )

        //тап на затемнённый экран
        clickToElement(
            locatorType = locatorsTypes.androidId,
            locator = "ru.sportmaster.app.handh.dev:id/qsgCitySearch"
        )

        //выбор города Москва
        clickToElement(
            locatorType = locatorsTypes.androidXPath,
            locator = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ViewFlipper/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]"
        )

        //тап "Профиль"
        clickToElement(
            locatorType = locatorsTypes.androidId,
            locator = "ru.sportmaster.app.handh.dev:id/profile_graph"
        )

        swipeOnScreen(
            startCordX = 12,
            startCordY = 45,
            moveCordX = 12,
            moveCordY = 23,
        )

        TimeUnit.SECONDS.sleep(10)
    }


}