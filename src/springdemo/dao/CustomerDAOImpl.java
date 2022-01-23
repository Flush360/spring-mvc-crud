package springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import springdemo.entity.Customer;
import springdemo.util.SortUtils;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomers(int sortField) {

		Session session = sessionFactory.getCurrentSession();
		String thefieldName = null;
		switch (sortField) {
		case SortUtils.FIRST_NAME:
			thefieldName = "firstName";
			break;
		case SortUtils.LAST_NAME:
			thefieldName = "lastName";
			break;
		case SortUtils.EMAIL:
			thefieldName = "email";
			break;

		default:
			thefieldName = "lastName";
		}

		String queryString = "from Customer order by " + thefieldName;

		Query<Customer> query = session.createQuery(queryString, Customer.class);
		List<Customer> customers = query.getResultList();

		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(theCustomer);

	}

	@Override
	public Customer getCustomer(int id) {
		Session session = sessionFactory.getCurrentSession();
		Customer theCustomer = session.get(Customer.class, id);
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query theQuery = session.createQuery("delete from Customer where id=:customerId").setParameter("customerId",
				id);
		theQuery.executeUpdate();

	}

	@Override
	public List<Customer> searchCustomers(String searchName) {
		Session session = sessionFactory.getCurrentSession();
		Query query = null;

		if (searchName != null && searchName.trim().length() > 0) {
			query = session.createQuery(
					"from Customer where lower(firstName) like :theName or lower(lastName) like :theName",
					Customer.class);
			query.setParameter("theName", "%" + searchName.toLowerCase() + "%");
		} else {
			query = session.createQuery("from Customer", Customer.class);
		}
		List<Customer> customers = query.getResultList();
		return customers;
	}

}
