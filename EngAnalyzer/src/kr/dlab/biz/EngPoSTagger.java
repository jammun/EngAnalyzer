package kr.dlab.biz;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import kr.dlab.util.ConfigLoader;

public class EngPoSTagger {
	
	private static EngPoSTagger myInstance;
	ConfigLoader cl = ConfigLoader.getInstance();
	MaxentTagger maxEntTagger;


	private EngPoSTagger() {
		super();
		setEngTagger();
	}
	
	public static EngPoSTagger getInstance() {
		if (myInstance == null ) {
			myInstance = new EngPoSTagger();
		}
		return myInstance;
	}
	
	public String posTag(String s) {
		return maxEntTagger.tagString(s);
	}
	
	private void setEngTagger() {
		String path = cl.getEngModel();
		maxEntTagger =  new MaxentTagger(path);
	}

}
