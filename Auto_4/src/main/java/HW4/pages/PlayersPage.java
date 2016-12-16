package HW4.pages;

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
	
	public PlayersPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
	}
	
	public void open() {
        driver.get(URL);
    }
	
	public void searchUserClickEdit(String username) {
	
		wait.until(ExpectedConditions.visibilityOf(usernameField));
		usernameField.clear();
		usernameField.sendKeys(username);
		wait.until(ExpectedConditions.visibilityOf(searchBtn));
		searchBtn.click();
		driver.findElement(By.xpath(".//tr [.//a[text()= '" + username + "'] ] //img[@title='Edit']")).click();
	}
	
	public void searchUser(String username) {
		wait.until(ExpectedConditions.visibilityOf(usernameField));
		usernameField.clear();
		usernameField.sendKeys(username);
		wait.until(ExpectedConditions.visibilityOf(searchBtn));
		searchBtn.click();
		driver.findElement(By.xpath(".//input[@value = 'Search']")).click();
	}
	
	
	@FindBy(xpath = ".//*[@id='723a925886__email']")
    private WebElement usernameEmail;

	
	public void searchUserByEmail(String email) {
		
		wait.until(ExpectedConditions.visibilityOf(usernameEmail));
		usernameEmail.clear();
		usernameEmail.sendKeys(email);
		searchBtn.click();
	}
	
	
	@FindBy(xpath = ".//*[@id='723a925886__firstname']")
    private WebElement firstNameField;

	public void searchUserByFirstName(String firstName, String username) {
		
		wait.until(ExpectedConditions.visibilityOf(firstNameField));
		firstNameField.clear();
		firstNameField.sendKeys(firstName);
		searchBtn.click();
	}

	@FindBy(xpath = ".//*[@id='723a925886__lastname']")
    private WebElement lastNameField;

	
	public void searchUserByLastName(String lastName, String username) {
		
		wait.until(ExpectedConditions.visibilityOf(lastNameField));
		lastNameField.clear();
		lastNameField.sendKeys(lastName);
		searchBtn.click();
		driver.findElement(By.xpath(".//tr [.//a[text()= '" + username + "'] ] //img[@title='Edit']")).click();
	}
	
}
