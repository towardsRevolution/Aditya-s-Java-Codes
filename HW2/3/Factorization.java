package hw_final; 

import java.util.*;

public class Factorization {
	
  public void eval_factors(int n, Stack st)
  {
	  int index=2,flg,j,itr=0;
	  int initial_n=n;
	  int[] fac=new int[50];
	  while(n!=1)
	  {
		  flg=0;
		  if(n%index==0)
		  {
			  st.push(index);
			  n=n/index;
			  flg=1;
		  }
		  if(flg==1)
			  index=1;
		  index++;			  
	  }
	  System.out.print(initial_n+" = ");
	    while(!st.empty())
  	      fac[itr++]=(int) st.pop(); 
        for(j=itr-1;j>=0;j--)
        {
        	System.out.print(fac[j]);
        	if(j!=0)
        		System.out.print("*");
        	
        }
  }		   
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n,index=0,j;
		Stack st= new Stack();
		Factorization f= new Factorization();
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter the number: ");
	    n=sc.nextInt();
	    f.eval_factors(n,st);
	}

}
