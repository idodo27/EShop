package eshop;



/**
 * 
 * @author Michael Winkler & Ido Nidbach
 * @date 23/07/20
 *
 */

public class Customer {

	/**
	 * Instance Variables:
	 */

	private String userName;
	private String password;
	private String shippingAddress;
	private String phoneNumber;
	private boolean onlineOrNot;
	private boolean paymentStatus;
	private Cart shoppingCart;
	private DBHandler db = new DBHandler();

	/**
	 * Parameterized Constructors:
	 * 
	 * @description This constructor will be used in the program to create a new
	 *              customer with given properties
	 * @param username        - Will represent the customers user-name.
	 * @param password        - Will represent the customers password.
	 * @param shippingAddress - Will represent the customers shipping address.
	 * @param phoneNumber     - Will represent the customers phone number.
	 */

	public Customer(String username, String password, String shippingAddress, String phoneNumber) {
		this.setUserName(username);
		this.setPassword(password);
		this.setShippingAddress(shippingAddress);
		this.setPhoneNumber(phoneNumber);
		this.setOnlineOrNot(false);
	}

	/**
	 * @category Constructors
	 * @description This constructor will be used in the program to check if the
	 *              user exists or not.
	 * @param username - will represents the customers username.
	 */

	// to check when creating a new customer if he exists already

	public Customer(String username) {
		this.setUserName(username);

	}

	/**
	 * @category Constructors
	 * @description This Constructor will be used in the program in order to
	 *              generate a random customer with a random user-name and password.
	 *              each customer will be put inside the customer's array.
	 * 
	 * 
	 */

	public Customer() {
		this.userName = db.generateCustomerName();
		this.password = db.generateCustomerPassword();

	}

	/**
	 * @category Getters
	 * @return Will return the customer's user-name.
	 */

	public String getUserName() {
		return userName;
	}

	/**
	 * @category Getters
	 * @return Will return the customer's phone number.
	 */

	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @category Getters
	 * @description This method is used to determine if the user has payed or not.
	 * @return Will return if the user has payed or not. True if he did pay, false
	 *         if not.
	 */

	public boolean getPaymentStatus() {
		return this.paymentStatus;

	}

	/**
	 * @category Getters
	 * @return Will return the customer's shipping address.
	 */

	public String getShippingAddress() {
		return shippingAddress;
	}

	/**
	 * @category Getters
	 * @return Will return the customer's shopping cart.
	 */

	public Cart getShoppingCart() {
		return shoppingCart;
	}

	/**
	 * @category Getters
	 * @return Will return the true if the customer is online, false if not.
	 */

	public boolean getOnlineStatus() {
		return onlineOrNot;
	}

	/**
	 * @category Getters
	 * @return Will return the customer's password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @category Setters
	 * @description This method is used to initialize the user's password.
	 * @param password - Will represent the user's first password.
	 * @apiNote In addition to the "set" methods that are used in this class, there
	 *          are "change" methods that will be used to changed already existing
	 *          user's properties.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @category Setters
	 * @description This method is used to initialize the user's shipping address.
	 * @param userAddress -Will represent the user's first shipping address.
	 * @apiNote In addition to the "set" methods that are used in this class, there
	 *          are "change" methods that will be used to changed already existing
	 *          user's properties.
	 */
	public void setShippingAddress(String userAddress) {
		this.shippingAddress = userAddress;
	}

	/**
	 * @category Setters
	 * @description This method is used to set the user's online status. The online
	 *              status will be used to determine if the user can take a cart or
	 *              not.
	 * @param onlineStatus - Will represent the status of the user. True, if he's
	 *                     online or false if he's offline.
	 */
	public void setOnlineOrNot(boolean onlineStatus) {
		if (this.onlineOrNot && onlineStatus == false) {
			this.setShoppingCart(null);
		}
		this.onlineOrNot = onlineStatus;
	}

	/**
	 * @category Setters
	 * @description This method is used to initialize the user's userName.
	 * @param userName - Will represent the user's first username.
	 * @apiNote In addition to the "set" methods that are used in this class, there
	 *          are "change" methods that will be used to changed already existing
	 *          user's properties.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @category Setters
	 * @description This method is used to initialize the users phoneNumber.
	 * @param phoneNumber - Will represent the user's first phone number.
	 * @apiNote In addition to the "set" methods that are used in this class, there
	 *          are "change" methods that will be used to changed already existing
	 *          user's properties.
	 */

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @category Customer actions.
	 * @description This methods is used to assign a shopping cart to a customer and
	 *              vice versa.
	 * @param newShoppingCart - Will represent the reference to the new cart that we
	 *                        want to assign to the customer.
	 */

