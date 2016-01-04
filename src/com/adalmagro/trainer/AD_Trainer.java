/**
 * 
 */
package com.adalmagro.trainer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.fitting.WeightedObservedPoint;

/**
 * @author arturo.diaz
 *
 */
public class AD_Trainer implements AD_ITrainer {

	
	private int p_resistanceLevels;
	private String p_name;
	private String p_vendor;
	private String p_model;
	
	private Map<Integer,List<WeightedObservedPoint>> p_origPowerSpeedValues;
	
	/**
	 * 
	 */
	public AD_Trainer(String t_name, String t_vendor, String t_model, int t_resistanceLevels) {
		this.p_name = t_name;
		this.p_vendor = t_vendor;
		this.p_model = t_model;
		this.p_resistanceLevels = t_resistanceLevels;
		
		this.p_origPowerSpeedValues = new HashMap<Integer,List<WeightedObservedPoint>>();
		
		// populate by default
		ArrayList<WeightedObservedPoint> aux;
		for (int i=0; i<p_resistanceLevels; i++) {
			aux = new ArrayList<WeightedObservedPoint>();
			aux.add(new WeightedObservedPoint(1, 0, 0));
			this.p_origPowerSpeedValues.put(new Integer(i),aux);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.adalmagro.trainer.AD_ITrainer#setNumberOfResistanceLevels(int)
	 */
	@Override
	public void setNumberOfResistanceLevels(int t_levels) {
		this.p_resistanceLevels = t_levels;
	}

	/* (non-Javadoc)
	 * @see com.adalmagro.trainer.AD_ITrainer#setPowerCurve(int, java.util.List, java.util.List)
	 */
	@Override
	public boolean setPowerCurveFor(int t_resistanceLevel, List<WeightedObservedPoint> t_speedPower) {
		return p_origPowerSpeedValues.put(
				new Integer(t_resistanceLevel),
				t_speedPower) != null;
	}

	/* (non-Javadoc)
	 * @see com.adalmagro.trainer.AD_ITrainer#getPowerForSpeed(float, float)
	 */
	@Override
	public double getPowerForSpeed(double t_resistanceLevel, double t_speed) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * 
	 * @param t_coeficients
	 * @param x_value
	 * @return
	 */
	private double evaluate(List<Double> t_coeficients, double x_value) {
		Double[] coefs = new Double[t_coeficients.size()];
		t_coeficients.to
		PolynomialFunction t_polynomy = new PolynomialFunction((double[])t_coeficients.toArray());
	}

}
