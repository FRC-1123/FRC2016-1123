package org.usfirst.frc.team1123.robot.subsystems;

import org.usfirst.frc.team1123.robot.RobotMap;
import org.usfirst.frc.team1123.robot.commands.DriveWithJoystick;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {
	public static int invertMultiplier = 1;
	public void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoystick());	// By default, call DriveWithJoystick() command (which calls takeXBoxInput() method below).
	}

	public double getLeftDrive() {   // Retrieves the values of the left drive and returns the average of the two.
		double tempLeftFront = RobotMap.lFDriveTalon.get();
		double tempLeftRear = RobotMap.lRDriveTalon.get();
		return (tempLeftFront + tempLeftRear) / 2;
	}

	public double getRightDrive() {  // Retrieves the values of the right drive and returns the average of the two.
		double tempRightFront = RobotMap.rFDriveTalon.get();
		double tempRightRear = RobotMap.rRDriveTalon.get();
		return (tempRightFront + tempRightRear) / 2;
	}

	public void setLeftDrive(double tempSpeed) { // Sets left drive to move at tempSpeed (a value out of 1.0), POSITIVE for forward, NEGATIVE for backward.
		RobotMap.lFDriveTalon.set(tempSpeed * invertMultiplier);
		RobotMap.lRDriveTalon.set(tempSpeed * invertMultiplier);
	}

	public void setRightDrive(double tempSpeed) {   // Sets right drive to move at tempSpeed (a value out of 1.0), NEGATIVE for forward, POSITIVE for backward.
		RobotMap.rFDriveTalon.set(tempSpeed * invertMultiplier);
		RobotMap.rRDriveTalon.set(tempSpeed * invertMultiplier);
	}

	// Sets both drives to move at respective speeds (values out of 1.0), values MUST BE OPPOSITE SIGNS for drives to move in same direction.
	public void setDrive(double a, double b) {
		setLeftDrive(a);
		setRightDrive(b);
	}

	public void stop() { // Full stop of the drive train.
		setDrive(0, 0);
	}

	public void takeXBoxInput(Joystick tempXBox) {
		double leftJoystickY = tempXBox.getRawAxis(1);
		double rightJoystickY = tempXBox.getRawAxis(5);

//		// If left and right joysticks are past deadzones (0.1 out of 1.0) and are in the same direction (essentially if driver wants to move forwards or backwards).
//		if (Math.abs(leftJoystickY) > 0.1 && Math.abs(rightJoystickY) > 0.1 && Math.signum(leftJoystickY) == Math.signum(rightJoystickY)) {
//			leftJoystickY = rightJoystickY = (leftJoystickY + rightJoystickY) / 2.0;   // Balances the two values (takes average and sets each to that value).
//		}
		
		if (Math.abs(leftJoystickY - rightJoystickY) < 0.3) {	// If the two joysticks are within 30% of each other.
			leftJoystickY = rightJoystickY = (leftJoystickY + rightJoystickY) / 2.0;   // Balances the two values (takes average and sets each to that value).
		}

		// If left joystick is past deadzone, move left drive at rate following an x^2 curve (increase speed exponentially as joystick moves farther).
		if (Math.abs(leftJoystickY) > 0.1) {
			setLeftDrive(-1 * leftJoystickY * Math.abs(leftJoystickY));
		} else { // Otherwise, it's in the deadzone, no left drive movement.
			setLeftDrive(0);
		}

		// If right joystick is past deadzone, move right drive at rate following an x^2 curve (increase speed exponentially as joystick moves farther).
		if (Math.abs(rightJoystickY) > 0.1){
			setRightDrive(rightJoystickY * Math.abs(rightJoystickY));
		} else { // Otherwise, it's in the deadzone, no right drive movement.
			setRightDrive(0);
		}
	}
}


