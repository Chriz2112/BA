package EigeneKlassen;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Scale implements Iterator<Double>{
	private TreeSet<Double> scale;
	
	public Scale (PossibilisticProgram program) {
		this.scale = new TreeSet<Double>();
		for(PossibilisticRule rule : program) {
			this.scale.add(rule.getNecessity());
		}
	}
	
	public Scale () {
		this.scale = new TreeSet<Double>();
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
	
	public Double [] toArray() {
		return (Double[]) this.scale.toArray();
	}
}
