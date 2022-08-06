package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import java.time.*;
import java.time.format.DateTimeFormatter;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {

    @BeforeEach
    void openBrowser() {
        open("http://localhost:9999");
    }

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    void shouldTestCorrectApplication() {
        SelenideElement form = $(".form");
        String planningDate = generateDate(3);

        form.$("[data-test-id = 'city'] input").setValue("Москва");
        form.$("[data-test-id = 'date'] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id = 'date'] input").setValue(planningDate);
        form.$("[data-test-id = 'name'] input").setValue("Иванов Иван");
        form.$("[data-test-id = 'phone'] input").setValue("+79012345678");
        form.$("[data-test-id = 'agreement']").click();
        form.$$("button.button").last().click();

        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @Test
    void shouldTestCityDash() {
        SelenideElement form = $(".form");
        String planningDate = generateDate(3);

        form.$("[data-test-id = 'city'] input").setValue("Санкт-Петербург");
        form.$("[data-test-id = 'date'] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id = 'date'] input").setValue(planningDate);
        form.$("[data-test-id = 'name'] input").setValue("Иванов Иван");
        form.$("[data-test-id = 'phone'] input").setValue("+79012345678");
        form.$("[data-test-id = 'agreement']").click();
        form.$$("button.button").last().click();

        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @Test
    void shouldTestWrongCity() {
        SelenideElement form = $(".form");
        String planningDate = generateDate(3);

        form.$("[data-test-id = 'city'] input").setValue("Дубна");
        form.$("[data-test-id = 'date'] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id = 'date'] input").setValue(planningDate);
        form.$("[data-test-id = 'name'] input").setValue("Иванов Иван");
        form.$("[data-test-id = 'phone'] input").setValue("+79012345678");
        form.$("[data-test-id = 'agreement']").click();
        form.$$("button.button").last().click();

        $("[data-test-id='city'] [class='input__sub']").shouldHave(exactText("Доставка в выбранный город" +
                " недоступна"));
    }

    @Test
    void shouldTestWrongCityEng() {
        SelenideElement form = $(".form");
        String planningDate = generateDate(3);

        form.$("[data-test-id = 'city'] input").setValue("Moscow");
        form.$("[data-test-id = 'date'] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id = 'date'] input").setValue(planningDate);
        form.$("[data-test-id = 'name'] input").setValue("Иванов Иван");
        form.$("[data-test-id = 'phone'] input").setValue("+79012345678");
        form.$("[data-test-id = 'agreement']").click();
        form.$$("button.button").last().click();

        $("[data-test-id='city'] [class='input__sub']").shouldHave(exactText("Доставка в выбранный город" +
                " недоступна"));
    }

    @Test
    void shouldTestWrongData() {
        SelenideElement form = $(".form");
        String planningDate = generateDate(2);

        form.$("[data-test-id = 'city'] input").setValue("Москва");
        form.$("[data-test-id = 'date'] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id = 'date'] input").setValue(planningDate);
        form.$("[data-test-id = 'name'] input").setValue("Иванов Иван");
        form.$("[data-test-id = 'phone'] input").setValue("+79012345678");
        form.$("[data-test-id = 'agreement']").click();
        form.$$("button.button").last().click();

        $("[data-test-id='date'] [class='input__sub']").shouldHave(exactText("Заказ на выбранную дату" +
                " невозможен"));
    }

    @Test
    void shouldTestTodayData() {
        SelenideElement form = $(".form");
        String planningDate = generateDate(0);

        form.$("[data-test-id = 'city'] input").setValue("Москва");
        form.$("[data-test-id = 'date'] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id = 'date'] input").setValue(planningDate);
        form.$("[data-test-id = 'name'] input").setValue("Иванов Иван");
        form.$("[data-test-id = 'phone'] input").setValue("+79012345678");
        form.$("[data-test-id = 'agreement']").click();
        form.$$("button.button").last().click();

        $("[data-test-id='date'] [class='input__sub']").shouldHave(exactText("Заказ на выбранную дату" +
                " невозможен"));
    }


    @Test
    void shouldTestNameDash() {
        SelenideElement form = $(".form");
        String planningDate = generateDate(3);

        form.$("[data-test-id = 'city'] input").setValue("Москва");
        form.$("[data-test-id = 'date'] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id = 'date'] input").setValue(planningDate);
        form.$("[data-test-id = 'name'] input").setValue("Петров-Водкин Алексей");
        form.$("[data-test-id = 'phone'] input").setValue("+79012345678");
        form.$("[data-test-id = 'agreement']").click();
        form.$$("button.button").last().click();

        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @Test
    void shouldTestWrongNameEng() {
        SelenideElement form = $(".form");
        String planningDate = generateDate(3);

        form.$("[data-test-id = 'city'] input").setValue("Москва");
        form.$("[data-test-id = 'date'] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id = 'date'] input").setValue(planningDate);
        form.$("[data-test-id = 'name'] input").setValue("Ivanov Ivan");
        form.$("[data-test-id = 'phone'] input").setValue("+79012345678");
        form.$("[data-test-id = 'agreement']").click();
        form.$$("button.button").last().click();

        $("[data-test-id='name'] [class='input__sub']").shouldHave(exactText("Имя и Фамилия указаные неверно." +
                " Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldTestWrongPhone() {
        SelenideElement form = $(".form");
        String planningDate = generateDate(3);

        form.$("[data-test-id = 'city'] input").setValue("Москва");
        form.$("[data-test-id = 'date'] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id = 'date'] input").setValue(planningDate);
        form.$("[data-test-id = 'name'] input").setValue("Иванов Иван");
        form.$("[data-test-id = 'phone'] input").setValue("9999");
        form.$("[data-test-id = 'agreement']").click();
        form.$$("button.button").last().click();

        $("[data-test-id='phone'] [class='input__sub']").shouldHave(exactText("Телефон указан неверно." +
                " Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldTestWrongPhone2() {
        SelenideElement form = $(".form");
        String planningDate = generateDate(3);

        form.$("[data-test-id = 'city'] input").setValue("Москва");
        form.$("[data-test-id = 'date'] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id = 'date'] input").setValue(planningDate);
        form.$("[data-test-id = 'name'] input").setValue("Иванов Иван");
        form.$("[data-test-id = 'phone'] input").setValue("+1111111111111");
        form.$("[data-test-id = 'agreement']").click();
        form.$$("button.button").last().click();

        $("[data-test-id='phone'] [class='input__sub']").shouldHave(exactText("Телефон указан неверно." +
                " Должно быть 11 цифр, например, +79012345678."));
    }


    @Test
    void shouldTestCheckboxOff() {
        SelenideElement form = $(".form");
        String planningDate = generateDate(3);

        form.$("[data-test-id = 'city'] input").setValue("Москва");
        form.$("[data-test-id = 'date'] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id = 'date'] input").setValue(planningDate);
        form.$("[data-test-id = 'name'] input").setValue("Иванов Иван");
        form.$("[data-test-id = 'phone'] input").setValue("+79012345678");
        form.$$("button.button").last().click();

        $("[data-test-id='agreement'] [class='checkbox__text']").shouldHave(exactText("Я соглашаюсь с" +
                " условиями обработки и использования моих персональных данных"));
    }
}
