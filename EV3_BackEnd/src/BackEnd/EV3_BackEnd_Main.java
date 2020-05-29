package BackEnd;

import java.io.IOException;

public class EV3_BackEnd_Main extends Thread
{

	private static VehicleBT VBT;
	private static VehicleController VC;
	private static LogManagement Log;
	private static int Control;
	private static int finThread;
	private static int modeAuto;
	private static Thread ThreadInput;
	private static Thread ThreadOutput; 
	
	
	public static void main(String[] args) throws InterruptedException, IOException 
	{
		
		VC = new VehicleController();
		Log = new LogManagement();
		VBT = new VehicleBT(); 
		VBT.connect();
		Log.writeLog("User is connected\n");
		finThread = 0;
		Control = 0;
		modeAuto = 0;
		ThreadInput = new Thread(new inputExecution()); // thread managing inputs from the android application
		ThreadInput.start();
		ThreadOutput = new Thread(new outputExecution(200)); //thread managing outputs to the android application
		ThreadOutput.start();
		
		
		
		
		while(Control != 99) //Loop allowing to manage the orders sent by the android application and exit with the message 99
    	{
			switch(Control)
			{
				case 1: //Control to move the robot forward
					if (modeAuto == 0)
					{
						VC.Forward();
						Log.writeLog("User do Forward\n");
						Control = 0;
						break;
					}
					else if(modeAuto == 1)
					{
						Control = 0;
						break;
					}
					
				case 2: //Control to turn the robot to the right
					if (modeAuto == 0)
					{
						VC.TurnR();
						Log.writeLog("User do TurnR\n");
						Control = 0;
						break;
					}
					else if(modeAuto == 1)
					{
						Control = 0;
						break;
					}
					
				case 3: //Control to turn the robot to the left
					if (modeAuto == 0)
					{
						VC.TurnL();
						Log.writeLog("User do Turn\n");
						Control = 0;
						break;
					}
					else if(modeAuto == 1)
					{
						Control = 0;
						break;
					}
					
				case 4: //Control to move the robot back
					if (modeAuto == 0)
					{
						VC.Backward();
						Log.writeLog("User do Backward\n");
						Control = 0;
						break;
					}
					else if(modeAuto == 1)
					{
						Control = 0;
						break;
					}
					
				case 5: //Control to activate and deactivate automatic mode
					if (modeAuto == 0)
					{
						VC.AutoMode();
						Log.writeLog("Mode Auto On\n");
						modeAuto = 1;
						Control = 0;
						break;
					}
					else if(modeAuto == 1)
					{
						VC.EndAutoMode();
						Log.writeLog("Mode Auto Off\n");
						modeAuto = 0;
						Control = 0;
						break;
					}
					
				case 6: //Control to stop the motors
					if (modeAuto == 0)
					{
						VC.StopMotors();
						Log.writeLog("User do Stop\n");
						Control = 0;
						break;
					}
					else if(modeAuto == 1)
					{
						Control = 0;
						break;
					}
				case 7://Control to use the horn
					if (modeAuto == 0)
					{
						VC.Horn();
						Log.writeLog("User do Horn\n");
						Control = 0;
						break;
					}
					else if(modeAuto == 1)
					{
						Control = 0;
						break;
					}
				case 8://Control to increase motor speed
					if (modeAuto == 0)
					{
						VC.Faster();
						Log.writeLog("User do Faster\n");
						Control = 0;
						break;
					}
					else if(modeAuto == 1)
					{
						Control = 0;
						break;
					}
				case 9://Control to decrease motor speed
					if (modeAuto == 0)
					{
						VC.Slower();
						Log.writeLog("User do Slower\n");
						Control = 0;
						break;
					}
					else if(modeAuto == 1)
					{
						Control = 0;
						break;
					}
				case 10://Control to show the log file on the LCD screen of the EV3
					if (modeAuto == 0)
					{
						System.out.println(Log.ReadLog());
						Control = 0;
						break;
					}
					else if(modeAuto == 1)
					{
						Control = 0;
						break;
					}
			}
    	}
		
		VC.finProg(); //method to stop all program threads of VehicleController
		finThread = 1;//method to stop all program threads of EV3_BackEnd_Main
		Log.writeLog("User disconnected\n");
	}
	
	private static class inputExecution implements Runnable //Run execute by ThreadBT allowing to read the inputs of the bluetooth stream
	{
		public inputExecution()
		{
		}
		
		@Override
		public void run()
		{
			while(finThread == 0)
			{
				try 
				{
					Control = VBT.ReadBT();
			    }
				catch (IOException ioe) 
				{
			        ioe.printStackTrace();
				}
			}
		}
	}
	
	private static class outputExecution implements Runnable //Run execute by ThreadBT allowing to write an output on the bluetooth stream
	{
		private int delai;
		public outputExecution(int delai)
		{
			this.delai = delai;
		}
		
		@Override
		public void run()
		{
			while(finThread == 0)
			{
			
				try 
				{
					byte[] DataTab;
					DataTab = new byte[8]; 					//Tableau qui sera envoyé vers l'android
					DataTab[0] = 99;		
					DataTab[1] = (byte) VC.GetGyro();		//Contient la valeur de l'angle
					DataTab[2] = (byte) (VC.GetSpeed()/10);	//Contient la valeur de la vitesse diviser par 10
					DataTab[3] = (byte) VC.Getlux();		//Contient la valeur de la luminausité
					DataTab[4] = (byte) VC.GetCon();		//Contient la valeur de contact
					DataTab[5] = (byte) VC.GetBat();		//Contient la valeur de la batterie
					DataTab[6] = (byte) (VC.GetUL()/125);	//Permet de determiner si la distance est supérieur a 125 
					DataTab[7] = (byte) (VC.GetUL()-(VC.GetUL()/125)*125);//envoie le reste de la division de la vitesse par 125
					VBT.WriteBT(DataTab);					//Envoie du tableau vers android
			    }
				catch (IOException ioe) 
				{
			        ioe.printStackTrace();
				}
				
				try 
				{
			        Thread.sleep(delai);
			    }
				catch (InterruptedException e) 
				{
			        e.printStackTrace();
				}
			}
		}
	}
}

		

