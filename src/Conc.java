/**
 * Opérateur de concaténation entre deux nodes
 */
public class Conc extends Node{

	public Conc(Node left, Node right){
		super(left,right);
	}

    @Override
    public String toString(){
	    return "Conc";
    }

}
