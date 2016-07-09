import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSound{
	
	static private ServerSocket server;
	static private Socket socket;
	static OutputStream output;
	static InputStream input;
	
	
	public static void main(String[] args){

		try {
			server =new ServerSocket(1026);
		
		while(true){
			socket = server.accept();
			output=socket.getOutputStream();
			input=socket.getInputStream();
			//output.write("Вы прислали: "+input.readObject());
			SynMIDI smidi=new SynMIDI();
			smidi.start();
			
			output.close();
			input.close();
		}} catch (IOException e) {
			e.printStackTrace();
		}
		
	} 
}