	public void setShoppingCart(Cart newShoppingCart) {

		if (this.getShoppingCart() != newShoppingCart) {
			if (newShoppingCart == null) {
				this.shoppingCart = null;
				return;
			}

			if (this.getShoppingCart() != null) {
				return;

			}
			if (newShoppingCart.getCustomer() != null) {
				newShoppingCart.getCustomer().shoppingCart = null;
			}
			this.shoppingCart = newShoppingCart;
			newShoppingCart.setCustomer(this);
		}

	}

	/**
	 * @category Customer actions.
	 * @description This method will use the "set shopping cart" method above to
	 *              assign the user a new shopping cart.
	 * @param carts - Will represent the reference to the array of carts the the
	 *              current shop contains.
	 */

	public void takeCart(Cart[] carts) {
		if (this.getOnlineStatus()) {
			for (int i = 0; i < carts.length; i++) {
				if (carts[i] != null) {
					if (carts[i].isAvailable()) {
						this.setShoppingCart(carts[i]);
						break;
					}
				}
			}
		}
	}
	
	public void addProductByNumber(int index) {
		this.getShoppingCart().addProductByIndex(index);
	}

	/**
	 * @category Customer actions.
	 * @description This method is used to add a product to the customer's shopping
	 *              cart.
	 * @param product Will represent the product the we want to add to the cart.
	 */

	public void addProductToCart(String product) {

		this.getShoppingCart().addProduct(product);
	}

	/**
	 * @category - Customer actions.
	 * @description - This method is used to remove a product from the user's
	 *              shopping cart.
	 * @param product - Will represent the product the we want to remove from the
	 *                cart.
	 */
	public void removeProductFromCart(String productName) {
		this.getShoppingCart().removeProduct(productName);
	}

	/**
	 * @category: - Customer actions.
	 * @description: - This method is used to release the user's shopping cart that
	 *               he's currently holding.
	 * 
	 */

	public void releaseCart() {
		this.getShoppingCart().setCustomer(null);
		this.setShoppingCart(null);
	}

	/**
	 * @category Customer actions.
	 * @description This methods is used to set the payment status to true in order
	 *              to calculate the shop's daily revenue.
	 * @return Will return how much the user has payed.
	 */
	public double payBill() {
		Shop shop = this.getShoppingCart().getShop();
		double value = this.getShoppingCart().calculateCartValue();
		double res = value;
		this.paymentStatus = true;
		shop.setRevenue(shop.getRevenue() + res);
		for(int i = 0 ; i < this.getShoppingCart().getProductCount(); i++) {
			shop.getProductsCounts()[shop.findProductIndex(this.getShoppingCart().getProducts()[i].getProductName())]++;
		}
		this.getShoppingCart().emptyCart();
		this.getShoppingCart().setCustomer(null);
		this.setShoppingCart(null);
		return res;
	}

	/**
	 * @category Customer actions
	 * @description This method is used to sign in the customer to the shop.
	 * @param userName  - Will represent the customer's username.
	 * @param password- Will represent the customer's password.
	 * @return Will return 0 if the user has entered a correct username and
	 *         password, will return 1 if he failed to enter correctly.
	 */

	public int customerLogin(String userName, String password) {

		if (this.getUserName().equals(userName) && this.getPassword().equals(password)) {

			this.setOnlineOrNot(true);
			return 0;
		}
		return 1;
	}

	/**
	 * @category Customer actions
	 * @description This method will be used to log the user out of the program. It
	 *              will set his status to "offline" and "take" his shopping cart.
	 */

	public void customerLogout() {
		this.setOnlineOrNot(false);
		this.releaseCart();
		return;
	}

	/**
	 * @category Customer actions
	 * @description This method will return the user's properties:
	 *              User-name,Shipping address and Phone number
	 */
	public String showCustomer() {
		String details = "";
		details += "Customer's name: " + this.getUserName() + "\nCustomer's address: " + this.getShippingAddress()
				+ "\nCustomer's phone: " + this.getPhoneNumber();
		return details;
	}
	
	/**
	 * @category Edit Customer Properties
	 * @description This method is used to change the user's current password.
	 * @param newPass - Will represent the user choice of a new password.
	 */
	public void changePassword(String newPass) {
		this.setPassword(newPass);
	}

	/**
	 * @category Edit Customer Properties
	 * @description This method is used to change the user's current shipping
	 *              address.
	 * @param newAdd - Will represent the user's new shipping address.
	 */
	public void changeAddress(String newAdd) {
		this.setShippingAddress(newAdd);
	}

	/**
	 * @category Edit Customer Properties
	 * @description This method will be used to change the user's current phone
	 *              number.
	 * @param newPhone - Will represent the user's new phone number.
	 */
	public void changePhoneNumber(String newPhone) {
		this.setPhoneNumber(newPhone);
	}



}
