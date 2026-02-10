/*

*/

import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
   A simple example of how to read the properties
   in a Java properties file.
<p>
   https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Properties.html
<p>
   Notice that the properties file is opened using
   a try-with-resources clause.
*/
class PropertiesExample
{
   public static void main(String[] args)
   {
      final Properties properties = new Properties();

      try (var fis = new FileInputStream(
                        new File("example.properties")))
      {
         // Read all the property key/value pairs.
         properties.load(fis);

         // Print all the properties.
         properties.list(System.out);
         System.out.println();

         // You can read the properties in any order.
         final String p5 = properties.getProperty("p5");
         final String p4 = properties.getProperty("p4");
         final String p1 = properties.getProperty("p1");
         final String p3 = properties.getProperty("p3");
         final String p2 = properties.getProperty("p2");
         System.out.println("p1 = " + p1);
         System.out.println("p2 = " + p2);
         System.out.println("p3 = " + p3);
         System.out.println("p4 = " + p4);
         System.out.println("p5 = " + p5);

         // You can check if a specific property exists.
         final String p6 = properties.getProperty("p6");
         if (p6 != null)
         {
            System.out.println("p6 = " + p6);
         }
         else
         {
            System.out.println("property p6 does not exist.");
         }
      }
      catch (FileNotFoundException e)
      {
         System.out.println( e );
      }
      catch (IOException e)
      {
         e.printStackTrace(System.err);
         System.exit(-1);
      }
   }
}
