package BackEnd;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class Motors {
	
	EV3LargeRegulatedMotor motorR;
	EV3LargeRegulatedMotor motorL;
	int SpeedRef;
	
	public Motors()
	{
		motorR = new EV3LargeRegulatedMotor(MotorPort.D);
		motorL = new EV3LargeRegulatedMotor(MotorPort.A);
		SpeedRef = 500;
	}
	
	public int getMotorSpeed()
	{
		return SpeedRef;
	}
	
	public int getSpeedR()
	{
		return motorR.getRotationSpeed();
	}
	
	public int getSpeedL()
	{
		return motorL.getRotationSpeed();
	}
	
	public float getMaxSpeed()
	{
		return motorR.getMaxSpeed();
	}
	
	public void stop() throws InterruptedException
	{
		motorL.stop(true);
		motorR.stop(true);
		Thread.sleep(100);
	}
	
	public void forward()
	{
		motorR.setSpeed(SpeedRef);
		motorL.setSpeed(SpeedRef);
		motorL.forward();
		motorR.forward();	
	}
	
	public void forward(int speed)
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
	
	public void backward()
	{
		motorR.setSpeed(SpeedRef);
		motorL.setSpeed(SpeedRef);
		motorR.backward();
		motorL.backward();
	}
	
	public void backward(int speed)
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
	
	public void Faster()
	{
		SpeedRef += 50;
	}
	
	public void Slower()
	{
		SpeedRef -= 50;
	}
	
	public void turnR() throws InterruptedException
	{
		motorR.setSpeed(motorR.getRotationSpeed()-200);
		Thread.sleep(1000);
		motorR.setSpeed(motorR.getRotationSpeed()+200);		
	}
	
	public void turnL() throws InterruptedException
	{
		motorL.setSpeed(motorL.getRotationSpeed()-200);
		Thread.sleep(1000);
		motorL.setSpeed(motorL.getRotationSpeed()+200);		
	}
	
	public void turn90R() throws InterruptedException
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
	
	public void turn90L() throws InterruptedException
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
	
	public void turn180() throws InterruptedException
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
