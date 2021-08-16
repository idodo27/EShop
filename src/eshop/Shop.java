package eshop;



/**
 * 
 * @author Michael Winkler & Ido Nidbach
 * @date 23/07/20
 *
 */

public class Shop {

	/**
	 * Instance variables:
	 */

	private String shopName;
	private Product[] products;
	private Customer[] customers;
	private Cart[] carts;
	private DBHandler db = new DBHandler();
	private double revenue;
	private int[] productsCounts;
	/**
	 * @category Constructors
	 * @description This constructor is used to create a an empty shop with 40
	 *              products in it.
	 * @param shopName         - Represent's the shop's name.
	 * @param maxProductCount  - Represent's the maximum number of product the shop
	 *                         could have.
	 * @param maxCustomerCount - Represent's the maximum number of customer the shop
	 *                         can contain.
	 * @param maxCarts         - Represent's the maximum number of carts the shop
	 *                         can have.
	 * @apiNote - In both constructors, we generate 40 products to the store.
	 */
	public Shop(String shopName, int maxProductCount, int maxCustomerCount, int maxCarts) {

		this.setShopName(shopName);
		this.setProducts(maxProductCount);
		this.setCustomers(maxCustomerCount);
		this.setCarts(maxCarts);
		this.setProductCounts(this.products.length);
		
	}

	/**
	 * @category Constructor
	 * @description This constructor is used to create a shop with an array the size
	 *              of 1000 products with 40 products in in it, an array of 90
	 *              customers with random names and password and an array of carts
	 *              the size of 90.
	 * 
	 * @param shopName - Represent's the shop's name.
	 * @apiNote - In both constructors, we generate 40 products to the store.
	 */

	public Shop(String shopName) {
		this.setShopName(shopName);
		// generate random products with names from a given list in DBHandler, generate
		// random prices from them.

		this.products = new Product[1000];
		this.products = db.generateProductsArray(products);
		this.customers = db.generateCustomerArray();
		this.carts = db.generateCartArray(this);
		this.setProductCounts(this.products.length);

	}

	/**
	 * @category Constructors
	 * @description This Constructor will be used at the first menu in the program
	 *              just to initialize the object, every other properties of the
	 *              shop will be added after by the user.
	 * 
	 * 
	 * 
	 * 
	 */
	public Shop() {
		this.shopName = "";
	}

	/**
	 * @category Getters
	 * @return Will return the shop's name.
	 */
	public String getShopName() {
		return shopName;
	}

	/**
	 * @category Getters
	 * @return Will return the shop's product array.
	 */

	public Product[] getProducts() {
		return products;
	}

	/**
	 * @category Getters
	 * @return Will return the shop's cart array.
	 */

	public Cart[] getCarts() {
		return carts;
	}

	/**
	 * @category Getters
	 * @return Will return the shop's customer array.
	 */

	public Customer[] getCustomers() {
		return this.customers;
	}

	/**
	 * @category Getters
	 * @return Will return the shop's current revenue for today.
	 */
	public double getRevenue() {
		return this.revenue;
	}
	
	public int[] getProductsCounts() {
		return this.productsCounts;
	}

	/**
	 * 
	 * @category Setters
	 * @param db Represent's the shop's database, this will be used when we generate
	 *           a shop automatically from the menu.
	 */
	
	
	
	public void setDBHandler(DBHandler db) {
		this.db = db;
	}

	/**
	 * @category Setters
	 * @param shopName Represent's the shop's new name.
	 */

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	/**
	 * @category Setters
	 * @description This method will create a product's array of a given size, then
	 *              will fill it with 40 products using the database.
	 * @param shopProductCount - Represent's the maximum amount of products the shop
	 *                         could have.
	 */

	public void setProducts(int shopProductCount) {
		this.products = new Product[shopProductCount];
		this.products = db.fillEmptyProductArray(products, 40);
	}

	/**
	 * @category Setters
	 * @description This method will create a new array of customers of a given
	 *              size.
	 * @param maxCustomerCount - Represent's the maximum amount of customers the
	 *                         shop could have.
	 */

