package tests;

import io.qameta.allure.junit4.DisplayName;
import model.MainPage;
import org.junit.Assert;
import org.junit.Test;

public class ConstructorTest extends BaseTest{

    @Test
    @DisplayName("Проверка перехода к разделу «Булки>»")
    public void toTheBunSectionTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSauceTab();
        mainPage.clickBunTab();

        Assert.assertTrue("Булка не подсвечивается", mainPage.isBunTabActive());
    }

    @Test
    @DisplayName("Проверка перехода к разделу «Соусы»")
    public void toTheSauceSectionTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSauceTab();

        Assert.assertTrue("Соус не подсвечивается", mainPage.isSauceTabActive());
    }

    @Test
    @DisplayName("Проверка перехода к разделу «Начинка»")
    public void toTheFillingSectionTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickFillingTab();

        Assert.assertTrue("Начинка не подсвечивается", mainPage.isFillingTabActive());
    }

}
