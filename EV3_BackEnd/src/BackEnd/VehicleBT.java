package BackEnd;

import lejos.hardware.Bluetooth;
import lejos.remote.nxt.BTConnection;
import lejos.remote.nxt.BTConnector;
import lejos.remote.nxt.NXTConnection;
import java.io.DataInputStream;
import java.io.DataOutputStream;
//import java.io.IOException;


public class VehicleBT {
	
	private DataOutputStream out; 
	private DataInputStream in;
	private BTConnection BTConnect;
	private BTConnector BTconnector;
	
	public VehicleBT()
	{
		BTConnector BTconnector = (BTConnector) Bluetooth.getNXTCommConnector();
	}

	public void connect()
	{  
		System.out.println("En attente");
		BTConnect = (BTConnection) BTconnector.waitForConnection(30000, NXTConnection.RAW);
		out = BTConnect.openDataOutputStream();
		in = BTConnect.openDataInputStream();
	}
}
