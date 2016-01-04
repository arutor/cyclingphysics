/**
 * 
 */
package com.adalmagro.trainer;

import java.util.List;

import org.apache.commons.math3.fitting.WeightedObservedPoint;

/**
 * @author arturo.diaz
 *
 */
public interface AD_ITrainer {

	/**
	 * Set the number of resistance levels of the AD_ITrainer.
	 * @param t_levels The total amounto of levels
	 */
	public void setNumberOfResistanceLevels(int t_levels);
	
	/**
	 * Characterizes the trainer power curve
	 * @param t_resistanceLevel The level of resistance to characterize
	 * @param t_speedPower The list of speed-power observed values
	 */
	public void setPowerCurveFor(int t_resistanceLevel, List<WeightedObservedPoint> t_speedPower);
	
	/**
	 * Calculates the power developed for the given speed at the t_resistanceLevel. In this method a
	 * curve fitting is executed in order to get an approximation to the real curve given the observed
	 * points.
	 * @param t_resistanceLevel The working resistance level 
	 * @param t_speed The speed to know the developed power
	 * @return Returns the power corresponding to the given speed at the given resistance level.
	 */
	public double getPowerForSpeed(int t_resistanceLevel, double t_speed);
}
