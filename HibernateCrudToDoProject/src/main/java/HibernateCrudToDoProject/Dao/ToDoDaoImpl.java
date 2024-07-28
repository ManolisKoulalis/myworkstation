package HibernateCrudToDoProject.Dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import HibernateCrudToDoProject.HibernateUtil.HibernateUtil;
import HibernateCrudToDoProject.model.ToDo;


public class ToDoDaoImpl implements ToDoDao {

	public ToDoDaoImpl() {
	}
	
	
	public void insertTodo(ToDo todo) throws SQLException {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the student object
			session.save(todo);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	
	
		public void deleteTodo(int id) throws SQLException {
			
			Transaction transaction=null;
			
			try(Session session=HibernateUtil.getSessionFactory().openSession()){
			transaction=session.beginTransaction();
			
			ToDo todo = session.get(ToDo.class, id);
			if(todo!=null) {
				session.delete(todo);
				System.out.println("todo is deleted");
			
			
			}
			transaction.commit();
			} catch (Exception e) {
				if (transaction != null) {
					transaction.rollback();
				}
				e.printStackTrace();
			}
			
	
		}
		
		
		public ToDo selectTodo(int todoId) {
			Transaction transaction = null;
			ToDo todo = null;
			try (Session session = HibernateUtil.getSessionFactory().openSession()) {
				// start a transaction
				transaction = session.beginTransaction();
				// get an user object
				todo = session.get(ToDo.class, todoId);
				// commit transaction
				transaction.commit();
			} catch (Exception e) {
				if (transaction != null) {
					transaction.rollback();
				}
				e.printStackTrace();
			}
			return todo;
		}
	
		@SuppressWarnings("unchecked")
		public List<ToDo> selectAllTodos() {
			
			Transaction transaction = null;
			List<ToDo> todos = null;
			try (Session session = HibernateUtil.getSessionFactory().openSession()) {
				// start a transaction
				transaction = session.beginTransaction();
				// get an user object
				
				todos = session.createQuery(" from ToDo").getResultList();
				
				// commit transaction
				transaction.commit();
			} catch (Exception e) {
			
				e.printStackTrace();
				
			}
			
			return todos;
		}
	
		public void updateTodo(ToDo todo) throws SQLException {
			
			Transaction transaction = null;
			try (Session session = HibernateUtil.getSessionFactory().openSession()) {
				// start a transaction
				transaction = session.beginTransaction();
				// save the student object
				session.update(todo);
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
