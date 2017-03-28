import java.util.ArrayList;
import java.util.List;

public class Grammar{
	private Scan scan;
	protected List<Node> rules;

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
	
	public boolean Analyse() {
		scan = new Scan();
        Gzero gZ = new Gzero();
        rules = gZ.rules;
        Atom sc = scan.scan();
		return mAnalyse(rules.get(0), sc);
	}

	public boolean mAnalyse(Node node, Atom sc) {
		if (node instanceof Conc) {
			if (mAnalyse(node.getLeft(), sc)) {
				return mAnalyse(node.getRight(),sc );
			} else {
				return false;
			}
		} else if (node instanceof Union) {
			if (node.getLeft() != null) {
				return true;
			} else {
				return mAnalyse(node.getRight(), sc);
			}
		} else if (node instanceof Star) {
			return mAnalyse(node.getLeft(),sc);
		} else if (node instanceof Un) {
			return mAnalyse(node.getLeft(),sc);
		} else if (node instanceof Atom) {
			if (((Atom) node).getAtype() == AtomType.TERMINAL) {
				if (((Atom) node).getChaine().equals(sc.getChaine())) {
					if (((Atom) node).getAction() != 0) {
                        //((Atom) node).getAction().exec();
                    }
                    System.out.println(sc.toString()+" OK");
                    sc = scan.scan();
                    if(sc == null)
                        return true;
					//return mAnalyse(rules.get(0),sc);
				} else
					return false;
			} else if (((Atom) node).getAtype() == AtomType.NONTERMINAL) {
				if (mAnalyse(rules.get(((Atom) node).getCode()),sc)) {
					//if (((Atom) node).getAction() != 0)
						//((Atom) node).getAction().exec();
					return true;
				} else
					return false;

			}
		}
        return false;
	}
	

	public static void main(String[] args) {
        Grammar g = new Grammar();
        System.out.println(g.Analyse());
	}

} 
