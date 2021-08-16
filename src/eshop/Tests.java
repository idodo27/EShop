package eshop;

import java.util.Random;

import javax.swing.JOptionPane;

public class Tests {

	public static void main(String[] args) {
		Cart c1 = new Cart();
		Customer donald = new Customer("donald");
		Customer dario = new Customer("dario");
		donald.setShoppingCart(c1);
		Cart c2 = new Cart();
		dario.setShoppingCart(c2);
		dario.setShoppingCart(c1);
		
		DBHandler test = new DBHandler();
		Random rand = new Random();

		Shop s1 = new Shop("shufersal");
		
		//System.out.println(s1.productNames);
	
		/*Notes :
		 * added in shop showTakenCarts(refererance to existings shop' cart array), didnt show them for some reason
		 * 
		 * problem when customer is taking a cart, he's assigned with the last cart instead of first. (if we added 10 , he gets 1009 instead of 1000)
		 * 
		 * 
		 * 23/7 :
		 * 	if user enters wrong input, back to main.
		 * 
		 * Customer Logout : added releaseCart();
		 * shop.customerSignUp -- set numbers and error messages
		 * 
		 * COMMENTED TAKE CART, WE DONT USE IT.
		 * 
		 * 
		 * 
		 * added : 	line 185			if(shop.customerSignUp(customer) == 1) {
					JOptionPane.showMessageDialog(null,"Sign up succesfull");
				}else if(shop.customerSignUp(customer) == 2) {
					JOptionPane.showMessageDialog(null,"customer already exists");
					break;
				}else {
					JOptionPane.showMessageDialog(null,"No place for new customers");
				}
				
				instead of 				shop.customerSignUp(customer);

		 * 
		 * CUSTOMER:
		 * LINE 221 : DELETED SYSTEM OUT PRINT
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 */
	
	
	
	
	
	
	
	
	
	
	
	
	}

}
