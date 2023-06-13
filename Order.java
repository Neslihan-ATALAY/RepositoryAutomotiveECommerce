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
@Table(name = "order")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name="userid")
	private long userid;
	@ManyToOne
	@JoinColumn(name="userid")
	private User user;
	@Column(name="basketid")
	private long basketid;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="basketid")
	private Basket basket;
	@Column(name="namesurname")
	private String nameSurname;
	@Column(name="address")
	private String address;
	@Column(name="cityid")
	private long cityid;
	@ManyToOne
	@JoinColumn(name="cityid")
	private City city;
	@Column(name="telephone")
	private String telephone;
	@Column(name="date")
	private String date;
	@Column(name="totalprice")
	private double totalPrice;
	@Column(name="cardinfoid")
	private long cardInfoid;
	@ManyToOne
	@JoinColumn(name="cardinfoid")
	private CardInfo cardInfo;
	@Column(name="issended")
	private boolean isSended;
	@Column(name="isdeleted")
	public boolean isDeleted;
	@Column(name="ischecked")
	public boolean isChecked;
	
	public Order() {
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
	public long getBasketid() {
		return basketid;
	}
	public void setBasketid(long basketid) {
		this.basketid = basketid;
	}
	public Basket getBasket() {
		return basket;
	}
	public void setBasket(Basket basket) {
		this.basket = basket;
	}
	public String getNameSurname() {
		return nameSurname;
	}
	public void setNameSurname(String nameSurname) {
		this.nameSurname = nameSurname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getCityid() {
		return cityid;
	}
	public void setCityid(long cityid) {
		this.cityid = cityid;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public long getCardInfoid() {
		return cardInfoid;
	}
	public void setCardInfoid(long cardInfoid) {
		this.cardInfoid = cardInfoid;
	}
	public CardInfo getCardInfo() {
		return cardInfo;
	}
	public void setCardInfo(CardInfo cardInfo) {
		this.cardInfo = cardInfo;
	}
	public boolean isSended() {
		return isSended;
	}
	public void setSended(boolean isSended) {
		this.isSended = isSended;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}
}