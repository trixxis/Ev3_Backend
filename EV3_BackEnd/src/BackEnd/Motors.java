package BackEnd;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class Motors {
	
	EV3LargeRegulatedMotor motorR;
	EV3LargeRegulatedMotor motorL;
	int SpeedRef;
	
	public Motors() 
	{
		motorR = new EV3LargeRegulatedMotor(MotorPort.D); //Object representing the right motor
		motorL = new EV3LargeRegulatedMotor(MotorPort.A); //Object representing the right motor
		SpeedRef = 500; //Cruising speed
	}
	
	public int getMotorSpeed() //method returning cruise speed
	{
		return SpeedRef;
	}
	
	public int getSpeedR() //method returning the speed of the right motor
	{
		return motorR.getRotationSpeed();
	}
	
	public int getSpeedL() //method returning the speed of the left motor
	{
		return motorL.getRotationSpeed();
	}
	
	public float getMaxSpeed() //method returning the max speed of the motor
	{
		return motorR.getMaxSpeed();
	}
	
	public void stop() throws InterruptedException //method stopping both motor
	{
		motorL.stop(true);
		motorR.stop(true);
		Thread.sleep(100);
	}
	
	public void forward() //method of advancing the two motors
	{
		motorR.setSpeed(SpeedRef);
		motorL.setSpeed(SpeedRef);
		motorL.forward();
		motorR.forward();	
	}
	
	public void forward(int speed)//method of advancing the two motors at a certain speed
	{
		if (speed < this.getMaxSpeed())
		{
			SpeedRef = speed;
			motorR.setSpeed(SpeedRef);
			motorL.setSpeed(SpeedRef);
			motorL.forward();
			motorR.forward();
		}
	}
	
	public void backward() //method for reversing the two motors
	{
		motorR.setSpeed(SpeedRef);
		motorL.setSpeed(SpeedRef);
		motorR.backward();
		motorL.backward();
	}
	
	public void backward(int speed)//method for reversing the two motors at a certain speed
	{
		if (speed < this.getMaxSpeed())
		{
			SpeedRef = speed;
			motorR.setSpeed(SpeedRef);
			motorL.setSpeed(SpeedRef);
			motorR.backward();
			motorL.backward();
		}
	}
	
	public void Faster()//method to accelerate both engines
	{
		SpeedRef = SpeedRef + 50;
		motorR.setSpeed(SpeedRef);
		motorL.setSpeed(SpeedRef);
	}
			
	
	public void Slower()//method to slow down both engines
	{
		SpeedRef = SpeedRef - 50;
		motorR.setSpeed(SpeedRef);
		motorL.setSpeed(SpeedRef);
	}
	
	public void turnR() throws InterruptedException //method to turn right
	{
		motorR.setSpeed(motorR.getRotationSpeed()-200);
		Thread.sleep(1000);
		motorR.setSpeed(motorR.getRotationSpeed()+200);		
	}
	
	public void turnL() throws InterruptedException //method to turn left
	{
		motorL.setSpeed(motorL.getRotationSpeed()-200);
		Thread.sleep(1000);
		motorL.setSpeed(motorL.getRotationSpeed()+200);		
	}
	
	public void turn90R() throws InterruptedException //method of turning 90 degrees to the right
	{
		if (SpeedRef == 400)
		{
			motorL.stop(true);
			motorR.stop(true);
			motorL.setSpeed(SpeedRef);
			motorR.setSpeed(SpeedRef);
			motorR.backward();
			motorL.backward();
			Thread.sleep(1000);
			motorL.stop(true);
			motorR.stop(true);
			motorR.setSpeed(0);
			motorL.setSpeed(SpeedRef);
			motorL.forward();
			Thread.sleep(1100);
			motorL.stop(true);
			motorR.stop(true);
			Thread.sleep(100);
		}
		else
		{
			motorL.stop(true);
			motorR.stop(true);
			motorL.setSpeed(SpeedRef);
			motorR.setSpeed(SpeedRef);
			motorR.backward();
			motorL.backward();
			Thread.sleep(1500);
			motorL.stop(true);
			motorR.stop(true);
			motorR.setSpeed(0);
			motorL.setSpeed(SpeedRef);
			motorL.forward();
			Thread.sleep(2100);
			motorL.stop(true);
			motorR.stop(true);
			Thread.sleep(100);
		}
	}
	
	public void turn90L() throws InterruptedException //method of turning 90 degrees to the left
	{
		if(SpeedRef == 400)
		{
			motorL.stop(true);
			motorR.stop(true);
			motorL.setSpeed(SpeedRef);
			motorR.setSpeed(SpeedRef);
			motorR.backward();
			motorL.backward();
			Thread.sleep(1000);
			motorL.stop(true);
			motorR.stop(true);
			motorR.setSpeed(SpeedRef);
			motorR.forward();
			motorL.setSpeed(0);
			Thread.sleep(1100);
			motorL.stop(true);
			motorR.stop(true);
			Thread.sleep(100);
		}
		else
		{
			motorL.stop(true);
			motorR.stop(true);
			motorL.setSpeed(SpeedRef);
			motorR.setSpeed(SpeedRef);
			motorR.backward();
			motorL.backward();
			Thread.sleep(1500);
			motorL.stop(true);
			motorR.stop(true);
			motorR.setSpeed(SpeedRef);
			motorR.forward();
			motorL.setSpeed(0);
			Thread.sleep(2100);
			motorL.stop(true);
			motorR.stop(true);
			Thread.sleep(100);
		}
		
	}
	
	public void turn180() throws InterruptedException //method for making a U-turn
	{
		motorL.stop(true);
		motorR.stop(true);
		motorL.setSpeed(SpeedRef);
		motorR.setSpeed(SpeedRef);
		motorR.backward();
		motorL.backward();
		Thread.sleep(600);
		motorL.stop(true);
		motorR.stop(true);
		motorR.setSpeed(0);
		motorL.setSpeed(SpeedRef);
		motorL.forward();
		Thread.sleep(2000);
		motorL.stop(true);
		motorR.stop(true);
		Thread.sleep(100);
	}
	

	
}
