package BackEnd;


public class VehicleController extends Thread{

	private static LuxSensor LSensor;
	private static UltrasonicSensor ULSensor;
	private static GyroSensor GySensor;
	private static ContactSensor ConSensor;
	private static VehicleSound VS;
	private static VehicleBatterie VB;
	private static VehicleScreen VSC;
	private static Motors TractionMotors;
	private static int GyroValue;
	private static int LuxValue;
	private static int ContactValue;
	private static int UltraValue;
	private static int SpeedValue;
	private static int BatterieValue;
	private static int finThread;
	private Thread ThreadLux;
	private Thread ThreadUL;
	private Thread ThreadInfo;
	private Thread ThreadBat;
	private Thread ThreadGyro;
	private Thread ThreadCon;
	private Thread ThreadSpeed;


	public VehicleController()//Constructeur de la classe
	{
		finThread=0;
		LSensor = new LuxSensor();
		ULSensor = new UltrasonicSensor();
		GySensor = new GyroSensor();
		ConSensor = new ContactSensor();
		VS = new VehicleSound();
		VB = new VehicleBatterie();
		VSC = new VehicleScreen();
		TractionMotors = new Motors();
		ThreadLux = new Thread(new LuxExecution(50));
		ThreadUL = new Thread(new ULExecution(50));
		ThreadInfo = new Thread(new InfoExecution(50));
		ThreadBat = new Thread(new BatExecution(1000));
		ThreadGyro = new Thread(new GyroExecution(50));
		ThreadCon = new Thread(new ConExecution(50));
		ThreadSpeed = new Thread(new SpeedExecution(50));
		
		ThreadLux.start();
		ThreadUL.start();
		ThreadInfo.start();
		ThreadBat.start();
		ThreadGyro.start();
		ThreadCon.start();
		ThreadSpeed.start();
		
		
	}
	
	public void finProg()// Méthode permettant une sortie de boucle sur les différents thread
	{
		finThread=1;
	}
	
	public void AutoMode()throws InterruptedException //Mode automatique du Robot
	{
		int cpt = 0;
		while(cpt < 600) 
		{
			if (UltraValue < 15 && UltraValue != 0)
			{
				VS.beep();
				TractionMotors.turn90L();
				TractionMotors.forward();
	
			}
			
			if (ContactValue == 1)
			{
				VS.beep();
				TractionMotors.turn90L();
				TractionMotors.forward();
			}
			
			
			if (LuxValue < 4)
			{
				VS.Ton3(1500);
				break;
			}
			
			if (LuxValue < 20 )
			{
				TractionMotors.forward(200);
			}
			
			if (LuxValue > 20)
			{
				TractionMotors.forward(400);
			}
			
			if ( GyroValue < -5)
			{
				TractionMotors.forward(500);
			}
			else TractionMotors.forward(400);
		
			VSC.setLcdInformation("Batterie: "+Float.toString(BatterieValue)+"%",1);
			VSC.setLcdInformation("Vitesse: "+TractionMotors.getSpeedR(),2);
			VSC.setLcdInformation("Light: "+Float.toString(LuxValue)+"%",3);
			VSC.setLcdInformation("Distance: "+Float.toString(UltraValue)+"cm",4);
			VSC.setLcdInformation("Angle: "+Float.toString(GyroValue)+"°",5);
			Thread.sleep(50);
			cpt++;
		}
		TractionMotors.stop();
	}
	
	public void Forward()//Méthode permettant de faire avancer le Robot
	{
		TractionMotors.forward();
	}
	
	public void Backward()//Méthode permettant de faire reculer le Robot
	{
		TractionMotors.backward();
	}
	
	public void TurnL()throws InterruptedException //Méthode permettant de faire tourner le robot a gauche
	{
		TractionMotors.turnL();
	}
	
	public void TurnR()throws InterruptedException //Méthode permettant de faire tourner le robot a droite
	{
		TractionMotors.turnR();
	}
	
	public void Faster() //Méthode permettant de faire d'augmenter la vitesse du robot
	{
		TractionMotors.Faster();
	}
	
	public void Slower() //Méthode permettant de faire diminuer la vitesse du robot
	{
		TractionMotors.Slower();
	}
	
