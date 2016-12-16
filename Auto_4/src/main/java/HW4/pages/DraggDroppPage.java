package HW4.pages;

import java.io.File;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DraggDroppPage {

	private WebDriver driver;
	private WebDriverWait wait;

	@FindBy(xpath = ".//*[@id='sortable']/li[3]")
	private WebElement draggableElement;

	@FindBy(id = "drop")
	private WebElement droppableElement;

	public DraggDroppPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 10);
		PageFactory.initElements(driver, this);
	}

	public void open() {
		driver.get(new File("./drag_and_drop/index.html").getAbsolutePath());

	}

	private String oldDragElement;

	public void dragAndDrop() {
		oldDragElement = draggableElement.getText();
		Actions builder = new Actions(driver);
		builder.dragAndDrop(draggableElement, droppableElement).perform();
	}

	public void canselAlert() {
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		alert.dismiss();
	}

	public void acceptAlert() {
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		alert.accept();
	}

	public boolean plesentDragElement() {
		if (oldDragElement.equals(draggableElement.getText())) {
			return true;
		}
		return false;
	}

	public void dragSort() {
		while (!checkSortOrder()) {
			for (int i = 1; i < 7; i++) {
				WebElement firstElement = driver.findElement(By.xpath(".//*[@id='sortable']/li[" + i + "]"));
				int prevElement = Integer.parseInt(firstElement.getText());
				// int j = i + 1;
				WebElement secondElement = driver.findElement(By.xpath(".//*[@id='sortable']/li[" + (i + 1) + "]"));
				int nextElement = Integer.parseInt(secondElement.getText());
				if (prevElement > nextElement) {
					Actions builder = new Actions(driver);
					builder.dragAndDrop(secondElement, firstElement).perform();
				}
			}

		}
	}

	public void dragSortReverse() {
		while (!checkSortReverseOrder()) {
			for (int i = 1; i < 7; i++) {
				WebElement firstElement = driver.findElement(By.xpath(".//*[@id='sortable']/li[" + i + "]"));
				int prevElement = Integer.parseInt(firstElement.getText());
				// int j = i + 1;
				WebElement secondElement = driver.findElement(By.xpath(".//*[@id='sortable']/li[" + (i + 1) + "]"));
				int nextElement = Integer.parseInt(secondElement.getText());
				if (prevElement < nextElement) {
					Actions builder = new Actions(driver);
					builder.dragAndDrop(secondElement, firstElement).perform();
				}
			}
		}
	}

	public boolean checkSortOrder() {
		int sum = 0;
		for (int i = 1; i <= 7; i++) {
			int number = Integer.parseInt(driver.findElement(By.xpath(".//*[@id='sortable']/li[" + i + "]")).getText());
			if (i == number) {
				sum++;
			}
		}
		if (sum == 7) {
			return true;
		}
		return false;
	}

	public boolean checkSortReverseOrder() {
		int sum = 0;
		int i, j;
		for (i = 7, j = 1; i >= 1; i--, j++) {
			int number = Integer.parseInt(driver.findElement(By.xpath(".//*[@id='sortable']/li[" + j + "]")).getText());
			if (i == number) {
				sum++;
			}
		}
		if (sum == 7) {
			return true;
		}
		return false;
	}

}
