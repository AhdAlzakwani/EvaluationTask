package evaluationPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import com.google.gson.stream.*;
import com.google.gson.Gson;

import evaluationPackage.Main;

public class Main {

	public static void main(String[] args) {
		final String path = "C:\\Users\\User009\\Desktop\\EvaluationTask\\EvaluationTaskFile.txt";
		Scanner userScanner = new Scanner(System.in);
		boolean menuExit = true;
		boolean SearchExit = true;
		List<UniverCity> univerCity;

		while (menuExit) {

			System.out.println("1- Consume API && Write Response in File ");
			System.out.println("2- Search Words");
			System.out.println("0- Exit Program");

			int option = userScanner.nextInt();
			switch (option) {
			case 1:
				try {

					URL url = new URL("http://universities.hipolabs.com/search");
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					conn.connect();
					StringBuilder universitiesinformation = new StringBuilder();
					int responseCode = conn.getResponseCode();
					if (responseCode != 200) {
						throw new RuntimeException("HttpresponseCode");

					} else {
						Scanner scanner = new Scanner(url.openStream());
						while (scanner.hasNext()) {
							universitiesinformation.append(scanner.nextLine());
						}
						scanner.close();
						System.out.println(universitiesinformation);

						Path pathFile = Paths.get(path);
						String infoString = universitiesinformation.toString();
						Files.writeString(pathFile, infoString, StandardCharsets.UTF_8);
						System.out.println("File Save Successfull ...");

							Gson gson = new Gson();

							UniverCity result = gson.fromJson(universitiesinformation.toString(), UniverCity.class);
							for (int k = 0; k < universitiesinformation.length(); k++) {

								System.out.println("User " + k);
								System.out.println(" ***************************** " + "|");
								System.out.println(
										"|" + "The Name Is : " + result.getCountry());
								System.out.println("|" + "The Cell Is : " + result.getDomains());
								System.out.println("|" + "The Email Is : " + result.getWeb_pages());
								System.out
										.println("|" + "The Gender Is : " + result.getAlpha_two_code());
								System.out.println("|" + "The Phone Is : " + result.getName());
								System.out.println("|" + " ***************************** " + "|");
							}

					}

				} catch (Exception e) {
					System.out.println(e);
				}

				break;
			case 2:

				Scanner sc1 = new Scanner(System.in);
				System.out.println(" Enter word to Search :");
				String word = sc1.next();
				boolean flag = false;
				int count = 0;

				Scanner scannerSearch = null;
				try {
					scannerSearch = new Scanner(new FileInputStream("C:\\Users\\User009\\Desktop\\EvaluationTask\\EvaluationTaskFile.txt"));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				while (scannerSearch.hasNextLine()) {
					String line = scannerSearch.nextLine();
					System.out.println(line);
					if (line.indexOf(word) != -1) {
						flag = true;
						count = count + 1;
					}
				}
				if (flag) {
					System.out.println("File contains the specified word");
					System.out.println("Number of occurrences is: " + count);
				} else {
					System.out.println("File does not contain the specified word");
				}

				break;

			case 0:
				System.out.println("Thank You");
				menuExit = false;
				SearchExit = false;
				break;

			default:
				System.out.println("Enter Between 0 to 2 Only");
			}
		}
		menuExit = false;

	}

}