	public void StopMotors()throws InterruptedException //Méthode permettant de stoper les mouvement du robot
	{
		TractionMotors.stop();
	}

	public void Horn() //Méthode permettant de faire klaxonner le robot
	{
		VS.Ton3(2000);
	}
	
	private static class LuxExecution implements Runnable //Run executer par ThreadLux permettant de recuperer périodiquement les valeurs du capteur
	{
		private int delai;
		public LuxExecution(int delai)
		{
			this.delai = delai;
		}
		
		@Override
		public void run()
		{
			while(finThread == 0)
			{
				LuxValue = LSensor.getLux();
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
	
	private static class ULExecution implements Runnable  //Run executer par ThreadUL permettant de recuperer périodiquement les valeurs du capteur
	{
		private int delai;
		public ULExecution(int delai)
		{
			this.delai = delai;
		}
		
		@Override
		public void run()
		{
			while(finThread == 0)
			{
				UltraValue = ULSensor.getDistance();
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
		
	private static class GyroExecution implements Runnable  //Run executer par ThreadGyro permettant de recuperer périodiquement les valeurs du capteur
	{
		private int delai;
		public GyroExecution(int delai)
		{
			this.delai = delai;
		}
		
		@Override
		public void run()
		{
			while(finThread == 0)
			{
				GyroValue = GySensor.getAngle();
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
	
	private static class ConExecution implements Runnable  //Run executer par ThreadCon permettant de recuperer périodiquement les valeurs du capteur
	{
		private int delai;
		public ConExecution(int delai)
		{
			this.delai = delai;
		}
		
		@Override
		public void run()
		{
			while(finThread == 0)
			{
				ContactValue = ConSensor.getContact();
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
	
	private static class SpeedExecution implements Runnable  //Run executer par ThreadSpeed permettant de recuperer périodiquement les valeurs du capteur
	{
		private int delai;
		public SpeedExecution(int delai)
		{
			this.delai = delai;
		}
		
		@Override
		public void run()
		{
			while(finThread == 0)
			{
				SpeedValue = TractionMotors.getMotorSpeed();
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
	
	private static class BatExecution implements Runnable //Run executer par ThreadBat permettant de recuperer périodiquement les valeurs du capteur
	{
		private int delai;
		public BatExecution(int delai)
		{
			this.delai = delai;
		}
		
		@Override
		public void run()
		{
			while(finThread == 0)
			{
				BatterieValue = (int) VB.getBatterieLevel();
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
	
	private static class InfoExecution implements Runnable //Run executer par ThreadInfo permettant de recuperer  et d'afficher périodiquement les valeurs des différents organes
	{
		private int delai;
		public InfoExecution(int delai)
		{
			this.delai = delai;
		}
		
		@Override
		public void run()
		{
			while(finThread == 0)
			{
				VSC.ClearLcd();
				VSC.setLcdInformation("Batterie: "+Float.toString(BatterieValue)+"%",1);
				VSC.setLcdInformation("Vitesse: "+Float.toString(SpeedValue),2);
				VSC.setLcdInformation("Light: "+Float.toString(LuxValue)+"%",3);
				VSC.setLcdInformation("Distance: "+Float.toString(UltraValue)+"cm",4);
				VSC.setLcdInformation("Angle: "+Float.toString(GyroValue)+"°",5);
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
	
	public int Getlux() //Méthode permettant de retourner la valeur de luminausité ambiante
	{
		return LuxValue;
	}
	
	public int GetUL() //Méthode permettant de retourner la Distance avec le prochaine obstacle
	{
		return UltraValue;
	}
	
	public int GetGyro() //Méthode permettant de retourner la valeur de l'angle d'inclinaison du robot
	{
		return GyroValue;
	}
	
	public int GetCon() //Méthode permettant de retourner 1 si il y a contact et 0 si il n'y a pas de contact
	{
		return ContactValue;
	}
	
	public int GetSpeed() //Méthode permettant de retourner la vitesse des moteurs du robot
	{
		return SpeedValue;
	}
	
	public int GetBat() //Méthode permettant de retourner le pourcentage de batterie restant
	{
		return BatterieValue;
	}
}