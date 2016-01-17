package hw_final;

class OnceOrMany {
	
    public static boolean singelton(String literal, String aNewString)    {
          return ( literal == aNewString );
    }
   public static void main( String args[] ) {
         String aString = "xyz"; //Creates a pointer to the string "xyz" in the Java String Literal pool
         //1.False, because "1.  xyz == aString: " is concatenated with "xyz" and then compared with aString
          System.out.println("1.  xyz == aString: " +     "xyz" == aString   );
         //2. Doubt: won't it be a literal to reference comparison OR is the reasoning that String literals always refer to an object in the String pool?
          //Is it so that xyz is a literal but "xyz" refers to an object in the String pool
          System.out.println("2.  xyz == aString: " +   ( "xyz" == aString ) );
         
         //3.False, because  in case of aString, a reference to the pooled instance is returned, on the other hand, in 
          //case of newString, a new String object (outside the String pool) is instantiated and a reference to this object is returned.
          //OR False, because String literals always refer to an object in the String pool but String objects created 
          //using the 'new' operator do  not refer to objects in the String pool
          String newString = new String("xyz");
          System.out.println("xyz == new String(xyz)\n    " + ("xyz" == newString) );

          //1. True, because a reference to the pooled instance "xyz" is stored in both local String variables of the method 
          //singleton(String,String)
          System.out.println("1: " + singelton("xyz", "xyz"));
          //2. False, because a reference to pooled instance "xyz" is stored in the local variable 'literal' and a reference
          //to a new String object (outside the String pool) instantiated is stored in the local variable 'newString'. 
          System.out.println("2: " + singelton("xyz", new String("xyz") ));
          //3. True, because literals "xy" and "z" are concatenated and then the reference to the pooled instance "xyz" is stored in the 
          // local variable 'newString', which is same as storing the reference to the pooled instance "xyz" in the local variable 'literal'.
          System.out.println("3: " + singelton("xyz", "xy" + "z"));
          //4. True, because literals "xy" and "z" are concatenated (String constant expression) and then the reference to the pooled instance 
          //"xyz" is stored in the local variable 'newString', which is same as storing the reference to the pooled instance "xyz" in the local variable 'literal'.
          System.out.println("4: " + singelton("x" + "y" + "z", "xyz"));
          //5. Doubt: In the below case, how is it that ""x"+"y"+ new String("z")" and "xyz" both have the same hashcode.
          System.out.println("5: " + singelton("x" + "y" + new String("z"), "xyz"));
          //6.True, because literals "y" and "z" are concatenated first, after which "x" is concatenated to "yz" and then  and then the reference to 
          //the pooled instance "xyz" is stored in the local variable 'literal', which is same as storing the reference to the pooled instance "xyz" 
          //in the local variable 'literal'.
          System.out.println("6: " + singelton("x" + ( "y" + "z"), "xyz"));           
          //7. True, because literals "y" and "z" are concatenated first, after which "x" is concatenated to "yz" and then  and then the reference to 
          //the pooled instance "xyz" is stored in the local variable 'literal', which is same as storing the reference to the pooled instance "xyz" 
          //in the local variable 'literal'.
          System.out.println("7: " + singelton("x" + ( "y" + "z"), "xyz"));
}
}
