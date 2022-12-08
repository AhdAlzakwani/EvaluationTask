package evaluationPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;



public class Main {

	public static void main(String[] args) throws IOException {
		final String path = "C:\\Users\\User009\\Desktop\\EvaluationTask\\EvaluationTaskFile.txt";
		final String pdfpath = "C:\\Users\\User009\\Desktop\\EvaluationTask\\EvaluationTaskFile.pdf";
		Scanner userScanner = new Scanner(System.in);
		boolean menuExit = true;
		boolean SearchExit = true;
		boolean searchExisting = true;
		Stack<String> userInputList = new Stack<>();
		Set<String> UserInputset1 = new HashSet<>();

		while (menuExit) {

			System.out.println("1- Consume API && Write Response in File ");
			System.out.println("2- Search Words");
			System.out.println("3- User Searching(Txt or Pdf)");
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

					}

				} catch (Exception e) {
					System.out.println(e);
				}
				break;

			case 2:
				while (SearchExit) {
					File dir = new File("C:\\Users\\User009\\Desktop\\EvaluationTask");
					FilenameFilter textFilter = new FilenameFilter() {
						public boolean accept(File dir, String name) {
							return name.toLowerCase().endsWith(".txt");
						}
					};
					File[] files = dir.listFiles(textFilter);
					for (File file : files) {
						if (file.isDirectory()) {
							System.out.print("directory:");
						} else {
							System.out.print("     file:");
						}
						try {
							System.out.println(file.getCanonicalPath());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					File f1 = new File("C:\\Users\\User009\\Desktop\\EvaluationTask\\EvaluationTaskFile.txt");
					Scanner scanner = new Scanner(f1);
					Path pathFile = Paths.get(path);
					while (scanner.hasNextLine())

					{
						Files.writeString(pathFile, scanner.nextLine().replaceAll("\\W", " "), StandardCharsets.UTF_8);
					}

					String[] words = null;
					FileReader fr = null;
					try {
						fr = new FileReader(f1);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					BufferedReader br = new BufferedReader(fr);
					String s;
					System.out.println("Please Write word to be Search :");

					String input = userScanner.next();
					int count = 0;
					UserInputset1.add(input);
					System.out.println(UserInputset1.toString());
					try {
						while ((s = br.readLine()) != null) {
							words = s.split(" ");

							for (File c : files) {
								for (String word : words) {
									if (word.equals(input)) {

										count++;

									}
								}
							}
						}

						if (count != 0) {
							System.out.println("The given word is present for " + count + " Times in the file");
							System.out.println("File moved successfully ........");

						} else {
							System.out.println("The given word is not present in the file");
						}

						System.out.println("If You want to Continue Searching press 1 , If Not Press 0");
						int search = userScanner.nextInt();
						if (search == 0) {
							SearchExit = false;
						} else {
							SearchExit = true;
						}

						fr.close();

					} catch (IOException e) {

						e.printStackTrace();
					}

				}
				SearchExit = false;

				break;

			case 3:
				System.out.println("Do Yow want to Serch in txt Or pdf ? if txt press 1 if pdf press 2 ");
				int userSearch = userScanner.nextInt();
				if (userSearch == 1) {
					File dir = new File("C:\\Users\\User009\\Desktop\\EvaluationTask");
					FilenameFilter textFilter = new FilenameFilter() {
						public boolean accept(File dir, String name) {
							return name.toLowerCase().endsWith(".txt");
						}
					};
					File[] files = dir.listFiles(textFilter);
					for (File file : files) {
						if (file.isDirectory()) {
							System.out.print("directory:");
						} else {
							System.out.print("     file:");
						}
						try {
							System.out.println(file.getCanonicalPath());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					File f1 = new File("C:\\Users\\User009\\Desktop\\EvaluationTask\\EvaluationTaskFile.txt");
					Scanner scanner = new Scanner(f1);
					Path pathFile = Paths.get(path);
					while (scanner.hasNextLine())

					{
						Files.writeString(pathFile, scanner.nextLine().replaceAll("\\W", " "), StandardCharsets.UTF_8);
					}

					String[] words = null;
					FileReader fr = null;
					try {
						fr = new FileReader(f1);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					BufferedReader br = new BufferedReader(fr);
					String s;
					System.out.println("Please Write word to be Search :");

					String input = userScanner.next();
					int count = 0;
					UserInputset1.add(input);
					System.out.println(UserInputset1.toString());
					try {
						while ((s = br.readLine()) != null) {
							words = s.split(" ");

							for (File c : files) {
								for (String word : words) {
									if (word.equals(input)) {

										count++;

									}
								}
							}
						}

						if (count != 0) {
							System.out.println("The given word is present for " + count + " Times in the file");
							System.out.println("File moved successfully ........");

						} else {
							System.out.println("The given word is not present in the file");
						}

						System.out.println("If You want to Continue Searching press 1 , If Not Press 0");
						int search = userScanner.nextInt();
						if (search == 0) {
							SearchExit = false;
						} else {
							SearchExit = true;
						}

						fr.close();

					} catch (IOException e) {

						e.printStackTrace();
					}

				}
				if (userSearch == 2) {
					

						boolean isExit = true;

						while (isExit) {

						Scanner sc = new Scanner(System.in);

						System.out.println("**********************************");
						// Reading the word to be found from the user
						System.out.println("Enter the word you want to search pdf ");
						String input = sc.next();

						// consuming the <enter> from input above
						sc.nextLine();
						boolean word2 = false;
						int wordCount = 0;

						// Reading the String of the file
						Scanner sc1 = new Scanner(new FileInputStream("sample.pdf"));
						while (sc1.hasNextLine()) {
						String word1 = sc1.nextLine();
//						     System.out.println(line);
						if (word1.indexOf(input) != -1) {
						word2 = true;
						wordCount = wordCount + 1;
						}
						}
						if (word2) {
						System.out.println("PDF File contains the  word");
						System.out.println("Number of word is: " + wordCount);
						System.out.println("__________________________________");

						} else {
						System.out.println("PDF File does not contain the word");
						System.out.println("__________________________________");

						}
						System.out.println("if you want to search another word press 1 and 2 to exit ");
						Integer s = sc.nextInt();
						if (s == 1) {
						isExit = true;
						} else if (s == 2) {
						isExit = false;
						System.out.println("Good Bye");
						}

						}
						


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
