package HW4.tests;

import java.util.concurrent.TimeUnit;

import HW4.pages.CreateEditPlayerPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import HW4.pages.LoginPage;
import HW4.pages.PlayersPage;

public class SearchTest {

	private WebDriver driver;
    private PlayersPage playersPage;
    private LoginPage loginPage;
    private CreateEditPlayerPage createPlayersPage;

	//TODO: preview data (you can create dataProvider)
	private final String username = RandomStringUtils.randomAlphabetic(10);
	private final String email = RandomStringUtils.randomAlphabetic(10) + "@mail.ru";
	private final String password = RandomStringUtils.randomAlphabetic(10);
	private final String firstName = RandomStringUtils.randomAlphabetic(10);
	private final String lastName = RandomStringUtils.randomAlphabetic(10);
	private final String city = RandomStringUtils.randomAlphabetic(10);
	private final String address = RandomStringUtils.randomAlphabetic(10);
	private final String phone = RandomStringUtils.randomNumeric(10);

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
        playersPage = loginPage.logIn();
        Assert.assertEquals(loginPage.getTitle(), "Players", "Wrong title.");

		createPlayersPage = playersPage.clickOnInsert();

		//TODO: you can create separate method in CreateEditPlayerPage for user creation:
		createPlayersPage.setUserName(username);
		createPlayersPage.setEmail(email);
		createPlayersPage.setPassword(password);
		createPlayersPage.setConfirmPassword(password);
		createPlayersPage.setFirstName(firstName);
		createPlayersPage.setLastName(lastName);
		createPlayersPage.setCity(city);
		createPlayersPage.setAddress(address);
		createPlayersPage.setPhone(phone);
		createPlayersPage.clickOnSave();
    }

    @BeforeMethod
	public void beforeMethod() {
		playersPage.open();
		playersPage.reset();
	}
    
    /**
     * Steps to reproduce:
     * 1. Open URL with title "Players"
     * 2. Set username and click search
     * 3. Verify that title of the page equals to "Players - Edit"
     */
    @Test
    public void positiveSearchByUsernameTest() {
    	playersPage.searchUser(username);
		Assert.assertEquals(playersPage.getNumberOfUsersWith(username), 1, "Search by username is not working.");
    }
    
    /**
     * Steps to reproduce:
     * 1. Open URL with title "Players"
     * 2. Set email and click search
     * 3. Verify that title of the page equals to "Players - Edit"
     */
    @Test
    public void positiveSearchByEmailTest() {
    	playersPage.searchUserByEmail(email);
		Assert.assertEquals(playersPage.getNumberOfUsersWith(username), 1, "Search by email is not working.");
    }
    
    /**
     * Steps to reproduce:
     * 1. Open URL with title "Players"
     * 2. Set firstName and click search
     * 3. Verify that title of the page equals to "Players - Edit"
     */
    @Test
    public void positiveSearchByFirstNameTest() {
    	playersPage.searchUserByFirstName(firstName);
		Assert.assertEquals(playersPage.getNumberOfUsersWith(username), 1, "Search by first name is not working.");
	}
    
    /**
     * Steps to reproduce:
     * 1. Open URL with title "Players"
     * 2. Set lastname and click search
     * 3. Verify that title of the page equals to "Players - Edit"
     */
    @Test
    public void positiveSearchByLastNameTest() {
    	playersPage.searchUserByLastName(lastName);
		Assert.assertEquals(playersPage.getNumberOfUsersWith(username), 1, "Search by last name is not working.");
    }

	//TODO: review this test method:
	@Test
	public void negativeSearchByLastNameTest() {
		playersPage.searchUserByLastName(firstName);
		Assert.assertEquals(playersPage.getNumberOfUsersWith(username), 0, "Search by last name is not working.");
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
