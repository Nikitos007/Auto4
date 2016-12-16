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


public class CRUDUserTests {

	private WebDriver driver;
    private EditPlayerPage crudUserPage;
    private User user;
    private SoftAssert softAssert;
    /**
	 * Precondition:
	 * 1. Open application LoginPage
	 * 2. Sign in
	 * 3. Verify that title of the page equals to "Players"
	 */
    @BeforeSuite
    public void beforeTest() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(100, TimeUnit.SECONDS);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.setUsername("admin");
        loginPage.setPassword("123");
        loginPage.logIn();
        Assert.assertEquals(driver.getTitle(), "Players", "Wrong title after unsuccessful login");
		this.crudUserPage = new EditPlayerPage(driver);
		this.user = new User();
		
    }
    
    @BeforeMethod
	public void beforeMethod() {
		new PlayersPage(driver).open();
		softAssert = new SoftAssert();
	}

    
    /**
     * Steps to reproduce:
     * 1. Open URL with title "Players"
     * 2. Click to Insert
     * 3. Verify that title of the page equals to "Players - Insert"
     * 4. Create new user
     * 5. Verify that title of the page equals to "Players"
     */
    @Test
    public void positiveInsertTest() {	
    	crudUserPage.openInsert();
    	softAssert.assertEquals(driver.getTitle(), "Players - Insert", "Wrong title after unsuccessful login");
    	crudUserPage.createUser(user);
    	softAssert.assertEquals(driver.getTitle(), "Players", "Wrong title after unsuccessful insert");
    	softAssert.assertAll();
    }
    
    /**
     * Steps to reproduce:
     * 1. Open URL with title "Players"
     * 2. Click to Insert
     * 3. Verify that title of the page equals to "Players - Insert"
     * 4. Create new user with empty essential fields
     * 5. Verify that title of the page equals to "Players - Insert"
     */
    @Test (dependsOnMethods = "positiveInsertTest")
    public void negativeInsertTest() {	
    	crudUserPage.openInsert();
    	softAssert.assertEquals(driver.getTitle(), "Players - Insert", "Wrong title after unsuccessful login");
    	User user = new User();
    	user.setUsername("");
    	user.setEmail("");
    	user.setPassword("");
    	user.setPhone("");    	
    	crudUserPage.createUser(user);
    	softAssert.assertEquals(driver.getTitle(), "Players - Insert", "Wrong title after unsuccessful insert");
    	softAssert.assertAll();
    }
    
    /**
     * Steps to reproduce:
     * 1. Open URL with title "Players"
     * 2. Search user by username
     * 3. Verify that title of the page equals to "Players - Edit"
     * 4. Insert new data to the user
     * 5. Update user
     * 6. Verify that title of the page equals to "Players"
     */
    @Test (dependsOnMethods = "negativeInsertTest")
    public void positiveUpdateTest() {	
    	PlayersPage playersPage = new PlayersPage(driver);
    	playersPage.searchUserClickEdit(user.getUsername());
    	softAssert.assertEquals(driver.getTitle(), "Players - Edit", "Wrong title after unsuccessful insert");
    	user.newInfoOfUser();
    	crudUserPage.updateUser(user);
    	softAssert.assertEquals(driver.getTitle(), "Players", "Wrong title after unsuccessful update");
    	softAssert.assertAll();
    }
    
    /**
     * Steps to reproduce:
     * 1. Open URL with title "Players"
     * 2. Search user by username
     * 3. Verify that title of the page equals to "Players - Edit"
     * 4. Read user info
     * 5. Equals fields of user
     */
    @Test (dependsOnMethods = "positiveDeleteTest")
    public void positiveReadTest() {	
    	PlayersPage playersPage = new PlayersPage(driver);
    	playersPage.searchUserClickEdit(user.getUsername());
    	softAssert.assertEquals(driver.getTitle(), "Players - Edit", "Wrong title after unsuccessful insert");
    	User actualUser = crudUserPage.readUser();
    	boolean equelsFlagUser = new User().equelsUsers(actualUser, user);
    	softAssert.assertEquals(equelsFlagUser, true, "Wrong users don't equels");
    	softAssert.assertAll();
    }
    
    /**
     * Steps to reproduce:
     * 1. Open URL with title "Players"
     * 2. Click to Insert
     * 3. Verify that title of the page equals to "Players - Insert"
     * 4. Create new user
     * 5. Search user by username
     * 6. Delete this user
     * 7. Verify that title of the page equals to "Players"
     */
    @Test (dependsOnMethods = "positiveUpdateTest")
    public void positiveDeleteTest() {	
    	crudUserPage.openInsert();
    	softAssert.assertEquals(driver.getTitle(), "Players - Insert", "Wrong title after unsuccessful login");
    	User user = new User();
    	crudUserPage.createUser(user);
    	PlayersPage playersPage = new PlayersPage(driver);
    	playersPage.searchUser(user.getUsername());
    	crudUserPage.deleteUser(user.getUsername());
    	softAssert.assertEquals(driver.getTitle(), "Players", "Wrong title after unsuccessful update");
    	softAssert.assertAll();
    }
    
    /**
     * Steps to reproduce:
     * 1. Close driver
     */
    @AfterSuite
	public void afterSuite() {
		driver.quit();
	}
    
}
