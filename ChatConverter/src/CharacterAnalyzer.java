import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CharacterAnalyzer {
	private Map<Character,String> dictionary;
	private File file;
	private String text;
	public CharacterAnalyzer(String path, Map<Character, String> dictionary) throws IOException{
		file = new File(path);
		this.dictionary = dictionary;
		readText();
	}
	
	private void readText() throws IOException {
		FileReader reader = null;
		try {
			reader = new FileReader(this.file);
			StringBuilder builder = new StringBuilder();
			int item;
			while ((item = reader.read()) > -1) {
				Character symbol = (char) item;
				if(!isLetter(symbol.toString())){
					if(symbol == '\n'){
						symbol = ' ';
					} else if(symbol != ' '){
						continue;
					}
				}
				builder.append((char) item);
			}
			this.text = builder.toString();
		} finally {
			reader.close();
		}
	}
	
	public int wordCount(){
		return this.text.split("\\s+").length;
	}
	
	public void analyze(){
		getStatisticMap().entrySet().stream()
			.sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
			.limit(7)
			.forEach(System.out::println);
	}
	
	private Map<String, Integer> getStatisticMap() {
		Map<String, Integer> result = new HashMap<>();
		for (int i = 0; i < this.text.length(); i++) {
			String symbol = String.valueOf(this.text.charAt(i));
			if (isLetter(symbol)) {
				int value = 1;
				if (result.containsKey(symbol)) {
					value += result.get(symbol);
				}
				result.put(symbol, value);
			}
		}
		return result;
	}
	private boolean isLetter(String letter){
		return dictionary.containsValue(letter);
	}
}
