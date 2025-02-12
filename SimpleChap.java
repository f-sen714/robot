package robotchap;

import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.NXTSoundSensor;
import lejos.hardware.sensor.NXTUltrasonicSensor;
import lejos.robotics.SampleProvider;

public class SimpleChap {

	public static void main(String[] args) {
		//float array to store sample from fetchSample
		float[] sample = new float[1];
        	float[] sample2 = new float[clapDetector.sampleSize()]; //this will hold sound data
		
		//define motors and sensor
		BaseRegulatedMotor mLeft = new EV3LargeRegulatedMotor(MotorPort.A);
		BaseRegulatedMotor mRight = new EV3LargeRegulatedMotor(MotorPort.B);
		
        	NXTSoundSensor soundSensor = new NXTSoundSensor(SensorPort.S2); //CHANGE PORT IF NEEDED 
        	SampleProvider sound = soundSensor.getDBAMode();
        	SampleProvider clapDetector = new ClapFilter(soundSensor, 0.6f, 100); //assuming 0.6 threshold, 100ms gap time btwn claps
        
        	System.out.println("Waiting for clap to be heard");
        	while (true) { //listening loop which breaks once clap has been heard
            		clapDetector.fetchSample(sample2, 0); 
            		if (sample2[0] == 1.0f) { //clapDetector level 1.0 indicates filter has heard a clap
                		System.out.println("Clap heard, Robbie moving forward.");
                		mLeft.forward();
                		mRight.forward();
                		break;
            		}
        	}
		NXTUltrasonicSensor sensor = new NXTUltrasonicSensor(SensorPort.S1);
		sensor.enable(); //enable sensor yippee
		
		//get a sample provider and store the first sample
		SampleProvider distanceProvider = sensor.getDistanceMode();
		distanceProvider.fetchSample(sample, 0);
		
		//continue to get a new distance sample until less than 50cm distance
		while (sample[0] >= 50) //supposedly measures in centimetres
			distanceProvider.fetchSample(sample, 0);
		
		//stop the motors and sensor
		mLeft.stop();
		mRight.stop();
		sensor.disable();
		
		//close all
		mLeft.close();
		mRight.close();
		sensor.close();
	}

}
