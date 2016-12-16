package HW4.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	private WebDriver driver;
	private WebDriverWait wait;
    public static final String URL = "http://80.92.229.236:81/auth/login";
      
    @FindBy(id = "username")
    private WebElement usernameInput;
    @FindBy(id = "password")
    private WebElement passwordInput;
    @FindBy(id = "logIn")
    private WebElement logInBtn;
    @FindBy(xpath = ".//ul[@class='errors']/li")
    private WebElement errorElement;

    
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get(URL);
    }

    public void setUsername(String value) {
        //create element and clear and sendKeys
    	//WebElement usernameInput = driver.findElement(By.id("username"));
    	wait.until(ExpectedConditions.visibilityOf(usernameInput));
        usernameInput.clear();
        usernameInput.sendKeys(value);
    }

    public void setPassword(String value) {
        //create element and clear and sendKeys
        //WebElement passwordInput = driver.findElement(By.id("password"));
    	wait.until(ExpectedConditions.visibilityOf(passwordInput));
    	passwordInput.clear();
        passwordInput.sendKeys(value);
    }

    public void logIn() {
        //find logIn button and click
        //WebElement logInBtn = driver.findElement(By.id("logIn"));
        wait.until(ExpectedConditions.visibilityOf(logInBtn));
        logInBtn.click();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getErrorMessage() {
//        WebElement errorElement = driver.findElement(By.xpath(".//ul[@class='errors']/li"));
        wait.until(ExpectedConditions.visibilityOf(errorElement));
        return errorElement.getText();
    }

    public void setUsernameUsingJS(String value) {
        String script = "document.getElementById('username').value = '" + value + "'";
        ((JavascriptExecutor) driver).executeScript(script);
    }
    
}
