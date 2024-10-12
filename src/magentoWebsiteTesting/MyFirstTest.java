package magentoWebsiteTesting;

import java.sql.Driver;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v126.css.model.StyleDeclarationEdit;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import dev.failsafe.internal.util.Assert;

public class MyFirstTest{
	
	WebDriver driver = new ChromeDriver();
	
	String myWebsite ="https://magento.softwaretestingboard.com";
	Random rand = new Random();

	String password = "iLoveMyMom!234k";
	String LogoutPage = "https://magento.softwaretestingboard.com/customer/account/logout/" ;
	String emailAddressToLogin = "";
	
	
	
	@BeforeTest
	public void mySetup() {
		driver.manage().window().maximize();
		driver.get(myWebsite);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));


	}
	
	@Test(priority = 1 ,enabled = false)
	public void CreateAnAccount() throws InterruptedException {

//      Three way to select element

//		1-xpath
//   	WebElement createAccountpage = driver.findElement(By.xpath("//a[@href='https://magento.softwaretestingboard.com/customer/account/create/']"));

//		2-partialLinkText		
// 		WebElement createAccountpage = driver.findElement(By.partialLinkText("Account"));
		
//		3-LinkText
        WebElement createAccountpage = driver.findElement(By.linkText("Create an Account"));
		
//		4-CSS Selector
//	  	WebElement createAccountpage = driver.findElement(By.cssSelector("header[class='page-header'] li:nth-child(3) a:nth-child(1)"));
        createAccountpage.click(); 

        
 
        /*Declaration*/
       
        String domainName = "@gmail.com";
        
        int randomNumber = rand.nextInt(9876);
           
        String[] first_Names = { "Alice", "Bob", "Charlie", "David", "Eve", "Fay", "Grace" };
		
        String[] Last_Names = { "Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia" };

		int randomIndexForTheFirstName = rand.nextInt(first_Names.length);
		int randomIndexForTheLastName = rand.nextInt(Last_Names.length);

//		System.out.println(randomIndexForTheFirstName);
		String firstname = first_Names[randomIndexForTheFirstName];
//		System.out.println(randomIndexForTheLastName);
		String lastname = Last_Names[randomIndexForTheLastName];
				
				
		/*Select*/
    	WebElement firstnameInput = driver.findElement(By.id("firstname"));
    	WebElement lastnameInput = driver.findElement(By.id("lastname"));
    	WebElement email =driver.findElement(By.id("email_address"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement confirmpassword = driver.findElement(By.id("password-confirmation"));
        WebElement createAccountButton = driver.findElement(By.xpath("//button[@title=\"Create an Account\"]"));
        
        
        
        /*operation*/
        firstnameInput.sendKeys(firstname);
        lastnameInput.sendKeys(lastname);
        email.sendKeys(firstname + lastname + randomNumber + domainName);
        passwordInput.sendKeys(password);
        confirmpassword.sendKeys(password);
        createAccountButton.click();
        
        emailAddressToLogin =firstname + lastname + randomNumber + domainName;
	
        Thread.sleep(5000);
       WebElement MessageAsWebElement = driver.findElement(By.className("messages"));
       

	
//        WebElement MessageAsWebElement = driver.findElement(By.className("messages"));

		String TheActualMessage = MessageAsWebElement.getText();
//		System.out.println(TheActualMessage);
		String ExpectedMessage = "Thank you for registering with Main Website Store.";
		org.testng.Assert.assertSame(TheActualMessage, ExpectedMessage);
	
	
	}
	
	
	@Test(priority = 2,enabled = false)
	public void logOut() {
		
		driver.get(LogoutPage);
		
		WebElement LogoutMessage =driver.findElement(By.xpath("//span[@data-ui-id=\"page-title-wrapper\"]"));
		
		String ActualMsg = LogoutMessage.getText();
		String ExpectedMsg = "You are signed out";

		org.testng.Assert.assertEquals(ActualMsg, ExpectedMsg);
	}
	
	
	@Test(priority = 3,enabled = false)
	public void loginTest() throws InterruptedException {
		WebElement LoginPage = driver.findElement(By.linkText("Sign In"));
		LoginPage.click();

		WebElement EmailLoginInput = driver.findElement(By.id("email"));
		WebElement passwordInput = driver.findElement(By.id("pass"));
		WebElement LoginButton = driver.findElement(By.cssSelector(".action.login.primary"));

		EmailLoginInput.sendKeys(emailAddressToLogin);
		passwordInput.sendKeys(password);
		LoginButton.click();
		Thread.sleep(5000);

//		WebElement WelcomeMessage = driver.findElement(By.className("page-title"));
//		String ActualValue = WelcomeMessage.getText();
//		String ExpectedValue = "My Account";
	
		String WelcomeMessage = driver.findElement(By.className("logged-in")).getText();
		boolean ActualValue = WelcomeMessage.contains("Welcome");
		boolean ExpectedValue = true;

		
		org.testng.Assert.assertEquals(ActualValue, ExpectedValue);
	}
	
	
	@Test(priority = 4 ,enabled = false)
	public void addMenItem() throws InterruptedException {

		
		/*Select*/
		WebElement MenSection = driver.findElement(By.id("ui-id-5"));
		

		/*operation*/
		MenSection.click();

		
		/*Select*/
		WebElement productITemsContainer = driver.findElement(By.className("product-items"));
		List<WebElement> AllItems = productITemsContainer.findElements(By.tagName("li"));
		int totalNumberOfItems = AllItems.size();
		int randomItem = rand.nextInt(totalNumberOfItems);
		
		/*operation*/
		AllItems.get(randomItem).click();
		
		
		/*Select*/
		WebElement theContainerOfSizes = driver.findElement(By.cssSelector(".swatch-attribute.size"));
		List<WebElement> ListOfSizes = theContainerOfSizes.findElements(By.className("swatch-option"));
		int numberofSizes = ListOfSizes.size();
		int randomSize = rand.nextInt(numberofSizes);
		
		/*operation*/
		ListOfSizes.get(randomSize).click();
	
		/*Select*/
		WebElement ColorsContainer = driver.findElement(By.cssSelector(".swatch-attribute.color"));
		List<WebElement> ListOfClors = ColorsContainer.findElements(By.tagName("div"));
		int numberOfColors = ListOfClors.size();
		int randomColor = rand.nextInt(numberOfColors);
		
		/*operation*/
		ListOfClors.get(randomColor).click();
		
		/*Select*/
		WebElement AddToCartButton = driver.findElement(By.id("product-addtocart-button"));
		/*operation*/
		AddToCartButton.click();
		
		WebElement MessageAdded = driver.findElement(By.className("message-success"));
		System.out.println(MessageAdded.getText().contains("You added"));
		
		org.testng.Assert.assertEquals(MessageAdded.getText().contains("You added"), true);

	}
	
	@Test(priority = 4 , enabled = true)
	public void  addWomenItem() throws InterruptedException {
		
		WebElement WomenSection = driver.findElement(By.id("ui-id-4"));
		WomenSection.click();
		
	
		WebElement productITemsContainer = driver.findElement(By.className("product-items"));
		List<WebElement> AllItems = productITemsContainer.findElements(By.tagName("li"));
		
		int totalNumberOfItems = AllItems.size();
		int randomitem = rand.nextInt(totalNumberOfItems);
		
		AllItems.get(randomitem).click();
	
		
	    WebElement theContainerOfSizes = driver.findElement(By.className("swatch-attribute"));
	    List<WebElement> ListOfSizes = theContainerOfSizes.findElements(By.tagName("div"));   
	    
	    int numberofSize = ListOfSizes.size();
	    int randomSize = rand.nextInt(numberofSize);
		
	    ListOfSizes.get(randomSize).click();	

		WebElement ColorsContainer = driver.findElement(By.cssSelector("div[class='swatch-attribute color'] div[role='listbox']"));
		List<WebElement> ListofColrs = ColorsContainer.findElements(By.tagName("div"));
		
		
		int numberOfColors = ListofColrs.size();
		int randomColor = rand.nextInt(numberOfColors);
		
		ListofColrs.get(randomColor).click();
	
		WebElement AddToCartButton  = driver.findElement(By.id("product-addtocart-button"));
		AddToCartButton.click();
		
		Thread.sleep(5000);
		
		WebElement MessageAdded = driver.findElement(By.cssSelector(".message-success.success.message"));
		boolean Massage = MessageAdded.getText().contains("You added");
		
		org.testng.Assert.assertEquals(Massage, true);
		

		driver.navigate().refresh();

		WebElement ReviewSEction = driver.findElement(By.id("tab-label-reviews-title"));

		ReviewSEction.click();

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("window.scrollTo(0,1200)");

		Thread.sleep(2000);

		WebElement RatingStars = driver.findElement(By.cssSelector(".control.review-control-vote"));

		;

		Thread.sleep(2000);

		System.out.println(RatingStars.findElements(By.tagName("label")).size() + "*****************");
//		RatingStars.findElements(By.tagName("label")).get(2).click();

		String[] ids = { "Rating_1", "Rating_2", "Rating_3", "Rating_4", "Rating_5" };
		int randomIndex = rand.nextInt(ids.length);
		WebElement element = driver.findElement(By.id(ids[randomIndex]));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

		WebElement nickname = driver.findElement(By.id("nickname_field"));
		nickname.sendKeys("soso");

		WebElement summary = driver.findElement(By.id("summary_field"));

		summary.sendKeys("mahmoud");

		WebElement review = driver.findElement(By.id("review_field"));

		review.sendKeys("hello this is a test");
		;

		WebElement submitReviewButton = driver.findElement(By.cssSelector(".action.submit.primary"));

		submitReviewButton.click();

		String actualTextforReview = driver.findElement(By.cssSelector(".message-success.success.message")).getText();
		String expectedTextforReview = "You submitted your review for moderation.";

		org.testng.Assert.assertEquals(actualTextforReview, expectedTextforReview);

		
		
	}
	
	
	
}
	

 

