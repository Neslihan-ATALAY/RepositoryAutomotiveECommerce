package java.com.javahiringchallenge.dao;

import java.com.javahiringchallenge.model.*;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ProductDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public String saveProduct(Product product) {
		Long isSuccess = (Long)getSession().save(product);
		if(isSuccess >= 1){
			return "Success";
		}else{
			return "Error while Saving Product";
		}
	}

	public boolean deleteProduct(Product product) {
		getSession().delete(product);
		return true;
	}
	
	public boolean updateProduct(Product product) {
		if(product != null) {
			getSession().update(product);
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<Product> getProductList() {
		return getSession().createQuery("from Product where stockquantity > 0" +
				" and isdeleted=false").list();
	}
	
	@SuppressWarnings("unchecked")
	public ProductGroup getProductGroupByName(String productGroupName) {
		ProductGroup productGroup = null;
		List list = getSession().createQuery("from ProductGroup "
				+ " where name='" + productGroupName + "'").list();
		if(list != null && list.size() == 1)
		{
			productGroup = new ProductGroup();
			productGroup.setId((Long) list.get(0));
			productGroup.setName(list.get(1).toString());
			productGroup.setDeleted((Boolean)list.get(2));
			return productGroup;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public ProductGroup getProductGroupById(Long productGroupId) {
		ProductGroup productGroup = null;
		List list = getSession().createQuery("from ProductGroup "
				+ " where id=" + productGroupId + "").list();
		if(list != null && list.size() == 1)
		{
			productGroup = new ProductGroup();
			productGroup.setId((Long) list.get(0));
			productGroup.setName(list.get(1).toString());
			productGroup.setDeleted((Boolean)list.get(2));
			return productGroup;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Product getProductById(Long id) {
		Product product = null;
		ProductGroup productGroup = null;
		List list = getSession().createQuery("from Product where id=" + id + "").list();
		if(list != null && list.size() == 1) {
			product = new Product();
			product.setId((Long) list.get(0));
			product.setName(list.get(1).toString());
			product.setMark(list.get(2).toString());
			product.setStockCode(list.get(3).toString());
			product.setProductGroupId((Long)list.get(4));
			productGroup = new ProductGroup();
			productGroup = getProductGroupById((Long)list.get(4));
			if(productGroup != null) {
				product.setProductGroup(productGroup);
			}
			product.setPrice((Double) list.get(5));
			product.setStockQuantity((Integer) list.get(6));
			product.setDeleted((Boolean)list.get(7));
			return product;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> listProductByGroup(Long id) {
		List<Product> listProduct = null;
		Product product = null;
		ProductGroup productGroup = null;
		List list = getSession().createQuery("from Product where productgroupid=" + id + "").list();
		if(list != null && list.size() > 0) {
			listProduct = new ArrayList<Product>();
			for(int i = 0; i < listProduct.size(); i++) {
				product = new Product();
				product.setId((Long) list.get(0));
				product.setName(list.get(1).toString());
				product.setMark(list.get(2).toString());
				product.setStockCode(list.get(3).toString());
				product.setProductGroupId((Long)list.get(4));
				productGroup = new ProductGroup();
				productGroup = getProductGroupById((Long)list.get(4));
				if(productGroup != null) {
					product.setProductGroup(productGroup);
				}
				product.setPrice((Double) list.get(5));
				product.setStockQuantity((Integer) list.get(6));
				product.setDeleted((Boolean)list.get(7));
				listProduct.add(product);
			}
			return listProduct;
		}
		return null;
	}
}