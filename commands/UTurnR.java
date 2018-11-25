package org.usfirst.frc.team1123.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

// Test class, not used.

public class UTurnR extends CommandGroup {	// Command that calls two RightTurns's, similar to UTurnL.
	public UTurnR() {
		addSequential(new RightTurn());
		addSequential(new RightTurn());
	}
}
