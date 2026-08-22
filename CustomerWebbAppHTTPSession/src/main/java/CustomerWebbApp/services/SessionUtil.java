package CustomerWebbApp.services;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



public class SessionUtil {

    // 1. Ελέγχει αν υπάρχει ενεργό login session
    public static boolean isLoggedIn(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        return session != null && session.getAttribute("loggedCustomerId") != null;
    }


    // 2. Επιστρέφει το id του logged-in customer
    public static Integer getLoggedCustomerId(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session == null) {
            return null;
        }

        return (Integer) session.getAttribute("loggedCustomerId");
    }


    
}