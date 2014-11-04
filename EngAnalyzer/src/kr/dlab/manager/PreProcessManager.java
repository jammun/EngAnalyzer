package kr.dlab.manager;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import kr.dlab.biz.EngPoSTagger;
import kr.dlab.biz.TaggedLine;
import kr.dlab.util.ConfigLoader;
import kr.dlab.worker.PreProcessWorker;

import org.apache.log4j.Logger;

public class PreProcessManager {

	private static final Logger myLogger = Logger.getLogger(PreProcessManager.class);
	private static ConfigLoader cl = ConfigLoader.getInstance();
	
	private TotalManager totalManager;
	private ArrayList taggedList;
	private Arrays arrays;
	private EngPoSTagger posTagger = EngPoSTagger.getInstance();
	private int finishedCnt = 0;

	public PreProcessManager(TotalManager boss) throws IOException, InterruptedException {
		super();
		this.totalManager = boss;
		this.taggedList = new ArrayList();
		doJob();
	}

	/*
	 * 비즈 로직 처리하는 메소드
	 */
	public void doJob() throws IOException, InterruptedException {
		
		long startTime = System.nanoTime();
		long rawDataCnt = totalManager.getRawDataList().size();
		
		if ( rawDataCnt < 1 ) {
			return;
		}
		
		//if input file is CSV, save for header.
		if ( getTotalManager().isCSV()) rawDataCnt = rawDataCnt -1;
		
		System.out.println("rawDataCnt = " + rawDataCnt);
		
		//split rows per threads
		double rowsPerThread = Math.ceil(rawDataCnt / (float)cl.getProcessThreads());

		System.out.println(rawDataCnt + " / " + rowsPerThread);
		
		long[] idxs = new long[cl.getProcessThreads()];
		
		//if rawdata has header, start with 1
		long point = 0;
		
		for ( int i = 0; i < idxs.length; i++ ) {
			idxs[i] = point;
			point = point + (long)rowsPerThread;
		}
		
		PreProcessWorker[] threads = new PreProcessWorker[cl.getProcessThreads()];
		
		for ( int i = 0; i < threads.length; i++ ) {
			PreProcessWorker worker = new PreProcessWorker(this, "["+i+"]", idxs[i], (long)rowsPerThread-1, posTagger);
			threads[i] = worker;
			worker.start();
		}
		
		synchronized (this) {
			while ( finishedCnt < cl.getProcessThreads() ) {
				wait(1000);
			}
		}
		
		long sortBefore = System.nanoTime();
		//fastest sort
		Collections.sort(taggedList);
		long sortAfter = System.nanoTime();
		
		writeListToFile(taggedList);
		
		long endTime = System.nanoTime();
		
		myLogger.info("Process done. total " + convertSeconds(endTime-startTime) + " secs elapsed. data array " +
					taggedList.size() + " rows sort time was " + convertSeconds(sortAfter - sortBefore) + " secs.");
		
	}
	
	private long convertSeconds(long elpased) {
		return TimeUnit.SECONDS.convert(elpased, TimeUnit.NANOSECONDS);
	}
	
	
	
	public TotalManager getTotalManager() {
		return totalManager;
	}
	
	public ArrayList getTaggedList() {
		return taggedList;
	}
	
	public void addTagged(TaggedLine tl) {
		taggedList.add(tl);
	}
	
	public synchronized void notifyFinished() {
		finishedCnt++;
		notifyAll();
	}
	
	private void writeListToFile(ArrayList o ) {
		getTotalManager().writeListToFile(o);
	}
}
