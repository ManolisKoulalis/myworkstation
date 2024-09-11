package SImpleWebProjectApplication.web;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import SImpleWebProjectApplication.dao.UserDao;
import SImpleWebProjectApplication.model.Address;
import SImpleWebProjectApplication.model.User;



@WebServlet("/")
public class UserServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  private UserDao userDao;
    
   public void init() {
       
       userDao= new UserDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action=request.getServletPath();
		
		try {
			switch (action) {
			case "/register":
				showRegisterForm(request, response);
				break;
			case "/details":
				showDetails(request, response);
				break;
			case "/delete":
				deleteUser(request, response);
				break;
			case "/displayUsers":
				showList(request, response);
				break;
			case "/insert":
				registerUser(request, response);
				break;
			default:
				response.sendRedirect("homepage.jsp");
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	public void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException,ServletException{
		
		int id= Integer.parseInt(request.getParameter("id"));
		userDao.deleteUser(id);
		List<User> userlist = userDao.getUserList();
		request.setAttribute("userlist",userlist); 
		RequestDispatcher dispatcher = request.getRequestDispatcher("displayUsers.jsp");
		dispatcher.forward(request, response);
		
	}
	
	public void showList(HttpServletRequest request, HttpServletResponse response) throws  IOException,ServletException{
		
		 List<User> userlist = userDao.getUserList();
		 request.setAttribute("userlist",userlist); 
		RequestDispatcher dispatcher = request.getRequestDispatcher("displayUsers.jsp");
		dispatcher.forward(request, response);
		
	}
	
	public void registerUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException,ServletException{
	
		String message = "";
	 	boolean valid = true;
		
		
	
	String name=request.getParameter("name");
	   if (name.matches(".*\\d.*") || name.length()<3 || name.length()>30) {
       	message += "Please insert a valid name (Only Characters Allowed, between 2-30 characters)<br>";
       	valid = false;
       }
	
	String surname=request.getParameter("surname");
	if (surname.matches(".*\\d.*") || surname.length()<3 || surname.length()>30) {
    	message += "Please insert a valid surname (Only Characters Allowed, between 2-30 characters)<br>";
    	valid = false;
    }
	
	String gender=request.getParameter("gender");	
	 if (!gender.equals("Male") && !gender.equals("Female")) {
 		message += "Please select a valid gender (Male or Female)<br>";
 		valid = false;
 	}
	
	LocalDate birthdate=LocalDate.parse(request.getParameter("birthdate"));
       		
	
	String workPostcode= request.getParameter("workPostcode");
	  if (workPostcode.length()>5) {
      	message += "Work postcode characters exceed limit (5)";
  		valid = false;
	    }
	
	String workAddressName=request.getParameter("workAddressName");
	 if (workAddressName.length()>255) {
	     	message += "Work Address characters exceed limit (255)";
	 		valid = false;
	 	}
	
	String homePostcode= request.getParameter("homePostcode");
	  if (homePostcode.length()>5) {
	      	message += "Home postcode characters exceed limit (5)";
	  		valid = false;
		    }
		
	
	String homeAddressName=request.getParameter("homeAddressName");
	 if (homeAddressName.length()>255) {
     	message += "Home Address characters exceed limit (255)";
 		valid = false;
 	}
	 
	 
	   if (valid==false) {
       	request.setAttribute("message", message);
       	request.getRequestDispatcher("/register.jsp").forward(request, response);
	        return;
       }
	
	Address homeAddress= new Address();
	homeAddress.setName(homeAddressName);
	homeAddress.setPostcode(homePostcode);
	Address workAddress= new Address();
	workAddress.setName(workAddressName);
	workAddress.setPostcode(workPostcode);

	User user= new User(name,surname,gender,birthdate,workAddress,homeAddress);
	
	userDao.saveUser(user);
	
	response.sendRedirect("homepage.jsp");
	
	}
	
	public void showDetails(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException,ServletException{
	
		int id=Integer.parseInt(request.getParameter("id"));
		User user=userDao.getUserbyId(id);
		request.setAttribute("user", user);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("details.jsp");
		dispatcher.forward(request, response);
	
	}

	public void showRegisterForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException,ServletException{
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
		dispatcher.forward(request, response);
		
	}
}
