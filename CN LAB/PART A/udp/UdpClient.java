import java.net.*;
class UdpClient
{
	public void client() throws Exception
	{
		DatagramSocket socket=new DatagramSocket();
		DatagramPacket packet;
		String message="Start";
		packet=new DatagramPacket(message.getBytes(),message.length(),InetAddress.getByName("localhost"),3333);
		socket.send(packet);
		System.out.println("Client: Sent data to Server: Message="+message);
		byte[] msgBuffer=new byte[1024];
		packet=new DatagramPacket(msgBuffer,msgBuffer.length);
		do
		{
			socket.receive(packet);
			message=new String(msgBuffer,0,packet.getLength());
			System.out.println(message);
		}
		while(!message.equals("stop"));
		socket.close();
	}
	public static void main (String[] args) throws Exception
	{
		UdpClient uc=new UdpClient();
		uc.client();
	}
}
