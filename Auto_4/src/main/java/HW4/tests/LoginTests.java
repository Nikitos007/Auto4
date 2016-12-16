package HW4.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import HW4.pages.LoginPage;

public class LoginTests {
	
	private WebDriver driver;
    private LoginPage loginPage;

   
    @BeforeSuite
    public void beforeTest() {
        driver = new FirefoxDriver();
        //wait while driver find element
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
    }
    
    /**
	 * Precondition:
	 * 1. Open application LoginPage
	 */
    @BeforeMethod
    public void beforeMethod() {
        loginPage = new LoginPage(driver);
        loginPage.open();
    }

    /**
     * Steps to reproduce:
     * 1. Set username to admin
     * 2. Set password to 123
     * 3. Click Login
     * 4. Verify that title of the page equals to "Players"
     * 5. Verify that URL of the page don't equals to LoginPage.URL
     */
    
    @DataProvider
	public Object[][] loginData() {
		return new Object[][] { 
			{ "admin", "123" } 
		};
	}
    
    @Test(dataProvider = "loginData", groups = "positive")
    public void positiveLoginTest(String username, String password) {
        loginPage.setUsername(username);
        loginPage.setPassword(password);
        loginPage.logIn();
        Assert.assertEquals(loginPage.getTitle(), "Players", "Wrong title.");
        Assert.assertNotEquals(driver.getCurrentUrl(), LoginPage.URL, "You are still on login page.");
    }

    /**
     * Steps to reproduce:
     * 1. Set username to admin
     * 2. Set password to 12345
     * 3. Click Login
     * 4. Verify that title of the page equals to "Login"
     * 5. Verify that URL of the page don't equals to LoginPage.URL
     * 6. Verify that error message on the page equals to "Invalid username or password"
     */
    @Parameters({"trueUsername", "failPassword"})
	@Test(dependsOnGroups = "positive", groups = "negative")
    public void negativeLoginTest(String username, String password) {
        loginPage.setUsernameUsingJS(username);
        loginPage.setPassword(password);
        loginPage.logIn();
        Assert.assertEquals(driver.getTitle(), "Login", "Wrong title after unsuccessful login");
        Assert.assertEquals(driver.getCurrentUrl(), LoginPage.URL, "You are NOT on login page.");
        Assert.assertEquals(loginPage.getErrorMessage(), "Invalid username or password", "Wrong error message.");
    }

    /**
     * Steps to reproduce:
     * 1. Set username to admin123
     * 2. Set password to 123
     * 3. Click Login
     * 4. Verify that title of the page equals to "Login"
     * 5. Verify that URL of the page don't equals to LoginPage.URL
     * 6. Verify that error message on the page equals to "Invalid username or password"
     */
    @Parameters({"failUsername", "truePassword"})
	@Test(groups = "negative")
    public void negativeTestWrongLogin(String username, String password)  {
		loginPage.setUsernameUsingJS(username);
        loginPage.setPassword(password);
        loginPage.logIn();
        Assert.assertEquals(driver.getTitle(), "Login", "Wrong title after unsuccessful login");
        Assert.assertEquals(driver.getCurrentUrl(), LoginPage.URL, "You are NOT on login page.");
        Assert.assertEquals(loginPage.getErrorMessage(), "Invalid username or password", "Wrong error message.");
	}

	 /**
     * Steps to reproduce:
     * 1. Click Login
     * 2. Verify that title of the page equals to "Login"
     * 3. Verify that URL of the page don't equals to LoginPage.URL
     * 4. Verify that error message on the page equals to "Invalid username or password"
     */
    @Test(groups = "negative")
	public void negativeTestEmptyFields() {
		loginPage.logIn();
        Assert.assertEquals(loginPage.getErrorMessage(), "Value is required and can't be empty", "Wrong error message.");
        Assert.assertEquals(driver.getCurrentUrl(), LoginPage.URL, "You are NOT on login page.");
		Assert.assertEquals(driver.getTitle(), "Login", "Wrong title after unsuccessful login");
	}

	/**
     * Steps to reproduce:
     * 1. Close driver
     */
	@AfterSuite
	public void afterTest() {
		driver.quit();
	}
}
