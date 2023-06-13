package java.com.javahiringchallenge.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name="name")
	private String name;
	@Column(name="mark")
	private String mark;
	@Column(name="stockcode")
	private String stockCode;
	@Column(name="productgroupid")
	private long productGroupId;
	@ManyToOne
	@JoinColumn(name="productgroupid")
	private ProductGroup productGroup;
	@Column(name="price")
	private double price;
	@Column(name="stockquantity")
	private int stockQuantity;
	@Column(name="isbasketed")
	private boolean isBasketed;
	@Column(name="isdeleted")
	private boolean isDeleted;
	@OneToMany(mappedBy="product")
	Set<Basket> baskets = new HashSet<Basket>();

	public Product() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}
	
	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	
	public long getProductGroupId() {
		return productGroupId;
	}
	
	public void setProductGroupId(long productGroupId) {
		this.productGroupId = productGroupId;
	}
	
	public ProductGroup getProductGroup() {
		return productGroup;
	}

	public void setProductGroup(ProductGroup productGroup) {
		this.productGroup = productGroup;
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public boolean isBasketed() {
		return isBasketed;
	}

	public void setBasketed(boolean isBasketed) {
		this.isBasketed = isBasketed;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Set<Basket> getBaskets() {
		return baskets;
	}

	public void setBaskets(Set<Basket> baskets) {
		this.baskets = baskets;
	}
}