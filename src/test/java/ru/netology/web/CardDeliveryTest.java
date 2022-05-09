package ru.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    @Test
    void shouldDeliveryCheckBox() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $x("//*[@placeholder=\"Город\"]").setValue("Рязань");
        $x("//*[@placeholder=\"Дата встречи\"]").setValue("12052022");
        $x("//*[@name=\"name\"]").setValue("Орлов Олег");
        $x("//*[@name=\"phone\"]").setValue("+79992840055");
        $x("//*[@class=\"checkbox__box\"]").click();
        $x("//*[@class=\"button__text\"]").click();
        $(withText("Встреча успешно забронирована")).should(Condition.visible, Duration.ofSeconds(16));
    }
}
