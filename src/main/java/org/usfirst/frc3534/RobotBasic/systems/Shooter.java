package org.usfirst.frc3534.RobotBasic.systems;

import org.usfirst.frc3534.RobotBasic.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Shooter extends SystemBase implements SystemInterface {

    private WPI_TalonFX shooter = RobotMap.shooter;
    private WPI_TalonSRX topBelt = RobotMap.topBelt;
    private WPI_TalonSRX indexWheel = RobotMap.indexWheel;
    private WPI_TalonSRX bottomBelt = RobotMap.bottomBelt;

    ShooterState shooterState = ShooterState.STOP;
    TopBeltState topBeltState = TopBeltState.STOP;
    IndexWheelState indexWheelState = IndexWheelState.STOP;
    BottomBeltState bottomBeltState = BottomBeltState.STOP;

    public Shooter(){

    }

    @Override
    public void process(){

        switch(shooterState){
        case SHOOT:

            setShooterPower(shooterState.value); 

            break;

        case STOP:

            setShooterPower(shooterState.value); 

            break;

        }

        switch(topBeltState){
            case RUN:
    
                setTopBeltPower(topBeltState.value); 
    
                break;
    
            case STOP:
    
                setTopBeltPower(topBeltState.value); 
    
                break;
    
            }

        switch(indexWheelState){
            case RUN:
    
                setIndexWheelPower(indexWheelState.value); 
    
                break;
    
            case STOP:
    
                setIndexWheelPower(indexWheelState.value); 
    
                break;
    
            }

        switch(bottomBeltState){
            case RUN:
    
                setBottomBeltPower(bottomBeltState.value); 
    
                break;

            case BURP:

                setBottomBeltPower(bottomBeltState.value);

                break;
    
            case STOP:
    
                setBottomBeltPower(bottomBeltState.value); 
    
                break;
    
            }

    }

    public enum ShooterState{
        
        SHOOT(RobotMap.PowerOutput.shooter_shooter_shoot.power),
        STOP(RobotMap.PowerOutput.shooter_shooter_stop.power);

        double value;

        private ShooterState(double value){

            this.value = value;

        }

    }

    public enum TopBeltState{
        
        RUN(RobotMap.PowerOutput.shooter_topBelt_run.power),
        STOP(RobotMap.PowerOutput.shooter_topBelt_stop.power);

        double value;

        private TopBeltState(double value){

            this.value = value;

        }

    }

    public enum IndexWheelState{
        
        RUN(RobotMap.PowerOutput.shooter_indexWheel_run.power),
        STOP(RobotMap.PowerOutput.shooter_indexWheel_stop.power);

        double value;

        private IndexWheelState(double value){

            this.value = value;

        }

    }

    public enum BottomBeltState{
        
        RUN(RobotMap.PowerOutput.shooter_bottomBelt_run.power),
        BURP(RobotMap.PowerOutput.shooter_bottomBelt_burp.power),
        STOP(RobotMap.PowerOutput.shooter_bottomBelt_stop.power);

        double value;

        private BottomBeltState(double value){

            this.value = value;

        }

    }

    public void setShooterState(ShooterState state){

        shooterState = state;

    }

    private void setShooterPower(double power){

        shooter.set(ControlMode.Velocity, power);

    }

    public void setTopBeltState(TopBeltState state){

        topBeltState = state;

    }

    private void setTopBeltPower(double power){

        topBelt.set(ControlMode.PercentOutput, power);

    }

    public void setIndexWheelState(IndexWheelState state){

        indexWheelState = state;

    }

    private void setIndexWheelPower(double power){

        indexWheel.set(ControlMode.PercentOutput, power);

    }

    public void setBottomBeltState(BottomBeltState state){

        bottomBeltState = state;

    }

    private void setBottomBeltPower(double power){

        bottomBelt.set(ControlMode.PercentOutput, power);

    }
}