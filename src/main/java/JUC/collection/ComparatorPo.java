package JUC.collection;

public class ComparatorPo  {

	/**
	 * @author JUC
	 * void
	 */
	private int id;
	

	private String str;
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getStr() {
		return str;
	}


	public void setStr(String str) {
		this.str = str;
	}

	public ComparatorPo(int id, String str) {
		super();
		this.id = id;
		this.str = str;
	}


	@Override
	public String toString() {
		return "ComparatorPo [id=" + id + ", str=" + str + "]";
	}

/*
	@Override
	public int compareTo(ComparatorPo o) {
		// TODO Auto-generated method stub	
		return this.id-o.getId();
	//	return this.getStr().compareTo(o.getStr());
		
		
		 if (this.getName().compareTo(o.getName()) != 0)  
	            return this.getName().compareTo(o.getName());  

	}*/

}
