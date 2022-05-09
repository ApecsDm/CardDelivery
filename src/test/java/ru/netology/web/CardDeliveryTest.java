package ru.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    @Test
    void shouldDeliveryCheckBox() {
        Configuration.holdBrowserOpen=true;
        open("http://localhost:9999");
        $("//*[@data-test-id=\"city\"]").setValue("Рязань");
    }

}
