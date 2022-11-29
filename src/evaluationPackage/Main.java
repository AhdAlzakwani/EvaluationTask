package evaluationPackage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

import com.google.gson.Gson;
import evaluationPackage.Main;


public class Main {

	public static void main(String[] args) throws IOException {
		final String path = "C:\\Users\\User009\\Desktop\\EvaluationTask\\EvaluationTaskFile.txt";
		Scanner userScanner = new Scanner(System.in);
		boolean menuExit = true;
		boolean SearchExit = true;
		Stack<String> userInputList = new Stack<>();
		Set<String> UserInputset1 = new HashSet<>();
		


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

//							Gson gson = new Gson();
//
//							Executer result = gson.fromJson(universitiesinformation.toString(), Executer.class);
//							for (int k = 0; k < 2; k++) {
//
//								System.out.println("User " + k);
//								System.out.println(" ***************************** " + "|");
//								System.out.println(
//										"|" + "Country : " + result.getUniverCity().get(0).getCountry());
//								System.out.println("|" + "Domains : " + result.getUniverCity().get(0).getDomains());
//								System.out.println("|" + " Web_pages : " + result.getUniverCity().get(0).getWeb_pages());
//								System.out
//										.println("|" + "Alpha_two_code : " + result.getUniverCity().get(0).getAlpha_two_code());
//								System.out.println("|" + "Name : " + result.getUniverCity().get(0).getName());
//								System.out.println("|" + " ***************************** " + "|");
							//}

					}

				} catch (Exception e) {
					System.out.println(e);
				}

				break;
			case 2:
				 while(SearchExit) {
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
					 
				        File f1=new File("C:\\Users\\User009\\Desktop\\EvaluationTask\\EvaluationTaskFile.txt"); 
				        Scanner scanner = new Scanner(f1);
				    	Path pathFile = Paths.get(path);
				   	 while(scanner.hasNextLine())
		 					
		 				{

		                 System.out.println(scanner.next().replaceAll("[^a-zA-Z]*", ""));
		                	Files.writeString(pathFile, scanner.next().replaceAll("[^a-zA-Z]*", ""), StandardCharsets.UTF_8);

		 				}
				 
					      String[] words=null;  
					      FileReader fr = null;
						try {
							fr = new FileReader(f1);
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						} 
					      BufferedReader br = new BufferedReader(fr); 
					      String s;     
					      System.out.println("Please Write word to be Search :");

					      String input=userScanner.next();   
					      int count=0;  
					      UserInputset1.add(input);
					      System.out.println(UserInputset1.toString());
					      try {
							while((s=br.readLine())!=null)   
							  {
							     words=s.split(" "); 
							     
							     for(File c: files) {
							      for (String word : words) 
							      {
							             if (word.equals(input))   
							             {
							            
                                         
							               count++;    
							             
							             }    
							      }
							     }
							  }

							if(count!=0)  
						      {
						         System.out.println("The given word is present for "+count+ " Times in the file");
						         System.out.println("File moved successfully ........");

						         
						      }
						      else
						      {
						         System.out.println("The given word is not present in the file");
						      }
							
							System.out.println("If You want to Continue Searching press 1 , If Not Press 0");
					         int search = userScanner.nextInt();
					         if(search == 0)
					         {
					        	 SearchExit = false;
					         }
					         else {
					        	 SearchExit = true;
					         }
					         
					         fr.close();
							
						} catch (IOException e) {
						
							e.printStackTrace();
						}
				        
				        
				        
				        
				        
				 }SearchExit = false;
				 
				

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
