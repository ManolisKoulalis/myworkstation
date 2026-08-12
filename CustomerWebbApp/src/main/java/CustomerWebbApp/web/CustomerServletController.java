package CustomerWebbApp.web;

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
import javax.servlet.http.HttpSession;

import CustomerWebbApp.Dao.CustomerDao;
import CustomerWebbApp.Dao.WorkDao;
import CustomerWebbApp.Model.Address;
import CustomerWebbApp.Model.Customer;


@WebServlet(urlPatterns = {
	    "/register",
	    "/editCustomer",
	    "/update",
	    "/delete",
	    "/displayCustomers",
	    "/insert",
	    "/loginpage",
	    "/loginCustomer",
	    "/indexpage"
	    
	})
public class CustomerServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private CustomerDao customerDao;
	
	
	public void init() {
	       
		customerDao= new CustomerDao();
	
	    }
	
	
	


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");

		doGet(request, response);
	}
	
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getServletPath();
		
		try {
			switch (action) {
			case "/register":
				showRegisterForm(request, response);
				break;
			case "/editCustomer":
				 showEditRegisterForm(request,response);
				break;
			case "/update":
				 updateCustomer(request,response);
				break;
			case "/delete":
				deleteCustomer(request, response);
				break;
			case "/displayCustomers":
				showList(request, response);
				break;
			case "/insert":
				registerCustomer(request, response);
				break;
			case "/loginCustomer":
				loginCustomer(request,response);
				break;
			case "/loginpage":
				showloginpage(request,response);
				break;
			case "/indexpage":
				response.sendRedirect("index.jsp");
				break;
			default:
				response.sendRedirect("index.jsp");
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	
	public void showloginpage (HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException,ServletException {
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("loginpage.jsp");
		dispatcher.forward(request, response);

	}
	

	public void loginCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException,ServletException {
		
		
		Customer customer=null;
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		customer=customerDao.loginmethod(username,password);
		if (customer==null)	{
			request.setAttribute("message","Wrong username or password");
			
			request.getRequestDispatcher("loginpage.jsp") .forward(request, response);
		   return;
		}
		else {
			
			
			//HttpSession session = request.getSession();
			//session.setAttribute("loggedCustomer", customer);
			request.setAttribute("customer", customer);
			RequestDispatcher dispatcher = request.getRequestDispatcher("logedinpage.jsp");
			dispatcher.forward(request, response);
		}
		

		
	}

	public void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException,ServletException{
		
		int id= Integer.parseInt(request.getParameter("customerId"));
		customerDao.deleteCustomer(id);
		List<Customer> customerlist = customerDao.getCustomerList();
		request.setAttribute("customerlist",customerlist); 
		RequestDispatcher dispatcher = request.getRequestDispatcher("listofcustomer.jsp");
		dispatcher.forward(request, response);
		
	}
	
	public void showList(HttpServletRequest request, HttpServletResponse response) throws  IOException,ServletException{
		
		 List<Customer> customerlist = customerDao.getCustomerList();
		 request.setAttribute("customerlist",customerlist); 
		RequestDispatcher dispatcher = request.getRequestDispatcher("listofcustomer.jsp");
		dispatcher.forward(request, response);
		
	}
	
	public void registerCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException,ServletException{
	
		String message = "";
	 	boolean valid = true;
		
		
	//Εντος της μεθοδου τοποθετουμε το name που δινουμε στο input του jsp και οχι το id 
	String name=request.getParameter("name");
	if (name == null || name.trim().isEmpty()) {
	    message += "Name is required.<br>";
	    valid = false;

	} else if (name.matches(".*\\d.*") || name.length() < 2|| name.length() > 30) {

	    message += "Please insert a valid name (Only Characters Allowed, between 2-30 characters)<br>";
	    valid = false;
	}
	 
	
	String surname=request.getParameter("surname");
	// διαφορετικα θα μπορουσα να βαλω αλλη συνθηκη για να δεχοταν μονο γραμματα οπως !surname.matches("\\p{L}+") ή if (!surname.matches("[a-zA-ZΑ-Ωα-ωΆ-Ώά-ώ]+"))
	if (surname == null || surname.trim().isEmpty()) {

	    message += "Surname is required.<br>";
	    valid = false;

	} else if (surname.matches(".*\\d.*") || surname.length() < 2  || surname.length() > 30) {

	    message += "Please insert a valid surname (Only Characters Allowed, between 2-30 characters)<br>";
	    valid = false;
	}
	
	
	 String afm=request.getParameter("afm");
		
		if (afm == null || afm.trim().isEmpty() || !afm.matches("\\d{9}")) {
		    message += "AFM must contain exactly 9 digits.<br>";
		    valid = false;
		}else {
		    Customer existing = customerDao.getCustomerbyAfm(afm);

		    if (existing != null) {
		        message += "AFM already exists.<br>";
		        valid = false;
		    }
		}
	
	
	 
	String gender=request.getParameter("gender");	
	 if (gender == null ||!gender.equals("Male") && !gender.equals("Female")) {
 		message += "Please select a valid gender (Male or Female)<br>";
 		valid = false;
 	}
	
	 
	 // LocalDate.parse(birthdateStr) κανει απο μονο του ελεγχο για ορθοτητα των δεδομενων ετσι αν ηθελα να χρησιμοποιησω if δεν θα εμφανιζα το δικο μηνυμα ευκολα καθως θα πετουσε ποιον πριν το σφαλμα του ελεγχου που κανει
	String birthdateStr =request.getParameter("birthdate");
	LocalDate birthdate=null;
	if (birthdateStr == null || birthdateStr.trim().isEmpty()) {

	    message += "Birth date is required.<br>";
	    valid = false;

	} else {

	    try {
	        birthdate = LocalDate.parse(birthdateStr);
	    } catch (Exception e) {
	        message += "Please select a valid date.<br>";
	        valid = false;
	    }
	}
	
	
	String workPostcode= request.getParameter("workPostcode");
	if (workPostcode == null || !workPostcode.matches("\\d{5}") ) {
	    message += "Work postcode must contain exactly 5 digits.<br>";
	    valid = false;
	}
	
	
	String workAddressName=request.getParameter("workAddressName");
	if (workAddressName == null || workAddressName.trim().isEmpty()) {

	    message += "Work address is required.<br>";
	    valid = false;

	} else if (workAddressName.length() > 255) {

	    message += "Work Address characters exceed limit (255).<br>";
	    valid = false;
	}
	
	String homePostcode= request.getParameter("homePostcode");
	  if ( homePostcode  == null || !homePostcode.matches("\\d{5}") ) {
		    message += "Home postcode must contain exactly 5 digits.<br>";
		    valid = false;
		}
		
	
	String homeAddressName=request.getParameter("homeAddressName");
	if (homeAddressName == null || homeAddressName.trim().isEmpty()) {

	    message += "Home address is required.<br>";
	    valid = false;

	} else if (homeAddressName.length() > 255) {

	    message += "Home Address characters exceed limit (255).<br>";
	    valid = false;
	}
	 

	
	String moreInfo=request.getParameter("moreInfo"); 
	 if (moreInfo.length()>255) {
	     	message += "Information  exceed limit (255).<br>";
	 		valid = false;
	 	}
	

	String username=request.getParameter("username");
	if (username == null || username.trim().isEmpty() || username.length() < 4  || username.length() > 20) {

	    message += "Username must be between 4 and 20 characters.<br>";
	    valid = false;
	}else {
		Customer existing = customerDao.getCustomerByUsername(username);

		if (existing != null) {
		    message += "Username already exists.<br>";
		    valid = false;
		}
		
	}
	
	
	
	
	
	String password=request.getParameter("password");
	if (password == null || password.trim().isEmpty() || password.length() < 8 ||  password.length() > 30) {
	    message += "Password must be at least 8 characters.<br>";
	    valid = false;
	}
	

	 if (!valid) {
	       	request.setAttribute("message", message);
	       	request.getRequestDispatcher("registercustomer.jsp").forward(request, response);
		       return;
	       }
		
	 Address homeAddress= new Address();
		homeAddress.setName(homeAddressName);
		homeAddress.setPostcode(homePostcode);
	 Address workAddress= new Address();
		workAddress.setName(workAddressName);
		workAddress.setPostcode(workPostcode);
	
	Customer customer= new Customer(name,surname,gender,afm,birthdate,workAddress,homeAddress,moreInfo,username,password);
	
	customerDao.saveCustomer(customer);
	
	response.sendRedirect("index.jsp");
	
	}
	

	public void showRegisterForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException,ServletException{
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("registercustomer.jsp");
		dispatcher.forward(request, response);
		
	}
	
	private void showEditRegisterForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("customerId"));
		Customer existingCustomer = customerDao.getCustomerbyId(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("registercustomer.jsp");
		request.setAttribute("customer", existingCustomer);
		dispatcher.forward(request, response);

	}
	
	private void updateCustomer(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException,ServletException {
	
		String message = "";
	 	boolean valid = true;
		
		int id = Integer.parseInt(request.getParameter("customerId"));
		
		String name = request.getParameter("name");
		if (name == null || name.trim().isEmpty()) {

		    message += "Name is required.<br>";
		    valid = false;

		} else if (name.matches(".*\\d.*") || name.length() < 2 || name.length() > 30) {

		    message += "Please insert a valid name (Only Characters Allowed, between 2-30 characters)<br>";
		    valid = false;
		}
		
		
		String surname = request.getParameter("surname");
		if (surname == null || surname.trim().isEmpty()) {

		    message += "Surname is required.<br>";
		    valid = false;

		} else if (surname.matches(".*\\d.*") || surname.length() < 2 || surname.length() > 30) {

		    message += "Please insert a valid surname (Only Characters Allowed, between 2-30 characters)<br>";
		    valid = false;
		}
		
		
		String gender=request.getParameter("gender");
		 if (gender==null || !gender.equals("Male") && !gender.equals("Female")) {
		 		message += "Please select a valid gender (Male or Female)<br>";
		 		valid = false;
		 	}
		
		 String afm=request.getParameter("afm");
		
			if (afm == null || afm.trim().isEmpty() || !afm.matches("\\d{9}")) {
			    message += "AFM must contain exactly 9 digits.<br>";
			    valid = false;
			}else {
				Customer existing = customerDao.getCustomerbyAfm(afm);

				if (existing != null && existing.getId() != id) {
				    message += "AFM already exists.<br>";
				    valid = false;
				}
			}
		  
		 
		String username=request.getParameter("username");
		if (username == null || username.trim().isEmpty()  || username.length() < 4 || username.length() > 20) {

		    message += "Username must be between 4 and 20 characters.<br>";
		    valid = false;
		}else {
			Customer existing = customerDao.getCustomerByUsername(username);

			if (existing != null && existing.getId() != id) {
			    message += "Username already exists.<br>";
			    valid = false;
			}
		}
	  
		
		
		String password=request.getParameter("password");
		if (password == null|| password.trim().isEmpty()  || password.length() < 8) {

		    message += "Password must be at least 8 characters.<br>";
		    valid = false;
		}
		
		
		String moreInfo=request.getParameter("moreInfo"); 
		 if (moreInfo.length()>255) {
		     	message += "Information  exceed limit (255).<br>";
		 		valid = false;
		 	}
		 
		
		String birthdateStr=request.getParameter("birthdate");
		LocalDate birthdate=null;
		if (birthdateStr == null || birthdateStr.trim().isEmpty()) {

		    message += "Birth date is required.<br>";
		    valid = false;

		} else {

		    try {
		        birthdate = LocalDate.parse(birthdateStr);
		    } catch (Exception e) {
		        message += "Please select a valid date.<br>";
		        valid = false;
		    }
		}
		
		
		String workAddressName=request.getParameter("workAddressName");
		if (workAddressName == null || workAddressName.trim().isEmpty()) {

		    message += "Work address is required.<br>";
		    valid = false;

		} else if (workAddressName.length() > 255) {

		    message += "Work Address characters exceed limit (255).<br>";
		    valid = false;
		}
		 
		String workAddressPost=request.getParameter("workPostcode");
		if (workAddressPost == null || !workAddressPost.matches("\\d{5}")) {

		    message += "Work postcode must contain exactly 5 digits.<br>";
		    valid = false;
		}
		
		String homeAddressName=request.getParameter("homeAddressName");
		if (homeAddressName == null || homeAddressName.trim().isEmpty()) {

		    message += "Home address is required.<br>";
		    valid = false;

		} else if (homeAddressName.length() > 255) {

		    message += "Home Address characters exceed limit (255).<br>";
		    valid = false;
		}
	
		 String homeAddressPost=request.getParameter("homePostcode");
		if (homeAddressPost == null || !homeAddressPost.matches("\\d{5}")) {

		    message += "Home postcode must contain exactly 5 digits.<br>";
		    valid = false;
		}
		
		 

		 if (valid==false) {
		       	request.setAttribute("message", message);
		       	request.getRequestDispatcher("/registercustomer.jsp").forward(request, response);
			        return;
		       }
		 
		Customer customer=customerDao.getCustomerbyId(id);
		 
		Address homeAddress=customer.getHomeAddress();
		homeAddress.setName(homeAddressName);
		homeAddress.setPostcode(homeAddressPost);
		Address workAddress=customer.getWorkAddress();
		workAddress.setName(workAddressName);
		workAddress.setPostcode(workAddressPost);
		
		
		
		
		
		/*σε αυτο το σημειο σε αντιθεση με το insert δεν δημιουργω καινουργιο αντικειμενο και απλα κανω set τις τιμες καθως δεν θελω να χασω την λιστα αφου δεν εκτελω αλλαγες 
		πανω της και δεν θελωνατην περασω απο την αρχη δηλααδη να την ξανακαλεσω για να την βαλω σαν ορισμα, ετσι αν δημιουργησω εναν πελατη χωρις λιστα ουσιαστηκα του σβηνω 
		την λιστα που μπορει να εχει μεχρι τωρα*/
		customer.setBirthdate(birthdate);
		customer.setGender(gender);
		customer.setAfm(afm);
		customer.setMoreInfo(moreInfo);
		customer.setName(name);
		customer.setPassword(password);
		customer.setSurname(surname);
		customer.setUsername(username);
		customer.setWorkAddress(workAddress);
		customer.setHomeAddress(homeAddress);
		
		customerDao.updateCustomer(customer);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("logedinpage.jsp");
		request.setAttribute("customer", customer);
		dispatcher.forward(request, response);
		
		
	}
	
	
}
