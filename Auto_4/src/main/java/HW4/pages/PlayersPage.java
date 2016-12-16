package HW4.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PlayersPage {

	private WebDriver driver;
	private WebDriverWait wait;
	public static final String URL = "http://80.92.229.236:81/players";
	
	@FindBy(xpath = ".//input[@id='723a925886__login']")
    private WebElement usernameField;
	@FindBy(xpath = ".//input[@value = 'Search']")
    private WebElement searchBtn;
	@FindBy(xpath = ".//*[@id='723a925886__email']")
    private WebElement usernameEmail;
	@FindBy(xpath = ".//*[@id='723a925886__firstname']")
    private WebElement firstNameField;
	@FindBy(xpath = ".//*[@id='723a925886__lastname']")
    private WebElement lastNameField;
	
	public PlayersPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
	}

	public void open() {
		driver.get(URL);
	}

	public CreateEditPlayerPage clickOnInsert() {
		driver.findElement(By.xpath(".//img[@alt='Insert']")).click();
		return new CreateEditPlayerPage(driver);
	}
	
	public void searchUser(String username) {
		wait.until(ExpectedConditions.visibilityOf(usernameField));
		wait.until(ExpectedConditions.visibilityOf(searchBtn));
		usernameField.clear();
		usernameField.sendKeys(username);
		searchBtn.click();
	}
	
	public void searchUserByEmail(String email) {
		wait.until(ExpectedConditions.visibilityOf(usernameEmail));
		usernameEmail.clear();
		usernameEmail.sendKeys(email);
		searchBtn.click();
	}
	
	public void searchUserByFirstName(String firstName) {
		wait.until(ExpectedConditions.visibilityOf(firstNameField));
		firstNameField.clear();
		firstNameField.sendKeys(firstName);
		searchBtn.click();
	}
	
	public void searchUserByLastName(String lastName) {
		wait.until(ExpectedConditions.visibilityOf(lastNameField));
		lastNameField.clear();
		lastNameField.sendKeys(lastName);
		searchBtn.click();
	}

	public void searchForUser(String username) {
		wait.until(ExpectedConditions.visibilityOf(usernameField));
		wait.until(ExpectedConditions.visibilityOf(searchBtn));
		usernameField.clear();
		usernameField.sendKeys(username);
		searchBtn.click();
	}

	public CreateEditPlayerPage searchUserClickEdit(String username) {
		searchForUser(username);
		driver.findElement(By.xpath(".//tr[.//a[text()= '" + username + "'] ] //img[@title='Edit']")).click();
		return new CreateEditPlayerPage(driver);
	}

	public void editUserUsing(String username) {
		driver.findElement(By.xpath(".//tr[.//a[text()= '" + username + "'] ] //img[@title='Edit']")).click();
	}

	/**
	 * Method deletes user by using username.
	 * @param username
	 */
	public void deleteUser(String username) {
		driver.findElement(By.xpath(".//tr[.//a[text() = '" + username + "']]//img[@title = 'Delete']")).click();
		waitForAlertAndAccept();
	}

	public void waitForAlertAndAccept() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		alert.accept();
	}

	//TODO: turn off implicit wait
	public int getNumberOfUsersWith(String userName) {
		return driver.findElements(By.xpath(".//tr[.//a[text()='" + userName + "']]")).size();
	}

	public void reset() {
		driver.findElement(By.xpath(".//*[@value='Reset']")).click();
	}
}
