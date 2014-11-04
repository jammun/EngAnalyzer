package kr.dlab.manager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import kr.dlab.biz.TaggedLine;
import kr.dlab.util.ConfigLoader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.mozilla.universalchardet.UniversalDetector;

public class DataManager {

	//Config handler
	private static ConfigLoader cl = ConfigLoader.getInstance();
	//Logger
	private static final Logger myLogger = Logger.getLogger(DataManager.class);
	
	private List orgDataList;
	private List orgContentsList;
	private File orgDataFile;
	
	private String orgCharSet;
	
	public static final String FILE_EXT_TXT = "txt";
	public static final String FILE_EXT_CSV = "csv";
	public static final String FILE_EXT_EXCEL = "xls*";
	public static final String INPUT_FILE = "INPUT";
	
	private HashMap fileTypeMap = new HashMap();
	
	public DataManager() throws IOException {
		super();
		readLawData();
	}
	
	public DataManager(String[] args) throws IOException {
		super();
		readLawData(args);
	}
	
	private void readLawData() throws IOException {
		//raw data
		orgDataFile = new File(cl.getInputFile());
		orgCharSet = getFileEncoding(orgDataFile);
		
		if ( orgDataFile.getName().toLowerCase().endsWith(FILE_EXT_CSV) ) {
			fileTypeMap.put(INPUT_FILE, FILE_EXT_CSV);
		} else if ( orgDataFile.getName().toLowerCase().endsWith(FILE_EXT_TXT) ) {
			fileTypeMap.put(INPUT_FILE, FILE_EXT_TXT);
		} else if ( orgDataFile.getName().toLowerCase().endsWith(FILE_EXT_EXCEL) ) {
			fileTypeMap.put(INPUT_FILE, FILE_EXT_EXCEL);
		} else {
			fileTypeMap.put(INPUT_FILE, FILE_EXT_TXT);
		}
		readLawData(orgDataFile);
	}
	
	private void readLawData(String[] args) throws IOException {
		
		if ( args == null ) {
			readLawData();
			return;
		}
		//raw data
		orgDataFile = new File(args[0]);
		orgCharSet = getFileEncoding(orgDataFile);
		
		if ( orgDataFile.getName().toLowerCase().endsWith(FILE_EXT_CSV) ) {
			fileTypeMap.put(INPUT_FILE, FILE_EXT_CSV);
		} else if ( orgDataFile.getName().toLowerCase().endsWith(FILE_EXT_TXT) ) {
			fileTypeMap.put(INPUT_FILE, FILE_EXT_TXT);
		} else if ( orgDataFile.getName().toLowerCase().endsWith(FILE_EXT_EXCEL) ) {
			fileTypeMap.put(INPUT_FILE, FILE_EXT_EXCEL);
		} else {
			fileTypeMap.put(INPUT_FILE, FILE_EXT_TXT);
		}
		readLawData(orgDataFile);
	}
	
	private void readLawData(File f) {
		
		String fileType = (String)fileTypeMap.get(INPUT_FILE);
		
		if ( fileType.equals(FILE_EXT_CSV)) {
			readCSV(f);
		} else if ( fileType.equals(FILE_EXT_TXT)) {
			readTxt(f);
		} else if ( fileType.equals(FILE_EXT_EXCEL)) {
			//do nothing yet.
			myLogger.warn("only [txt,csv] file format supported.");
		} else {
			readTxt(f);
		}
	}
	
	private void readCSV(File f) {
		
		CSVParser parser = null;
		
		try {
			
			InputStreamReader isr = new InputStreamReader(new FileInputStream(f), orgCharSet);
			parser = new CSVParser(isr, CSVFormat.RFC4180);
			
			orgDataList = parser.getRecords();
			orgContentsList = new ArrayList();
			
			CSVRecord header = (CSVRecord)orgDataList.get(0);
			int columnsCnt = header.size();
			
			//StringBuffer for contents
			StringBuffer sb = new StringBuffer();
			
			//save for header
			for ( int i =1; i < orgDataList.size(); i++ ) {
				
				CSVRecord record = (CSVRecord)orgDataList.get(i);
				
				//merge comma seperated 
				for ( int j = columnsCnt-1; j < record.size(); j++ ) {
					String contentWithComma = (String)record.get(j);
					sb.append(contentWithComma);
				}
				orgContentsList.add(sb.toString());
				sb = new StringBuffer();
			}
			
//			System.out.println();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			myLogger.warn(e.getMessage());
		}	
	}
	
