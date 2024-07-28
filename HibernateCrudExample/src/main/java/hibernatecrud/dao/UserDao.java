package hibernatecrud.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import hibernatecrud.hibernateutil.HibernateUtil;
import hibernatecrud.model.User;

public class UserDao {
	
	// save 
	public void saveUser(User user) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the user object
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
	
	//update 
	public void updateUser(User user) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the user object
			session.update(user);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	
	// delete
	public void deleteUser(int id) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// delete the user
			User user =  session.get(User.class, id);
			if (user != null) {
				session.delete(user);
				System.out.println("the user is deleted");
			}
		
		// commit transaction
		transaction.commit();
		
	}catch (Exception e) {
		if (transaction != null) {
			transaction.rollback();
		}
		e.printStackTrace();
	}
	
	}
	

	//select a specific user
	public User selectUser (int id) {
		
		Transaction transaction = null;
		User user=null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			//get user object
			user= session.get(User.class, id);
			transaction.commit();
			}catch (Exception e) {
				
				if (transaction != null) {
					transaction.rollback();
				}
				e.printStackTrace();
			}
		return user;
	}
		
		
	// select the list of the users
	@SuppressWarnings("unchecked")
	public List<User> selectAllUsers(){
		
		Transaction transaction = null;
		List<User> listofUser=null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			listofUser=session.createQuery("from User").getResultList();
			// commit transaction
			transaction.commit();
		}catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return listofUser;
	}
	

}


































	


