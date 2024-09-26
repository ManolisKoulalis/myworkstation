package SimpleWebbApp.web;

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

import SimpleWebbApp.dao.UserDao;
import SimpleWebbApp.model.Address;
import SimpleWebbApp.model.User;



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
				response.sendRedirect("index.jsp");
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
	 
	
	String surname=request.getParameter("surname");
	
	
	String gender=request.getParameter("gender");	
	
	
	LocalDate birthdate=LocalDate.parse(request.getParameter("birthdate"));
       		
	
	String workPostcode= request.getParameter("workPostcode");
	  if (workPostcode.length()>6) {
      	message += "Work postcode characters exceed limit (5).<br>";
  		valid = false;
	    }
	
	String workAddressName=request.getParameter("workAddressName");
	 if (workAddressName.length()>255) {
	     	message += "Work Address characters exceed limit (255).<br>";
	 		valid = false;
	 	}
	
	String homePostcode= request.getParameter("homePostcode");
	  if (homePostcode.length()>6) {
	      	message += "Home postcode characters exceed limit (5).<br>";
	  		valid = false;
		    }
		
	
	String homeAddressName=request.getParameter("homeAddressName");
	 if (homeAddressName.length()>255) {
     	message += "Home Address characters exceed limit (255).<br>";
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
	
	response.sendRedirect("index.jsp");
	
	}
	
	public void showDetails(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException,ServletException{
	
		int id=Integer.parseInt(request.getParameter("id"));
		User user=userDao.getUserbyId(id);
		

				
		request.setAttribute("user", user);
		request.setAttribute("workAddress",user.getWork_address());
		request.setAttribute("homeAddress",user.getHome_address());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("details.jsp");
		dispatcher.forward(request, response);
	
	}

	public void showRegisterForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException,ServletException{
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
		dispatcher.forward(request, response);
		
	}
}