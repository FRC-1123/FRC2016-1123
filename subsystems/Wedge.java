package org.usfirst.frc.team1123.robot.subsystems;

import org.usfirst.frc.team1123.robot.Robot;
import org.usfirst.frc.team1123.robot.RobotMap;
import org.usfirst.frc.team1123.robot.commands.MoveWedge;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Wedge extends Subsystem {
   private CANTalon talonEncoder = RobotMap.wedgeTalon;
   public Wedge() {
      talonEncoder.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
   }

   protected void initDefaultCommand() {
      setDefaultCommand(new MoveWedge()); // By default, call MoveWedge() command (which calls takeXBoxInput() method below).
   }

   public void setMovement(double tempRate) {   // Sets the rate of movement for the wedge, a value out of 1.0.
      if (tempRate <= 1.0) {
         talonEncoder.set(tempRate);
      }
   }

   public void stop() {
      talonEncoder.set(0);
   }

   public double getValue() {
      return talonEncoder.getPosition();
   }

   public void takeXBoxInput(Joystick tempXBox) {  // Uses XBox triggers to control wedge movement.
      double leftTrigger = tempXBox.getRawAxis(2);
      double rightTrigger = tempXBox.getRawAxis(3);
      // Only listens to one trigger at a time, to avoid confusion, based on how far each is pressed.
      if (leftTrigger > 0.1 || rightTrigger > 0.1) {	// If either left trigger or right trigger are past dead zones.
         if (leftTrigger > rightTrigger) {   // If left trigger is pressed farther than right, left takes precedence.
            SmartDashboard.putString("Wedge direction: ", "Up");
            setMovement(-0.6);
         } else if (leftTrigger < rightTrigger) {  // If right trigger is pressed farther than left, right takes precedence.
            SmartDashboard.putString("Wedge direction: ", "Down");
            setMovement(0.6);
         }
      } else {
      	Robot.wedge.stop();
      }
   }
}