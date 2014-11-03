package kr.dlab.biz;

import java.util.ArrayList;

public class TestArray {

	private String id;
	private ArrayList list;
	
	public TestArray(String id, ArrayList list) {
		super();
		this.id = id;
		this.list = list;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList getList() {
		return list;
	}

	public void setList(ArrayList list) {
		this.list = list;
	}
	
	public boolean equals(Object o) {
	    if(o == null)                return false;
	    if( !(o instanceof TestArray) ) return false;
	    
		TestArray remote = (TestArray)o;
		return getId().equalsIgnoreCase(remote.getId());		
	}
	
	public int hashCode() {
		return Integer.parseInt(getId());
	}
	
	public String toString() {
		return getId() + "|" + getList().toString();
	}
	
	
	
}
