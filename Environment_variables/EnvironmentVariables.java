/**
   A simple demonstration of using environment variables
   (and command line arguments).
<p>
   Try
     > java EnvironmentVariables  prompt temp systemroot username bananas
<p>
   See
   https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/System.html#getenv(java.lang.String)
*/
public class EnvironmentVariables
{
   public static void main(String[] args)
   {
      if (0 == args.length)
      {
         System.out.println("Usage: java EnvironmentVariables env_vars...");
         System.exit(-1);
      }

      // Walk the args array and look for environment variables.
      for (final String cmdLineArg : args)
      {
         final String value = System.getenv( cmdLineArg );
         if (null == value)
         {
            System.out.println( cmdLineArg + " is not an environment variable." );
         }
         else
         {
            System.out.println( "The value of " + cmdLineArg + " is " + value );
         }
      }
   }
}
