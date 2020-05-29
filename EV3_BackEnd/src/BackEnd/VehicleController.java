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
	private static int AutoValue;
	private Thread ThreadLux;
	private Thread ThreadUL;
	private Thread ThreadBat;
	private Thread ThreadGyro;
	private Thread ThreadCon;
	private Thread ThreadSpeed;
	private Thread ThreadModeAuto;


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
		ThreadBat = new Thread(new BatExecution(1000));
		ThreadGyro = new Thread(new GyroExecution(50));
		ThreadCon = new Thread(new ConExecution(50));
		ThreadSpeed = new Thread(new SpeedExecution(50));
		
		ThreadLux.start();
		ThreadUL.start();
		ThreadBat.start();
		ThreadGyro.start();
		ThreadCon.start();
		ThreadSpeed.start();
		System.out.println("Initialisation OK");
		
	}
	
	public void finProg()//Method allowing a loop exit on the different threads
	{
		finThread=1;
	}
	
	public void AutoMode()throws InterruptedException //Automatic robot mode
	{
		AutoValue = 1;
		ThreadModeAuto = new Thread(new ModeAutoExecution(50));
		ThreadModeAuto.start();
	}

	public void EndAutoMode()//Method for exiting auto mode
	{
		AutoValue = 0;
	}
	
	public void Forward()//Method for advancing the Robot
	{
		TractionMotors.forward();
	}
	
	public void Backward()//Method for reversing the Robot
	{
		TractionMotors.backward();
	}
	
	public void TurnL()throws InterruptedException //Method for turning the robot to the left
	{
		TractionMotors.turnL();
	}
	
	public void TurnR()throws InterruptedException //Method for turning the robot to the right
	{
		TractionMotors.turnR();
	}
	
	public void Faster() //Method for increasing engine speed
	{
		TractionMotors.Faster();
	}
	
	public void Slower() //Method to decrease engine speed
	{
		TractionMotors.Slower();
	}
	
	public void StopMotors()throws InterruptedException //Method for stopping robot movements
	{
		TractionMotors.stop();
	}

	public void Horn() //Method for honking the robot
	{
		VS.Ton2(250);
		VS.Ton2(500);
	}
	
	private static class LuxExecution implements Runnable //Run execute by ThreadLux allowing to recover the sensor values
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
	
	private static class ULExecution implements Runnable//Run execute by ThreadUL allowing to recover periodically the values f the sensors
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
		
	private static class GyroExecution implements Runnable  //Run execute by ThreadGyro allowing to recover periodically the sensor values
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
	
	private static class ConExecution implements Runnable  //Run run by ThreadCon allowing to recover periodically the values f the sensor
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
	
	private static class SpeedExecution implements Runnable  //Run execute by ThreadSpeed allowing to recover the sensor values periodically
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
				SpeedValue = TractionMotors.getSpeedR();
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
	
	private static class BatExecution implements Runnable //Run execute by ThreadBat allowing to recover periodically the values of the sensor
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
	
	private static class ModeAutoExecution implements Runnable //Run execute by ThreadAuto allowing to activate the automatic mode of the robot
	{
		private int delai;
		public ModeAutoExecution(int delai)
		{
			this.delai = delai;
		}
		
		@Override
		public void run()
		{
			while(AutoValue == 1) 
			{
				if (UltraValue < 15 && UltraValue != 0)
				{
					VS.beep();
					try 
					{
						TractionMotors.turn90L();
				    }
					catch (InterruptedException e) 
					{
				        e.printStackTrace();
					}
					TractionMotors.forward();
		
				}
				
				if (ContactValue == 1)
				{
					VS.beep();
					try 
					{
						TractionMotors.turn90L();
				    }
					catch (InterruptedException e) 
					{
				        e.printStackTrace();
					}
					TractionMotors.forward();
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
			
				try 
				{
					Thread.sleep(delai);
			    }
				catch (InterruptedException e) 
				{
			        e.printStackTrace();
				}
			}
			try 
			{
				TractionMotors.stop();
		    }
			catch (InterruptedException e) 
			{
		        e.printStackTrace();
			}
			
		}
	}
	
	public int Getlux() //Method for returning the ambient brightness value
	{
		return LuxValue;
	}
	
	public int GetUL() //Method to return the Distance with the next obstacle
	{
		return UltraValue;
	}
	
	public int GetGyro() //Method for returning the value of the tilt angle of the robot
	{
		return GyroValue;
	}
	
	public int GetCon() //Method allowing to return 1 if there is contact and 0 if there is no contact
	{
		return ContactValue;
	}
	
	public int GetSpeed() //Method for returning the speed of the robot motors
	{
		return SpeedValue;
	}
	
	public int GetBat() //Method for returning the percentage of remaining battery
	{
		return BatterieValue;
	}
}