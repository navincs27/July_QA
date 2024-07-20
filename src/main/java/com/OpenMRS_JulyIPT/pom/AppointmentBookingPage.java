package com.OpenMRS_JulyIPT.pom;

<<<<<<< HEAD
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.OpenMRS_JulyIPT.base.BaseClass;

public class AppointmentBookingPage extends BaseClass{
	WebDriver driver;
	public AppointmentBookingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
=======
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AppointmentBookingPage {
	@FindBy(xpath = "//div[contains(text(),'Start Visit')]")
	private WebElement startvisit;
>>>>>>> Bala

}
