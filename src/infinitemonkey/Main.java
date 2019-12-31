package infinitemonkey;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import infinitemonkey.DNA_Pool;

public class Main {
	
	static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args)
	{
		DNA_Pool pool;
		
		while(true)
		{
			System.out.println("Press 1 to enter a string.\nPress 2 to read from file.\n");
			int choice = scanner.nextInt();
			String text = "";
	
			if(choice == 1)
			{
				System.out.println("Please enter a string:\n");
				scanner.nextLine();
				text = scanner.nextLine();
				
			}
			
			else if(choice == 2)
			{
				System.out.println("Ensure that file is in 'texts' folder at root directory.\n");
				System.out.println("Please enter the filename: ");
				String fileName = scanner.next();
				StringBuilder builderText = new StringBuilder();
	
				FileReader reader = null;
	
				try
				{
					reader = new FileReader("texts/" + fileName);
					
				}
				catch(IOException ioe)
				{
					System.out.println("ERROR: FILE NOT FOUND!");
				}
				
				BufferedReader buffer = new BufferedReader(reader);
				try
				{
					String line;
					while((line = buffer.readLine()) != null)
					{
						builderText.append(line + " ");
					}
				}
				catch(IOException ioe)
				{
					System.out.println("ERROR: PROBLEM WITH BUFFER!");
				}
				
				try
				{
					reader.close();
				}
				catch(IOException ioe)
				{
					System.out.println("ERROR: PROBLEM CLOSING READER!");
				}
				
				text = builderText.toString();
			}
			
			pool = new DNA_Pool(100,text);
			System.out.println("DNA Pool initiliased.\nPress any key to contine.\n");
			scanner.nextLine();
			pool.loopUntilTargetMet();
			
			System.out.println("\n");
		}
	}
	
	

}
