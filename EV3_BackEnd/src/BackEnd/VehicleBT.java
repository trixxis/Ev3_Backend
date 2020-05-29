package BackEnd;

import lejos.hardware.Bluetooth;
import lejos.remote.nxt.BTConnection;
import lejos.remote.nxt.BTConnector;
import lejos.remote.nxt.NXTConnection;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;


public class VehicleBT {
	
	private DataOutputStream out; 
	private DataInputStream in;
	private BTConnection BTConnect;
	private BTConnector BTconnector;
	
	public VehicleBT()
	{
	}

	public void connect() //Method for connecting via bluetooth
	{  
		BTconnector = (BTConnector) Bluetooth.getNXTCommConnector();
		BTConnect = (BTConnection) BTconnector.waitForConnection(60000, NXTConnection.RAW);
		System.out.println("Connexion OK");
		out = BTConnect.openDataOutputStream();
		in = BTConnect.openDataInputStream();
	}
	
	public int ReadBT() throws IOException //Method for reading information on the bluetooth stream
	{
		return (int) in.readByte();
		
	}
	
	public void WriteBT(byte[] data) throws IOException //Method for writing information on the bluetooth stream
	{
		out.write(data, 0, 8);
	}	
}
