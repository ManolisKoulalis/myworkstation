package LoginRegisterTodoExample.web;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import LoginRegisterTodoExample.model.User;
import LoginRegisterTodoExample.dao.UserDao;


@WebServlet("/register")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private UserDao userDao;

    public UserController() {
    	userDao=new UserDao();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.sendRedirect("register/register.jsp");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String firstName = request.getParameter("firstName");
	        String lastName = request.getParameter("lastName");
	        String username = request.getParameter("username");
	        String password = request.getParameter("password");
	        
	        User user = new User();
	        user.setFirstName(firstName);
	        user.setLastName(lastName);
	        user.setUsername(username);
	        user.setPassword(password);
	        
	        try {
	            int result = userDao.registerEmployee(user);
	            if (result == 1) {
	                request.setAttribute("NOTIFICATION", "User Registered Successfully!");
	            }

	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }

	        RequestDispatcher dispatcher = request.getRequestDispatcher("register/register.jsp");
	        dispatcher.forward(request, response);
	    }
	}

