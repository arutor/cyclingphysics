/**
 * 
 */
package com.adalmagro.trainer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.fitting.PolynomialCurveFitter;
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
	
	private boolean[] p_curveSet;
	private final int p_polynomialDegree = 5;
	private Map<Integer,List<WeightedObservedPoint>> p_origPowerSpeedValues;
	private Map<Integer,List<Double>> p_powerCurves;
	
	/**
	 * 
	 */
	public AD_Trainer(String t_name, String t_vendor, String t_model, int t_resistanceLevels) {
		this.p_name = t_name;
		this.p_vendor = t_vendor;
		this.p_model = t_model;
		this.p_resistanceLevels = t_resistanceLevels;
		this.p_curveSet = new boolean[t_resistanceLevels];
		
		this.p_origPowerSpeedValues = new HashMap<Integer,List<WeightedObservedPoint>>();
		
		// populate by default
		ArrayList<WeightedObservedPoint> aux;
		for (int i=0; i<p_resistanceLevels; i++) {
			aux = new ArrayList<WeightedObservedPoint>();
			aux.add(new WeightedObservedPoint(1, 0, 0));
			this.p_origPowerSpeedValues.put(new Integer(i),aux);
		}

		this.p_powerCurves = new HashMap<Integer,List<Double>>();		
		// populate by default with a p_polynomialDegree degree polynomial
		ArrayList<Double> t_coefs;
		for (int i=0; i<p_resistanceLevels; i++) {
			t_coefs = new ArrayList<Double>(p_polynomialDegree);
			p_powerCurves.put(new Integer(i),t_coefs);
			p_curveSet[i] = false;
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
	public void setPowerCurveFor(int t_resistanceLevel, List<WeightedObservedPoint> t_speedPower) {
		// storing the original values
		p_curveSet[t_resistanceLevel] = true;
		p_origPowerSpeedValues.put(new Integer(t_resistanceLevel),t_speedPower);
		// computing the power curve for the given resistance level
		PolynomialCurveFitter fitter = PolynomialCurveFitter.create(p_polynomialDegree);
		ArrayList<Double> t_coefs = new ArrayList<Double>();
		for (double coef : fitter.fit(t_speedPower)) {
			// add each coefficient to the arraylist
			t_coefs.add(new Double(coef));
		}
		// finally, setting the coefs for the given resistance level
		p_powerCurves.put(new Integer(t_resistanceLevel),t_coefs);
		
	}

	/* (non-Javadoc)
	 * @see com.adalmagro.trainer.AD_ITrainer#getPowerForSpeed(float, float)
	 */
	@Override
	public double getPowerForSpeed(int t_resistanceLevel, double t_speed) {
		// first check if there is power curve defined for the given resistance level
		if (p_curveSet[t_resistanceLevel] == true) {
			return evaluate(p_powerCurves.get(t_resistanceLevel),t_speed);
		} else {
			return -1;
		}
	}
	
	/**
	 * 
	 * @param t_coefficients
	 * @param x_value
	 * @return
	 */
	private double evaluate(List<Double> t_coefficients, double x_value) {
		// firstly, building the array of doubles
		double[] coefs = new double[t_coefficients.size()];
		for (int i=0; i<t_coefficients.size(); i++) {
				coefs[i] = t_coefficients.get(i).doubleValue();
		}
		// building the polynomial function..
		PolynomialFunction t_polynomy = new PolynomialFunction(coefs);
		// ... and evaluating the x_value for the polynomy
		return t_polynomy.value(x_value);
	}

}
