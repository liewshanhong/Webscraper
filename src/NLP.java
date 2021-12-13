import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.stanford.nlp.simple.*;

public class NLP {
	public static void main(String[] args) throws IOException {
		Scanner scn = new Scanner(new File("jobDescriptions/OutSales.txt"));
		FileWriter f = new FileWriter("test.txt");
		List<String> input = new ArrayList<String>();
		List<String> sk = new ArrayList<String>();
		// reads the job postings from the file
		while (scn.hasNextLine()) {
			input.add(scn.nextLine() + " ");
		}
		
		// performs nlp on string
		// sentence splits
		// tokenizes
		// ner
		// pos tagging
		//lemmatizes 1139
		for (int i = 0; i < 1; i++) {
			System.out.println(i);
			Document d = new Document(input.get(i).toString());
			for (Sentence s : d.sentences()) {
				for (int w = 0; w < s.words().size(); w++) {
					String post = s.posTag(w);
					String ner = s.nerTag(w);
					String word = s.word(w);
					if ((post.equals("NNP") || post.equals("NNP") || post.equals("VBG")) && ner.equals("O")) {
						System.out.println("NER: " + ner + ", WORD: " + word + " ,TYPE: " + post);
						if (!word.equals("working") || !word.equals("COVID") || !word.equals("19")) {
							sk.add(s.lemma(w));
						}
					}
					 else {
							sk.add("NAN");
						}
				}
			}
		}
		
		for(int i = 0; i < sk.size();i++) {
			if(!sk.get(i).equals("NAN")) {
				String temp = "";
				for(int j = i; j < sk.size();j++) {
					if(!sk.get(j).equals("NAN")) {
						temp+=sk.get(j) + " ";
						i++;
					}
					else {
						break;
					}
				}
				System.out.println(temp);
				f.write(temp + "\n");
			}
		}
		f.close();
	}

}
