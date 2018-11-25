package org.usfirst.frc.team1123.robot.subsystems;

import org.usfirst.frc.team1123.robot.Robot;
import org.usfirst.frc.team1123.robot.RobotMap;
import org.usfirst.frc.team1123.robot.commands.MoveBall;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Name: IronCrystal
 * Date: 4/27/2016 @ 10:52 AM
 */
public class BallIntake extends Subsystem {
   protected void initDefaultCommand() {
     setDefaultCommand(new MoveBall());   // By default, call MoveBall() command (which calls takeXBoxInput() method below).
   }

   public void setMovement(double tempRate) {   // Sets the rate of movement for the ball mechanism, a value out of 1.0.
      if (tempRate <= 1.0) {
         RobotMap.ballTalon.set(tempRate);
      }
   }

   public void stop() {
      RobotMap.ballTalon.set(0);
   }
   
   public void takeXBoxInput(Joystick tempXBox) {  // Use XBox shoulder buttons to control direction of ball intake, B button to stop mechanism completely.
   	boolean leftShoulder = tempXBox.getRawButton(5);
      boolean rightShoulder = tempXBox.getRawButton(6);
      boolean bButton = tempXBox.getRawButton(2);
      
      if (bButton) {
      	Robot.ballIntake.stop();
      } else if (leftShoulder) {
      	Robot.ballIntake.setMovement(1.0);
      } else if (rightShoulder) {
      	Robot.ballIntake.setMovement(-1.0);
      }
   }
}
