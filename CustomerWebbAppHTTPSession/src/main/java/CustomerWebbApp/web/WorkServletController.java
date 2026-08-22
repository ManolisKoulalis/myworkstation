package CustomerWebbApp.web;

import java.io.IOException;
import java.sql.SQLException;
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
import CustomerWebbApp.Model.Work;
import CustomerWebbApp.services.SessionUtil;



@WebServlet(urlPatterns = {
		"/addWork",
		"/insertWork",
	    "/deleteWork",
	    "/updateWork",
	    "/editWork",
	    "/workDetails"
	})
public class WorkServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	private CustomerDao customerDao;
	private WorkDao workDao;
	
	public void init() {
	       
		customerDao= new CustomerDao();
		workDao= new WorkDao();
	    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");

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
		
		/*ο ελεγχος εδω γινεται προκειμενου να δεις αν καποιος ειναι logedin , γιατι μπορει καποιος να γραψει χειροκινητα στο url http://localhost:8080/CustomerWebbApp/addWork 
		 * και να ανοιξει την σελιδα, βεβαια οταν θα πατησει το submit παλι θα γινει ο ελεγχος και δεν θα του ανοιξει
		 * αλλα για λογους ασφαλειας ειναι προτιμοτερο να μην μπορει να το ανοιξει καν
		 * */
		  if (!SessionUtil.isLoggedIn(request)) {
		        response.sendRedirect(request.getContextPath() + "/loginpage"
		        );
		        return;
		    }
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("insertWorkPage.jsp");
		dispatcher.forward(request, response);
		
	}
	
	
	
	public void deleteWork(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException,ServletException{
		
		
		/*Εδω παιρνουμε το work απο ενα session και οταν παιρνουμε το customer το κανουμε απο αλλο session με αποτελεσμα
		το work με πχ id=5 και το work που ειναι μεσα στο worklist με id=5 να μπορει να εχουν διαφορετικες αναφορες , ετσι οταν παω να κανω το remove(work) που συγκρινει με το equals
		αρα με αναφορες να μην μπορει πολλες φορες να βρει το σωστο work ετσι χρησιμοποιω το removeif για να επιλλεξω αυτο που εχει το ιδιο id και οχι την ιδια αναφορα */
		int id= Integer.parseInt(request.getParameter("workId"));
		
	
		//ελεγχω αν υπαρχει session και το id αν υπαρχουν συνεχιζω κανονικα διαφορετικα τους στελνω στον λογκιν
		  if (!SessionUtil.isLoggedIn(request)) {

		        response.sendRedirect(request.getContextPath() + "/loginpage" );
		        return;
		    }
		
		int customerid= SessionUtil.getLoggedCustomerId(request);
		// 4. Ελέγχω ότι το Work ανήκει στον logged-in customer
	    if (!workDao.workBelongsToCustomer(id, customerid)) {
	        response.sendError( HttpServletResponse.SC_FORBIDDEN,"You cannot delete this work.");
	        return;
	    }
		
		Customer customer= customerDao.getCustomerbyId(customerid);

		/* αν ηθελα να το κανω με lamda
		final int tempId = id;
		customer.getWorklist().removeIf(w -> w.getId() == tempId);
		*/
		List<Work> worklist=null;
		worklist = customer.getWorklist();
		
		Work worktoRemove = null;
		
		for (Work w : worklist) {
		    if (w.getId() == id) {
		        worktoRemove = w;
		        break;
		    }
		}
		// δεν κανω το remove γτ  μπορεί να πετάξει ConcurrentModificationException
		if (worktoRemove != null) {
		    worklist.remove(worktoRemove);
		}

		
		customerDao.updateCustomer(customer);
		
		//List<Work> worklist = customer.getWorklist();
		request.setAttribute("customer",customer);
		//request.setAttribute("worklist",worklist); 
		RequestDispatcher dispatcher = request.getRequestDispatcher("logedinpage.jsp");
		dispatcher.forward(request, response);
		
	}
	
	
	
	
	public void insertWork(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException,ServletException{
		
		if (!SessionUtil.isLoggedIn(request)) {
	        response.sendRedirect(request.getContextPath() + "/loginpage");
	        return;
	    }

		int customerId = SessionUtil.getLoggedCustomerId(request);
		
		 // Customer customer = customerDao.getCustomerbyId(customerId);
		
		 String message = "";
		 boolean valid = true;
		 
		 String workType=request.getParameter("workType");
		 if(workType==null || workType.trim().isEmpty()) {
			 message += "Work type is required.<br>";
			  valid = false;

		 }
		 else if (!workType.matches("[A-Za-zΑ-Ωα-ωΆ-Ώά-ώ\\s]{2,30}") ) {
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
		 if (constructionPostcode == null || !constructionPostcode.matches("\\d{5}")) {
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
		 if (moreInfo == null) {
			    moreInfo = "";
			}

			if (moreInfo.length() > 255) {
			    message += "Information characters exceed limit (255).<br>";
			    valid = false;
			}
		
		 
		 if (!valid) {

			    request.setAttribute("message", message);

			    request.getRequestDispatcher("insertWorkPage.jsp") .forward(request, response);

			    return;
			}
		 
		 Address constructionAddress= new Address();
		 
		 constructionAddress.setName(constructionAddressName);
		 
		 constructionAddress.setPostcode(constructionPostcode);
		
		 
		 
		 Work work = new Work(workType,constructionAddress,chargeCost,paidCharge,moreInfo);
		
		// Αν δεν ειχα το cascade θα χρειαζοταν και αυτη η εντολη τωρα εφοσον γινεται update  customer ροσθετεται και τοwork  workDao.saveWork(work);
		
		Customer customer= customerDao.addWorkToCustomer(customerId, work);
		customerDao.updateCustomer(customer);
		
		 RequestDispatcher dispatcher = request.getRequestDispatcher("logedinpage.jsp");
		 request.setAttribute("customer", customer);
		 dispatcher.forward(request, response);
		 
		 
	}
	
	private void showEditWorkForm(HttpServletRequest request, HttpServletResponse response)throws SQLException, ServletException, IOException {
		
		/*ο ελεγχος εδω γινεται προκειμενου να δεις αν καποιος ειναι logedin , γιατι μπορει καποιος να γραψει χειροκινητα στο url http://localhost:8080/CustomerWebbApp/addWork 
		 * και να ανοιξει την σελιδα, βεβαια οταν θα πατησει το submit παλι θα γινει ο ελεγχος και δεν θα του ανοιξει
		 * αλλα για λογους ασφαλειας ειναι προτιμοτερο να μην μπορει να το ανοιξει καν
		 * */
		if (!SessionUtil.isLoggedIn(request)) {
	        response.sendRedirect(request.getContextPath() + "/loginpage");
	        return;
	    }
		
		int customerId =SessionUtil.getLoggedCustomerId(request);
		
		
		
		 int id = Integer.parseInt(request.getParameter("workId"));
		
		  if (!workDao.workBelongsToCustomer(id, customerId)) {
		        response.sendError(HttpServletResponse.SC_FORBIDDEN,"This work does not belong to the logged-in customer." );
		        return;
		    }
		
		
		Work existingWork = workDao.getWorkbyId(id);
		
				
		RequestDispatcher dispatcher = request.getRequestDispatcher("insertWorkPage.jsp");
		request.setAttribute("work", existingWork);
		dispatcher.forward(request, response);

	}
	
	
	private void updateWork(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException,ServletException {
	
		String message = "";
		boolean valid = true;
		
		 // 1. Είναι logged in;
		 if (!SessionUtil.isLoggedIn(request)) {
		        response.sendRedirect( request.getContextPath() + "/loginpage" );
		        return;
		    }
			
		// 2. Ποιος customer είναι logged in;
		 int customerid = SessionUtil.getLoggedCustomerId(request);
		
		
		
		int id = Integer.parseInt(request.getParameter("workId"));
		Work work =workDao.getWorkbyId(id);
		
		//  Ελέγχω ότι το Work ανήκει στον logged-in customer
	    if (!workDao.workBelongsToCustomer(id, customerid)) {
	        response.sendError( HttpServletResponse.SC_FORBIDDEN,"You cannot update this work.");
	        return;
	    }
		
		
		String workType = request.getParameter("workType");
		 if(workType==null || workType.trim().isEmpty()) {
			 message += "Work type is required.<br>";
			  valid = false;

		 }
		 else if (!workType.matches("[A-Za-zΑ-Ωα-ωΆ-Ώά-ώ\\s]{2,30}") ) {
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
		if (constructionPostcode == null || !constructionPostcode.matches("\\d{5}")) {
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
		if (moreInfo == null) {
		    moreInfo = "";
		}

		if (moreInfo.length() > 255) {
		    message += "Information characters exceed limit (255).<br>";
		    valid = false;
		}
		
		
		if (!valid) {

		    request.setAttribute("message", message);

		    request.getRequestDispatcher("insertWorkPage.jsp") .forward(request, response);

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
		 Customer customer=customerDao.getCustomerbyId(customerid);
		
		
		
		 RequestDispatcher dispatcher = request.getRequestDispatcher("logedinpage.jsp");
		 request.setAttribute("customer", customer);
		 dispatcher.forward(request, response);
		
		
		
	}
	
	


	
}


	
	
	


