package cn.bmy.test;


public class BigNumberCalculation
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		int a[]=new int[]{5234,5678,2234,5678};
		int b[]=new int[]{345,1458,3423,2345};
		int c[]=new int[4];
		BigNumberCalculation calculation = new BigNumberCalculation();
		calculation.add(a, b, c);
		//calculation.subtraction(a, b, c);
		//calculation.multiplication(a, 5, c);
//		calculation.division(a, 2, c);
		for(int i=0;i<c.length;i++)
		{
			System.out.print(c[i]+"\t");
		}
		
	}
	public void add(int a[],int b[],int c[])
	{
		int m=a.length;
		int k=c.length;
		int carry=0;
		for(int i=m-1;i>=0;i--)
		{
			c[--k]=a[i]+b[i]+carry;
			if(c[i]>10000)
			{
				carry=1;
				c[k]=c[k]-10000;
			}
			else
			{
				carry=0;
			}
		}
		if(c.length>m&&c[k]>10000)
		{
			c[k]=c[k]-10000;
			c[--k]=1;
		}
	}
	public void subtraction (int a[],int b[],int c[])
	{
		int m=a.length;
		int carry=0;
		for(int i=m-1;i>=0;i--)
		{
			c[i]=a[i]-b[i]-carry;
			if(i>0)
			{
				if(c[i]<0)
				{
					carry=1;
					c[i]=c[i]+10000;
				}
				else
				{
					carry=0;
				}
			}
		}
	}
	public void multiplication(int a[],int b,int c[])
	{
		int m=a.length;
		int k=c.length;
		int carry=0;
		int temp=0;
		for(int i=m-1;i>=0;i--)
		{
			temp=a[i]*b+carry;
			carry=temp/10000;
			c[--k]=temp%10000;
		}
		if(c.length>m&&carry!=0)
		{
			c[--k]=carry;
		}
	}
	public void division(int a[],int b,int c[])
	{
		int m=a.length;
		int remain=0;
		for(int i=0;i<m;i++)
		{
			c[i]=(a[i]+remain)/b;
			remain=a[i]%b*10000;
		}
	}
}