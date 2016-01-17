package hw_final;

import java.util.*;

public class Subsets {
	public void print(Stack st)
	{ int itr=0,j=0;
	  char[] fac=new char[20];
	  while(!st.empty())
	  {
	     fac[itr++]= (char) st.pop();
	     
	  }
	  System.out.print(" {");
	  while(j<itr)
	   {
	     if(fac[j]=='1')
	       System.out.print(j+1);
	     j++;
	   }
	  System.out.print("} ");
	}
	public void pairUp(String binString2)
	{
		Stack st=new Stack();
		int index=0;
		while(index<binString2.length())
		{
			st.push(binString2.charAt(index));
			index++;
		}
		print(st);
	}

	public void find_subsets(int num)
	{
	   int one=Integer.parseInt("1",2);
	   int index=0,index2=0;
	   String binString="0";
	   while(index<num-1)
	   {
		   binString=binString+"0";
		   index++;
	   }
	   while(binString.length()<(num+1))
	   {
		   int int_num=Integer.parseInt(binString,2);
		   String binString2=Integer.toBinaryString(int_num);
		   pairUp(binString2);
		   int_num=int_num+one;
		   binString=Integer.toBinaryString(int_num);
	   }
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter the number of people attending the party:");
		int num=sc.nextInt();
		Subsets sb=new Subsets();
        sb.find_subsets(num);
	}

}
