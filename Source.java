/*
   This program is a source of data for the filter program.
*/

import java.util.Random;

/**
   This program takes three optional command-line arguments.
<p>
   The first determines how many lines of output there are.
<p>
   The second determines how many random numbers there are on a line of output.
<p>
   The third determines the range of the random numbers.
<p>
   By default, this program produces a random number of lines of output
   and each line of output contains a random number of numbers.
<p>
   The default range for the random numbers is 10,000.
<p>
   Without any command-line arguments, this program will produce a random number
   of output lines (up to 100) and each output line will contain a random number
   of doubles (up to 10) and each random double will be less than 10,000.
*/
class Source
{
   public static void main(String[] args)
   {
      final Random random = new Random();

      final int lines;
      if (args.length >= 1)
      {
         lines = Integer.parseInt( args[0] ); // Fixed number of lines of output.
      }
      else
      {
         lines = 1 + random.nextInt(100); // Random number of lines of output.
      }

      final int range;
      if (args.length >= 3)
      {
         range = Integer.parseInt( args[2] ); // Range for the random numbers.
      }
      else
      {
         range = 10_000; // Default range for the random numbers.
      }

      for (int i = 0; i < lines; ++i)
      {
         final int data_points;
         if (args.length >= 2)
         {
            data_points = Integer.parseInt( args[1] ); // Fixed number of numbers on this line of output.
         }
         else
         {
            data_points = 1 + random.nextInt(10); // Random number of numbers on this line of output.
         }

         if ( random.nextBoolean() ) // "flip a coin" to decide if there should be leading spaces.
         {
            final int spaces = 1 + random.nextInt(20); // Random leading spaces.
            for (int j = 0; j < spaces; ++j)
            {
               System.out.printf(" ");
            }
         }

         for (int j = 0; j < data_points - 1; ++j)
         {
            final double d = range * random.nextDouble();
            System.out.printf("%.13f", d);
            final int spaces = 1 + random.nextInt(20); // At least once space between numbers,
            for (int k = 0; k < spaces; ++k)           // with some extra random spaces.
            {
               System.out.printf(" ");
            }
         }
         final double d = range * random.nextDouble(); // Last number on this line.
         System.out.printf("%.13f", d);

         if ( random.nextBoolean() ) // "flip a coin" to decide if there should be trailing spaces.
         {
            final int spaces = 1 + random.nextInt(20); // Random trailing spaces.
            for (int k = 0; k < spaces; ++k)
            {
               System.out.printf(" ");
            }
         }

         System.out.printf("\n");  // End this line.
       //System.out.printf("#\n"); // End this line in a way that we can see where it ends.
      }
   }
}
