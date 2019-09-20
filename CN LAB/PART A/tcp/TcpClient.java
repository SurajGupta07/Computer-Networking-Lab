import java.io.*;
import java.net.*;
import java.util.*;
public class TcpClient
{
	void client() throws Exception
	{
		Socket s=new Socket("localhost",3333);
		DataInputStream din=new DataInputStream(s.getInputStream());
		DataOutputStream dout=new DataOutputStream(s.getOutputStream());
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter file name: ");
		String filename=scanner.next();
		dout.writeUTF(filename);
		String message;
		do
		{
			message=din.readUTF();
			System.out.println("Client: "+message);
		}while(!message.equals("stop"));
		scanner.close();
		dout.close();
		s.close();
	}
	public static void main(String args[]) throws Exception
	{
		TcpClient tc=new TcpClient();
		tc.client();
	}
}
