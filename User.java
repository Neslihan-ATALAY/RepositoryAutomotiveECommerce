package java.com.javahiringchallenge.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	// user name - email
	@Column(name="username")
	private String userName;
	@Column(name="password")
	private String password;
	@Column(name="name")
	private String name;
	@Column(name="surname")
	private String surname;
	@Column(name="isdeleted")
	private boolean isDeleted;
	@Column(name="ischecked")
	private boolean isChecked;
	@OneToMany(mappedBy="user")
	Set<UserRole> userRoles = new HashSet<UserRole>();	
	@OneToMany(mappedBy="user")
	Set<CardInfo> cardInfos = new HashSet<CardInfo>();
	@OneToMany(mappedBy="user")
	Set<Basket> baskets = new HashSet<Basket>();
	@OneToMany(mappedBy="user")
	Set<Order> orders = new HashSet<Order>();
	@ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(
			name = "userrole",
			joinColumns = { @JoinColumn(name = "userid") },
			inverseJoinColumns = { @JoinColumn(name = "roleid") })
	Set<Role> roles = new HashSet<Role>();
	
	public User() {
		super();
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
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

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public Set<CardInfo> getCardInfos() {
		return cardInfos;
	}

	public void setCardInfos(Set<CardInfo> cardInfos) {
		this.cardInfos = cardInfos;
	}	

	public Set<Basket> getBaskets() {
		return baskets;
	}

	public void setBaskets(Set<Basket> baskets) {
		this.baskets = baskets;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}	
}