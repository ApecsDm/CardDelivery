package ru.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    @Test
    void shouldDeliveryCheckBox() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $x("//*[@placeholder=\"Город\"]").setValue("Рязань");
        $x("//*[@placeholder=\"Дата встречи\"]").setValue("14052022");
        $x("//*[@name=\"name\"]").setValue("Орлов Олег");
        $x("//*[@name=\"phone\"]").setValue("+79992840055");
        $x("//*[@class=\"checkbox__box\"]").click();
        $x("//*[@class=\"button__text\"]").click();
        $(withText("Встреча успешно забронирована")).should(Condition.visible, Duration.ofSeconds(16));
    }

    @Test
    void shouldAppearMessageIfUnavailableCity() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $x("//*[@placeholder=\"Город\"]").setValue("Луховицы");
        $x("//*[@placeholder=\"Дата встречи\"]").setValue("14052022");
        $x("//*[@name=\"name\"]").setValue("Орлов Олег");
        $x("//*[@name=\"phone\"]").setValue("+79992840055");
        $x("//*[@class=\"checkbox__box\"]").click();
        $x("//*[@class=\"button__text\"]").click();
        $("[class]").findElement(byCssSelector("[data-test-id=\"city\"].input_invalid"));
        $(byText("Доставка в выбранный город недоступна")).should(Condition.visible);
    }

    @Test
    void shouldAppearMessageIfUnavailableDate() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $x("//*[@placeholder=\"Город\"]").setValue("Москва");
        $x("//*[@placeholder=\"Дата встречи\"]").doubleClick().sendKeys(Keys.BACK_SPACE);
        $x("//*[@placeholder=\"Дата встречи\"]").setValue("10022022");
        $x("//*[@name=\"name\"]").setValue("Орлов Олег");
        $x("//*[@name=\"phone\"]").setValue("+79168001213");
        $x("//*[@class=\"checkbox__box\"]").click();
        $x("//*[@class=\"button__text\"]").click();
        $("[class]").findElement(byCssSelector(".input_invalid"));
        $(byText("Заказ на выбранную дату невозможен")).should(Condition.visible);
    }

    @Test
    void shouldAppearMessageIfUnavailableName() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $x("//*[@placeholder=\"Город\"]").setValue("Казань");
        $x("//*[@placeholder=\"Дата встречи\"]").setValue("14052022");
        $x("//*[@name=\"name\"]").setValue("Orlov Oleg");
        $x("//*[@name=\"phone\"]").setValue("+79032844872");
        $x("//*[@class=\"checkbox__box\"]").click();
        $x("//*[@class=\"button__text\"]").click();
        $("[class]").findElement(byCssSelector("[data-test-id=\"name\"].input_invalid"));
        $(withText("Имя и Фамилия указаные неверно.")).should(Condition.visible);
    }

    @Test
    void shouldAppearMessageIfUnavailablePhone() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $x("//*[@placeholder=\"Город\"]").setValue("Владимир");
        $x("//*[@placeholder=\"Дата встречи\"]").setValue("12052022");
        $x("//*[@name=\"name\"]").setValue("Орлова Ольга");
        $x("//*[@name=\"phone\"]").setValue("+790328448722");
        $x("//*[@class=\"checkbox__box\"]").click();
        $x("//*[@class=\"button__text\"]").click();
        $("[class]").findElement(byCssSelector("[data-test-id=\"phone\"].input_invalid"));
        $(withText("Телефон указан неверно.")).should(Condition.visible);
    }

    @Test
    void shouldAppearMessageIfCheckboxIsNotMarked() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $x("//*[@placeholder=\"Город\"]").setValue("Астрахань");
        $x("//*[@placeholder=\"Дата встречи\"]").setValue("15052022");
        $x("//*[@name=\"name\"]").setValue("Боброва-Филатова Анна");
        $x("//*[@name=\"phone\"]").setValue("+79858754011");
        $x("//*[@class=\"button__text\"]").click();
        $("[class]").findElement(byCssSelector("[data-test-id=\"agreement\"].input_invalid"));
    }
}
