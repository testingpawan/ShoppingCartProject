package com.onlineshopping.core.testcases;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import com.onlineshopping.core.basedata.BaseTestData;
import com.relevantcodes.extentreports.LogStatus;




public class DummyTestA extends BaseTestData {

	/* Account Creation */
	
	@Test(priority=1)
	public void  accountCreation()  {
				
		test=rep.startTest("Account Creation");
		test.log(LogStatus.INFO,"Staring the Test");
		
				
		try{			
			
		openBrowser("Chrome");
		navigate("appurl");
		click("sign_in_Button_Xpath");							
		typeData("create_email_text_Xpath",prop.getProperty("emailId"));
		click("create_Account_button_Xpath");		
		click("gender_title_Xpath");
		typeData("Fname_Xpath",prop.getProperty("Customer_First_Name"));
		typeData("Lname_Xpath",prop.getProperty("Customer_Last_Name"));		
		typeData("Passwrd_Xpath",prop.getProperty("Password"));
		selectData("Dob_day_Xpath",prop.getProperty("BirthDay"),"Dob_Month_Xpath",prop.getProperty("BirthMonth"),"Dob_year_Xpath",prop.getProperty("BirthYear"));				
		typeData("company_Xpath",prop.getProperty("Company"));
		typeData("Address_Xpath",prop.getProperty("Address"));
		typeData("city_Xpath",prop.getProperty("City"));
		selectData("State_Xpath",prop.getProperty("State"));
		typeData("Zipcode_Xpath",prop.getProperty("ZipCode"));
		selectData("Country_Xpath",prop.getProperty("Country"));		
		typeData("Add_Information_Xpath",prop.getProperty("Additional_info"));
		typeData("Home_Phone_Xpath",prop.getProperty("Home_Number"));
		typeData("Mobile_number_Xpath",prop.getProperty("Mobile_Number"));
		typeData("Address_alias_Xpath",prop.getProperty("Address_Alias"));
		click("Register_Button_Xpath");
		
	    /*typeData("userName","test1222@gmail.com");
		typeData("pass","#####3######");
		click("Signin");*/
			             
        textDisplayed("Welcome_validation");
		
       
		
		
		
		
		
		}
		catch(Exception E){
			System.out.println(E.getMessage());			
			reportFailure("Account not created sucessfully");
			test.log(LogStatus.FAIL,"Account Creation Failed");
		}
		
		
		
	}
	
	
	/* Adding Multiple Products to cart */
	
	@Test(priority=2,dependsOnMethods={"accountCreation"})
	public void shoppingCart(){
		
		test=rep.startTest("Adding products to cart");
		test.log(LogStatus.INFO,"Test Start for Adding Products");
		try{
		click("T-Shirt_Xpath");
		click("Size_T-Shirt_Xpath");
		click("List_View_Xpath");		
		click("Add_to_Cart_Button_Xpath");		
		handlingFrames("Frame_Tag_Name_Xpath");
		click("Continue_Shopping_button_Xpath");
		click("Dress_tab_Xpath");
		click("Printed_dress_Add_to_cart_Xpath");
        handlingFrames("Frame_Tag_Name_Xpath");
		click("Proceed_to_Checkout_Xpath");		
		
		click("Shopping_Summary_Proceed_to_Checkout_Xpath");
		click("Address_Summary_Proceed_to_Checkout_Xpath");
		
	    /*String A=driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[3]/div/div/form/div/div[2]/div[1]/div/div/table/tbody/tr/td[3]")).getText();
	    System.out.println("value of A" +A);
	    
		validateMessage(prop.getProperty("Shipping_Option_Message_Xpath"),"Shipping_Message");*/
		click("Terms_Conditions_Xpath");
		click("Shipping_Proceed_to_Checkout_Xpath");
		}
		
		catch(Exception E){
			System.out.println(E.getMessage());			
			reportFailure("Products not ordered Sucessfully");
			test.log(LogStatus.FAIL,"Products ordered failed");
		}
			
		 }
		
	
		
	
   @AfterTest
	public void quit(){
		rep.endTest(test);
		rep.flush();		
		System.out.println("Test");
		driver.close();
		
		
   }
	
	
}
