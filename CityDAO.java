package java.com.javahiringchallenge.dao;

import java.com.javahiringchallenge.model.City;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CityDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public String saveCity(City city) {
		Long isSuccess = (Long)getSession().save(city);
		if(isSuccess >= 1){
			return "Success";
		}else{
			return "Error while Saving City";
		}
	}

	public boolean deleteCity(City city) {
		getSession().delete(city);
		return true;
	}
	
	public boolean updateCity(City city) {
		if(city != null) {
			getSession().update(city);
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<City> getCityList() {
		return getSession().createQuery("from City").list();
	}
	
	@SuppressWarnings("unchecked")
	public City getCityById(long id) {
		City city = null;
		List list = getSession().createQuery("from City where id=" + id + "").list();
		if(list != null && list.size() == 1)
		{
			city = new City();
			city.setId((Long) list.get(0));
			city.setCityName(list.get(1).toString());
			return city;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public City getCityByName(String cityname) {
		City city = null;
		List list = getSession().createQuery("from City where name='" + cityname + "'").list();
		if(list != null && list.size() == 1)
		{
			city = new City();
			city.setId((Long) list.get(0));
			city.setCityName(list.get(1).toString());
			return city;
		}
		return null;
	}
}