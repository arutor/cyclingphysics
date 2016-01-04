/**
 * 
 */
package com.adalmagro.trainer;

import java.util.ArrayList;

import org.apache.commons.math3.fitting.WeightedObservedPoint;

/**
 * @author arturo.diaz
 *
 */
public class AD_TrainerTest {

	private final static AD_Trainer trainer = new AD_Trainer("MyElite","Elite","Nove Force 5",5);
	
	/**
	 * 
	 */
	public AD_TrainerTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final ArrayList<WeightedObservedPoint> observedSpeedPower = new ArrayList<WeightedObservedPoint>();
		observedSpeedPower.add(new WeightedObservedPoint(1,0,0));
		observedSpeedPower.add(new WeightedObservedPoint(1,10,45));
		observedSpeedPower.add(new WeightedObservedPoint(1,20,110));
		observedSpeedPower.add(new WeightedObservedPoint(1,30,190));
		observedSpeedPower.add(new WeightedObservedPoint(1,40,250));
		
		trainer.setPowerCurveFor(1,observedSpeedPower);
		
		double speed=17;		
		System.out.println("For SPEED="+speed+" POWER="+ trainer.getPowerForSpeed(1,speed));
	}

}
