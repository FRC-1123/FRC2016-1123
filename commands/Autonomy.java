package org.usfirst.frc.team1123.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

// Test class, not used.

public class Autonomy extends CommandGroup {
   public Autonomy() {
   	addSequential(new AutonomyBarrier());
   	addSequential(new RightTurn());
   }
}