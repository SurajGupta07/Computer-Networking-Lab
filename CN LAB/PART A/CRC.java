import java.util.Scanner;
public class CRC
{
	int w;
	char[] p;
	String checkSum;
	String message;
	CRC()
	{
		w=16;
		p="10001000000100001".toCharArray();
	}
	void crc()
	{
		String crc="0000000000000000";
		char[] msg=(message+crc).toCharArray();
		char[] rem=(crc+'0').toCharArray();
		int n=0;
		while(n<msg.length)
		{
			rem[w]=msg[n++];
			boolean xorcopy=rem[0]=='1';
			for(int i=1;i<=w;i++)
			{
				rem[i-1]=xorcopy?(rem[i]==p[i]?'0':'1'):rem[i];
			} 
		}
		checkSum=String.valueOf(rem).substring(0,w);
	}
	void input()
	{
		Scanner scanner=new Scanner(System.in);
		System.out.println("message");
		message=scanner.next();
		scanner.close();
	}
	void output()
	{
		System.out.println("Checksum="+checkSum);
	}
	public static void main(String args[])
	{
		CRC crc=new CRC();
		crc.input();
		crc.crc();
		crc.output();
	}
}
