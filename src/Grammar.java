import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Grammar{
	private List<Node> rules;

	public Grammar(){
		rules = new ArrayList<Node>();
	}

	public void add(Node rule){
		rules.add(rule);
	}

	public String imprimerArbre(int idrule){
        StringBuilder buildTree = new StringBuilder();
        Node node;
        String niveau = "";
	    Node rule = rules.get(idrule);
        Queue<Node> file = new ArrayDeque<>();

        file.offer(rule);
        //affichage de la racine
        buildTree.append(niveau+"--->"+rule.toString()+"\n");

        while(!file.isEmpty()){
            niveau += "---"; //étage suivant
            node = file.poll();
            if(node.getLeft()!=null) {
                file.offer(node.getLeft());
                buildTree.append(niveau+"--->"+node.getLeft().toString()+"\n");
                if(node.getRight()!=null) {
                    file.offer(node.getRight());
                    buildTree.append(niveau+"--->"+node.getRight().toString()
                            +"\n");
                }
            }
        }
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

    public static void main(String[] args) {
        Grammar test = new Grammar();

        test.add(new Atom('A',0,AtomType.TERMINAL));

        test.add(new Conc(new Union(new Atom('B',0,AtomType.TERMINAL),new
                Atom('B',0,AtomType.TERMINAL)),new Atom('C',0,AtomType.TERMINAL)));


        System.out.println(test.imprimerArbre(0));
        System.out.println(test.imprimerArbre(1));

			StringBuilder buildstr = new StringBuilder();
			test.imprim_rec(0, buildstr,new Conc(new Union(new Atom('B',0,AtomType.TERMINAL),new
                Atom('B',0,AtomType.TERMINAL)),new Atom('C',0,AtomType.TERMINAL)));
			System.out.println(buildstr.toString());

    }

} 
