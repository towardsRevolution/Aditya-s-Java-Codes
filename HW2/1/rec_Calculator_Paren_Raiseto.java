package hw_final;

/**
 * Calculator.java
 * @version $ID: Calculator.java, v 1.8 09/2/2015 11:44am
 * 
 * Revision: 2.11 09/2/2015 11:44am
 * 
 *
 */
import java.util.*;
/**
 * This program creates a calculator that can evaluate the 
 * basic arithmetic operations such as *, /, %, +, - and ^ , using the following 
 * precedence of operators:-  1)."(" ")"  2). "^"  3)."/"  4)."*"  5)."%"  6)."+"  7)."-" 
 * 
 * @author Aditya Pulekar
 * @author Mandar Badave
 *
 */

//Try these examples: 1). 3+4*5+(2*3+6)*7-5   2). 2+(3*(6-2))   3). 2+(3*(6*6/(12-3)))

public class rec_Calculator_Paren_Raiseto {
	public static int postf_z=0;
	/**
	   * The main program.
	   *
	   * @param    args    command line arguments (ignored)
	*/
	public int exp_paren(Vector<String> v, int paren, String[] num_arr,Stack st)
	{
		
		int i;
		  while(!(v.elementAt(paren).equals(")")))
		  {
			 if((v.elementAt(paren).equals("+"))||(v.elementAt(paren).equals("-"))||(v.elementAt(paren).equals("%"))
					 || (v.elementAt(paren).equals("*"))||(v.elementAt(paren).equals("/"))
					 ||(v.elementAt(paren).equals("^"))||(v.elementAt(paren).equals("("))) 
			 {
					 if(st.empty())
					 {
						 st.push(v.elementAt(paren));
					 }
					 else if(v.elementAt(paren).equals("*") && (st.peek().equals("+") ||st.peek().equals("-") 
	    		 			  || st.peek().equals("%")))
	    				  st.push(v.elementAt(paren));
				     else if(v.elementAt(paren).equals("/") && (st.peek().equals("+") ||st.peek().equals("-") 
	    		 			  || st.peek().equals("%") || st.peek().equals("*")))
	    				  st.push(v.elementAt(paren));
				     else if(v.elementAt(paren).equals("^") && (st.peek().equals("+") ||st.peek().equals("-") 
	    					  || st.peek().equals("%") || st.peek().equals("*") || st.peek().equals("/")))
	    				  st.push(v.elementAt(paren));
				     else if(v.elementAt(paren).equals("%") && (st.peek().equals("+") ||st.peek().equals("-")))
	    				  st.push(v.elementAt(paren));
				     else if(v.elementAt(paren).equals("+") && st.peek().equals("-"))
	    				  st.push(v.elementAt(paren)); 
				     //The "else" below covers the condition of the occurrence of ")" as well as popping of the 
	    		     // operators in the stack in case a lower or equal precedence operator arrives in v.elementAt(paren)
				     else if(v.elementAt(paren).equals("("))
				     {
				    	 st.push(v.elementAt(paren));
				    	 paren=paren+1;
				    	 paren=exp_paren(v,paren,num_arr,st);
				     }
				     else 
	    		     {
	    			  	while(!(st.peek().equals("(")))  
	   				 	{
	   					  num_arr[postf_z++]=(String) st.pop();
	   				 	}
	    			    
	    			    	st.push(v.elementAt(paren));
	    		      }
			 }
			 else  
			 {
				 if(!(v.elementAt(paren).equals(")"))|| !(v.elementAt(paren).equals("(")))
					 num_arr[postf_z++]=v.elementAt(paren);
	    	 } 	       	    		  
			  paren++;  
		  }
		  //System.out.println("Stack Elements now: "+ st + "  At the top of the stack: "+ st.peek());
		  while(!(st.peek().equals("(")))               //After Closing Parenthesis are encountered
		  {
			  num_arr[postf_z++]=(String) st.pop();
		  }
		  st.pop();
		  //System.out.println("Value of Postf_z: "+ postf_z);
   		  /*for(i=0;i<postf_z;i++)
   			  System.out.println(num_arr[i]);
   		  System.out.println("\n");*/
		  return (paren);
	    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	   int index=0,index2=0,num,var,flg=0,paren=0;
	   int itr=0,j=0,paren_end;
	   double res;
	   String res2,p,q;
	   String[] num_arr=new String[30];
	   String in;
       Stack s=new Stack();
       Stack st= new Stack();
       Vector<String> v=new Vector<String>();
       Scanner sc= new Scanner(System.in);
       System.out.println("Enter the expression: \n");
       while(itr<50)
       {
    	   
    	   in=sc.nextLine();
    	   if(in.equals(""))
    	   {
    		   break;
    	   }
    	   v.add(itr,in);
    	   itr++;
       }
       
      //Precedence: 1)."(" ")"  2). "^"  3)."/"  4)."*"  5)."%"  6)."+"  7)."-"
      while(index<v.size())
      {
    	  if((v.elementAt(index).equals("+"))||(v.elementAt(index).equals("-"))||(v.elementAt(index).equals("%"))
    			  || (v.elementAt(index).equals("*"))||(v.elementAt(index).equals("/"))
    			  ||(v.elementAt(index).equals("("))||(v.elementAt(index).equals("^")))
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
    			  else if(v.elementAt(index).equals("^") && (s.peek().equals("+") ||s.peek().equals("-") 
    					  || s.peek().equals("%") || s.peek().equals("*") || s.peek().equals("/"))
    					  || s.peek().equals("^"))
    				  s.push(v.elementAt(index));
    			  else if(v.elementAt(index).equals("%") && (s.peek().equals("+") ||s.peek().equals("-")))
    				  s.push(v.elementAt(index));
    			  else if(v.elementAt(index).equals("+") && s.peek().equals("-"))
    				  s.push(v.elementAt(index));    
    			  //Pop the operators in the stack if a lower precedence or equal precedence operator is to 
    			  //be pushed on the stack and add it to the array		  
     			  else if(v.elementAt(index).equals("("))
     			  {  
     				  st.push(v.elementAt(index));
     				  rec_Calculator_Paren_Raiseto c=new rec_Calculator_Paren_Raiseto();
     				  paren=index+1;
     				  index=c.exp_paren(v,paren,num_arr,st);
     			  }
    			  
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
      j=0;
      /*for(j=0;j<postf_z;j++)
    	  System.out.println(num_arr[j]);*/
      while(index2<postf_z)
      {
    	  if(num_arr[index2].equals("+")||num_arr[index2].equals("-")||num_arr[index2].equals("*")
    			  ||num_arr[index2].equals("/")||num_arr[index2].equals("%")||num_arr[index2].equals("^"))
    	  {
    		  //Pushing the result of an operation on the stack
    		  if(!s.empty())
    		  {
    			  p=(String) s.pop();     
    			  double y1=Double.valueOf(p);
        		  q=(String) s.pop();
        		  double x1=Double.valueOf(q);      		  
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
	    		  else if(num_arr[index2].equals("^"))
	    		  {
	    			  res=Math.pow(x1, y1);
	    			  res2=String.valueOf(res);
	    			  s.push(res2);
	    		  }
    		  }
    	  }
    	  //Pushing the operand on the stack
    	  else
    	  {
    		  s.push(num_arr[index2]);
    		  //flg=1;
    	  }  
    	  index2++;
      }
      //Popping the result out of the stack (last element in the stack) and printing it
      res2=(String) s.pop();
      System.out.println("\nResult: "+ res2);
	}
}