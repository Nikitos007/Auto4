package HW4.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import HW4.Entity.User;

public class EditPlayerPage {

	private WebDriver driver;
	private WebDriverWait wait;
    public static final String URL = "http://80.92.229.236:81/players";
	
	public EditPlayerPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
	}
	
    public void open() {
        driver.get(URL);
    }
    
	public void openInsert() {
		driver.findElement(By.xpath(".//a[text() = 'Insert']")).click();
	}

	
	@FindBy(xpath = ".//input[@id='ff14642ac1c__us_login']")
	private WebElement userNameField;
	
	@FindBy(xpath = ".//input[@id='ff14642ac1c__us_email']")
	private WebElement userEmailField;
	
	@FindBy(xpath = ".//input[@id='ff14642ac1c__us_password']")
	private WebElement userPassField;
	
	@FindBy(xpath = ".//input[@id='ff14642ac1c__confirm_password']")
	private WebElement userPassConfirmField;
	
	@FindBy(xpath = ".//input[@id='ff14642ac1c__us_fname']")
	private WebElement userFirstNameField;
	
	@FindBy(xpath = ".//input[@id='ff14642ac1c__us_lname']")
	private WebElement userLastNameField;
	
	@FindBy(xpath = ".//input[@id='ff14642ac1c__us_city']")
	private WebElement userCityField;
	
	@FindBy(xpath = ".//textarea[@id='ff14642ac1c__us_address']")
	private WebElement userAddressField;
	
	@FindBy(xpath = ".//input[@id='ff14642ac1c__us_phone']")
	private WebElement userPhoneField;
	  
	@FindBy(xpath = ".//input[@value = 'Save']")
	private WebElement saveBtn;
	
	public void createUser(User user) {
		wait.until(ExpectedConditions.visibilityOf(userNameField));
		wait.until(ExpectedConditions.visibilityOf(userEmailField));
		wait.until(ExpectedConditions.visibilityOf(userPassField));
		wait.until(ExpectedConditions.visibilityOf(userPassConfirmField));
		wait.until(ExpectedConditions.visibilityOf(userFirstNameField));
		wait.until(ExpectedConditions.visibilityOf(userLastNameField));
		wait.until(ExpectedConditions.visibilityOf(userCityField));
		wait.until(ExpectedConditions.visibilityOf(userAddressField));
		wait.until(ExpectedConditions.visibilityOf(userPhoneField));
		wait.until(ExpectedConditions.visibilityOf(saveBtn));
		
		userNameField.sendKeys(user.getUsername());
		userEmailField.sendKeys(user.getEmail());
		userPassField.sendKeys(user.getPassword());
		userPassConfirmField.sendKeys(user.getPassword());
		userFirstNameField.sendKeys(user.getFirstName());
		userLastNameField.sendKeys(user.getLastName());
		userCityField.sendKeys(user.getCity());
		userAddressField.sendKeys(user.getAddress());
		userPhoneField.sendKeys(user.getPhone());
		saveBtn.click();
		
	}
	
	public void updateUser(User user) {
		wait.until(ExpectedConditions.visibilityOf(userEmailField));
		wait.until(ExpectedConditions.visibilityOf(userFirstNameField));
		wait.until(ExpectedConditions.visibilityOf(userLastNameField));
		wait.until(ExpectedConditions.visibilityOf(userCityField));
		wait.until(ExpectedConditions.visibilityOf(userAddressField));
		wait.until(ExpectedConditions.visibilityOf(userPhoneField));
		wait.until(ExpectedConditions.visibilityOf(saveBtn));
		
		userEmailField.clear();
		userEmailField.sendKeys(user.getEmail());
		userFirstNameField.clear();
		userFirstNameField.sendKeys(user.getFirstName());
		userLastNameField.clear();
		userLastNameField.sendKeys(user.getLastName());
		userCityField.clear();
		userCityField.sendKeys(user.getCity());
		userAddressField.clear();
		userAddressField.sendKeys(user.getAddress());
		userPhoneField.clear();
		userPhoneField.sendKeys(user.getPhone());
		saveBtn.click();
		
	}

	public User readUser() {
		wait.until(ExpectedConditions.visibilityOf(userNameField));
		wait.until(ExpectedConditions.visibilityOf(userEmailField));
		wait.until(ExpectedConditions.visibilityOf(userFirstNameField));
		wait.until(ExpectedConditions.visibilityOf(userLastNameField));
		wait.until(ExpectedConditions.visibilityOf(userCityField));
		wait.until(ExpectedConditions.visibilityOf(userAddressField));
		wait.until(ExpectedConditions.visibilityOf(userPhoneField));
		wait.until(ExpectedConditions.visibilityOf(saveBtn));
		
		User user = new User();
		userNameField.getAttribute("value");
		userEmailField.getAttribute("value");
		userFirstNameField.getAttribute("value");
		userLastNameField.getAttribute("value");
		userCityField.getAttribute("value");
		userAddressField.getAttribute("value");
		userPhoneField.getAttribute("value");
		return user;
	}

	public void deleteUser(String username) {
		driver.findElement(By.xpath(".//tr [.//td/a[text() = '" + username + "']] /td/a/img[@title = 'Delete']")).click();
		try{
			   //Wait 10 seconds till alert is present
			   WebDriverWait wait = new WebDriverWait(driver, 10);
			   Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			   //Accepting alert.
			   alert.accept();
			   System.out.println("Accepted the alert successfully.");
			}catch(Throwable e){
			   System.err.println("Error came while waiting for the alert popup. "+e.getMessage());
			}
	}

}
