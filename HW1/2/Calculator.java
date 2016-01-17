/**
 * Calculator.java
 * @version $ID: Calculator.java, v 1.8 08/27/2015 11:58am 
 * 
 * Revision: 1.21 08/27/2015 11:58am
 * Revision: 1.22 08/30/2015 2:43pm
 *
 */
import java.util.*;
/**
 * This program creates a simple calculator that can evaluate the 
 * basic arithmetic operations such as *, /, %, +, - , using the normal
 * precedence of operators:-  1)."/" 2)."*" 3)."%" 4)."+" 5)."-"
 * 
 * @author Aditya Pulekar
 *
 */
public class Calculator {
	/**
	   * The main program.
	   *
	   * @param    args    command line arguments (ignored)
	*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	   int index=0,index2=0,num,postf_z=0,var,flg=0;
	   int res,pushInt,itr=0;
	   String res2,p,q;
	   String[] num_arr=new String[30];
	   String in;
       Stack s=new Stack();
       Vector<String> v=new Vector<String>();
       Scanner sc= new Scanner(System.in);
       System.out.println("Enter the total number of integers in the expression: ");
       num=sc.nextInt();
       System.out.println("Enter the expression: \n");
       num=2*num-1;
       //Storing the expression in a vector
       while(itr<num)  
       {
    	   in=sc.next();
    	   v.add(itr,in);
    	   itr++;
       }
       
      //Precedence: 1."/"  2."*"  3."%"  4."+"  5."-"
      while(index<v.size())
      {
    	  if((v.elementAt(index).equals("+"))||(v.elementAt(index).equals("-"))||(v.elementAt(index).equals("%"))
    			  || (v.elementAt(index).equals("*"))||(v.elementAt(index).equals("/")))
    	  {
    		  if(s.empty())
    			  s.push(v.elementAt(index));
    		  else
    		  {
    			  //Push the operator on the stack if the operator on the top of the stack is lower precedence
    			  if(v.elementAt(index).equals("*") && (s.peek().equals("+") ||s.peek().equals("-") 
    					  || s.peek().equals("%")))
    				  s.push(v.elementAt(index));
    			  else if(v.elementAt(index).equals("/") && (s.peek().equals("+") ||s.peek().equals("-") 
    					  || s.peek().equals("%") || s.peek().equals("*")))
    				  s.push(v.elementAt(index));
    			  else if(v.elementAt(index).equals("%") && (s.peek().equals("+") ||s.peek().equals("-")))
    				  s.push(v.elementAt(index));
    			  else if(v.elementAt(index).equals("+") && s.peek().equals("-"))
    				  s.push(v.elementAt(index));    
    			  //Pop the operators in the stack if a lower precedence or equal precedence operator is to 
    			  //be pushed on the stack and add it to the array
    			  else 
    			  {
    				  while(!s.empty())
    				  {
    					  num_arr[postf_z++]=(String) s.pop();
    				  }
    				  s.push(v.elementAt(index));	
    			  }
    		  }
    	  }
    	  
    	 //If an operand,then add it to the array
         else
    	  {
    		  num_arr[postf_z++]=v.elementAt(index);
    	  }
    	 
    	  index++;
    	  if(index==v.size())
    	  {
    		  while(!s.empty())
    			  num_arr[postf_z++]=(String) s.pop();
    			  
    	  }
      }
      //Now, num_arr[] has the Postfix expression
      while(index2<postf_z)
      {
    	  if(num_arr[index2].equals("+")||num_arr[index2].equals("-")||num_arr[index2].equals("*")
    			  ||num_arr[index2].equals("/")||num_arr[index2].equals("%"))
    	  {
    		  //Pushing the result of an operation on the stack
    		  if(!s.empty())
    		  {
    			  p=(String) s.pop();     
    			  int y1=Integer.valueOf(p);
        		  q=(String) s.pop();
        		  int x1=Integer.valueOf(q);      		  
        		  if(num_arr[index2].equals("+"))
	    		  {
	    			  res=x1+y1;
	    			  res2=String.valueOf(res);
	    			  s.push(res2);
	    		  }
	    		  else if(num_arr[index2].equals("*"))
	    		  {
	    			  res=x1*y1;
	    			  res2=String.valueOf(res);
	    			  s.push(res2);
	    		  }
	    		  else if(num_arr[index2].equals("/"))
	    		  {
	    			  //Illegal Argument exception to  be thrown if denominator is zero
	    			  if(y1==0)
	    			  {
	    				  throw new IllegalArgumentException("Denominator is zero!!");
	    			  }
	    			  else
	    			  {
	    				  res=x1/y1;
		    			  res2=String.valueOf(res);
		    			  s.push(res2); 
	    			  }
	    			  
	    		  }
	    		  else if(num_arr[index2].equals("-"))
	    		  {
	    			  res=x1-y1;
	    			  res2=String.valueOf(res);
	    			  s.push(res2);
	    		  }
	    		  else if(num_arr[index2].equals("%"))
	    		  {
	    			  res=x1%y1;
	    			  res2=String.valueOf(res);
	    			  s.push(res2);
	    		  }
    		  }
    	  }
    	  //Pushing the operand on the stack
    	  else
    	  {
    		  s.push(num_arr[index2]);
    		  flg=1;
    	  }  
    	  index2++;
      }
      //Popping the result out of the stack (last element in the stack) and printing it
      res2=(String) s.pop();
      System.out.println("\nResult: "+ res2);
	}
}


