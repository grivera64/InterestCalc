package calcinterest;
//allows for IO Exceptions (if file doesn't exist; input/output error)
import java.io.IOException;
//File class
import java.io.File;
//Scanner class
import java.util.Scanner;


public class CalcInterest
{
   
   //making the public scanner
   public static Scanner keyboard = new Scanner(System.in);	//create a local (to the class, it's a global) variable 
   
   public static void main(String[] args) throws IOException	//if there is no input/output, don't crash program
   {
      File input;  //name of the files
      Scanner inFile;   //declaring as Scanner, but not having file that is read name yet
      
      String fileName;  //the file name
      String fullName;
      float years, numCompounded;
      String codeString;
      char code = 'F';
      double depositAmt, interestRate, interestEarned, total;   //the outputs that will be taken out of the file
      
      System.out.print("Enter File name:\t");
      fileName = keyboard.nextLine();  //taking fileName from user
      input = new File(fileName);   //opening file
      if (!input.exists())	//checking if the file exists
      {
         System.out.print("\nCould not open file.  Program terminated.");
         System.exit(0);
      }
      
      inFile = new Scanner(input);  //scanner that reads from the file
      
      if (inFile.hasNext())	//checking if there is data on the file
      {
         System.out.println("\nName                Years  Deposit Amount   Interest Earned  Total ");
      }
      else
      {
         System.out.print("\nThere was no data in the file.\tProgram terminated.");
         System.exit(0);
      }
      
      while (inFile.hasNext())   //checking if the file (still) has (more) contents to read
      {							//this is done for every 2 lines of data on the file (must be even # of lines)
         fullName = inFile.nextLine();			//The file format:
         depositAmt = inFile.nextDouble();		//	Name
         years = inFile.nextFloat();			//	depositAmt years code
         codeString = inFile.nextLine();		//	...
         
         if (years >= 5)		//setting the interest rate based on the years variable
         {
            interestRate = 0.045;
         }
         else if (years >= 4)
         {
            interestRate = 0.04;
         }
         else if (years >= 3)
         {
            interestRate = 0.035;
         }
         else if (years >= 2)
         {
            interestRate = 0.025;
         }
         else if (years >= 1)
         {
            interestRate = 0.02;
         }
         else if (years >= 0)
         {
            interestRate = 0.015;
         }
         else
         {
            interestRate = -1000;
         }
         
        // System.out.println("DEBUG CODESTRING: " + codeString);
         
         for (int i = 0; i < codeString.length(); i++)	//checking for the location of the character and saving it
         {
            //System.out.print("CODE STRING " + i + ": " + codeString.charAt(i) + " ");
            if (!Character.isLetter(codeString.charAt(i)))
            {
               continue;
            }
            else
            {
               code = codeString.charAt(i);
               //System.out.println("\nDEBUG CODE: " + code + " at i = " + i);
               break;
            }
         }
         
         switch (code)	//checking numCoumpounded based on letter (not case-sensitive)
         {
            case 'A':
            case 'a':
               numCompounded = 4;
               break;
            case 'B':
            case 'b':
               numCompounded = 2;
               break;
            case 'C':
            case 'c':
               numCompounded = 1;
               break;
            case 'D':
            case 'd':
               numCompounded = 12;
               break;
            case 'E':
            case 'e':
               numCompounded = 365;
               break;
            default:
               numCompounded = -100000;
               break;
         }
         //System.out.println("DEBUG: NUMCOMPOUNDED: " + numCompounded);
         
         
         total = depositAmt * (Math.pow((1 + (interestRate / numCompounded)), (numCompounded * years)));//compound interest formula
         
         interestEarned = total - depositAmt;	//it's the added interest
         
         
         System.out.printf("%-20s%-7.2f$%-16.2f$%-15.2f$%-7.2f\n", fullName, years, depositAmt, interestEarned, total); //printing the data in one line
      
         
      }
      
      inFile.close();
   }
}
