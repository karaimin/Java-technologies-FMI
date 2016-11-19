import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

public class AlphabetConverter {
	private Map<Character, String> dictionary;
	private BufferedWriter writer;
	private boolean isFinished;
	private String path;
	
	public AlphabetConverter() throws IOException{
		try {
			fillDictionary();
			this.path = "C:\\Users\\i333653\\Desktop\\cplusplus\\translated.txt";
			File outputFile = new File(path);
			outputFile.createNewFile();
			clearOutputFile(outputFile);
			this.writer = new BufferedWriter(new FileWriter(outputFile));
			this.isFinished = false;
		} catch (IOException e) {
			closeOutputStream();
			throw new IOException(e);
		}
	}
	
	public String getFilePath(){
		return this.path;
	}
	
	public Map<Character,String> getDictionary(){
		return dictionary;
	}
	
	public void translate(InputStream readStream) throws IOException{
		InputStream readerForCheck = null;
		InputStreamReader readerForPerform = null;
		try {
			byte[] readedBytes = convertToByteArray(readStream);
			readerForCheck = new ByteArrayInputStream(readedBytes);
			readerForPerform = new InputStreamReader(new ByteArrayInputStream(readedBytes));
			if (isFinished(readerForCheck)) {
				this.isFinished = true;
				return;
			}
			int character;
			while ((character = readerForPerform.read()) != -1) {
				String convertedSymbol = convertSymbol((char)character);
				this.writer.write(convertedSymbol);
			}
			this.writer.newLine();
			this.writer.flush();
		} catch (IOException e) {
			closeOutputStream();
			throw new IOException("something went wrong while reading");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			readerForCheck.close();
			readerForPerform.close();
		}
	}
	
	public boolean isFinished(){
		return this.isFinished;
	}
	
	private String convertSymbol(Character shlyokovicaSymbol){
		String symbol = this.dictionary.get(shlyokovicaSymbol);
		if(symbol == null){
			symbol = shlyokovicaSymbol.toString();
		}
		return symbol;
	}
	
	private boolean isFinished(InputStream stream) throws IOException {
		try(Scanner scanner = new Scanner(stream)){
			return scanner.next().equals("kraj");
		}
	}
	
	private void closeOutputStream() throws IOException{
		if(writer != null){
			writer.close();
		}
	}
	private void clearOutputFile(File file) throws FileNotFoundException{
		new PrintWriter(file).close();
	}
	
	private byte[] convertToByteArray(InputStream stream) throws IOException {
		BufferedReader scanner = new BufferedReader(new InputStreamReader(stream));
		String line = scanner.readLine();
		return line.getBytes();
	}
	
	private void fillDictionary() throws FileNotFoundException, IOException{
		Properties prop = new Properties();
		try(InputStream reader = new FileInputStream(new File("Resources/alphabet1.properties"))) {
			prop.load(reader);
			dictionary = new HashMap<>();
			for(final String word : prop.stringPropertyNames()){
				dictionary.put(word.charAt(0), prop.getProperty(word));
			}
		}  
	}
	
}
