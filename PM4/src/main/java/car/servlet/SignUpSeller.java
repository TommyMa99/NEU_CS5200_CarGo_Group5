package car.servlet;

import java.io.IOException;
import java.sql.SQLException;
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
 * Servlet implementation class SignUpSeller
 * @author xii12
 */
@WebServlet("/signUpSeller")
public class SignUpSeller extends HttpServlet {

	protected SellerDao sellerDao;
	protected UserDao userDao;
       

	@Override
	public void init() throws ServletException {
		sellerDao = SellerDao.getInstance();
		userDao = UserDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/signupseller/signupseller.jsp").forward(req, resp);
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
        req.setAttribute("zip", resultEmail);
		String resultAddress = req.getParameter("address");
        req.setAttribute("address", resultEmail);
		String resultIntroduction = req.getParameter("instroduction");
        req.setAttribute("instroduction", resultEmail);
		String resultCompanyId = req.getParameter("companyId");
        req.setAttribute("companyId", resultEmail);
        
        if (resultEmail == null || resultEmail.trim().isEmpty() ||
        	resultPassword == null || resultPassword.trim().isEmpty() ||
			resultFirstName == null || resultFirstName.trim().isEmpty() ||
			resultLastName == null || resultLastName.trim().isEmpty() ||
        	resultZip == null || resultZip.trim().isEmpty() ||
        	resultAddress == null || resultAddress.trim().isEmpty() ||
        	resultIntroduction == null || resultIntroduction.trim().isEmpty() ||
        	resultCompanyId == null || resultCompanyId.trim().isEmpty() ) {
            messages.put("success", "Please provide all required information");
        } else {
    		int resultZipInt = Integer.valueOf(resultZip);
    		int resultCompanyIdInt = Integer.valueOf(resultCompanyId);
    		Companies company = null;
			try {
				company = CompanyDao.getInstance().getCompanyById(resultCompanyIdInt);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			// User user = new User(resultFirstName, resultLastName, resultEmail, resultPassword);
			Sellers seller = new Sellers(company,resultZipInt, resultAddress, resultIntroduction,
					resultFirstName, resultLastName, resultEmail, resultPassword);
			
			try {
				// if email exists, throw the exception
				// user = userDao.create(user);

				messages.put("success", "Successfully created new user with email: " + resultEmail);
			
				seller = sellerDao.create(seller);
				messages.put("success", "Successfully created new seller with email: " + resultEmail);
			
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
        	
    		
        }
        req.getRequestDispatcher("/signupseller/signupseller.jsp").forward(req, resp);
    }
	

}
