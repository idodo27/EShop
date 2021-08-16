package eshop;



/**
 * 
 * @author Michael Winkler & Ido Nidbach
 * @date 23/07/20
 *
 */
public class Product {

	/**
	 * Instance Variables:
	 */

	private static int currentID = 1000;
	private String productName;
	private final int id;
	private double productPrice;
	private double discountPercent;

	/**
	 * @category Constructor
	 * @description This Parameterized Constructor will be used to create a new
	 *              product with a unique ID, a given name and a given price.
	 * @param name  - Will represent the product's name.
	 * @param price - Will represent the product's price.
	 */

	public Product(String name, double price) {
		this.id = currentID++;
		this.setProductName(name);
		this.setProductPrice(price);

	}

	/**
	 * @category Constructor
	 * @description This Parameterized Constructor will be used to create a new
	 *              product with a unique ID, a given name, a price and a
	 *              discountPercent)
	 * @param name          - Will represent the new product's name.
	 * @param price         - Will represent the new product's price.
	 * @param discountPrice - Will represent the new product's discount percent.
	 */
	public Product(String name, double price, double discountPrice) {
		this.id = currentID++;
		this.setProductName(name);
		this.setProductPrice(price);
		this.setDiscountPercent(discountPrice);
	}

	/**
	 * @category Getters
	 * @return This method will return the product's name.
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @category Getters
	 * @return This method will return the product's ID.
	 */

	public int getProductID() {
		return this.id;
	}

	/**
	 * @category Getters
	 * @return This method will return the product's discount price.
	 */

	public double getDiscountPercent() {
		return discountPercent;
	}

	/**
	 * @category Getters
	 * @return This method will return the product's price.
	 */
	public double getProductPrice() {
		return this.productPrice;
	}

	/**
	 * @category Setters
	 * @param productName - Represent's the new name for the product.
	 */

	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @category Setters
	 * @param discountPercent - Will represent the product's discount percent.
	 */

	public void setDiscountPercent(double discountPercent) {
		this.discountPercent = discountPercent;
	}

	/**
	 * @category Setters
	 * @param productPrice - This method is used to set the product's price.
	 */
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	/**
	 * @category Product actions
	 * @return This method will return a String that will contain the product's:
	 *         name, ID, price, discount percent and price after discount.
	 */

	public String showProduct() {
		String productToShow = "";
		productToShow += "Product name : " + this.getProductName() + "\nProduct ID : " + this.getProductID()
				+ "\nProduct Price : " + this.getProductPrice() + "\nProduct Discount Percent : "
				+ this.getDiscountPercent() + "%\n" + "Price after discount : "
				+ (this.getProductPrice() - (this.getProductPrice() * this.getDiscountPercent()));
		return productToShow;
	}

}