package org.testpackage;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class FirstTest {
    @Test (priority = 0)
    void loginTest() throws InterruptedException {
        open("https://freetrial1.ushur.in/mob3.0/auth/");
        $(By.className("username")).setValue("test_admin@ushurdummy.me");
        $(By.className("password")).setValue("Ushur@123");
        $(By.className("btn-login")).click();
        Thread.sleep(10000);
    }
    @Test (dependsOnMethods = "loginTest")
    void createWorkflow() throws InterruptedException {
        $(By.xpath("//button[@aria-label='Create Workflow']")).click();
        $(By.className("FreeTrialCreateWorkflowModal_cardBody__3T86A")).click();
        $(By.xpath("//div[@aria-label='Select Project']")).click();
        $(By.xpath("//button[text()='TestAutomation']")).click();
        $(By.xpath("//input[@aria-label='Workflow Name']")).setValue("Logesh03012023");
        $(By.cssSelector("input.form-check-input")).click();
        Thread.sleep(10000);
        if ($(By.cssSelector("i.bi.bi.bi-exclamation-diamond-fill")).exists()){
            $(By.xpath("//button[@aria-label='Cancel']")).click();
        } else {
            $(By.xpath("//button[@aria-label='Create']")).click();
        }
    }
    @Test (dependsOnMethods = "createWorkflow")
    void openWorkflow(){
        $(By.xpath("//*[@id=\"acc-TestAutomation\"]")).click();
        $(By.xpath("//div//div[@id='Logesh03012023']")).hover();
        $(By.xpath("//div//div[@id='Logesh03012023']//child::button")).click();
        $(By.xpath("//*[text()=\"Welcome!\"]")).click();
        if (!$(By.xpath("//*[text()=\"Click or drag and drop your modules here.\"]//..")).exists()){
            //some code for inspector not loaded
        }
    }

    @Test (dependsOnMethods = "openWorkflow")
    void dragAndDrop() throws InterruptedException {
        SelenideElement sourceElement = $(By.xpath("//div[@data-rbd-draggable-id='message-module']"));
        SelenideElement targetLocation = $(By.xpath("//*[text()=\"Click or drag and drop your modules here.\"]//.."));
        actions().clickAndHold(sourceElement).moveToElement(targetLocation).pause(5000).release().build().perform();

        //actions().dragAndDrop(sourceElement, targetLocation );
        //$("div.pl-2:nth-child(7) > div:nth-child(1)").dragAndDropTo(targetLocation);
                //dragAndDropTo(".configured-modules-container");
        Thread.sleep(20000);
    }
}
