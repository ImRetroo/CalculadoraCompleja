package es.yorman.dad.calculadora;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Complejo {
	
	private DoubleProperty real = new SimpleDoubleProperty();
	private DoubleProperty imaginario = new SimpleDoubleProperty();
	
	public Complejo() {}

	public Complejo(double real, double imaginario) {
		setReal(real);
		setImaginario(imaginario);
	}
	
	public double getReal() {
		return real.get();
	}

	public void setReal(double real) {
		this.real.set(real);;
	}

	public double getImaginario() {
		return imaginario.get();
	}

	public void setImaginario(double complejo) {
		this.imaginario.set(complejo);
	}
	
	public DoubleProperty realProperty() {
		return real;
	}
	
	public DoubleProperty imaginarioProperty() {
		return imaginario;
	}

	@Override
	public String toString() {
		return "Complejo [real=" + getReal() + ", imaginario=" + getImaginario() + "]";
	}
}