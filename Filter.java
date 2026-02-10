/*
   Course: CS 33600
   Name: Dimitri Josifoski
   Email: djosifos@purdue.edu
   Assignment: 2
*/

import java.util.Scanner;
import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
   This program filters.

   When playing with file filters, it is useful to know that "end-of-file" is
   denoted at the command-line in Windows by Ctrl-Z and by Ctrl-D in Linux.
*/
public class Filter
{
   public static void main(String[] args)
   {
      final Scanner in = new Scanner(System.in);

      int columns = 3;    // Default number of columns.
      int precision = 13; // Default number of digits after a decimal point.
      int groups = 0;     // Default size of a group. Zero means no groups.


      // Check for a properties file and override defaults
      final Properties properties = new Properties();

      try (var fis = new FileInputStream(
                        new File("filter.properties")))
      {
         // Read all the property key/value pairs.
         properties.load(fis);

         // Check if the properties exist and override defaults
         columns = properties.getProperty("columns") != null ? Integer.parseInt(properties.getProperty("columns")) : columns;
         precision = properties.getProperty("precision") != null ? Integer.parseInt(properties.getProperty("precision")) : precision;
         groups = properties.getProperty("groups") != null ? Integer.parseInt(properties.getProperty("groups")) : groups;
         
      }
      catch (FileNotFoundException e)
      {
         System.out.println( "filter.properties not found." );
      }
      catch (IOException e)
      {
         e.printStackTrace(System.err);
         System.exit(-1);
      }
      
      
      // Check for environment variables and override defaults
      try 
      {
         columns = (System.getenv( "CS336_COLUMNS" ) != null) ? Integer.parseInt(System.getenv( "CS336_COLUMNS" )) : columns;
         precision = (System.getenv( "CS336_PRECISION" ) != null) ? Integer.parseInt(System.getenv( "CS336_PRECISION" )) : precision;
         groups = (System.getenv( "CS336_GROUPS" ) != null) ? Integer.parseInt(System.getenv( "CS336_GROUPS" )) : groups;
      }
      catch (NumberFormatException e) 
      {
         System.err.println("Invalid environment variable: " + e.getMessage());
         System.exit(1);
      }
      // If there are command-line arguments, try to parse them as integers for
      // columns, precision, and groups. If parsing fails, print an error message
      if (args.length > 0)
      {
         try
         {
            columns = Integer.parseInt(args[0]);
            precision = Integer.parseInt(args[1]);
            groups = Integer.parseInt(args[2]);
         }
         catch (NumberFormatException e)
         {
            System.err.println("Invalid argument: " + e.getMessage());
            System.exit(1);
         }
      }

      int spaceWidth = 4 + 1 + precision; // Width of the columns
      int columnCounter = 0; // Counter for the number of columns printed so far
      int groupCounter = 0; // Counter for the number of values printed in the current group
      while (in.hasNextDouble())
      {
         double value = in.nextDouble();
         System.out.printf("%" + spaceWidth + "." + precision + "f  ", value);
         ++columnCounter;
         // Move to the next line after printing the specified number of columns
         if (columnCounter == columns) 
         {
            System.out.println();
            columnCounter = 0;
         }
         // Separates groups of values with a blank line
         // TODO: input validation for things like negative numbers
         if (groups > 0) 
         {
            ++groupCounter;
            if (groupCounter == groups) 
            {
               System.out.println("\n");
               groupCounter = 0;
               columnCounter = 0; // Reset column counter at the end of a group
            }
         }
      }


      in.close();
   }
}
