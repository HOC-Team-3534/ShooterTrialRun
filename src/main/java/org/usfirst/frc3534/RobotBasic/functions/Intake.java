package org.usfirst.frc3534.RobotBasic.functions;

import org.usfirst.frc3534.RobotBasic.Robot;
import org.usfirst.frc3534.RobotBasic.RobotMap;
import org.usfirst.frc3534.RobotBasic.OI.Buttons;
import org.usfirst.frc3534.RobotBasic.RobotMap.FunctionStateDelay;
import org.usfirst.frc3534.RobotBasic.RobotMap.PowerOutput;
import org.usfirst.frc3534.RobotBasic.systems.Shooter.BottomBeltState;
import org.usfirst.frc3534.RobotBasic.systems.Shooter.IndexWheelState;
import org.usfirst.frc3534.RobotBasic.systems.Shooter.ShooterState;
import org.usfirst.frc3534.RobotBasic.systems.Shooter.TopBeltState;

public class Intake extends FunctionBase implements FunctionInterface{

    long originalTime = 0;

    public Intake(){

        reset();
        completed();

    }

    @Override
    public void process(){

        if(!running && Buttons.Intake.getButton()){

            this.reset();

        }

        switch(this.state) {

        case 0:

            if(Buttons.Intake.getButton()){
                this.started();
                this.state = 10;
                
            }

            break;

        case 10:

            if(!Robot.functionProcessor.shoot.isRunning()){
                
                Robot.shooter.setBottomBeltState(BottomBeltState.RUN);
                this.state = 20;

            }else{

                this.state = 100;

            }
            
            break;

        case 20:

            if(System.currentTimeMillis() - originalTime > FunctionStateDelay.intake_load.time){

                originalTime = System.currentTimeMillis();
                this.state = 100;

            }

            break;

        case 100:

            if(!Buttons.Intake.getButton()){

                Robot.shooter.setBottomBeltState(BottomBeltState.STOP);
                
            }
            
            completed();

            break;
    
        }
    }
}