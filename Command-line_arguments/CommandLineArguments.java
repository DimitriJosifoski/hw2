/**
   A simple demonstration of using command-line arguments.
<p>
   args is an array of String and it holds the strings from
   the command-line that started the program. The strings are
   contained in args in the order in which they appeared on
   the command-line reading the command-line from left to right.
   (Note: Unlike in C or C++, the first argument, args[0],
   is NOT the name of the program.)
<p>
   The strings on the command-line are considered as "arguments"
   for the program (analogous to "arguments" for a function call).
   Hence the name args, which is an abbreviation of "arguments".
<p>
   Be sure to try examples like,
     > java  CommandLineArguments  a b "c d"
     > java  CommandLineArguments  *
     > java  CommandLineArguments  *.java
*/
public class CommandLineArguments
{
   public static void main(String[] args)
   {
      System.out.println("There were " + args.length + " command line arguments.");
      System.out.println("They are:");

      // Walk the args array printing out each string.
      for (int i = 0; i < args.length; ++i)
      {
         System.out.println( args[i] );
      }

      // The modern way to walk the args array.
      //for (final String cmdLineArg : args)
      //{
      //   System.out.println( cmdLineArg );
      //}
   }
}