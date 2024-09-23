package SimpleWebbApp.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import SimpleWebbApp.hibernateUtil.HibernateUtil;
import SimpleWebbApp.model.User;

public class UserDao {

	
	public void saveUser(User user) {
		
		Transaction transaction = null;
		
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start transaction
			transaction = session.beginTransaction();
			// save the object
			session.save(user);
			// commit transaction
			transaction.commit();
			
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		
	}
	
	public User getUserbyId(int id) {
		Transaction transaction = null;
		User user=null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			// load the object
			 user=(User) session.get(User.class,id);			
		transaction.commit();
		
		} catch (Exception e) {
			 if (transaction != null) {
		            transaction.rollback();
		        }
			e.printStackTrace();
		
		}
		return user; 
	}
	
	public List<User> getUserList() {
		
		Transaction transaction = null;
		List<User> userlist = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			// return the data to a list from database
		    userlist = session.createQuery(" from User ", User.class).getResultList();
		    transaction.commit();

		} catch (Exception e) {
			 if (transaction != null) {
		            transaction.rollback();
		        }
			e.printStackTrace();
			
		}

		 return userlist;
	}
	
	public void deleteUser(int id) throws SQLException {
		
		Transaction transaction=null;
		
		try(Session session=HibernateUtil.getSessionFactory().openSession()){
		transaction=session.beginTransaction();
		
		User user = session.get(User.class, id);
		if(user!=null) {
			session.delete(user);
			System.out.println("user is deleted");
			
		
		}
		transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		

	}
	
	
	
	
	
	
}