	public void setCustomers(int maxCustomerCount) {
		this.customers = new Customer[maxCustomerCount];
	}

	/**
	 * @category Setters
	 * @description This method will create a new array of carts of a given size.
	 * @param maxCarts - Represent's the maximum amount of carts the shop could
	 *                 have.
	 */

	public void setCarts(int maxCarts) {
		this.carts = new Cart[maxCarts];
	}

	/**
	 * @category Setters
	 * @description Will be used if we would like to set the revenue at a specific
	 *              number.
	 * @param revenue - Represent's the daily revenue of the shop.
	 */
	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}
	
	public void setProductCounts(int size) {
		this.productsCounts = new int[size];
	}

	/**
	 * @category Customer methods
	 * @description This method will be used to sign up a new customer to the shop.
	 * @param newCustomer - Represent's the object of the new customer we want to
	 *                    add to the shop.
	 */

	public int customerSignUp(Customer newCustomer) {
		if (this.getCurrentCustomerCount() < this.getCustomers().length - 1 && !this.customerExists(newCustomer)) {
			this.getCustomers()[this.getCurrentCustomerCount() + 1] = newCustomer;
			this.fixCustomerArray();
			return 1;
		}
		if (this.customerExists(newCustomer)) {
			return 2;
		}
		else {
			return 0;
		}
		

	}

	/**
	 * @category Customer methods
	 * @param customerName - Represent's the customer's name we want to remove from
	 *                     the shop.
	 * @return Will return a String with the answer if it has succeded or not to
	 *         delete the customer.
	 */

	public String customerRemoval(String customerName) {
		String answer = "";

		if (!this.customerNameExists(customerName)) {
			answer = "could not find the customer.";
		} else {
			this.getCustomers()[this.findCustomerIndex(customerName)].getShoppingCart().setCustomer(null);;
			this.getCustomers()[this.findCustomerIndex(customerName)] = null;
			answer = "customer deleted succesfully";
		}
		this.fixCustomerArray();
		return answer;
	}

	/**
	 * @category Customer methods
	 * @description This method will be used in a program to check if a user exists
	 *              with this user-name.
	 * @param username - Represent's the user-name we want to check.
	 * @return Will return true if it does exist, false if not.
	 */

	public boolean customerNameExists(String username) {

		for (int i = 0; i < this.getCurrentCustomerCount(); i++) {
			if (this.getCustomers()[i].getUserName().equals(username)) {
				return true;
			}
		}
		return false;

	}

	/**
	 * @category Customer methods
	 * @description Another method to check if a user already exist.
	 * @param customerToCheck - Represent's the object of the customer we would want
	 *                        to check if it exists.
	 * @return Will return true if it does exist, false if not.
	 */

	public boolean customerExists(Customer customerToCheck) {
		for (int i = 0; i < this.getCurrentCustomerCount(); i++) {
			if (this.getCustomers()[i].getUserName().equals(customerToCheck.getUserName())) {
				return true;
			}
		}

		return false;
	}

	/**
	 * @category Customer methods
	 * @description This method will be used in the program to check if a user
	 *              exists, if it does it will find his index in the customer's
	 *              array.
	 * @param customerName -Represent's the customer we want to find his index.
	 * @return The position of the customer inside the customer's array does it will
	 *         return the index of the user in inside the customer's array. if it
	 *         does not exists, it will return -1.
	 */

	public int findCustomerIndex(String customerName) {
		if (this.customerNameExists(customerName)) {
			for (int i = 0; i <= this.getCurrentCustomerCount(); i++) {
				if (this.getCustomers()[i].getUserName().equals(customerName)) {
					return i;
				}
			}
		}
		return -1;
	}

	/**
	 * @category Cart methods
	 * @description This method will be used to create a new cart to the shop. it
	 *              will be assigned a cart id automatically.
	 * 
	 * @param newCart Represent the object of the new cart we would like to add to
	 *                the shop.
	 * @apiNote Clarification: If we add 100 carts to the shop, the cart's ID will
	 *          be from 1000 to 1099, after removing lets say a single cart, the
	 *          ID'S will keep climbing so if we add a new one, the new cart's id
	 *          will be 1100 and so on.
	 */

	

	/**
	 * @category Cart methods
	 * @param cartID - Will represent the cart's ID we would like to remove from the
	 *               shop.
	 * @apiNote Clarification: If we add 100 carts to the shop, the cart's ID will
	 *          be from 1000 to 1099, after removing lets say a single cart, the
	 *          ID'S will keep climbing so if we add a new one, the new cart's id
	 *          will be 1100 and so on.
	 * @return Will return 0 if the cart does not exist, 1 if it does.
	 */

	public int removeCart(int cartID) {
		// if the cart does not exists in the shop.
		if (!this.cartExists(cartID)) {
			return 0;
		} else {
			this.getCarts()[this.findCartIndex(cartID)] = null;
			this.fixCartArray();
			return 1;
		}
	}

	/**
	 * @category Cart methods
	 * @description This private method will be used to find the cart's index inside
	 *              the cart's array.
	 * @param cartID - Represent's the cart's ID we would like to find his index.
	 * @return Will return -1 if it does not exist and if it does it will return the
	 *         cart's index.
	 */

	private int findCartIndex(int cartID) {

		if (!cartExists(cartID)) {
			return -1;
		}

		for (int i = 0; i < this.getCurrentCartCount(); i++) {
			if (this.getCarts()[i].getCartID() == cartID) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * @category Cart methods
	 * @description This private method will be used to check if a specific cart
	 *              already exists in the shop
	 * 
	 * @param cartIDToCheck - Represent's the cart's ID we would like to check.
	 * @return Will return true if it does exist, false if not.
	 */

	private boolean cartExists(int cartIDToCheck) {
		for (int i = 0; i < this.getCurrentCartCount(); i++) {
			if (this.getCarts()[i].getCartID() == cartIDToCheck) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @category Cart methods
	 * @description This method will be used in the program to add multiple carts to
	 *              the shop.
	 * @param num Represent's the number of carts we would want to add to the shop.
	 * @return Will return 0 if it accomplished, if not will return how carts we can
	 *         add.
	 */

	public int addMultipleCarts(int num) {

		if (this.getCurrentCartCount() <= this.getCarts().length - num) {
			for (int i = 0; i < num; i++) {
				this.getCarts()[this.getCurrentCartCount()] = new Cart(this);
			}
			this.fixCartArray();
			return 0;
		}

		return this.getCarts().length - this.getCurrentCartCount();
	}

	/**
	 * @category Product methods
	 * @description This method is used to find a specific products index inside the
	 *              product's array.
	 * @param productToFind - Represent's the product's name we would like to find.
	 * @return Will return the index of the product if it was found. if it wasn't
	 *         will return -1.
	 */

	public int findProductIndex(String productToFind) {
		for (int i = 0; i < this.getProductCount(); i++) {
			if (this.getProducts()[i].getProductName().equals(productToFind)) {
				return i;
			}

		}
		return -1;
	}

	/**
	 * @category Product methods
	 * @description This method will be used to check if a specific product already
	 *              exists in the shop.
	 * @param productName - Represent's the product's name we would like to check.
	 * @return Will return true if it does. False if not.
	 */

	private boolean productExists(String productName) {
		for (int i = 0; i < this.getProductCount(); i++) {
			if (this.getProducts()[i] != null) {
				if (this.getProducts()[i].getProductName().equalsIgnoreCase(productName)) {
					return true;
				}
			}

		}

		return false;
	}

	/**
	 * @category Product methods
	 * @description This method will be used to remove an already existing product
	 *              from the shop.
	 * 
	 * @param productToRemove - Represent's the product's name we would like to
	 *                        remove from the shop.
	 * @return The method will return 1 if it has accomplished removing the product,
	 *         0 if not.
	 */

	public int removeProductFromShop(String productToRemove) {

		for (int i = 0; i < this.getProductCount(); i++) {
			if (productExists(productToRemove)) {
				int productIndex = this.findProductIndex(productToRemove);
				this.getProducts()[productIndex] = null;
				this.fixShopProductArray();
				return 1;
			}

		}
		return 0;

	}

	/**
	 * @category Product methods
	 * @description This method will be used to change the product's current price.
	 * @param productToChange - Represent's the object (product) we would like to
	 *                        change his price.
	 * @param newPrice        - Represent's the new product's price.
	 * @return will return 1 if it has accomplished changing the price, 0 if not.
	 */

	public int changeProductPrice(Product productToChange, double newPrice) {
		if (productExists(productToChange.getProductName())) {
			productToChange.setProductPrice(newPrice);
			return 1;
		}
		return 0;
	}

	/**
	 * @category Product Methods
	 * @description This method will be used to change the product's current
	 *              discount percent.
	 * @param productTochange    - Represent's the object (product) we would like to
	 *                           change his discount percent.
	 * @param newDiscountPercent - Represent's the new discount percent.
	 * @return
	 */

	public int changeCurrentDiscountPercent(Product productTochange, double newDiscountPercent) {
		if (this.productExists(productTochange.getProductName())) {
			productTochange.setDiscountPercent(newDiscountPercent);
			return 1;
		}
		return 0;

	}

	/**
	 * @category Product methods
	 * @description This method is used to add a new product to the shop.
	 * @param newProduct - Represent's the new object we would like to add to the
	 *                   shop.
	 * @return Will return 2 if the product exists already, 1 if it has accomplished
	 *         adding the new product and 0 if there is no place for new products.
	 */

	public int addProductToShop(Product newProduct) {

		if (this.productExists(newProduct.getProductName())) {
			return 2;
		}
		if (this.getProductCount() < this.getProducts().length - 1) {

			for (int i = 0; i <= this.getProductCount(); i++) {

				if (this.getProducts()[i] == null) {
					this.getProducts()[i] = newProduct;
					return 1;
				}
			}

		}
		return 0;

	}

	/**
	 * @category Counters
	 * @description This method is used to get the current customer count of the
	 *              shop in order to know if we have place to add more.
	 * 
	 * @return Will return an integer that will represent the number of customer at
	 *         the shop.
	 */

	public int getCurrentCustomerCount() {
		int customerCount = 0;
		for (int i = 0; i < this.getCustomers().length; i++) {
			if (this.getCustomers()[i] != null) {
				customerCount++;
			} else {
				break;
			}
		}
		return customerCount;

	}

	/**
	 * @category Counters
	 * @description This method will be used to get the current cart count of the
	 *              shop.
	 * @return Will return an integer that will represent the shops current cart
	 *         count.
	 */

	public int getCurrentCartCount() {
		int cartCount = 0;
		for (int i = 0; i < this.getCarts().length; i++) {
			if (this.getCarts()[i] != null) {
				cartCount++;
			} else {
				continue;
			}
		}
		return cartCount;

	}

	/**
	 * @category Counters
	 * @description This method will be used to get the shop's current product
	 *              count.
	 * 
	 * @return The method will return an integer that will represent the number of
	 *         product the shop currently have.
	 */

	private int getProductCount() {
		int count = 0;

		for (int i = 0; i < this.getProducts().length; i++) {
			if (this.getProducts()[i] != null) {
				count++;
			}

		}
		return count;
	}

	/**
	 * @category Array sorts
	 * @description This private method will be used inside the class only, it will
	 *              use bubble sort each time we add or remove a customer to the
	 *              shop.
	 * 
	 */

	private void fixCustomerArray() {
		int length = this.getCustomers().length;
		for (int i = 0; i < length; i++) {
			if (customers[i] == null) {

				for (int j = i; j < length; j++) {
					if (customers[j] != null) {
						Customer tmp = customers[j];
						customers[j] = null;
						customers[i] = tmp;
						break;
					}
				}

			} else {
				continue;
			}

		}

	}

	/**
	 * @category Array sorts
	 * @description This method will be used to sort the array of carts after
	 *              removing or adding a cart to the shop. The method will use
	 *              bubble sort each time we will call it.
	 * 
	 */

	private void fixCartArray() {
		int length = this.getCarts().length;
		for (int i = 0; i < length; i++) {
			if (carts[i] == null) {
				for (int j = i; j < length; j++) {
					if (carts[j] != null) {
						Cart tmp = carts[j];
						carts[j] = null;
						carts[i] = tmp;
						break;
					}
				}
			} else {
				continue;
			}

		}

	}

	/**
	 * @category Array sorts
	 * @description This private method will be used to sort the product's array
	 *              after adding or removing a new product. This method will use
	 *              bubble sort each time we call it.
	 * 
	 */

	private void fixShopProductArray() {
		int length = this.getProducts().length;

		for (int i = 0; i < length; i++) {
			if (products[i] == null) {
				for (int j = i; j < length; j++) {
					if (products[j] != null) {
						Product tmp = products[j];
						products[j] = null;
						products[i] = tmp;
						break;
					}
				}
			} else {
				continue;
			}
		}
	}

	/**
	 * @category Reports
	 * @description This method will be used to shops sales until the moment the
	 *              method was called.
	 * @return This method will return a string with the total number of products we
	 *         sold. Next to each product, the amount we sold.
	 */

	public String getProductSales() {
		int sales = 0;
		String productSales = "";
		Product[] products = this.getProducts();

		for (int i = 0; i < this.getCurrentCustomerCount(); i++) {
			if (this.getCurrentCustomerCount() == 0) {
				break;
			}
			if (this.getCustomers()[i] != null) {
				if(this.getCustomers()[i].getShoppingCart() != null) {
					sales += this.getCustomers()[i].getShoppingCart().getProductCount();
				}
			}
		}
		productSales += "Total items sold for today : " + sales + "\n";
		for (int i = 0; i < products.length; i++) {
			if (products[i] != null) {
				productSales += "Product name: " + products[i].getProductName() + "\nProduct Sales: "
						+ this.getSingleProductSale(products[i]) + "\n";
			}
		}
		return productSales;
	}

	/**
	 * @category Reports
	 * @description This method will be used to return a single product sales.
	 * @param product - Represent's the single product we would like to get his
	 *                sales.
	 * @return Will return an integer the will represent the product sales for this
	 *         product.
	 */

	private int getSingleProductSale(Product product) {
		return this.getProductsCounts()[this.findProductIndex(product.getProductName())];

	}

	/**
	 * @category Show methods
	 * @description This method will be used to show all the carts currently in the
	 *              shop.
	 * @return Will return a long string with each cart's properties.
	 */

	public String showCarts() {
		String cartList = "";
		if (this.getCurrentCartCount() == 0) {
			return "No carts added to the shop yet.";
		}
		for (int i = 0; i < this.getCarts().length; i++) {
			if (this.getCarts()[i] != null) {

				cartList += "cart ID : " + this.getCarts()[i].getCartID() + "\n";
				cartList += "Current products inside the cart : " + this.getCarts()[i].getProductCount() + "\n";
				if (this.getCarts()[i].getCustomer() == null) {
					cartList += "free cart" + "\n";
				} else {
					cartList += "customer holding the cart : " + this.getCarts()[i].getCustomer().getUserName() + "\n";
				}

			}
		}
		return cartList;
	}

	/**
	 * @category Show methods
	 * @description This method will be used to show only the shopping carts taken
	 *              by a customer.
	 * 
	 * 
	 * @param carts Represents the shop's cart array.
	 * @return Will return a string with each cart's properties and the customer
	 *         assigned to it.
	 */

	public String showTakenCarts(Cart[] carts) {
		String takenCarts = "";
		int length = carts.length;
		for (int i = 0; i < length; i++) {
			if (this.carts[i] != null) {
				if (this.carts[i].getCustomer() != null) {
					takenCarts += i + 1 + ". Cart ID: " + this.carts[i].getCartID() + "\n";
					takenCarts += "    Customer :  " + this.carts[i].getCustomer().getUserName() + "\n";
				}
			}

		}
		return takenCarts;
	}

	/**
	 * @category Show methods
	 * @description This methods will be used to show all the available products of
	 *              the shop.
	 * @return This method will return a long string with each product's properties
	 *         (ID, NAME, PRICE).
	 */

	public String showProducts() {
		String availableProducts = "";
		for (int i = 0; i < this.getProducts().length; i++) {
			if (this.getProducts()[i] != null) {
				availableProducts += "ID: 			" + this.getProducts()[i].getProductID()
						+ "\nProduct name: 		" + this.getProducts()[i].getProductName();
				availableProducts += "\nProduct Price :		 " + this.getProducts()[i].getProductPrice() + "\n";
				availableProducts += "------------------------------------------\n";
			}

		}
		return availableProducts;
	}

	/**
	 * @category Show methods
	 * @description This method will be used to display only the shop's product's
	 *              with their name and prices
	 * @return Will return a long string representing all the shop's product.
	 */

	public String productNames() {
		String names = "";

		for (int i = 0; i < this.getProductCount(); i++) {

			names += (i + 1) + ". " + this.getProducts()[i].getProductName() + " --- Price : "
					+ this.getProducts()[i].getProductPrice() + "$\n";
		}

		return names;
	}

	/**
	 * @category Show methods
	 * @description This method will be used to show a specific products
	 *              properties.(ID, name, price, discount percent, price after
	 *              discount).
	 * @param productToShow - Represent's the product's object we would like to
	 *                      display.
	 * @return Will print the product's properties and return 1 . 0 if the product
	 *         was not found.
	 */

	public String displaySingleProduct(Product productToShow) {
		if (productExists(productToShow.getProductName())) {
			return productToShow.showProduct();
		}

		return "Product not found";

	}

	/**
	 * @category Show methods
	 * @description This method will be used to show all the online customers of the
	 *              shop.
	 * @return Will return a long string with all the customer's user-names.
	 */

	public String showOnlineCustomers() {
		String onlineCustomers = "";
		int counter = 0;
		for (int i = 0; i < this.getCurrentCustomerCount(); i++) {

			if (this.getCustomers()[i].getOnlineStatus()) {

				onlineCustomers += i + 1 + ". Username: " + this.getCustomers()[i].getUserName() + "\n";
				counter++;
			}

		}
		if (counter == 0) {
			onlineCustomers = "No online customers";
			return onlineCustomers;
		}

		return onlineCustomers;

	}

	/**
	 * @category Show methods
	 * @description This methods will find the customer's index, if it does it will
	 *              print a string containing the client's (customer) properties.
	 * @param customerName Represent's the customer's name we would like to display.
	 * @return Will return a string with the customer's name, address and phone
	 *         number. If it does not find it will return customer does not exists.
	 */

	public String showClient(String customerName) {
		if (!customerNameExists(customerName)) {
			return "Customer does not exist.";
		} else {
			return this.getCustomers()[this.findCustomerIndex(customerName)].showCustomer();
		}
	}

	/**
	 * @category Show methods
	 * @description This method will be used to display all the customer that are
	 *              currently in the shop.
	 * @return will return a long string with all the user's user-name and the
	 *         cart's cartID if he was assigned a cart.
	 */

	public String showCustomers() {
		String allCustomers = "";

		for (int i = 0; i < this.getCurrentCustomerCount(); i++) {

			allCustomers += i + 1 + ".Username: " + this.getCustomers()[i].getUserName() + "\n";
			if (this.getCustomers()[i].getShoppingCart() != null) {
				allCustomers += "Cart ID: " + this.getCustomers()[i].getShoppingCart().getCartID() + "\n";
			} else {
				allCustomers += "Cart ID: none \n";
			}

		}

		return allCustomers;
	}

}
