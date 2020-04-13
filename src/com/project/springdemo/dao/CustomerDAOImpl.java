package com.project.springdemo.dao;

import com.project.springdemo.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> getCustomers() {

        Session currentSession = sessionFactory.getCurrentSession();
        Query<Customer> query = currentSession.createQuery("from Customer order by lastName",
                                                                Customer.class);

        List<Customer> customers = query.getResultList();

        return customers;
    }

    @Override
    public Customer getCustomer(int id) {
        Session currentSession = sessionFactory.getCurrentSession();

        return currentSession.get(Customer.class, id);
    }

    @Override
    public void deleteCustomer(int id) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query query = currentSession.createQuery("DELETE FROM Customer WHERE id=:customerId");
        query.setParameter("customerId", id);
        query.executeUpdate();
    }

    @Override
    public List<Customer> searchCustomer(String searchName) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query query = null;

        if(searchName != null && searchName.trim().length() > 0){
            query = currentSession.createQuery("FROM Customer WHERE lower(firstName) LIKE :name OR lower(lastName) LIKE :name",
                    Customer.class);
            query.setParameter("name","%" + searchName.toLowerCase() + "%");
        } else {
            query = currentSession.createQuery("FROM Customer",
                    Customer.class);
        }

        List<Customer> customers = query.getResultList();

        return customers;
    }

    @Override
    public void saveCustomer(Customer customer) {
        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.saveOrUpdate(customer);
    }
}
