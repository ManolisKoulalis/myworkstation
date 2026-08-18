package CustomerWebbApp.Dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import CustomerWebbApp.hibernateUtil.HibernateUtil;

import CustomerWebbApp.Model.Customer;
import CustomerWebbApp.Model.Work;



public class WorkDao {
	
	
	
	
	public void saveWork(Work awork) {
		
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start transaction
			transaction = session.beginTransaction();
			// save the object
			session.save(awork);
			// commit transaction
			transaction.commit();
			
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		
	}
	
	
	public Work getWorkbyId(int id) {
		
		Transaction transaction = null;
		Work work=null;
		
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			// load the object
			 work=(Work) session.get(Work.class,id);			
			 transaction.commit();
		
		} catch (Exception e) {
			 if (transaction != null) {
		            transaction.rollback();
		        }
			e.printStackTrace();
		
		}
		return work; 
	}
	
	
	public List<Work> getWorkList() {
		
		Transaction transaction = null;
		List<Work> worklist = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			// return the data to a list from database
		    worklist = session.createQuery(" from Work ", Work.class).getResultList();
		    transaction.commit();

		} catch (Exception e) {
			 if (transaction != null) {
		            transaction.rollback();
		        }
			e.printStackTrace();
			
		}

		 return worklist;
	}
	
	

	public void deleteWork(int id)  {
		
		Transaction transaction=null;
		
		try(Session session=HibernateUtil.getSessionFactory().openSession()){
		transaction=session.beginTransaction();
		
		Work work = session.get(Work.class, id);
		if(work!=null) {
			session.delete(work);
			System.out.println("work is deleted");
			
		
		}
		transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		
	}
	
	
		
		public void updateWork(Work work) {
			
			Transaction transaction = null;
			try (Session session = HibernateUtil.getSessionFactory().openSession()) {
				// start a transaction
				transaction = session.beginTransaction();
			
				session.merge(work);
				// commit transaction
				transaction.commit();
			} catch (Exception e) {
				if (transaction != null) {
					transaction.rollback();
				}
				e.printStackTrace();
			}
		}

		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	


