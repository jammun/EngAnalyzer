package kr.dlab.biz;

import java.io.Serializable;

import com.google.common.primitives.Ints;

public class TaggedLine implements Comparable, Serializable {
	
	private long rowIdx;
	private String tagged;
	
	public TaggedLine(long rowIdx, String tagged) {
		super();
		this.rowIdx = rowIdx;
		this.tagged = tagged;
	}

	public long getRowIdx() {
		return rowIdx;
	}

	public void setRowIdx(long rowIdx) {
		this.rowIdx = rowIdx;
	}

	public String getTagged() {
		return tagged;
	}

	public void setTagged(String tagged) {
		this.tagged = tagged;
	}

	@Override
	public int compareTo(Object o) {
		TaggedLine org = (TaggedLine)o;
//		Long l = new Long( getRowIdx() - org.getRowIdx());
		return  safeLongToInt(getRowIdx() - org.getRowIdx());
	}
	
	private int safeLongToInt(long l) {
	    return Ints.checkedCast(l);
	}
	
	public String toString() {
		return "[" + getRowIdx() + "]," + tagged;
	}
	
	
	
}
