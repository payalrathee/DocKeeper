package beans;

public class Doc {

	private int id;
	private String name;
	private String url;
	private String note;
	private int userId;
	
	public Doc(int id, String name, String url, String note, int userId) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
		this.note = note;
		this.userId = userId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
}
