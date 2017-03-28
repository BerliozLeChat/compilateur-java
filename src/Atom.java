public class Atom extends Node{

    private Integer code;
    private int action;
    private AtomType atype;
    private String chaine;
    private String valeur;

	public Atom(Integer code,int action,AtomType atype, String chaine){
	    super(null,null);
		this.code = code;
		this.action = action;
		this.atype = atype;
        this.chaine = chaine;
	}

    public Atom(Integer code,int action,AtomType atype, String chaine, String
            valeur){
        super(null,null);
        this.code = code;
        this.action = action;
        this.atype = atype;
        this.chaine = chaine;
        this.valeur = valeur;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public void setAtype(AtomType atype) {
        this.atype = atype;
    }

    public String getChaine() {
        return chaine;
    }

    public void setChaine(String chaine) {
        this.chaine = chaine;
    }

    public Integer getCode() {
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
	    return "Atom : "+code+" "+action+" "+atype+" "+chaine;
    }

    @Override
    public String afficher(String tmp) {
        return null;
    }
}
