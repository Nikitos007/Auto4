package HW4.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateEditPlayerPage {

    private WebDriver driver;
    private WebDriverWait wait;
    public static final String URL_INSERT = "http://80.92.229.236:81/players/insert";

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
    
    
    public CreateEditPlayerPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    public void setUserName(String userName) {
    	wait.until(ExpectedConditions.visibilityOf(userNameField));
    	userNameField.clear();
    	userNameField.sendKeys(userName);
    }

    public void setEmail(String email) {
    	wait.until(ExpectedConditions.visibilityOf(userEmailField));
    	userEmailField.clear();
    	userEmailField.sendKeys(email);
    }

    public void setPassword(String password) {
    	wait.until(ExpectedConditions.visibilityOf(userPassField));
    	userPassField.clear();
    	userPassField.sendKeys(password);
    }

    public void setConfirmPassword(String password) {
    	wait.until(ExpectedConditions.visibilityOf(userPassConfirmField));
    	userPassConfirmField.clear();
    	userPassConfirmField.sendKeys(password);
    }

    public void setFirstName(String firstName) {
    	wait.until(ExpectedConditions.visibilityOf(userFirstNameField));
    	userFirstNameField.clear();
    	userFirstNameField.sendKeys(firstName);
    }

    public void setLastName(String lastName) {
    	wait.until(ExpectedConditions.visibilityOf(userLastNameField));
    	userLastNameField.clear();
    	userLastNameField.sendKeys(lastName);
    }

    public void setCity(String city) {
    	wait.until(ExpectedConditions.visibilityOf(userCityField));
    	userCityField.clear();
    	userCityField.sendKeys(city);
    }

    public void setAddress(String address) {
    	wait.until(ExpectedConditions.visibilityOf(userAddressField));
    	userAddressField.clear();
    	userAddressField.sendKeys(address);
    }

    public void setPhone(String phone) {
    	wait.until(ExpectedConditions.visibilityOf(userPhoneField));
    	userPhoneField.clear();
    	userPhoneField.sendKeys(phone);
    }

    public void clickOnSave() {
    	wait.until(ExpectedConditions.visibilityOf(saveBtn));
    	saveBtn.click();
    }

    public String getUserName() {
        return userNameField.getAttribute("value");
    }

    public String getFirstName() {
        return userFirstNameField.getAttribute("value");
    }

    public String getEmail() {
        return userEmailField.getAttribute("value");
    }

    public String getLastName() {
        return userLastNameField.getAttribute("value");
    }

    public String getCity() {
        return userCityField.getAttribute("value");
    }

    public String getAddress() {
        return userAddressField.getAttribute("value");
    }

    public String getPhone() {
        return userPhoneField.getAttribute("value");
    }
}
