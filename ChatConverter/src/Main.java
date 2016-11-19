import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		try {
			
			AlphabetConverter converter = new AlphabetConverter();
			while(!converter.isFinished()){
				converter.translate(System.in);
			}
			CharacterAnalyzer analyzer = new CharacterAnalyzer(converter.getFilePath(), converter.getDictionary());
			System.out.println(analyzer.wordCount());
			analyzer.analyze();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			
		}
	}
}
