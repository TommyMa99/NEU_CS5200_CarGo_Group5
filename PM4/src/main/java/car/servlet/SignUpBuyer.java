package car.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import car.dal.*;
import car.model.*;

/**
 * Servlet implementation class SignUpBuyer
 * @author xii12
 */
@WebServlet("/signUpBuyer")
public class SignUpBuyer extends HttpServlet {

	protected BuyerDao buyerDao;
       

	@Override
	public void init() throws ServletException {
		buyerDao = BuyerDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/signupbuyer/signupbuyer.jsp").forward(req, resp);
	}

	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        String resultEmail = req.getParameter("email");
        req.setAttribute("email", resultEmail);
        String resultPassword = req.getParameter("password");
        req.setAttribute("password", resultEmail);
		String resultFirstName = req.getParameter("firstName");
        req.setAttribute("firstName", resultEmail);
		String resultLastName = req.getParameter("lastName");
        req.setAttribute("lastName", resultEmail);
		String resultZip = req.getParameter("zip");
        req.setAttribute("zip", resultZip);
		String resultDob = req.getParameter("dob");
        req.setAttribute("dob", resultDob);
        
        if (resultEmail == null || resultEmail.trim().isEmpty() ||
        	resultPassword == null || resultPassword.trim().isEmpty() ||
			resultFirstName == null || resultFirstName.trim().isEmpty() ||
			resultLastName == null || resultLastName.trim().isEmpty() ||
        	resultZip == null || resultZip.trim().isEmpty() ||
        	resultDob == null || resultDob.trim().isEmpty()) {
            messages.put("success", "Please provide all required information");
        } else {
    		int resultZipInt = Integer.valueOf(resultZip);
    		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    		Date resultDobDate;
			try {
				resultDobDate = formatter.parse(resultDob);
				try {
					// if email exists, throw the exception
					Buyer buyer = new Buyer(resultDobDate,resultZipInt, resultFirstName, resultLastName, resultEmail, resultPassword);
					buyer = buyerDao.create(buyer);
					messages.put("success", "Successfully created new buyer with email: " + resultEmail);
				} catch (SQLException e) {
					e.printStackTrace();
					throw new IOException(e);
				}
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			
        	
    		
        }
        req.getRequestDispatcher("/signupbuyer/signupbuyer.jsp").forward(req, resp);
    }
	

}
