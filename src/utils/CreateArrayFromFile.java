package utils;

import java.io.*;
import java.util.*;

public class CreateArrayFromFile {
	private String fileName;
	private String[] file;

	public CreateArrayFromFile(String fileName) throws IOException {
		this.fileName = fileName;
		file = new String[countLines()];
		makeArray();
	}

	private int countLines() throws IOException {
		int count = 0;
		File input = new File(fileName);
		Scanner words = new Scanner(input);
		while (words.hasNextLine() == true) {
			@SuppressWarnings("unused")
			String line = words.nextLine();
			count++;
		}
		words.close();
		return count;
	}

	private void makeArray() throws IOException {
		int i = 0;
		File input = new File(fileName);
		Scanner word = new Scanner(input);
		while (word.hasNextLine()) {
			file[i] = word.nextLine();
			i++;
		}
		word.close();
	}

	public String[] getArray() {
		return file;
	}

	public static void main(String[] args) throws IOException {
	}
}
