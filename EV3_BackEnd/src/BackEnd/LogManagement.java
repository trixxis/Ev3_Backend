package BackEnd;

import java.io.*;

public class LogManagement {
	

	FileOutputStream out;
	FileInputStream in;
    File data;
    
	public LogManagement()
	{
		out = null;
		in = null;
	    File data = new File("log.dat");
	    try 
	    {
	        out = new FileOutputStream(data);
	        in = new FileInputStream(data);
	    } 
	    catch(IOException e) 
	    {
	      	System.err.println("Failed to create output stream");
	      	System.exit(1);
	    }
	}
	
	public void writeLog(String infoLog) //method to write to the log file
	{
		PrintStream dataOut = new PrintStream(out);
		dataOut.print(infoLog);
	}
	
	public String ReadLog() //method to read to the log file and and return its content
	{
	
		try
		{
		BufferedReader buffer = new BufferedReader( new InputStreamReader(in, "UTF-8" ));

		StringBuilder String = new StringBuilder();
		String line;
		while(( line = buffer.readLine()) != null ) 
		{
			String.append( line );
			String.append( '\n' );
		}
		return String.toString();
		}
		catch(IOException e) 
	    {
	      	System.err.println("Failed to read File");
	      	System.exit(1);
	    }
		return ("End");
	}

}
