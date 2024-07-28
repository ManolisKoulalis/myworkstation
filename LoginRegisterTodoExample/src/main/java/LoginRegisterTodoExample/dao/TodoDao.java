package LoginRegisterTodoExample.dao;
import java.sql.SQLException;
import java.util.List;

import LoginRegisterTodoExample.model.Todo;

public interface TodoDao {

 void insertTodo(Todo todo) throws SQLException;

 Todo selectTodo(int todoId);

 List<Todo> selectAllTodos();

 boolean deleteTodo(int id) throws SQLException;

 boolean updateTodo(Todo todo) throws SQLException;
}
