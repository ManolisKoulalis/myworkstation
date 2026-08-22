package CustomerWebbApp.Dao;


import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import CustomerWebbApp.hibernateUtil.HibernateUtil;
import org.mindrot.jbcrypt.BCrypt;
import CustomerWebbApp.Model.Customer;
import CustomerWebbApp.Model.Work;

public class CustomerDao {

	
	
	
	public Customer loginmethod(String username,String password) {
		Customer customer=null;
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			customer=(Customer)session.createQuery("from Customer where username=:username ", Customer.class)
					.setParameter("username", username)
					.uniqueResult();
			
    /* εδω φορτωνω και την λιστα καθως στο logedinpage πεταει ερρορ http status 500  εξετιας του lazy strategy που χρησιμοποιει το hibernate
	μεταξυ της συσχετισης των κλασεων customer-work που αφορα το worklist, ετσι δεν φορτονεται απο την αρρχη το worklist και   
	sτο login φορτωνεται ο customer αλλα οχι το worklisτ και με το που γινει η διαδικασια login κλεινει το session 
	και οταν φορτωθει η επομενη σελιδα ζηταει το worklist  αλλα το προηγουμενο session εχει ηδη κλεισει οποτε εχουμε τον customer χωρις το worklist  */
			if (customer != null) {
				boolean passwordMatches = BCrypt.checkpw(password, customer.getPassword() );

	            if (!passwordMatches) {
	                customer = null;
	            }
				customer.getWorklist().size();
				
	        }
			
			transaction.commit();
			
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		
		return customer;
	}
	
	
	public void saveCustomer(Customer acustomer) {
		
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start transaction
			transaction = session.beginTransaction();
			// save the object
			session.save(acustomer);
			// commit transaction
			transaction.commit();
			
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		
	}
	
	
	
	public Customer getCustomerbyId(int id) {
		
		Transaction transaction = null;
		Customer customer=null;
		
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			// load the object
			 customer=(Customer) session.get(Customer.class,id);	
			 customer.getWorklist().size();
			 transaction.commit();
		
		} catch (Exception e) {
			 if (transaction != null) {
		            transaction.rollback();
		        }
			e.printStackTrace();
		
		}
		return customer; 
	}
	
	
	
	public List<Customer> getCustomerList() {
		
		Transaction transaction = null;
		List<Customer> customerlist = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			// return the data to a list from database
		    customerlist = session.createQuery(" from Customer ", Customer.class).getResultList();
		    transaction.commit();

		} catch (Exception e) {
			 if (transaction != null) {
		            transaction.rollback();
		        }
			e.printStackTrace();
			
		}

		 return customerlist;
	}
	
	
	
	
	
	
	public void deleteCustomer(int id) throws SQLException {
		
		Transaction transaction=null;
		
		try(Session session=HibernateUtil.getSessionFactory().openSession()){
		transaction=session.beginTransaction();
		
		Customer customer = session.get(Customer.class, id);
		
		if(customer!=null) {
			/*	Αν δεν ειχα το cascade θα επρεπε να κανω και αυτη την ενεργεια
			List<Work> worklist=customer.getWorklist();
			for(Work w: worklist) {
				session.delete(w);
			}
			*/
			session.delete(customer);
			System.out.println("customer is deleted");
			
		
		}
		transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		

	}
	
	public void updateCustomer(Customer customer) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the student object
			session.merge(customer);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	
	
	
	
	public Customer getCustomerbyAfm(String afm) {
		
		Transaction transaction = null;
		Customer customer=null;
		
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			// load the object
			 customer=(Customer)session.createQuery("from Customer where afm=:afm", Customer.class)
						.setParameter("afm", afm)
						 .uniqueResult();			
			 transaction.commit();
		
		} catch (Exception e) {
			 if (transaction != null) {
		            transaction.rollback();
		        }
			e.printStackTrace();
		
		}
		return customer; 
	}
	
	
	public Customer getCustomerByUsername(String username) {
		
		Transaction transaction = null;
		Customer customer=null;
		
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			// load the object
			 customer=(Customer)session.createQuery("from Customer where username=:username", Customer.class)
						.setParameter("username", username)
						.uniqueResult();			
			 transaction.commit();
		
		} catch (Exception e) {
			 if (transaction != null) {
		            transaction.rollback();
		        }
			e.printStackTrace();
		
		}
		return customer; 
	}
	
	
	public Customer addWorkToCustomer(int customerId, Work work) {

	    Transaction transaction = null;
	    Customer customer = null;

	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {

	        transaction = session.beginTransaction();

	        customer = session.get(Customer.class, customerId);

	        if (customer != null) {
	            customer.addWork(work);
	        }

	        transaction.commit();

	    } catch (Exception e) {

	        if (transaction != null) {
	            transaction.rollback();
	        }

	        e.printStackTrace();
	    }

	    return customer;
	}
	
	
	
	
	
		
		
	}
	

	
	

