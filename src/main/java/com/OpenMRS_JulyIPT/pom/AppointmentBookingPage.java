package com.OpenMRS_JulyIPT.pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AppointmentBookingPage {
	@FindBy(xpath = "//div[contains(text(),'Start Visit')]")
	private WebElement startvisit;

}
