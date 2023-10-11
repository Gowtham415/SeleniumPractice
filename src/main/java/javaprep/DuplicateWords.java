package javaprep;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DuplicateWords {
	
	public static void main(String[] args) {
		String s = "This is a new addiction and this is a new addiction world of addiction";
		duplicateStrings(s);
	}

	private static void duplicateStrings(String s) {
		String[] words = s.split("\\s");
		
		Map<String,Integer> wordMap = new HashMap<>();
		
		Arrays.asList(words).stream()
			.forEach(word->wordMap.put(word, wordMap.getOrDefault(word, 0)+1));
		
		wordMap.entrySet().stream().forEach(entry-> System.out.println(entry.getKey()+" : "+entry.getValue()));
		
	}

}
