package EigeneKlassen;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * this class is for modeling the scale of a DMU
 * 
 * @author Christoph Meyer
 *
 */

public class Scale implements Iterator<Double>{
	private TreeSet<Double> scale;
	
	/**
	 * creates the scale of a program
	 * @param program PossibilisticProgram
	 */
	
	public Scale (PossibilisticProgram program) {
		this.scale = new TreeSet<Double>();
		this.scale.add(0.0);
		for(PossibilisticRule rule : program) {
			this.scale.add(rule.getNecessity());
		}
	}
	/**
	 * creates new scale with single element 0.0
	 */
	
	public Scale () {
		this.scale = new TreeSet<Double>();
		this.scale.add(0.0);
	}
	
	public void add(Double necessity) {
		this.scale.add(necessity);
	}
	
	public Iterator<Double> iterator(){
		return this.scale.iterator();
	}

	@Override
	public boolean hasNext() {
		if (this.scale.iterator().hasNext()) {
			return true;
		}
		return false;
	}

	@Override
	public Double next() {
		if (this.scale.iterator().hasNext()) {
			return this.scale.iterator().next();
		}
		return null;
	}
	
	/**
	 * converts scale to array for computing upper bound in decision making
	 * @return result Double[]
	 */
	
	public Double [] toArray() {
		Double[] result = this.scale.toArray(new Double[this.scale.size()]);
		return result;
	}
}
