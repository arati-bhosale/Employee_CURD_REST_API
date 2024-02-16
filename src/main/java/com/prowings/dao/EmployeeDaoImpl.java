package com.prowings.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prowings.model.Employee;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void saveEmployee(Employee employee) {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(employee);
		tx.commit();
	}

	@Override
	public Employee getEmployeeById(int id) {

		Employee emp;
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		emp = session.get(Employee.class, id);
		tx.commit();

		return emp;
	}

	@Override
	public List<Employee> getAllEmployee() {

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<Employee> Emplist = null;

		try {

			tx = session.beginTransaction();
			Query<Employee> query = session.createQuery("from Employee");
			Emplist = query.list();
			tx.commit();
		}

		catch (HibernateException ex) {

			if (tx != null) {
				tx.rollback();
			}
			System.out.println("Exception:" + ex.getMessage());
			ex.printStackTrace(System.err);
		}

		finally {

			session.close();
			return Emplist;
		}
	}
}
