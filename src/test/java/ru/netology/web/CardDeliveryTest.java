package ru.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.conditions.ExactText;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {
    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }


    @Test
    void shouldDeliveryCheckBox() {
        String planningDate = generateDate(4);

        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $x("//*[@placeholder=\"Город\"]").setValue("Рязань");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $x("//*[@name=\"name\"]").setValue("Орлов Олег");
        $x("//*[@name=\"phone\"]").setValue("+79992840055");
        $x("//*[@class=\"checkbox__box\"]").click();
        $x("//*[@class=\"button__text\"]").click();
        $$("[class=\"notification__content\"]").find(Condition.exactText("Встреча успешно забронирована на "+ planningDate)).should(Condition.visible,Duration.ofSeconds(15));
    }

    @Test
    void shouldAppearMessageIfUnavailableCity() {
        String planningDate = generateDate(4);

        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $x("//*[@placeholder=\"Город\"]").setValue("Луховицы");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $x("//*[@name=\"name\"]").setValue("Орлов Олег");
        $x("//*[@name=\"phone\"]").setValue("+79992840055");
        $x("//*[@class=\"checkbox__box\"]").click();
        $x("//*[@class=\"button__text\"]").click();
        $("[class]").findElement(byCssSelector("[data-test-id=\"city\"].input_invalid"));
        $(byText("Доставка в выбранный город недоступна")).should(Condition.visible);
    }

    @Test
    void shouldAppearMessageIfUnavailableDate() {
        String planningDate = generateDate(2);

        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $x("//*[@placeholder=\"Город\"]").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $x("//*[@name=\"name\"]").setValue("Орлов Олег");
        $x("//*[@name=\"phone\"]").setValue("+79168001213");
        $x("//*[@class=\"checkbox__box\"]").click();
        $x("//*[@class=\"button__text\"]").click();
        $("[class]").findElement(byCssSelector(".input_invalid"));
        $(byText("Заказ на выбранную дату невозможен")).should(Condition.visible);
    }

    @Test
    void shouldAppearMessageIfUnavailableName() {
        String planningDate = generateDate(4);

        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $x("//*[@placeholder=\"Город\"]").setValue("Казань");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $x("//*[@name=\"name\"]").setValue("Orlov Oleg");
        $x("//*[@name=\"phone\"]").setValue("+79032844872");
        $x("//*[@class=\"checkbox__box\"]").click();
        $x("//*[@class=\"button__text\"]").click();
        $("[class]").findElement(byCssSelector("[data-test-id=\"name\"].input_invalid"));
        $(withText("Имя и Фамилия указаные неверно.")).should(Condition.visible);
    }

    @Test
    void shouldAppearMessageIfUnavailablePhone() {
        String planningDate = generateDate(4);

        open("http://localhost:9999");
        $x("//*[@placeholder=\"Город\"]").setValue("Владимир");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $x("//*[@name=\"name\"]").setValue("Орлова Ольга");
        $x("//*[@name=\"phone\"]").setValue("+790328448722");
        $x("//*[@class=\"checkbox__box\"]").click();
        $x("//*[@class=\"button__text\"]").click();
        $("[class]").findElement(byCssSelector("[data-test-id=\"phone\"].input_invalid"));
        $(withText("Телефон указан неверно.")).should(Condition.visible);
    }

    @Test
    void shouldAppearMessageIfCheckboxIsNotMarked() {
        String planningDate = generateDate(4);

        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $x("//*[@placeholder=\"Город\"]").setValue("Астрахань");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $x("//*[@name=\"name\"]").setValue("Боброва-Филатова Анна");
        $x("//*[@name=\"phone\"]").setValue("+79858754011");
        $x("//*[@class=\"button__text\"]").click();
        $("[class]").findElement(byCssSelector("[data-test-id=\"agreement\"].input_invalid"));
    }
}
