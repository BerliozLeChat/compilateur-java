import java.util.ArrayList;
import java.util.List;

public class Grammar{
	private Scan scan;
    public Atom sc;
	protected List<Node> rules;
	GPLBuilder gpl;

	public Grammar(){
		rules = new ArrayList<Node>();
	}

	public void add(Node rule){
		rules.add(rule);
	}

	public String imprimerArbre(int idrule){
		StringBuilder buildTree = new StringBuilder();
		imprim_rec(0,buildTree,rules.get(idrule));
		return buildTree.toString();
	}

	public void imprim_rec(int level, StringBuilder builder, Node node){
		for(int i=0; i<level; ++i){
			builder.append("---");
		}
		builder.append(node.toString()+"\n");
		if(node.getLeft() != null){
			imprim_rec(level+1, builder,node.getLeft());
			if(node.getRight() != null)
				imprim_rec(level+1, builder,node.getRight());
		}
	}

	@Override
	public String toString(){
		return null;
	}

	public Grammar Analyse() {
		gpl = new GPLBuilder();
		scan = new Scan();
        Gzero gZ = new Gzero();
        rules = gZ.rules;
        sc = scan.scan();
		boolean res =  mAnalyse(rules.get(0));
		System.out.println(res);
		return gpl.getGrammar();
	}

	public boolean mAnalyse(Node node) {
        if(sc == null)
            return true;
		if (node instanceof Conc) {
			if (mAnalyse(node.getLeft())) {
				return mAnalyse(node.getRight());
			} else {
				return false;
			}
		} else if (node instanceof Union) {
			if (mAnalyse(node.getLeft())) {
				return true;
			} else {
				return mAnalyse(node.getRight());
			}
		} else if (node instanceof Star) {
			return mAnalyse(node.getLeft());
		} else if (node instanceof Un) {
			return mAnalyse(node.getLeft());
		} else if (node instanceof Atom) {
			if (((Atom) node).getAtype() == AtomType.TERMINAL) {
				if (((Atom) node).getChaine().equals(sc.getChaine())) {
					if (((Atom) node).getAction() != 0) {
                        gpl.goAction(((Atom) node).getAction(),sc);
                    }
                    System.out.println(sc.toString()+" OK");
                    sc = scan.scan();
					return true;
				} else
					return false;
			} else if (((Atom) node).getAtype() == AtomType.NONTERMINAL) {
				if (mAnalyse(rules.get(((Atom) node).getCode()))) {
					if (((Atom) node).getAction() != 0)
						gpl.goAction(((Atom) node).getAction(),sc);
					return true;
				} else
					return false;

			}
		}
        return false;
	}
	

	public static void main(String[] args) {
        Grammar g = new Grammar();
        g.Analyse();
	}

} 
