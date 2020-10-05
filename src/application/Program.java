package application;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Program {
	private final static String pathIFRN = "C:\\Users\\Pablo Paiva\\Desktop\\AUXILIO COMPARAÇÃO\\IFRN NOMES.txt";
	private final static String pathUFRN = "C:\\Users\\Pablo Paiva\\Desktop\\AUXILIO COMPARAÇÃO\\UFRN NOMES.txt";

	public static void main(String[] args) {
		try {
			List<Alune> IFRN = carregarAlunes(pathIFRN);
			List<Alune> UFRN = carregarAlunes(pathUFRN);

			IFRN.sort((a, b) -> a.getInicial() == b.getInicial() ? 0 : (a.getInicial() < b.getInicial() ? -1 : 1));
			UFRN.sort((a, b) -> a.getInicial() == b.getInicial() ? 0 : (a.getInicial() < b.getInicial() ? -1 : 1));
			
			Map<Alune, Alune> matchers = new HashMap<>();
			
			
			for (int i = 0; i < IFRN.size(); i++) {
				for (int j = 0; j < UFRN.size(); j++) {
					if(IFRN.get(i).hashCode() == UFRN.get(j).hashCode()) {
						matchers.put(IFRN.get(i), UFRN.get(j));
						System.out.println("-------------");
						System.out.println(IFRN.get(i) + "\n" + UFRN.get(j));
						System.out.println("-------------");
					}
				}
			}
			
			
		} catch (Exception e) {
			System.err.println("BASE ERROR");
			e.printStackTrace();
		}
	}

	private static List<Alune> carregarAlunes(String path)
			throws UnsupportedEncodingException, FileNotFoundException, IOException {
		List<Alune> alunos = new ArrayList<Alune>();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "Cp1252"))) {
			String line = br.readLine();

			while (line != null) {
				try {
					line = formatar(line);
					alunos.add(new Alune(line, line.charAt(0)));
				} catch (Exception e) {
				}
				line = br.readLine();
			}
		}

		return alunos;
	}

	private static String formatar(String line) {
		line = line.trim();
		line = line.toLowerCase();

		Matcher m = Pattern.compile("ç").matcher(line);
		line = m.replaceAll("c");
		m = Pattern.compile("ñ").matcher(line);
		line = m.replaceAll("n");

		m = Pattern.compile("[à-ã]").matcher(line);
		line = m.replaceAll("a");
		m = Pattern.compile("[è-ë]").matcher(line);
		line = m.replaceAll("e");
		m = Pattern.compile("[ì-ï]").matcher(line);
		line = m.replaceAll("i");
		m = Pattern.compile("[ò-ö]").matcher(line);
		line = m.replaceAll("o");
		m = Pattern.compile("[ù-ü]").matcher(line);
		line = m.replaceAll("u");

		return line;
	}

}
