package BackEnd;


public class EV3_BackEnd_Main 
{

	//private static VehicleBT VBT;
	private static VehicleController VC;
	
	public static void main(String[] args) throws InterruptedException 
	{
		//VBT = new VehicleBT();
		//VBT.connect();
		
		VC = new VehicleController();
		int cpt = 0;
		while (cpt < 15)
		{
			VC.Forward();
			Thread.sleep(1000);
			VC.Backward();
			Thread.sleep(1000);
			cpt++;
		}
		VC.StopMotors();
		VC.Horn();
		VC.finProg();
		
	}
}
