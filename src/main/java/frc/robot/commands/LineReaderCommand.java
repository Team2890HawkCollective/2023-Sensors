package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LineReader;

public class LineReaderCommand extends CommandBase{
    
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final LineReader m_subsystem;
  
    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
  
  
  
    public LineReaderCommand(LineReader subsystem) {
      m_subsystem = subsystem;
      // Use addRequirements() here to declare subsystem dependencies.
      addRequirements(subsystem);
    }
  
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {}
  
    @Override
    public void execute() 
      {
      //System.out.println("Drive Train Execute");
          LineReader.isLine();
      }
  
  
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {}
  
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
      return false;
    }
}
