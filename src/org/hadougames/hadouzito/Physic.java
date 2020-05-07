package org.hadougames.hadouzito;

public class Physic {
	private double gravity = 1.15;
	private double speed = 1;

	public double getGravityFactor() {
		double factor = 0;
		speed *= gravity;
		factor = speed;
		
		return factor;
	}
}
