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


@WebServlet("/save")
public class SaveCar extends HttpServlet{

	protected CarDao carDao;
	protected UserDao userDao;
	protected SavesDao savesDao;	
	protected BuyerDao buyerDao;
	@Override
	public void init() throws ServletException {
		carDao = CarDao.getInstance();
		userDao = UserDao.getInstance();
		savesDao = SavesDao.getInstance();
		buyerDao = BuyerDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/search/search.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        String resultVin = req.getParameter("vin");
        String resultUserId = req.getParameter("buyerId");
        if (resultVin == null || resultVin.trim().isEmpty()) {
            messages.put("success", "Invalid Vin number");
        }else if(resultUserId == null || resultUserId.trim().isEmpty()){
        	messages.put("success", "Invalid UserID number");
        } else {
	        try {
	        	Cars cur_Car = carDao.getCarByVin(resultVin);
	        	Buyer cur_Buyer = buyerDao.getBuyerByUserId(Integer.valueOf(resultUserId));
	        	Saves newSave = new Saves(cur_Car, cur_Buyer);
	        	newSave = savesDao.create(newSave);
	        	messages.put("success", "Successfully save car with vin: " + resultVin);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }     
        req.getRequestDispatcher("/search/search.jsp").forward(req, resp);
    }
	
}
