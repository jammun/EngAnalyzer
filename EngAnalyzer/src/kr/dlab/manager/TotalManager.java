package kr.dlab.manager;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import kr.dlab.util.ConfigLoader;


/*
 * TotalManager 는 전처리(Preprocessing), 모델링(Modeling), Indexing 업무를 총괄하는 Manager Class 입니다.
 */
public class TotalManager {

	//Config handler
	private static ConfigLoader cl = ConfigLoader.getInstance();
	private static final Logger myLogger = Logger.getLogger(TotalManager.class);

	private DataManager dataMan ;
	private PreProcessManager preMan ;
	
	public TotalManager() throws Exception {
		super();
		doJob();
	}
	
	public TotalManager(String[] args) throws Exception {
		super();
		doJob(args);
	}
	
	/*
	 * 비즈 로직 처리하는 메소드
	 */
	public void doJob(String[] args) {
		
		try {
			dataMan = new DataManager(args);
			preMan = new PreProcessManager(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void doJob() {
		doJob(null);
	}
	
	public List getRawDataList() {
		if ( dataMan == null ) return null;
		return dataMan.getOrgDataList();
	}
	
	public List getContentData() {
		if ( dataMan == null ) return null;
		return dataMan.getOrgContentsList();
	}	
	
	public boolean isCSV() {
		return dataMan.isCSV();
	}
	
	public void writeListToFile(List l) {
		if ( dataMan == null ) return;
		try {
			dataMan.writeData(l);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			myLogger.info("File write error : " + e.getMessage());
		}
	}

}
