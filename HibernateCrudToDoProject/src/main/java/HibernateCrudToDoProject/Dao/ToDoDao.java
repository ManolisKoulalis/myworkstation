package HibernateCrudToDoProject.Dao;

import java.sql.SQLException;
import java.util.List;

import HibernateCrudToDoProject.model.ToDo;

public interface ToDoDao {

	void insertTodo(ToDo todo) throws SQLException;

	ToDo selectTodo(int toDoId);

	List<ToDo> selectAllTodos();

	void deleteTodo(int id) throws SQLException;

	void updateTodo(ToDo todo) throws SQLException;

}