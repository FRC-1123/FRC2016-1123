package org.usfirst.frc.team1123.robot;

import edu.wpi.first.wpilibj.CANTalon;

public class RobotMap {
	public static CANTalon lFDriveTalon;   // lF = Left front.
   public static CANTalon rFDriveTalon;   // rF = Right front.
   public static CANTalon lRDriveTalon;   // lR = Left rear.
	public static CANTalon rRDriveTalon;   // rR = Right rear.
	public static CANTalon wedgeTalon;
	public static CANTalon ballTalon;

	public static void init() {
		lFDriveTalon = new CANTalon(1);
		rFDriveTalon = new CANTalon(2);
		lRDriveTalon = new CANTalon(3);
		rRDriveTalon = new CANTalon(4);
		wedgeTalon = new CANTalon(5);
		ballTalon = new CANTalon(6);
	}
}