package BackEnd;

import java.io.IOException;

public class EV3_BackEnd_Main extends Thread
{

	private static VehicleBT VBT;
	private static VehicleController VC;
	private static int Control;
	private static int finThread;
	private static int modeAuto;
	private static Thread ThreadInput;
	private static Thread ThreadOuput;
	
	
	public static void main(String[] args) throws InterruptedException, IOException 
	{
		
		VC = new VehicleController();
		VBT = new VehicleBT();
		VBT.connect();
		finThread = 0;
		Control = 0;
		modeAuto = 0;
		ThreadInput = new Thread(new inputExecution());
		ThreadInput.start();
		
		
		
		while(Control != 7)
    	{
			switch(Control)
			{
				case 1: //Controle pour faire avancer le robot
					if (modeAuto == 0)
					{
						VC.Forward();
						Control = 0;
						break;
					}
					else if(modeAuto == 1)
					{
						Control = 0;
						break;
					}
					
				case 2: //Controle pour faire tourner a droie le robot
					if (modeAuto == 0)
					{
						VC.TurnR();
						Control = 0;
						break;
					}
					else if(modeAuto == 1)
					{
						Control = 0;
						break;
					}
					
				case 3: //Controle pour faire tourner a gauche le robot
					if (modeAuto == 0)
					{
						VC.TurnL();
						Control = 0;
						break;
					}
					else if(modeAuto == 1)
					{
						Control = 0;
						break;
					}
					
				case 4: //Controle pour faire reculer le robot
					if (modeAuto == 0)
					{
						VC.Backward();
						Control = 0;
						break;
					}
					else if(modeAuto == 1)
					{
						Control = 0;
						break;
					}
					
				case 5: //Controle pour activer le mode automatique
					if (modeAuto == 0)
					{
						VC.AutoMode();
						modeAuto = 1;
						Control = 0;
						break;
					}
					else if(modeAuto == 1)
					{
						VC.EndAutoMode();
						modeAuto = 0;
						Control = 0;
						break;
					}
					
				case 6: //Controle admin mais utiliser pour fermer l'application
					if (modeAuto == 0)
					{
						VC.StopMotors();
						VC.Horn();
						Control = 0;
						break;
					}
					else if(modeAuto == 1)
					{
						Control = 0;
						break;
					}
				//case 7:
				//case 8:
			}
    	}

		VC.finProg();
		finThread = 1;
	}
	
	private static class inputExecution implements Runnable //Run executer par ThreadBT permettant de lire les inputs du stream bluetooth
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
}

		

