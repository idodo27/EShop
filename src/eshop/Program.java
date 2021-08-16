package eshop;

import javax.swing.JOptionPane;

/**
 * 
 * @author Michael Winkler & Ido Nidbach
 * @date 23/07/20
 *
 */

public class Program {

	public static void main(String[] args) {


		
		// Shop's related objects and variables:
		
		Shop shop = new Shop();		
		String shopName = "";
		Cart currentCart;
		Product product;
		Customer customer;
		Customer loggedCustomer;
		

		
		// Customer/user related variables:
		 
		String userName = "";
		String password = "";
		String userMenu = "";
		String userAddress = "";
		String phoneNumber = "";
		String cartContent = "";
		String loginPassword = "";		
		String passwordAuthentication = "";
		
		 // Different Menu choices:

		
		String userChoice = "";
		String adminChoice = "";
		String shopChoiceMenu = "";
		String settingsChoice = "";
		String cartMenuChoice = "";
		String choiceAfterLogin = "";
		String reportsMenuChoice = "";
		String productMenuChoice = "";
		String customerMenuChoice = "";
		
		 //Product related variables:
		 
		
		String productName = "";
		String productNames = "";
		String productPrice = "";
		String productDiscount = "";
		String numberOfCarts = "";
		String cartList = "";
		String cartIDAsString = "";
		

		/**
		 * Error Messages:
		 */
		
		
		String productNotFound = "Product not found.";
		String customerNotExists = "Customer does not exists";
		String noShoppingCartTaken = "No shopping cart taken yet";
		String invalidInput = "Please enter a number from the menu.";
		String invalidPassword = "Passwords don't match please re-enter";
		String noCartsAssigned = "Please assign carts to the shop first (from administrative menu)";
		
		/**
		 * Menus:
		 */
		
		String cartMenu = "1. Add new cart \n2. Remove cart \n3. Show carts\n4. Exit";
		String reportsMenu = "1. Show product Sales \n2. Show shop's current revenue \n3. Exit";
		String settingsMenu = "1. Change password\n2. Change shipping address\n3. Change phone number\n4. Exit to menu";
		String adminMenu = "1. Product actions \n2. Reports \n3. Customer actions \n4. Cart actions \n5. Return to menu";
		String customerMenu = "1. Add new customer \n2. Remove customer \n3. Show customer details. \n4. Show customer names \n5. Exit";
		String productMenu = "1. Add product \n2. Remove product \n3. Change product price \n4. Change discount percent \n5. Show product \n6. Exit ";
		String menu = "1. Sign up\n2. login \n3. Show Available Products  \n4. Show online customers \n5. Show taken Carts \n6. Administrative menu \n7. Exit";
		String menuAfterLogin = "1. Take Cart\n2. Add Product \n3. Remove Product \n4. Show Cart Content \n5. Clear cart \n6. Release cart \n7. Pay bill \n8. Log out \n9. Settings\n10. Exit to main menu";
		String shopMenu = "1. Choose a name for the shop. \n2. Choose the maximum amount of Products \n3. Choose maximum customers \n4. Choose maximum carts \n5. Create a ready shop.\n6. Quit the program";
		

		
		
		int maxProducts = 0, maxCustomers = 0, maxCarts = 0;
		int passwordInputTimes = 0;
		int nameTakenStatus = 0;
		int customerIndex = 0;
		int productIndex = 0;
		int parameter = 0;
		int cartID = 0;

		
		double discount = 0.0;
		double newPrice = 0.0;
		double price = 0.0;

		// Shop creation menu:
		
		do {
			shopChoiceMenu = JOptionPane.showInputDialog(null, shopMenu);
			switch (shopChoiceMenu) {
			case "1":
				adminChoice = JOptionPane.showInputDialog(null, "Enter the name: ");
				shopName = adminChoice;
			case "2":
				do {
					adminChoice = JOptionPane.showInputDialog(null,
							"Enter Max amount of Products: (between 40 and 1000) ");
					maxProducts = Integer.parseInt(adminChoice);
				} while (maxProducts < 40 || maxProducts > 1000);
			case "3":
				do {
					adminChoice = JOptionPane.showInputDialog(null,
							"Enter Max amount of Customers : (between 1 and 100)");
					maxCustomers = Integer.parseInt(adminChoice);
				} while (maxCustomers < 1 || maxCustomers > 100);

			case "4":
				do {
					adminChoice = JOptionPane.showInputDialog(null, "Enter Max amount of carts: (between 1 and 100)");
					maxCarts = Integer.parseInt(adminChoice);
				} while (maxCarts < 1 || maxCarts > 100);
				shop = new Shop(shopName, maxProducts, maxCustomers, maxCarts);
				shopChoiceMenu = "4";
				break;
			case "5":
				shopName = JOptionPane.showInputDialog(null,
						"Enter the name of the shop.\nEverything else will be created randomly : ");
				shop = new Shop(shopName);
				break;
			case "6":
				return;
			default:
				JOptionPane.showMessageDialog(null, invalidInput, "ERROR", JOptionPane.ERROR_MESSAGE);
				shopChoiceMenu = "1";

			}

		} while (!shopChoiceMenu.contentEquals("4") && !shopChoiceMenu.contentEquals("5"));

		JOptionPane.showMessageDialog(null, "Welcome to " + shop.getShopName());
		userChoice = JOptionPane.showInputDialog(null, menu);

		
		// User menu:
		do {
			switch (userChoice) {
			// User menu: 1. Sign up
			case "1":
				nameTakenStatus = 0;
				do {
					if (nameTakenStatus > 0) {
						JOptionPane.showMessageDialog(null, "This username is already taken:");
						userName = JOptionPane.showInputDialog(null, "Please enter a different user name");
						customer = new Customer(userName);
					} else {
						userName = JOptionPane.showInputDialog(null, "Please enter your user name");
						customer = new Customer(userName);
					}

					nameTakenStatus++;
				} while (shop.customerExists(customer));
				do {
					password = JOptionPane.showInputDialog(null, "Please enter your password, at least 4 characters");
					if (password.length() < 4) {
						JOptionPane.showMessageDialog(null, "Your password is too short\nTry again.", "Warning",
								JOptionPane.WARNING_MESSAGE);
					}
				} while (password.length() < 4);

				userAddress = JOptionPane.showInputDialog(null, "Please enter your shipping address : ");
				phoneNumber = JOptionPane.showInputDialog(null, "Please enter your phone number: ");

				customer = new Customer(userName, password, userAddress, phoneNumber);
				if(shop.customerSignUp(customer) == 1) {
					JOptionPane.showMessageDialog(null,"Sign up succesfull");
				}else if(shop.customerSignUp(customer) == 2) {
					JOptionPane.showMessageDialog(null,"customer already exists");
				}else {
					JOptionPane.showMessageDialog(null,"No place for new customers");
				}
				break;
			// User menu: 2. login
			case "2":
				userName = JOptionPane.showInputDialog(null, "Please enter your user name");
				if (shop.customerNameExists(userName)) {
					customerIndex = shop.findCustomerIndex(userName);
					passwordInputTimes = 0;
					JOptionPane.showMessageDialog(null, "Hello, " + userName);
					do {

						loginPassword = JOptionPane.showInputDialog(null, "Please enter your password");

						if (shop.getCustomers()[customerIndex].customerLogin(userName, loginPassword) == 0) {

							JOptionPane.showMessageDialog(null, "Welcome, " + userName);
							shop.getCustomers()[customerIndex].customerLogin(userName, loginPassword);
							loggedCustomer = shop.getCustomers()[customerIndex];
							userMenu = "Hello " + loggedCustomer.getUserName() + "\n" + menuAfterLogin;
							// Menu after login:
							do {

								choiceAfterLogin = JOptionPane.showInputDialog(null, userMenu);
								switch (choiceAfterLogin) {
								// Menu after login: 1.Take Cart
								case "1":
									loggedCustomer.takeCart(shop.getCarts());
									if (loggedCustomer.getShoppingCart() != null) {
										JOptionPane.showMessageDialog(null, "You have Taken cart number: "
												+ loggedCustomer.getShoppingCart().getCartID());
										break;
									} else {
										JOptionPane.showMessageDialog(null, noCartsAssigned, "ERROR",
												JOptionPane.ERROR_MESSAGE);
										break;
									}

								// Menu after login: 2.Add Product
								case "2":
									if (loggedCustomer.getShoppingCart() != null) {
										productNames = shop.productNames();
										productName = JOptionPane.showInputDialog(null,
												"Please choose a product:\n" + productNames);
										if(productName.charAt(0) >= '1' && productName.charAt(0) <= '9') {
											loggedCustomer.addProductByNumber(Integer.parseInt(productName));
											JOptionPane.showMessageDialog(null, "You have added " + shop.getProducts()[Integer.parseInt(productName)-1].getProductName());
											break;
										}
										else {
											loggedCustomer.addProductToCart(productName);
											JOptionPane.showMessageDialog(null, "You have added " + shop.getProducts()[shop.findProductIndex(productName)].getProductName());
											break;
										}
										
										
									} else {
										JOptionPane.showMessageDialog(null, "Please take a shopping cart first");
										break;
									}
								// Menu after login: 3.Remove Product
								case "3":
									// Bill included.
									if(loggedCustomer.getShoppingCart() != null) {
										currentCart = loggedCustomer.getShoppingCart();
										productName = JOptionPane.showInputDialog(null,
												"Please choose a product to remove from your cart :\n"
														+ currentCart.showCartContent());
										currentCart.removeProduct(productName);
										JOptionPane.showMessageDialog(null, "You have Removed : " + productName);
									}
									else {
										JOptionPane.showMessageDialog(null, "please take a shopping cart first");
									}
									break;
								// Menu after login: 4.Show Cart Content
								case "4":
									if (loggedCustomer.getShoppingCart() != null) {
										cartContent = loggedCustomer.getShoppingCart().showCartContent();
										JOptionPane.showMessageDialog(null, cartContent);
										break;
									} else {
										JOptionPane.showMessageDialog(null, "please take a shopping cart first");
										break;
									}
								// Menu after login: 5.Clear cart
								case "5":
									if (loggedCustomer.getShoppingCart() != null) {
										loggedCustomer.getShoppingCart().emptyCart();
										JOptionPane.showMessageDialog(null, "Shopping cart cleared.");
									} else {
										JOptionPane.showMessageDialog(null, noShoppingCartTaken, "ERROR",
												JOptionPane.ERROR_MESSAGE);
									}

									break;
								// Menu after login: 6.Release cart	
								case "6":
									if (loggedCustomer.getShoppingCart() != null) {
										JOptionPane.showMessageDialog(null, "You have released cart number: "
												+ loggedCustomer.getShoppingCart().getCartID());
										loggedCustomer.releaseCart();
									} else {
										JOptionPane.showMessageDialog(null, noShoppingCartTaken, "ERROR",
												JOptionPane.ERROR_MESSAGE);
									}

									break;
								// Menu after login: 7.Pay bill	
								case "7":
									if (loggedCustomer.getShoppingCart() != null) {
										JOptionPane.showMessageDialog(null,
												"You have paid : " + loggedCustomer.payBill());
										break;
									} else {
										JOptionPane.showMessageDialog(null, noShoppingCartTaken, "ERROR",
												JOptionPane.ERROR_MESSAGE);

									}

								// Menu after login: 8.Log out
								case "8":
									loggedCustomer.setOnlineOrNot(false);
									loggedCustomer = null;
									break;
								// Menu after login: 9.User Settings:
								case "9":
									do {
										settingsChoice = JOptionPane.showInputDialog(null, settingsMenu);
										switch (settingsChoice) {
										// User Settings: 1.Password Change:
										case "1":
											password = JOptionPane.showInputDialog(null, "Please enter your password");
											passwordInputTimes = 0;
											do {
												if (loggedCustomer.getPassword().equals(password)) {

													passwordInputTimes = 4;
													break;
												}
												passwordInputTimes++;

												if (passwordInputTimes > 3) {
													password = JOptionPane.showInputDialog(null,
															"You have one more try\n" + invalidPassword, "WARNING",
															JOptionPane.WARNING_MESSAGE);
												} else if (passwordInputTimes <= 3
														&& !loggedCustomer.getPassword().equals(password)) {
													password = JOptionPane.showInputDialog(null, invalidPassword,
															JOptionPane.ERROR_MESSAGE);
												}

											} while (passwordInputTimes <= 3);

											if (loggedCustomer.getPassword().equals(password)) {
												password = JOptionPane.showInputDialog(null,
														"Please enter your new password");
												passwordInputTimes = 0;
												do {
													if (passwordInputTimes == 0) {
														passwordAuthentication = JOptionPane.showInputDialog(null,
																"Please re-enter your new password");
													}

													passwordInputTimes++;
													if (password.equals(passwordAuthentication)) {
														loggedCustomer.changePassword(passwordAuthentication);
														JOptionPane.showMessageDialog(null, "Password changed");
														passwordInputTimes = 4;
														break;
													}
													if (passwordInputTimes > 3) {
														passwordAuthentication = JOptionPane.showInputDialog(null,
																"You have one more try\n" + invalidInput, "WARNING",
																JOptionPane.WARNING_MESSAGE);
													} else if (passwordInputTimes <= 3
															&& !password.equals(passwordAuthentication)) {
														passwordAuthentication = JOptionPane.showInputDialog(null,
																invalidPassword, JOptionPane.ERROR_MESSAGE);
													}

												} while (passwordInputTimes <= 3);
											} else {
												JOptionPane.showInputDialog(null, invalidPassword,
														JOptionPane.ERROR_MESSAGE);
											}
											break;
										// User Settings: 2.Shipping address edit:
										case "2":
											password = JOptionPane.showInputDialog(null, "Please enter your password");
											passwordInputTimes = 0;
											do {
												if (loggedCustomer.getPassword().equals(password)) {
													String newAdd = JOptionPane.showInputDialog(null,
															"Please enter your new address");
													loggedCustomer.changeAddress(newAdd);
													JOptionPane.showMessageDialog(null, "Address changed.");
													passwordInputTimes = 4;
													break;
												}
												passwordInputTimes++;

												if (passwordInputTimes > 3) {
													password = JOptionPane.showInputDialog(null,
															"You have one more try\n" + invalidPassword, "WARNING",
															JOptionPane.WARNING_MESSAGE);
												} else if (passwordInputTimes <= 3
														&& !loggedCustomer.getPassword().equals(password)) {
													password = JOptionPane.showInputDialog(null, invalidPassword,
															"ERROR", JOptionPane.ERROR_MESSAGE);
												}

											} while (passwordInputTimes <= 3);

											break;
										// User Settings: 3.Phone number edit:
										case "3":
											password = JOptionPane.showInputDialog(null, "Please enter your password");
											passwordInputTimes = 0;
											do {
												if (loggedCustomer.getPassword().equals(password)) {
													String newPhone = JOptionPane.showInputDialog(null,
															"Please enter your new phone");
													loggedCustomer.changePhoneNumber(newPhone);
													JOptionPane.showMessageDialog(null, "Phone changed.");
													passwordInputTimes = 4;
													break;
												}
												passwordInputTimes++;

												if (passwordInputTimes > 3) {
													password = JOptionPane.showInputDialog(null,
															"You have one more try\n" + invalidPassword + "WARNING",
															JOptionPane.ERROR_MESSAGE);
												} else if (passwordInputTimes <= 3
														&& !loggedCustomer.getPassword().equals(password)) {
													password = JOptionPane.showInputDialog(null, invalidPassword,
															"ERROR", JOptionPane.ERROR_MESSAGE);
												}

											} while (passwordInputTimes <= 3);
											break;
										//// User Settings: 4.Exit to menu	
										case "4":
											break;

										default:
											JOptionPane.showMessageDialog(null, invalidInput, "ERROR",
													JOptionPane.ERROR_MESSAGE);

										}
									} while (!settingsChoice.contentEquals("4"));
									break;
								// 10.Back to previous menu
								case "10":
									loggedCustomer = null;
									break;
								default:
									JOptionPane.showMessageDialog(null, invalidInput, "ERROR",
											JOptionPane.ERROR_MESSAGE);
									break;
								}

							} while (loggedCustomer != null);
							break;
						} else {
							JOptionPane.showMessageDialog(null, invalidPassword, "ERROR", JOptionPane.ERROR_MESSAGE);
							passwordInputTimes++;
						}
						if (passwordInputTimes == 4) {
							JOptionPane.showMessageDialog(null, "You have one more try.", "WARNING",
									JOptionPane.WARNING_MESSAGE);
						}
						if (passwordInputTimes == 5) {
							JOptionPane.showMessageDialog(null, "You have exceeded the max login tries.", "ERROR",
									JOptionPane.ERROR_MESSAGE);

						}
					} while (passwordInputTimes < 5);
				} else {
					JOptionPane.showMessageDialog(null, customerNotExists, "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				break;
			// User menu: 3.Show Available Products
			case "3":

				String products = shop.showProducts();
				JOptionPane.showMessageDialog(null, products);
				break;
			// User menu: 4.Show Online Customers
			case "4":

				String customers = shop.showOnlineCustomers();
				JOptionPane.showMessageDialog(null, customers);
				break;
			// User menu: 5.Show taken carts :
			case "5":

				String takenCarts = shop.showTakenCarts(shop.getCarts());
				JOptionPane.showMessageDialog(null, takenCarts);
				break;

			// User menu: 6.Administrative actions:
			case "6":
				do {
					adminChoice = JOptionPane.showInputDialog(null, adminMenu);

					switch (adminChoice) {

					// Administrative actions: 1. Product actions:
					case "1":
						//
						productMenuChoice = JOptionPane.showInputDialog(null, productMenu);
						switch (productMenuChoice) {
						//Product actions: 1.Add product
						case "1":
							productName = JOptionPane.showInputDialog(null, "Enter the product's name:");
							productPrice = JOptionPane.showInputDialog(null, "Enter the product's price:");
							productDiscount = JOptionPane.showInputDialog(null, "Enter the product's discount:");
							price = Double.parseDouble(productPrice);
							discount = Double.parseDouble(productDiscount);
							product = new Product(productName, price, discount);

							if (shop.addProductToShop(product) == 1) {
								JOptionPane.showMessageDialog(null, "You have added " + product.getProductName());
								break;
							} else if (shop.addProductToShop(product) == 2) {
								JOptionPane.showMessageDialog(null, "Product exists in the shop.");
								break;
							} else {
								JOptionPane.showMessageDialog(null, "No place for new products in the shop.");
								break;
							}

						// Product actions: 2.Remove product
						case "2":
							productNames = shop.productNames();
							productName = JOptionPane.showInputDialog(null,
									"Enter the product's name:\n" + productNames);
							if (shop.removeProductFromShop(productName) > 0) {
								JOptionPane.showMessageDialog(null, "You have removed " + productName);
							} else {
								JOptionPane.showMessageDialog(null, productNotFound, "ERROR",
										JOptionPane.ERROR_MESSAGE);
							}

							break;
						// Product actions: 3.Change product price
						case "3":
							productNames = shop.productNames();
							productName = JOptionPane.showInputDialog(null,
									"Enter the product's name:\n" + productNames);
							productPrice = JOptionPane.showInputDialog(null, "Please enter the new price : ");
							productIndex = shop.findProductIndex(productName);
							newPrice = Double.parseDouble(productPrice);
							
							if (shop.findProductIndex(productName) != -1) {
								shop.changeProductPrice(shop.getProducts()[productIndex], newPrice);
								Product productToDisplay = shop.getProducts()[productIndex];
								JOptionPane.showMessageDialog(null, "You changed " + productToDisplay.getProductName()
										+ "'s Price.\n" + productToDisplay.showProduct());
								break;
							} else {
								JOptionPane.showMessageDialog(null, productNotFound, "ERROR",
										JOptionPane.ERROR_MESSAGE);
								break;
							}

						// Product actions: 4.Change discount percent
						case "4":
							productNames = shop.productNames();

							productName = JOptionPane.showInputDialog(null,
									"Enter the product's name:\n" + productNames);

							productDiscount = JOptionPane.showInputDialog(null,
									"Please enter the discount percent (between 0 and 1) :");
							discount = Double.parseDouble(productDiscount);
							productIndex = shop.findProductIndex(productName);

							if (shop.findProductIndex(productName) != -1) {
								shop.changeCurrentDiscountPercent(shop.getProducts()[productIndex], discount);
								product = shop.getProducts()[productIndex];
								JOptionPane.showMessageDialog(null, "You changed " + product.getProductName()
										+ "'s discount price.\nProduct's properties: " + product.showProduct());
								break;

							} else {
								JOptionPane.showMessageDialog(null, productNotFound, "ERROR",
										JOptionPane.ERROR_MESSAGE);
								break;
							}

						// Product actions: 5.Show product
						case "5":
							productName = JOptionPane.showInputDialog(null,
									"Please choose a product from the list:\n" + shop.productNames());

							productIndex = shop.findProductIndex(productName);
							if (shop.findProductIndex(productName) != -1) {
								JOptionPane.showMessageDialog(null, shop.displaySingleProduct(shop.getProducts()[productIndex]));
								break;
							} else {
								JOptionPane.showMessageDialog(null, productNotFound, "ERROR",
										JOptionPane.ERROR_MESSAGE);
								break;
							}

						// Product actions: 6.Back to previous menu.
						case "6":
							break;
						default:
							JOptionPane.showMessageDialog(null, invalidInput, "ERROR", JOptionPane.ERROR_MESSAGE);
						}
						break;
					// Administrative actions: 2. Reports :
					case "2":

						do {
							reportsMenuChoice = JOptionPane.showInputDialog(null, reportsMenu);
							switch (reportsMenuChoice) {
							// Show product sales:
							case "1":
								if(shop.getRevenue() > 0) {
									JOptionPane.showMessageDialog(null, shop.getProductSales());
									break;
								}
								else {
									JOptionPane.showMessageDialog(null, "No sales were made yet.");
									break;
								}
							// Show shop's current revenue.
							case "2":
								JOptionPane.showMessageDialog(null, shop.getRevenue());
								break;
							// Return to Previous menu.
							case "3":
								break;
							default:
								JOptionPane.showMessageDialog(null, invalidInput, "ERROR", JOptionPane.ERROR_MESSAGE);

							}
						} while (!reportsMenuChoice.contentEquals("3"));

						break;
					// Administrative actions: 3. Customer actions:
					case "3":
						customer = new Customer();

						do {
							customerMenuChoice = JOptionPane.showInputDialog(null, customerMenu);

							switch (customerMenuChoice) {
							// Customer actions: 1.Add new customer
							case "1":
								nameTakenStatus = 0;
								do {
									if (nameTakenStatus > 0) {
										JOptionPane.showMessageDialog(null, "This username is already taken:", "ERROR", JOptionPane.ERROR_MESSAGE);
										userName = JOptionPane.showInputDialog(null,
												"Please enter a different user name");
										customer = new Customer(userName);
									} else {
										userName = JOptionPane.showInputDialog(null,
												"Please enter the customer's user name");
										customer = new Customer(userName);
									}

									nameTakenStatus++;
								} while (shop.customerExists(customer));

								do {
									password = JOptionPane.showInputDialog(null, "Please enter your password, at least 4 characters");
									if (password.length() < 4) {
										JOptionPane.showMessageDialog(null, "Your password is too short\nTry again.", "Warning",
												JOptionPane.WARNING_MESSAGE);
									}
								} while (password.length() < 4);
								userAddress = JOptionPane.showInputDialog(null,
										"Please enter the customer's shipping address : ");
								phoneNumber = JOptionPane.showInputDialog(null,
										"Please enter the customer's phone number: ");
								customer = new Customer(userName, password, userAddress, phoneNumber);

								shop.customerSignUp(customer);
								break;
							// Customer actions: 2.Remove existing customer
							case "2":
								userName = JOptionPane.showInputDialog(null, "Please enter the customer's user-name");
								JOptionPane.showMessageDialog(null, shop.customerRemoval(userName));
								break;
							// Customer actions: 3.Show individual customer properties	
							case "3":
								userName = JOptionPane.showInputDialog(null, "Please enter the customer's user-name");
								JOptionPane.showMessageDialog(null, shop.showClient(userName));
								break;
							// Customer actions: 4.Show all the shops customers	
							case "4":
								JOptionPane.showMessageDialog(null, shop.showCustomers());
								break;
							// Customer actions: 5.Back to previous menu.
							case "5":
								break;
							default:
								JOptionPane.showMessageDialog(null, invalidInput, "ERROR", JOptionPane.ERROR_MESSAGE);
							}

						} while (!customerMenuChoice.contentEquals("5"));

						break;
					// Administrative actions: 4.Cart actions
					case "4":
						do {
							cartMenuChoice = JOptionPane.showInputDialog(null, cartMenu);
							switch (cartMenuChoice) {
							// Cart actions: 1.Add multiple carts
							case "1":
								numberOfCarts = JOptionPane.showInputDialog(null,
										"Enter how many carts you want to add: ");
								parameter = Integer.parseInt(numberOfCarts);

								if (shop.addMultipleCarts(parameter) == 0) {
									JOptionPane.showMessageDialog(null,
											"You have added " + parameter + " new carts to the shop");
								} else {
									JOptionPane.showMessageDialog(null,
											"You have space for " + shop.addMultipleCarts(parameter) + " more carts.", "WARNING", JOptionPane.WARNING_MESSAGE);
									break;
								}
								break;
							// Cart actions: 2.Remove a cart from the list
							case "2":
								cartList = shop.showCarts();
								cartIDAsString = JOptionPane.showInputDialog(null,
										"Choose The cart ID you want to delete from the list : \n" + cartList);
								cartID = Integer.parseInt(cartIDAsString);

								if (shop.removeCart(cartID) == 1) {
									JOptionPane.showMessageDialog(null, "You have removed cart number : " + cartID);
									break;
								} else {
									JOptionPane.showMessageDialog(null, "Could not find specified cart", "ERROR", JOptionPane.ERROR_MESSAGE);
									break;
								}
							// Cart actions: 3.Show all the shop's cart
							case "3":
								JOptionPane.showMessageDialog(null, shop.showCarts());
								break;
							// Cart actions:  4.Back to previous menu.
							case "4":
								break;
							default:
								JOptionPane.showMessageDialog(null, invalidInput, "ERROR", JOptionPane.ERROR_MESSAGE);
								break;

							}

						} while (cartMenuChoice.contentEquals("3"));

						break;
					//  Administrative actions: 5.Return to previous menu
					case "5":
						break;
					default:
						JOptionPane.showMessageDialog(null, invalidInput, "ERROR", JOptionPane.ERROR_MESSAGE);
						break;
					}
				} while (!adminChoice.contentEquals("5"));
				break;
			// User menu: 7.Quit the program
			case "7":
				JOptionPane.showMessageDialog(null, "Have a nice day!");
				return;
			default:
				JOptionPane.showMessageDialog(null, invalidInput, "ERROR", JOptionPane.ERROR_MESSAGE);
				userChoice = JOptionPane.showInputDialog(null, menu);
			}
			userChoice = JOptionPane.showInputDialog(null, menu);
		} while (!userChoice.contentEquals("7"));

	}

}
