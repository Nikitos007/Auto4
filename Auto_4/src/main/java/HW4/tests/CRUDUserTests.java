package HW4.tests;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import HW4.pages.CreateEditPlayerPage;
import HW4.pages.LoginPage;
import HW4.pages.PlayersPage;

public class CRUDUserTests {

	private WebDriver driver;
    private PlayersPage playersPage;

	//TODO: preview data (you can create dataProvider)
	private final String username = RandomStringUtils.randomAlphabetic(10);
	private final String email = RandomStringUtils.randomAlphabetic(10) + "@mail.ru";
	private final String password = RandomStringUtils.randomAlphabetic(10);
	private final String firstName = RandomStringUtils.randomAlphabetic(10);
	private final String lastName = RandomStringUtils.randomAlphabetic(10);
	private final String city = RandomStringUtils.randomAlphabetic(10);
	private final String address = RandomStringUtils.randomAlphabetic(10);
	private final String phone = RandomStringUtils.randomNumeric(10);

	private final String newEmail = RandomStringUtils.randomAlphabetic(10) + "@mail.ru";
	private final String newFirstName = RandomStringUtils.randomAlphabetic(10);
	private final String newLastName = RandomStringUtils.randomAlphabetic(10);
	private final String newCity = RandomStringUtils.randomAlphabetic(10);
	private final String newAddress = RandomStringUtils.randomAlphabetic(10);
	private final String newPhone = RandomStringUtils.randomNumeric(10);

    /**
	 * Precondition:
	 * 1. Open application LoginPage
	 * 2. Sign in
	 * 3. Verify that title of the page equals to "Players"
	 */
    @BeforeSuite
    public void beforeTest() {
        driver = new FirefoxDriver();
        //wait while driver find element
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
		LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.setUsername("admin");
        loginPage.setPassword("123");
        playersPage = loginPage.logIn();
        /*Assert.assertEquals(driver.getTitle(), "Players", "Wrong title after unsuccessful login");
		this.crudUserPage = new EditPlayerPage(driver);
		this.user = new User();*/
    }

    @BeforeMethod
	public void beforeMethod() {
		playersPage.open();
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
    	//driver.get("http://80.92.229.236:81/players");
		CreateEditPlayerPage createPlayerPage = playersPage.clickOnInsert();
    	Assert.assertEquals(driver.getTitle(), "Players - Insert", "Wrong title after unsuccessful login");
		createPlayerPage.setUserName(username);
		createPlayerPage.setEmail(email);
		createPlayerPage.setPassword(password);
		createPlayerPage.setConfirmPassword(password);
		createPlayerPage.setFirstName(firstName);
		createPlayerPage.setLastName(lastName);
		createPlayerPage.setCity(city);
		createPlayerPage.setAddress(address);
		createPlayerPage.setPhone(phone);
		createPlayerPage.clickOnSave();
		Assert.assertEquals(driver.getCurrentUrl(), PlayersPage.URL, "You are NOT on players page.");
    }

	@Test(dependsOnMethods = "positiveReadTestAfterEditing")
	public void positiveReadTestAfterCreation() {
		PlayersPage playersPage = new PlayersPage(driver);
		CreateEditPlayerPage editPlayerPage = playersPage.searchUserClickEdit(username);
		Assert.assertEquals(driver.getTitle(), "Players - Edit", "Wrong title after unsuccessful insert");
		Assert.assertEquals(editPlayerPage.getUserName(), username, "username does not match");
		Assert.assertEquals(editPlayerPage.getEmail(), email, "email does not match");
		Assert.assertEquals(editPlayerPage.getFirstName(), firstName, "first name matches");
		Assert.assertEquals(editPlayerPage.getLastName(), lastName, "last name matches");
		Assert.assertEquals(editPlayerPage.getCity(), city, "city does not match");
		Assert.assertEquals(editPlayerPage.getAddress(), address, "address matches");
		Assert.assertEquals(editPlayerPage.getPhone(), phone, "phone does not match");
		
	}
    
    /**
     * Steps to reproduce:
     * 1. Open URL with title "Players"
     * 2. Click to Insert
     * 3. Verify that title of the page equals to "Players - Insert"
     * 4. Create new user with empty essential fields
     * 5. Verify that title of the page equals to "Players - Insert"
     */
	@Test(dependsOnMethods = "positiveInsertTest")
    public void negativeInsertTest() {	
    	//driver.get("http://80.92.229.236:81/players");
		CreateEditPlayerPage createPlayerPage = playersPage.clickOnInsert();
    	Assert.assertEquals(driver.getTitle(), "Players - Insert", "Wrong title after unsuccessful login");
		createPlayerPage.setUserName("");
		createPlayerPage.setEmail("");
		createPlayerPage.setPassword("");
		createPlayerPage.setConfirmPassword("");
		createPlayerPage.setPhone("");
		createPlayerPage.clickOnSave();
    	Assert.assertEquals(driver.getTitle(), "Players - Insert", "Wrong title after unsuccessful insert");
		//TODO: get list of errors and verify them
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
    @Test(dependsOnMethods = "negativeInsertTest")
    public void positiveUpdateTest() {	
    	CreateEditPlayerPage editPlayerPage = playersPage.searchUserClickEdit(username);
		Assert.assertEquals(driver.getTitle(), "Players - Edit", "Wrong title after unsuccessful insert");
		editPlayerPage.setEmail(newEmail);
		editPlayerPage.setFirstName(newFirstName);
		editPlayerPage.setLastName(newLastName);
		editPlayerPage.setCity(newCity);
		editPlayerPage.setAddress(newAddress);
		editPlayerPage.setPhone(newPhone);
		editPlayerPage.clickOnSave();
		Assert.assertEquals(driver.getTitle(), "Players", "Wrong title after unsuccessful update");
    }
    
    /**
     * Steps to reproduce:
     * 1. Open URL with title "Players"
     * 2. Search user by username
     * 3. Verify that title of the page equals to "Players - Edit"
     * 4. Read user info
     * 5. Equals fields of user
     */
    @Test(dependsOnMethods = "positiveDeleteTest")
    public void positiveReadTestAfterEditing() {
    	PlayersPage playersPage = new PlayersPage(driver);
		CreateEditPlayerPage editPlayerPage = playersPage.searchUserClickEdit(username);
		Assert.assertEquals(driver.getTitle(), "Players - Edit", "Wrong title after unsuccessful insert");
		Assert.assertEquals(editPlayerPage.getUserName(), username, "Wrong username.");
		Assert.assertEquals(editPlayerPage.getEmail(), newEmail, "Wrong email.");
		Assert.assertEquals(editPlayerPage.getFirstName(), newFirstName, "Wrong first name.");
		Assert.assertEquals(editPlayerPage.getLastName(), newLastName, "Wrong last name.");
		Assert.assertEquals(editPlayerPage.getCity(), newCity, "Wrong city.");
		Assert.assertEquals(editPlayerPage.getAddress(), newAddress, "Wrong address.");
		Assert.assertEquals(editPlayerPage.getPhone(), newPhone, "Wrong phone.");
		
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
    @Test(dependsOnMethods = "positiveUpdateTest")
    public void positiveDeleteTest() {	
    	playersPage.searchUser(username);
		Assert.assertEquals(playersPage.getNumberOfUsersWith(username), 1, "Search by username is not working.");
		playersPage.deleteUser(username);
    	Assert.assertEquals(driver.getTitle(), "Players", "Wrong title after unsuccessful update");
		playersPage.searchUser(username);
		Assert.assertEquals(playersPage.getNumberOfUsersWith(username), 0, "Search by username is not working.");
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
