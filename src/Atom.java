public class Atom extends Node{

    private char code;
    private int action;
    private AtomType atype;

	public Atom(char code,int action,AtomType atype){
	    super(null,null);
		this.code = code;
		this.action = action;
		this.atype = atype;
	}

    public char getCode() {
        return code;
    }

    public int getAction() {
        return action;
    }

    public AtomType getAtype() {
        return atype;
    }

    @Override
    public String toString(){
	    return "Atom : "+code+" "+action+" "+atype;
    }

    @Override
    public String afficher(String tmp) {
        return null;
    }
}