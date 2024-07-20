package com.OpenMRS_JulyIPT.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	private WebDriver logindriver;
	
	public HomePage( WebDriver Consdriver) {
		
		logindriver = Consdriver;
		PageFactory.initElements(logindriver, this);
	}
	
	@FindBy(id = "username")
	private WebElement userName;
	
	@FindBy(id = "password")
	private WebElement passWord;
	
	@FindBy(id = "Inpatient Ward")
	private WebElement location;
	
	@FindBy(id = "loginButton" )
	private WebElement loginBtn;

}
