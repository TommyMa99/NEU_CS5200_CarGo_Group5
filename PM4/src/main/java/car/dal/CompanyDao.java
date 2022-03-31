package car.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import car.model.Companies;
/**
 * The DAO for Company.
 * 
 * @author Jianqing Ma, Sen Yan, Bo Chen, Bingfan Tian, Qihui Liu, Kailun He
 *
 */
public class CompanyDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static CompanyDao instance = null;
	protected CompanyDao() {
		connectionManager = new ConnectionManager();
	}
	public static CompanyDao getInstance() {
		if(instance == null) {
			instance = new CompanyDao();
		}
		return instance;
	}
	
	/**
	 * Create Company Operation
	 * 
	 * @param company The compnay object to be created in sql.
	 * @return The company object
	 * @throws SQLException If sql connection has failed.
	 */
	public Companies create(Companies company) throws SQLException {
		String insertCompany =
				"INSERT INTO Companies(CompanyName,Description) " +
				"VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCompany,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, company.getCompanyName());
			insertStmt.setString(2, company.getDescription());
			insertStmt.executeUpdate();
				
				// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int companyId = -1;
			if(resultKey.next()) {
				companyId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			company.setCompanyId(companyId);;
			return company;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
			if(resultKey != null) {
				resultKey.close();
			}
		}
	}
	
	/**
	 * Read operation that reads a company by its ID.
	 * 
	 * @param companyId The ID of the company to be read.
	 * @return The read company.
	 * @throws SQLException If SQL connection has failed.
	 */
	public Companies getCompanyById(int companyId) throws SQLException {
		String selectCompany = "SELECT CompanyId,CompanyName,Description FROM Companies WHERE CompanyId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCompany);
			selectStmt.setInt(1, companyId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int resultcompanyId = results.getInt("CompanyId");
				String resultCompanyName = results.getString("CompanyName");
				String resultDescription = results.getString("Description");
				Companies company = new Companies(resultcompanyId,resultCompanyName, resultDescription);
				return company;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}
	
	/**
	 * Update Operation for Company.
	 * 
	 * @param company The company object to be updated.
	 * @param companyName The new companyName you want to update.
	 * @param Description The new Description you want to update.
	 * @return The updated company object.
	 * @throws SQLException If SQL connection has failed.
	 */
	public Companies updateCompanies(Companies company, String companyName, String Description) throws SQLException {
		String updateCompany = "UPDATE Companies SET CompanyName=?, Description=? WHERE CompanyId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateCompany);
			updateStmt.setString(1, companyName);
			updateStmt.setString(2, Description);
			updateStmt.setInt(3, company.getCompanyId());
			updateStmt.executeUpdate();
			company.setCompanyName(companyName);
			company.setDescription(Description);
			return company;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}
	
	/**
	 * Read operation that gets company by company name.
	 * 
	 * @param companyName The name of the company.
	 * @return the list of companies with the given company name.
	 * @throws SQLException If SQL connection failed.
	 */
	public List<Companies> getCompanyByCompanyName(String companyName) throws SQLException {
		List<Companies> companies = new ArrayList<Companies>();
		String selectCompany =
			"SELECT CompanyId, CompanyName, Description" +
			"FROM Companies" +
			"WHERE CompanyName=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCompany);
			selectStmt.setString(1, companyName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int resultCompanyId = results.getInt("CompanyId");
				String resultCompanyName = results.getString("CompanyName");
				String resultDescription = results.getString("Description");
				Companies c = new Companies(resultCompanyId,resultCompanyName,resultDescription);
				companies.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return companies;
	}
	
	/**
	 * Deletion operation for Company Object.
	 * 
	 * @param company The company to be deleted.
	 * @return null because the company object is deleted.
	 * @throws SQLException if Connection to SQL has failed.
	 */
	public Companies delete(Companies company) throws SQLException {
		String deleteCompany = "DELETE FROM Companies WHERE CompanyId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCompany);
			deleteStmt.setInt(1, company.getCompanyId());
			deleteStmt.executeUpdate();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
}
