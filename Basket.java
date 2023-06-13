package java.com.javahiringchallenge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "basket")
public class Basket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name="userid")
	private long userid;
	@ManyToOne
	@JoinColumn(name="userid")
	private User user;
	@Column(name="productid")
	private long productid;
	@ManyToOne
	@JoinColumn(name="productid")
	private Product product;
	@Column(name="quantity")
	private int quantity;
	@Column(name="totalprice")
	private double totalPrice;
	@Column(name="isordered")
	private boolean isOrdered;
	@Column(name="isdeleted")
	public boolean isDeleted;
	@OneToOne(mappedBy = "basket", fetch = FetchType.LAZY)
	private Order order;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getUserid() {
		return userid;
	}
	
	public void setUserid(long userid) {
		this.userid = userid;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public long getProductid() {
		return productid;
	}
	
	public void setProductid(long productid) {
		this.productid = productid;
	}
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double getTotalPrice() {
		return totalPrice;
	}
	
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public boolean isOrdered() {
		return isOrdered;
	}
	
	public void setOrdered(boolean isOrdered) {
		this.isOrdered = isOrdered;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}	
}