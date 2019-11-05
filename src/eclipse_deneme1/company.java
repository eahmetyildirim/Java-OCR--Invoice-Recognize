package eclipse_deneme1;

public class company {
	private int id;
	private String name;
	private String search_text;
	public company(int id,String name,String search_text) {
		this.id=id;
		this.name=name;
		this.search_text=search_text;
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
	public String getSearch_text() {
		return search_text;
	}
	public void setSearch_text(String search_text) {
		this.search_text = search_text;
	}
	
	
	
}
