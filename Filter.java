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
         if (properties.getProperty("columns") != null) 
         {
            int propColumns = Integer.parseInt(properties.getProperty("columns"));
            if (propColumns > 0) columns = propColumns; // Only override if valid
         }
         if (properties.getProperty("precision") != null) 
         {
            int propPrecision = Integer.parseInt(properties.getProperty("precision"));
            if (propPrecision > 0) precision = propPrecision; // Only override if valid
         }
         if (properties.getProperty("groups") != null) 
         {
            int propGroups = Integer.parseInt(properties.getProperty("groups"));
            if (propGroups >= 0) groups = propGroups; // Only override if valid
         }
         
      }
      catch (FileNotFoundException e)
      {
         // System.out.println( "filter.properties not found." );
      }
      catch (IOException e)
      {
         System.out.println( "Error reading filter.properties: " + e.getMessage() );
         System.exit(-1);
      }
      catch (NumberFormatException e)
      {
         // Ignore
      }
      
      
      // Check for environment variables and override defaults
      try 
      {
         if (System.getenv( "CS336_COLUMNS" ) != null)
         {
            int envCols = Integer.parseInt(System.getenv( "CS336_COLUMNS" ));
            if (envCols > 0) columns = envCols; // Only override if valid
         }
         if (System.getenv( "CS336_PRECISION" ) != null)
         {
            int envPrecision = Integer.parseInt(System.getenv( "CS336_PRECISION" ));
            if (envPrecision > 0) precision = envPrecision; // Only override if valid
         }
         if (System.getenv( "CS336_GROUPS" ) != null)
         {
            int envGroups = Integer.parseInt(System.getenv( "CS336_GROUPS" ));
            if (envGroups >= 0) groups = envGroups; // Only override if valid
         }
      }
      catch (NumberFormatException e) 
      {
         // Ignore
      }
      // If there are command-line arguments, try to parse them as integers for
      // columns, precision, and groups. If parsing fails, print an error message
      if (args.length > 0)
      {
         if (args.length >= 1) 
         {
            try 
            {
               int argCols = Integer.parseInt(args[0]);
               if (argCols > 0) columns = argCols; // Only override if valid
            }
            catch (NumberFormatException e) 
            {
               // Ignore
            }
         }
         if (args.length >= 2) 
         {
            try 
            {
               int argPrecision = Integer.parseInt(args[1]);
               if (argPrecision > 0) precision = argPrecision; // Only override if valid
            }
            catch (NumberFormatException e) 
            {
               // Ignore
            }
         }
         if (args.length >= 3)
         {
            try 
            {
               int argGroups = Integer.parseInt(args[2]);
               if (argGroups >= 0) groups = argGroups; // Only override if valid
            }
            catch (NumberFormatException e) 
            {
               // Ignore
            }
         }
      }

      int spaceWidth = 4 + 1 + precision; // Width of the columns
      int columnCounter = 0; // Counter for the number of columns printed so far
      int groupCounter = 0; // Counter for the number of values printed in the current group
      while (in.hasNextDouble())
      {
         double value = in.nextDouble();
         System.out.printf("%" + spaceWidth + "." + precision + "f  ", value);
         
         // Separates groups of values with a blank line
         if (groups > 0) 
         {
            columnCounter++;
            groupCounter++;
            if (groupCounter == groups) 
            {
               System.out.println("\n");
               groupCounter = 0;
               columnCounter = 0; // Reset column counter at the end of a group
            }
            else if (columnCounter == columns) 
            {
               System.out.println();
               columnCounter = 0;
            }
         }
         else 
         {
            columnCounter++;
            // Move to the next line after printing the specified number of columns
            if (columnCounter == columns) 
            {
               System.out.println();
               columnCounter = 0;
            }
         }
      }
      
      in.close();
   }
}
