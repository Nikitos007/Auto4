package HW4.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import HW4.pages.DraggDroppPage;

public class DragDroppTest {

	private WebDriver driver;
    private DraggDroppPage dragDropPage;
    private SoftAssert softAssert;

    @BeforeTest
    public void beforeTest() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        dragDropPage = new DraggDroppPage(driver);
        dragDropPage.open();
    }

    @BeforeMethod
    public void beforeMethod() {
    	dragDropPage.open();   
    	softAssert = new SoftAssert();
    } 
    
    @Test
    public void dragSortTest() {
    	dragDropPage.dragSort();
    	softAssert.assertEquals(true, dragDropPage.checkSortOrder());
    	softAssert.assertAll();
    }

    @Test
    public void dragAndDropTest() {
    	dragDropPage.dragAndDrop();
    	dragDropPage.canselAlert();
    	softAssert.assertEquals(true, dragDropPage.plesentDragElement());
    	dragDropPage.dragAndDrop();
    	dragDropPage.acceptAlert();
    	softAssert.assertEquals(false, dragDropPage.plesentDragElement());
    	softAssert.assertAll();
    }

    @Test
    public void dragSortReverseTest() {
    	dragDropPage.dragSortReverse();
    	softAssert.assertEquals(true, dragDropPage.checkSortReverseOrder());
    	softAssert.assertAll();
    }
    @AfterTest
    public void afterTest() {
        driver.quit();
    }
    
}
