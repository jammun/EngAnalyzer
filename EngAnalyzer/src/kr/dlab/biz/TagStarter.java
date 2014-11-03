package kr.dlab.biz;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import kr.dlab.manager.TotalManager;
import kr.dlab.util.ConfigLoader;

public class TagStarter {

	public static void main(String[] args) throws Exception {
		
		//System.out.println("arg length : " + args.length);
		
		if ( args.length >= 1 ) {
			new TotalManager(args);
		} else {
			System.out.println("usage :  engpostagger \"input text file\" [enter]");
			System.exit(1);
			//new TotalManager();
		}
	}

}
