package car.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import car.dal.CarDao;
import car.dal.SellerDao;
import car.dal.UserDao;
import car.model.Cars;
import car.model.Sellers;
import car.model.User;


/**   
 * @author: Bingfan Tian  
 * @date: 2022.04.01 
 */
@WebServlet("/profile/posts")
public class AccountPostTest extends HttpServlet {
	
	protected UserDao userDao;
//	protected Mess
	
	@Override
	public void init() throws ServletException {
		userDao = UserDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
//        Map<String, String> messages = new HashMap<String, String>();
//        req.setAttribute("messages", messages);
//        List<Cars> cars = new ArrayList<Cars>();
//        
//        String resultUserId = req.getParameter("userId");
//        if(resultUserId == null || resultUserId.trim().isEmpty()){
//        	messages.put("success", "Invalid UserID number");
//        } else {
//	        try {
//	        	cars = savesDao.getCarsFromSavesByUserId(Integer.valueOf(resultUserId));
//	        	User cur_User = userDao.getUserByUserId(Integer.valueOf(resultUserId));
//				if(cur_User == null) {
//	        		messages.put("success", "UserID does not exist.");
//	        	} else {
//	        		req.setAttribute("cars", cars);
//	        	}
//	        	messages.put("success", " Saves car list for user: " + resultUserId);
//	        } catch (SQLException e) {
//				e.printStackTrace();
//				throw new IOException(e);
//	        }
//        }     
        req.getRequestDispatcher("/profile/accountposts.jsp").forward(req, resp);
	}
	
}
