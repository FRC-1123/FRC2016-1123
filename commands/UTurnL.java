package org.usfirst.frc.team1123.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

// Test class, not used.

public class UTurnL extends CommandGroup {   // Command that calls two LeftTurn's.
   public UTurnL() {
      addSequential(new LeftTurn());   // addSequential means execute after previous command is completed.
      addSequential(new LeftTurn());   // (not here) addParallel means execute at the same time as previous command.
   }
}
