package src.cucumber.features;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class StepDefinitions {
	
	private WebElement searchField, suggestion, checkInCalendar, nextMonth, checkInDay;
	private String winHandleBefore;
	private FirefoxDriver driver = new FirefoxDriver();
	
	// Adding a Second comment
	@Given("^I have started the browser$")
	public void startBrowser() throws Throwable {
	//	FirefoxDriver driver = new FirefoxDriver();
		driver.get("http://www.booking.com.au");
		driver.manage().window().maximize();
	}
	
	@When("^I have performed a CitySearch$")
	public void citySearch() throws Throwable {
	WebElement	searchField = driver.findElement(By.xpath("//*[@id='destination']"));
		searchField.sendKeys("London");
		 suggestion = (new WebDriverWait(driver, 10))
	      		  .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='b2indexPage']/ul/li[1]/a")));
	    suggestion.click();
	}

	@When("^I have set a CheckInDate$")
	public void setCheckInDate() throws Throwable {
		// Select Calendar object
		WebElement checkInCalendar = (new WebDriverWait(driver, 10))
		 		  .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='first_calendar']")));
		checkInCalendar.click();
		WebElement nextMonth = (new WebDriverWait(driver, 10))
		 		  .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div/div[3]/div[1]/div[1]/form/fieldset/div[2]/div[2]/div[1]/div[1]/div/div[2]/div[2]/div[2]/div[2]/span")));
		// Navigate to December
		for (int i = 0;i < 4;i++){
			nextMonth.click();	
		}
		// Select 20th December
		 WebElement checkInDay = (new WebDriverWait(driver, 10))
	 		  .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='frm']/fieldset/div[2]/div[2]/div[1]/div[1]/div/div[2]/div[2]/div[2]/div[3]/div/div/div[5]/table/tbody/tr[4]/td[1]")));
		checkInDay.click();
	}

	@When("^I have set a setCheckOutDate$")
	public void setCheckOutDate() throws Throwable {
		WebElement checkOutCalendar = (new WebDriverWait(driver, 10))
		 		  .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='frm']/fieldset/div[2]/div[2]/div[1]/div[2]/div/div[2]/div[1]")));
		checkOutCalendar.click();
		WebElement nextMonth = (new WebDriverWait(driver, 10))
			  .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='frm']/fieldset/div[2]/div[2]/div[1]/div[2]/div/div[2]/div[2]/div[2]/div[2]/span")));
	// Navigate to January
	nextMonth.click();	
			
	// Select 10th January
	WebElement checkOutDay = (new WebDriverWait(driver, 10))
		  .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='frm']/fieldset/div[2]/div[2]/div[1]/div[2]/div/div[2]/div[2]/div[2]/div[3]/div/div/div[6]/table/tbody/tr[3]/td[1]/span")));
	checkOutDay.click();
	}

	@When("^I have setGuests$")
	public void setGuests() throws Throwable {
		// Select more Options
		Select options = new Select(driver.findElement(By.xpath("//*[@class='b-selectbox__element b-selectbox__groupselection']")));
		options.selectByVisibleText("More options");
		// Select Number of Children from dropdown
		Select childrenNum = new Select(driver.findElement(By.xpath("//*[@class='b-form-custom-group b-form-group b-form-group_subgroup']/div/div/div/div[3]/label/select")));
		childrenNum.selectByValue("1");
		// Select Age of Child from dropdown
		Select childAge = new Select(driver.findElement(By.xpath("//*[@class='b-children-ages-configurator']/div/label/select")));
		childAge.selectByValue("11");
	}

	@When("^I have selectedDistrict$")
	public void selectedDistrict() throws Throwable {
		// Click Search button
		WebElement searchButton = (new WebDriverWait(driver, 10))
		   		  .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='frm']/fieldset/div[5]/div/button/span")));
		searchButton.click();
		// Click District to view available 
		WebElement districtsAvailable = (new WebDriverWait(driver, 10))
	     		  .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='filter_district']/div[1]/h3")));
		districtsAvailable.click();
		//Pause for 2 seconds
		try {
			Thread.sleep(2000);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// Find the selected District
		WebElement selection = (new WebDriverWait(driver, 10))
	     		  .until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Lambeth")));
		selection.click();
	}

	@When("^I have selected the FirstHotelOnList$")
	public void firstHotelOnList() throws Throwable {
		// Store the current window handle
		winHandleBefore = driver.getWindowHandle();
				
		// Select the Hotel which is returned on the top of the list just to get this up and running
		WebElement headOfList = (new WebDriverWait(driver, 10))
	     		  .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='hotellist_inner']/div[1]/div[2]/div[1]/div[1]/h3/a")));
		headOfList.click();
				
		// Switch to new window opened
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
						
		/*************************************************************
		// Perform the actions on new window
		// Close the new window, if that window no more required
		//	driver.close();
		// Switch back to original browser (first window)
		//	driver.switchTo().window(winHandleBefore);
		// Continue with original browser (first window)
		 ***************************************************************/
	}

	@When("^I have booked a Room$")
	public void bookRoom() throws Throwable {
		WebElement book = (new WebDriverWait(driver, 15))
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='hcta']")));
		book.click();
	}

	@When("^I enterDetails$")
	public void enterDetails() throws Throwable {
		// Populate the details form with the required fields
		
				// Select Title
				Select title = new Select(driver.findElement(By.xpath("//*[@class='booker_title_select bp_input_select']")));
				title.selectByValue("mr");
				// Populate first Name
				WebElement firstNameField = driver.findElement(By.id("firstname"));
				firstNameField.sendKeys("Christopher");
				// Populate last Name
				WebElement lastNameField = driver.findElement(By.id("lastname"));
				lastNameField.sendKeys("Clark");
				// Populate Email address
				WebElement emailAddressField = driver.findElement(By.id("email"));
				emailAddressField.sendKeys("joe.bloggs@gmail.com");
				// Populate confirm Email address
				WebElement confirmationEmailAddressField = driver.findElement(By.id("email_confirm"));
				confirmationEmailAddressField.sendKeys("joe.bloggs@gmail.com");
				
			// This Link works on the page it shows the Booking details popup
			//	WebElement bookingDetails = (new WebDriverWait(driver, 10))
			//		  	.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='bookconditions']")));
			//		bookingDetails.click();
			//WebElement proceed = (new WebDriverWait(driver, 10))
			 //	.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='b_tt_holder_6']/button")));
			// THIS FINALLY FINDS the Continue Button the xpath to the Button which can be seen above
			//	FAILS although this is what xpath Checker says it is !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			
			// continue to payment page
			WebElement proceed = (new WebDriverWait(driver, 10))
			 	.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'Continue')]")));
			proceed.click();
	}

	@Then("^I can confirmReservation$")
	public void confirmReservation() throws Throwable {
		// Populate Address line
		WebElement addressField = driver.findElement(By.id("address1"));
		addressField.sendKeys("48 Lyle Street");
		// Populate City
		WebElement cityField = driver.findElement(By.id("city"));
		cityField.sendKeys("Melbourne");
		// Populate Postcode
		WebElement postCodeField = driver.findElement(By.id("zip"));
		postCodeField.sendKeys("3056");
		// Populate Phone number
		WebElement phoneNumberField = driver.findElement(By.id("phone"));
		phoneNumberField.sendKeys("0422448123");
		
		try {
			Thread.sleep(4000);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		driver.quit();
	}
}
