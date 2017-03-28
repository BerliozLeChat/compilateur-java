import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Scan{
	public List<String> symboles = new ArrayList<String>();
    public List<String> vocabulairet = new ArrayList<String>();
    public List<String> vocabulairent = new ArrayList<String>();
	private String rule;

	public Scan(){
		System.out.println("Entrez la règle à scanner");
		Scanner in = new Scanner(System.in);
		rule = in.nextLine();
		in.close();
	}

	public Atom scan(){
		return scan_rec(rule,"");
	}
	
	private Atom scan_rec(String rule, String acc) {
		if (!rule.isEmpty()) {
			char first = rule.charAt(0);
			if (first == '=') {
				if (acc.equals("::")) {
					acc = "";
					symboles.add("::=");
					this.rule = rule.substring(1);
					return new Atom(null, 0, AtomType.TERMINAL, "::=");
					//scan_rec(rule.substring(1), acc);
				} else {
					System.out.println("Symbole non reconnu");
					//return scan_rec(rule.substring(1), acc);
				}
			} else if (first == '+' || first == '[' || first == ']' || first == '.' || first == '(' || first == ')') {
				if (!acc.isEmpty()) {
					vocabulairent.add(acc);
					return new Atom(null, 0, AtomType.NONTERMINAL,"IDNTER",
							""+acc);

				}

				symboles.add("" + first);
				this.rule = rule.substring(1);
				return new Atom(null, 0, AtomType.TERMINAL, "" + first);
				//scan_rec(rule.substring(1), acc);
			} else if (first == ':') {
				if (!acc.isEmpty() && !acc.equals(":")) {
					vocabulairent.add(acc);
					//this.rule = rule.substring(1);
					return new Atom(null, 0, AtomType.NONTERMINAL,"IDNTER",
                            acc);
				}
				acc += first;
				this.rule = rule.substring(1);
				return scan_rec(this.rule, acc);
			}
			//Si c'est pas un symbole
			else if (first == '"') {
				if (acc.isEmpty()) {
				    this.rule = rule.substring(1);
                    return scan_rec(this.rule, acc);
                }
				else {
					vocabulairet.add(acc);
					this.rule = rule.substring(1);
					return new Atom(null, 0, AtomType.TERMINAL,"ELTER", acc);
					//scan_rec(rule.substring(1), acc);
				}

			} else {
				acc += first;
                this.rule = rule.substring(1);
				return scan_rec(this.rule, acc);
			}
		} else {
			vocabulairent.add(acc);
			this.rule = rule.substring(1);
			return new Atom(null, 0, AtomType.TERMINAL, acc);
		}
		return null;
	}
	
	public static void main(String[] args){
		Scan sc = new Scan();
		sc.scan();
		
		System.out.println("Symboles : ");
		for(String s : sc.symboles)
			System.out.println(s);
		System.out.println("Vocabulaire non terminal : ");
		for(String st : sc.vocabulairent)
			System.out.println(st);
        System.out.println("Vocabulaire terminal : ");
        for(String snt : sc.vocabulairet)
            System.out.println(snt);
	}
}
