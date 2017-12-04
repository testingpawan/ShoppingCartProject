package com.onlineshopping.core.testcases;

import org.testng.annotations.Test;

import com.onlineshopping.core.basedata.BaseTestData;

public class DummyTestB extends BaseTestData {

	
	@Test(priority=2)
	public void shoppingCart(){
		
		
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
		
		
	
}
