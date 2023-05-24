package Experiment._230523.fileReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileReader extends java.io.FileReader {
	public FileReader(String fileName) throws FileNotFoundException {
		super(fileName);
	}
	static String read(String fileName) {
		String path = FileReader.class.getResource(FileReader.class.getSimpleName() + ".class").getPath();
        String directory = path.substring(0, path.lastIndexOf("/")).replace("/", "\\").replace("bin", "src");
		String text = "";
		try(var fileReader = new FileReader(directory + "\\" + fileName); var bufferedReader = new BufferedReader(fileReader)) {
			String line;
			while((line = bufferedReader.readLine()) != null) text += line + "\n";
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		return text;
	}
}
