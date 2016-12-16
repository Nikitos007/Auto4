package HW4.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import HW4.Entity.User;
import HW4.pages.EditPlayerPage;
import HW4.pages.LoginPage;
import HW4.pages.PlayersPage;


public class SearchTest {

	private WebDriver driver;
    private PlayersPage playersPage;
    private LoginPage loginPage;
    private EditPlayerPage crudUserPage;
	private User user;
	private SoftAssert softAssert;

	/**
	 * Precondition:
	 * 1. Open application LoginPage
	 * 2. Sign in
	 * 3. Verify that title of the page equals to "Players"
	 * 4. Create new user
	 */
    @BeforeSuite
    public void beforeTest() {
        driver = new FirefoxDriver();
        //wait while driver find element
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.setUsername("admin");
        loginPage.setPassword("123");
        loginPage.logIn();
        Assert.assertEquals(loginPage.getTitle(), "Players", "Wrong title.");
        this.playersPage = new PlayersPage(driver);
		this.user = new User();
		crudUserPage = new EditPlayerPage(driver);
		crudUserPage.openInsert();
    	crudUserPage.createUser(user);
    }
    
    @BeforeMethod
	public void beforeMethod() {
		playersPage.open();
		softAssert = new SoftAssert();
	}
    
    
    /**
     * Steps to reproduce:
     * 1. Open URL with title "Players"
     * 2. Set username and click search
     * 3. Verify that title of the page equals to "Players - Edit"
     */
    @Test (dependsOnMethods = "positiveSearchByEmailTest")
    public void positiveSearchByUsernameTest() {
    	playersPage.open();playersPage.searchUserClickEdit(user.getUsername());
    	softAssert.assertEquals(driver.getTitle(), "Players - Edit", "Wrong title after unsuccessful search by username");
    	softAssert.assertAll();
    }
    
    /**
     * Steps to reproduce:
     * 1. Open URL with title "Players"
     * 2. Set email and click search
     * 3. Verify that title of the page equals to "Players - Edit"
     */
    @Test (dependsOnMethods = "positiveSearchByFirstNameTest")
    public void positiveSearchByEmailTest() {
    	playersPage.searchUserByEmail(user.getEmail());
    	softAssert.assertEquals(driver.getTitle(), "Players - Edit", "Wrong title after unsuccessful search by email");
    	softAssert.assertAll();
    }
    
    /**
     * Steps to reproduce:
     * 1. Open URL with title "Players"
     * 2. Set firstName and click search
     * 3. Verify that title of the page equals to "Players - Edit"
     */
    @Test(dependsOnMethods = "positiveSearchByLastNameTest")
    public void positiveSearchByFirstNameTest() {
    	playersPage.searchUserByFirstName(user.getFirstName(), user.getUsername());
    	softAssert.assertEquals(driver.getTitle(), "Players - Edit", "Wrong title after unsuccessful search by first name");
    	softAssert.assertAll();
    }
    
    /**
     * Steps to reproduce:
     * 1. Open URL with title "Players"
     * 2. Set lastname and click search
     * 3. Verify that title of the page equals to "Players - Edit"
     */
    @Test
    public void positiveSearchByLastNameTest() {
    	playersPage.searchUserByLastName(user.getLastName(), user.getUsername());
    	softAssert.assertEquals(driver.getTitle(), "Players - Edit", "Wrong title after unsuccessful search by last name");
    	softAssert.assertAll();
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
