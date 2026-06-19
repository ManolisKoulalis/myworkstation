package CustomerWebbApp.web;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import CustomerWebbApp.Dao.CustomerDao;
import CustomerWebbApp.Dao.WorkDao;
import CustomerWebbApp.Model.Address;
import CustomerWebbApp.Model.Customer;
import CustomerWebbApp.Model.Work;

/**
 * Servlet implementation class WorkServletController
 */
public class WorkServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	private CustomerDao customerDao;
	private WorkDao workDao;
	
	public void init() {
	       
		customerDao= new CustomerDao();
		workDao= new WorkDao();
	    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
String action=request.getServletPath();
		
		try {
			switch (action) {
			case "/addWork":
				showWorkForm(request, response);
				break;
			case "/insertWork":
				insertWork(request, response);
				break;
			case "/deleteWork":
				deleteWork(request, response);
				break;
			case "/editWork":
				showEditWorkForm(request, response);
				break;
			case "/updateWork":
				updateWork(request, response);
				break;
			default:
				response.sendRedirect("index.jsp");
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
		
	}

	
	
	
	
	public void showWorkForm(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException,ServletException {
		
		  String customerId = request.getParameter("customerId");

		  request.setAttribute("customerId", customerId);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WorkFormPage.jsp");
		dispatcher.forward(request, response);
		
	}
	
	
	
	public void deleteWork(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException,ServletException{
		
		int id= Integer.parseInt(request.getParameter("id"));
		workDao.deleteWork(id);
		int idcustomer= Integer.parseInt(request.getParameter("id"));
		Customer customer= customerDao.getCustomerbyId(idcustomer);
		
		//List<Work> worklist = customer.getWorklist();
		request.setAttribute("customer",customer);
		//request.setAttribute("worklist",worklist); 
		RequestDispatcher dispatcher = request.getRequestDispatcher("logedinCustomer.jsp");
		dispatcher.forward(request, response);
		
	}
	
	
	
	
	public void insertWork(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException,ServletException{
		
		//με το κουμπι add work στελνω το customer id στην σελιδα με την φορμα για το το work ετσι πηρα εδω το id απο το hidden πεδιο στην σελιδα
		 int customerId = Integer.parseInt(request.getParameter("customerId"));
		 Customer customer = customerDao.getCustomerbyId(customerId);
		
		 String message = "";
		 boolean valid = true;
		 
		 String workType=request.getParameter("workType");
		 if(workType==null || workType.trim().isEmpty()) {
			 message += "Work type is required.<br>";
			  valid = false;

		 }
		 else if (workType.matches(".*\\d.*") || workType.length()<2 || workType.length()>30  ) {
		     	message += "Please insert a valid work type (Only Characters Allowed, between 2-30 characters)<br>";
		     	valid = false;
		     }
		 
		 
		 String constructionAddressName=request.getParameter("constructionAddressName");
		 
		 if(constructionAddressName==null || constructionAddressName.trim().isEmpty()) {
			  message += "Construction address is required.<br>";
			   valid = false;
		 }
		 else if (constructionAddressName.length()>255) {
		     	message += "Construction Address characters exceed limit (255).<br>";
		 		valid = false;
		 	}
		 
		 String constructionPostcode= request.getParameter("constructionPostcode");
		 if (!constructionPostcode.matches("\\d{5}")||constructionPostcode == null) {
			    message += "Construction postcode must contain exactly 5 digits.<br>";
			    valid = false;
			}
		
		
		 
		 String chargeCostStr =request.getParameter("chargeCost");
		 double chargeCost = 0;

		 if (chargeCostStr == null || chargeCostStr.trim().isEmpty()) {

			    message += "Charge cost is required.<br>";
			    valid = false;
			
		 }
		 else {
		 try {

		     chargeCost = Double.parseDouble(request.getParameter("chargeCost"));

		 } catch (NumberFormatException e) {

		     message += "Charge cost must be a valid number and must use  . not ,<br>";
		     valid = false;
		 }
		 }
		 if (chargeCost < 0) {

			    message += "Charge cost cannot be negative.<br>";
			    valid = false;
			}
		 
		 
		 String paidChargeStr =request.getParameter("paidCharge");
		 double paidCharge = 0;
		 if (paidChargeStr == null || paidChargeStr.trim().isEmpty()) {

			    message += "Paid cost is required.<br>";
			    valid = false;
			
		 }
		 else {
		 try {

		     paidCharge = Double.parseDouble(request.getParameter("paidCharge"));

		 } catch (NumberFormatException e) {

		     message += "Paid charge must be a valid number and must use  . not ,<br>";
		     valid = false;
		 }
		 }
		 if (paidCharge < 0) {

			    message += "Paid charge cannot be negative.<br>";
			    valid = false;
			}
		 
		
		 
		 if (paidCharge > chargeCost) {

			 message += "Paid charge cannot exceed charge cost.<br>";
			    valid = false;
			}
		 
		 
		 String moreInfo=request.getParameter("moreInfo");
		 if (moreInfo.length()>255) {
		     	message += "Information characters exceed limit (255).<br>";
		 		valid = false;
		 	}
		
		 
		 if (!valid) {

			    request.setAttribute("message", message);

			    request.getRequestDispatcher("workForm.jsp") .forward(request, response);

			    return;
			}
		 
		 Address constructionAddress= new Address();
		 
		 constructionAddress.setName(constructionAddressName);
		 
		 constructionAddress.setPostcode(constructionPostcode);
		
		 
		 
		 Work work = new Work(workType,constructionAddress,chargeCost,paidCharge,moreInfo);
		
		 // Αν δεν ειχα το cascade θα χρειαζοταν και αυτη η εντολη τωρα εφοσον γινεται update  customer ροσθετεται και τοwork  workDao.saveWork(work);
		 customer.addWork(work);
		 customerDao.updateCustomer(customer);
		
		 RequestDispatcher dispatcher = request.getRequestDispatcher("logedinpage.jsp");
		 request.setAttribute("customer", customer);
		 dispatcher.forward(request, response);
		 
		 
	}
	
	private void showEditWorkForm(HttpServletRequest request, HttpServletResponse response)throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Work existingWork = workDao.getWorkbyId(id);
		
		 String customerId = request.getParameter("customerId");
         request.setAttribute("customerId", customerId);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WorkFromPage.jsp");
		request.setAttribute("work", existingWork);
		dispatcher.forward(request, response);

	}
	
	
	private void updateWork(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException,ServletException {
	
		String message = "";
		boolean valid = true;
		
		int id = Integer.parseInt(request.getParameter("workId"));
		Work work =workDao.getWorkbyId(id);
		
		int customerid=Integer.parseInt(request.getParameter("customerId"));
		Customer customer=customerDao.getCustomerbyId(customerid);
		
		String workType = request.getParameter("workType");
		 if(workType==null || workType.trim().isEmpty()) {
			 message += "Work type is required.<br>";
			  valid = false;

		 }
		 else if (workType.matches(".*\\d.*") || workType.length()<2 || workType.length()>30  ) {
		     	message += "Please insert a valid work type (Only Characters Allowed, between 2-30 characters)<br>";
		     	valid = false;
		     }
		

		String constructionAddressName=request.getParameter("constructionAddressName");
		 if(constructionAddressName==null || constructionAddressName.trim().isEmpty()) {
			  message += "Construction address is required.<br>";
			   valid = false;
		 }
		 else if (constructionAddressName.length()>255) {
		     	message += "Construction Address characters exceed limit (255).<br>";
		 		valid = false;
		 	}
		 
		String constructionPostcode=request.getParameter("constructionPostcode");
		if (!constructionPostcode.matches("\\d{5}")||constructionPostcode == null) {
		    message += "Construction postcode must contain exactly 5 digits.<br>";
		    valid = false;
		}
		
		
		
		//αρχικοποιω το chargecost καθως χρησιμοποιω try catch, ετσι ανδεν το κανω μπορει να μου πεταξει πως αν υπαρξει σφαλμα η μεταβλητη δεν θα παρει ποτε τιμη
		// κανω χρηση της μεθοδου parseDouble που ελεγχει απο μονη της αν ειναι σωστο το δεδομενο που δεχομαι οποτε προτιμαω το try catch απο το if για να μπορω να εμφανισω πιο ευκολα το μηνυμα μου
		 String chargeCostStr =request.getParameter("chargeCost");
		 double chargeCost = 0;

		 if (chargeCostStr == null || chargeCostStr.trim().isEmpty()) {

			    message += "Charge cost is required.<br>";
			    valid = false;
			
		 }
		 else {
		 try {

		     chargeCost = Double.parseDouble(request.getParameter("chargeCost"));

		 } catch (NumberFormatException e) {

		     message += "Charge cost must be a valid number and must use  . not ,<br>";
		     valid = false;
		 }
		 }
		 if (chargeCost < 0) {

			    message += "Charge cost cannot be negative.<br>";
			    valid = false;
			}
		 
		 
		 String paidChargeStr =request.getParameter("paidCharge");
		 double paidCharge = 0;
		 if (paidChargeStr == null || paidChargeStr.trim().isEmpty()) {

			    message += "Paid cost is required.<br>";
			    valid = false;
			
		 }
		 else {
		 try {

		     paidCharge = Double.parseDouble(request.getParameter("paidCharge"));

		 } catch (NumberFormatException e) {

		     message += "Paid charge must be a valid number and must use  . not ,<br>";
		     valid = false;
		 }
		 }
		 if (paidCharge < 0) {

			    message += "Paid charge cannot be negative.<br>";
			    valid = false;
			}
		 
		
		 
		 if (paidCharge > chargeCost) {

			 message += "Paid charge cannot exceed charge cost.<br>";
			    valid = false;
			}
		 
		 
		
		String moreInfo=request.getParameter("moreInfo");
		if (moreInfo.length()>255) {
	     	message += "Information characters exceed limit (255).<br>";
	 		valid = false;
	 	}
		
		
		if (!valid) {

		    request.setAttribute("message", message);

		    request.getRequestDispatcher("workForm.jsp") .forward(request, response);

		    return;
		}
	 
		
		
		
		
		Address constructionAddress=work.getConstructionAddress();
		constructionAddress.setName(constructionAddressName);
		constructionAddress.setPostcode(constructionPostcode);
		
		
		work.setWorkType(workType);
		work.setChargeCost(chargeCost);
		work.setConstructionAddress(constructionAddress);
		work.setPaidCharge(paidCharge);
		work.setMoreInfo(moreInfo);
		
		
		 workDao.updateWork(work);
		
		
		
		 RequestDispatcher dispatcher = request.getRequestDispatcher("logedinpage.jsp");
		 request.setAttribute("customer", customer);
		 dispatcher.forward(request, response);
		
		
		
	}
	

	
}


	
	
	


