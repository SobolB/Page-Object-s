package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement transferButton = $("[data-test-id='action-transfer']");
    private SelenideElement amountInput = $("[data-test-id='amount'] input");
    private SelenideElement fromInput = $("[data-test-id='from'] input");
    private SelenideElement transferHead = $(byText("Пополнение карты"));
    private SelenideElement errorMessage = $("[data-test-id='error-message']");

    public TransferPage() {
        transferHead.shouldBe(Condition.visible);
    }

    public DashboardPage makeValidTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        makeValidTransfer(amountToTransfer, cardInfo);
        return new DashboardPage();
    }

    public void makeTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        amountInput.setValue(amountToTransfer);
        fromInput.setValue(cardInfo.getCardNumber());
        transferButton.click();
    }

    public void findErrorMessage(String expectedTest) {
        errorMessage.shouldHave(exactText(expectedTest), Duration.ofSeconds(15)).shouldBe(Condition.visible);
    }
}