package java.com.javahiringchallenge.model;

import java.sql.Date;
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
@Table(name = "cardinfo")
public class CardInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name="userid")
	private long userid;
	@ManyToOne
	@JoinColumn(name="userid")
	private User user;
	@Column(name="cardnumber")
	private String cardNumber;
	@Column(name="cardLastDate")
	private Date cardLastDate;
	@Column(name="cvc")
	private String cvc;
	@Column(name="isdeleted")
	private boolean isDeleted;
	@OneToMany(mappedBy="cardinfo")
	private Set<Order> orders = new HashSet<Order>();
	
	public CardInfo() {
		super();
	}
	
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
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public Date getCardLastDate() {
		return cardLastDate;
	}
	public void setCardLastDate(Date date) {
		this.cardLastDate = date;
	}
	public String getCvc() {
		return cvc;
	}
	public void setCvc(String cvc) {
		this.cvc = cvc;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public Set<Order> getOrders() {
		return orders;
	}
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
}