package services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Clean {

	public static void main(String[] args) throws InterruptedException {
		String pathIn = "C:\\Users\\Pablo Paiva\\Desktop\\AUXILIO COMPARAÇÃO\\UF\\UFRN BRUTO.txt";
		String pathOut = "C:\\Users\\Pablo Paiva\\Desktop\\AUXILIO COMPARAÇÃO\\UF\\UFRN NOMES.txt";
		BufferedReader br = null;
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathOut))) {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(pathIn), "UTF-8"));
			String line = br.readLine();
			while (line != null) {
				Matcher m = Pattern.compile("([\\sA-Za-zÀ-ü]+)$").matcher(line.trim());
				String match = "";
				while (m.find()) {
					String value = m.group();
					match = value.equals("") ? match : value;
				}
				
				try {
					System.out.println(match);
					bw.write(match);
				} catch (Exception e) {
					System.out.println("---ERROR---");
					bw.write("---ERROR---");
				}
				
				bw.newLine();
				line = br.readLine();
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
