package kr.dlab.worker;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import kr.dlab.biz.EngPoSTagger;
import kr.dlab.biz.TaggedLine;
import kr.dlab.manager.PreProcessManager;

public class PreProcessWorker extends Thread {

	private List rawList;
	private long myStartNo;
	private long quota;
	private long myEndNo;
	private PreProcessManager myManager ;
	private Pattern keywordsPattern;
	private EngPoSTagger posTagger;
	
	public PreProcessWorker(PreProcessManager boss, String name, long startIdx, long quota, EngPoSTagger tagger) {
		this.myManager = boss;
		this.rawList = myManager.getTotalManager().getContentData();
		this.myStartNo = startIdx;
		this.quota = quota;
		this.myEndNo = myStartNo + quota;
		this.posTagger = tagger;
		
		if ( myEndNo >= rawList.size() ) {
			myEndNo = rawList.size() -1;
//			myEndNo = rawList.size();
		}
	}
	

	/**
	 * @override
	 */	
	public void run() {

		System.out.println("AnayzeWorker" + getName() + " List size : " + rawList.size() + " start " + myStartNo +"-"+ myEndNo + " rows ");
		
		//"AnayzeWorker [0] start 0-24 rows.";
		
		for ( long i = myStartNo; i <= myEndNo; i++) {
			Object record = rawList.get((int)i);
			String line = (String)record;
			String tagged = posTagger.posTag(line);
//			System.out.println(line);
			
			myManager.addTagged(new TaggedLine(i, tagged));
		}
		myManager.notifyFinished();
	}
	
	
	public List getRawList() {
		return rawList;
	}



	public void setRawList(List rawList) {
		this.rawList = rawList;
	}



	public long getMyStartNo() {
		return myStartNo;
	}



	public void setMyStartNo(long myStartNo) {
		this.myStartNo = myStartNo;
	}



	public long getQuota() {
		return quota;
	}



	public void setQuota(long quota) {
		this.quota = quota;
	}

}
