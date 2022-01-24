package Tests

import constructor_classes.locatorsTypes
import jdk.nashorn.internal.runtime.regexp.joni.Config.log
import locators.*
import main.TestMedhods
import org.testng.annotations.Test
import java.util.concurrent.TimeUnit

class TestOne : TestMedhods() {

    @Test

    //авторизация при первом старте приложения
    fun testOne() {
        log.println("Запуск Теста1")
        TimeUnit.SECONDS.sleep(1)

        //сплеш/тап на крестик закрытия
        clickToElement(
            locatorType = locatorsTypes.androidXPath,
            locator = SplashScreenLocators().exitButtonSplashScreen.androidXpath
        )

        //ввод телефона в поле авторизации
        inputTextInField(
            locatorType = locatorsTypes.androidId,
            locator = AuthorizationScreenLocators().editTextPhone.androidId,
            inputText = "999 999 99 67",
        )
        TimeUnit.SECONDS.sleep(2)

        //тап "Получить код"
        clickToElement(
            locatorType = locatorsTypes.androidId,
            locator = AuthorizationScreenLocators().tapButtonGetCode.androidId
        )

        //ввод кода sms
        inputTextInField(
            locatorType = locatorsTypes.androidId,
            locator = InputCodeScreenLocators().pinCodeEditText.androidId,
            inputText = "1111"
        )
        log.println("Авторизация прошла успешно")
        TimeUnit.SECONDS.sleep(5)


        //тап "Использовать геопозицию во время использования приложения"
        clickToElement(
            locatorType = locatorsTypes.androidId,
            locator = PermissionControllerLocators().allowForegroundOnlyButton.androidId
        )

        //тап "Другой город"
        clickToElement(
            locatorType = locatorsTypes.androidId,
            locator = SelectCityScreenLocators().tapPopUpButtonAnotherCity.androidId
        )

        //тап на затемнённый экран
        tapByCoordinates(
            cordX = 490,
            cordY = 1307,
        )

        //выбор города Москва
        clickToElement(
            locatorType = locatorsTypes.androidXPath,
            locator = SelectCityScreenLocators().selectCityMoscow.androidXpath
        )

        //тап "Профиль"
        clickToElement(
            locatorType = locatorsTypes.androidId,
            locator = BottomNavigationLocators().tapProfileGraph.androidId
        )

        //тап "Редактировать профиль"
        clickToElement(
            locatorType = locatorsTypes.androidId,
            locator = ProfileScreenLocators().tapButtonEditProfile.androidId
        )

        //ввод данных в поле "Фамилия"
        inputTextInField(
            locatorType = locatorsTypes.androidId,
            locator = ProfileScreenLocators().editTextLastName.androidId,
            inputText = "Тестовая",
        )

        //проверка текста в поле "Фамилия"
        checkTextInElement(
            locatorType = locatorsTypes.androidId,
            locator = ProfileScreenLocators().editTextLastName.androidId,
            text = "Тестовая"
        )
        log.println("Текст в поле <Фамилия> соответствует условиям")

        //очистка поля "Фамилия"
        cleanField(
            locatorType = locatorsTypes.androidId,
            locator = ProfileScreenLocators().editTextLastName.androidId,
        )
        log.println("Поле <Фамилия> очищено")
        TimeUnit.SECONDS.sleep(5)

        //скролл экрана вниз
        swipeOnScreen(
            startCordX = 490,
            startCordY = 1588,
            moveCordX = 505,
            moveCordY = 1258,
        )

        //тап "Выйти из профиля"
        clickToElement(
            locatorType = locatorsTypes.androidId,
            locator = ProfileScreenLocators().tapButtonLogout.androidId
        )

        //проверка доступности кнопки "Войти"
        checkAvailableElement(
            locatorType = locatorsTypes.androidId,
            locator = SignInScreenLocators().buttonSignIn.androidId
        )
        log.println("Экран авторизации. Кнопка <Войти> доступна.")
        TimeUnit.SECONDS.sleep(10)
    }


}