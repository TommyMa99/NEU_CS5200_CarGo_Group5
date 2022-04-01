package car.dal;

public class SearchesDao{
	protected ConnectionManager connectionManager;

	private static SearchesDao instance = null;
	protected SearchesDao() {
		connectionManager = new ConnectionManager();
	}
	public static SearchesDao getInstance() {
		if(instance == null) {
			instance = new SearchesDao();
		}
		return instance;
	}
	
	
}