	private void readTxt(File f) {
		try {
			orgDataList = FileUtils.readLines(f, orgCharSet);
			orgContentsList = new ArrayList();
			
			for ( int i =0; i < orgDataList.size(); i++ ) {
				String content = (String)orgDataList.get(i);
				orgContentsList.add(content);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			myLogger.warn(e.getMessage());
		}	
	}
	
	public boolean isCSV() {
		return fileTypeMap.get(INPUT_FILE).equals(FILE_EXT_CSV);
	}
	
	public List getOrgDataList() {
		return orgDataList;
	}
	
	public List getOrgContentsList() {
		return orgContentsList;
	}

	public void writeData(List<TaggedLine>toData) throws IOException {
		
		String fileType = (String)fileTypeMap.get(INPUT_FILE);
		
		if ( fileType.equals(FILE_EXT_CSV)) {
			writeCSV(toData);
		} else if ( fileType.equals(FILE_EXT_TXT)) {
			writeTxt(toData);
		} else if ( fileType.equals(FILE_EXT_EXCEL)) {
			//do nothing yet.
		} else {
			writeTxt(toData);
		}
	}

	/**
	 * @param toData
	 * @throws IOException
	 */
	private void writeTxt(List<TaggedLine> toData) throws IOException {
		File outFile = new File(orgDataFile.getAbsolutePath() + "_out");
		BufferedWriter bw = new BufferedWriter(new FileWriter(outFile, false));

		for ( int i = 0; i < toData.size(); i++ ) {
			TaggedLine tl = (TaggedLine)toData.get(i);
			
			bw.write(tl.getTagged());
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}
	
	/**
	 * @param toData
	 * @throws IOException
	 */
	private void writeCSV(List<TaggedLine> toData) throws IOException {
		
		File outFile = new File(orgDataFile.getAbsolutePath() + "_out");
		BufferedWriter bw = new BufferedWriter(new FileWriter(outFile, false));
		CSVRecord header = (CSVRecord)orgDataList.get(0);
		int columnsCnt = header.size();
		
		writeCSVHeader(header, bw);
		bw.newLine();
		
		for ( int i = 0; i < toData.size(); i++ ) {
			
			CSVRecord csv = (CSVRecord)orgDataList.get(i+1); //cvs content row
			TaggedLine tl = (TaggedLine)toData.get(i); //pos tagged content
			
			//write before content column 
			for ( int j = 0; j < columnsCnt-1; j++ ) {
				String columnData = (String)csv.get(j);
				bw.write(columnData + ",");
			}
			bw.write(tl.getTagged());
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}
	
	private void writeCSVHeader(CSVRecord header, BufferedWriter bw) throws IOException {
		for ( int i = 0; i < header.size(); i++) {
			String columns = (String)header.get(i);
			bw.write(columns + ",");
		}
	}
	
	  public static String getFileEncoding(File f) throws java.io.IOException {
		  
		  if ( f == null ) return "";
		  
		    byte[] buf = new byte[4096];
		    FileInputStream fis = new java.io.FileInputStream(f.getAbsoluteFile());

		    // (1)
		    UniversalDetector detector = new UniversalDetector(null);

		    // (2)
		    int nread;
		    while ((nread = fis.read(buf)) > 0 && !detector.isDone()) {
		      detector.handleData(buf, 0, nread);
		    }
		    // (3)
		    detector.dataEnd();

		    // (4)
		    String encoding = detector.getDetectedCharset();
		    if (encoding != null) {
		      myLogger.info("automatic file encoding detected. encoding = " + encoding);
		    } else {
		      myLogger.info("No encoding detected.");
		    }

		    // (5)
		    detector.reset();
		    return encoding;
		  }
}
