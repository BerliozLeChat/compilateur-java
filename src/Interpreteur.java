import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Interpreteur {

    private List<String> pcode;
    private List<Integer> pilex;
    private int c0 = 0;
    private int spx;
    private Scanner scan;

    public Interpreteur(List<String> pcode, List<Integer>pilex, int spx){
        this.pcode = pcode;
        this.pilex = pilex;
        this.spx = spx;
        this.scan = new Scanner(System.in);
    }

    public void exec() throws Exception{
        while(!pcode.get(c0).equals("STOP")){
            interprete();
        }
        scan.close();
    }

    private void interprete() throws Exception {
        if (pcode.get(c0).equals("LDA")) {
            spx++;
            pilex.set(spx, Integer.parseInt(pcode.get(c0 + 1)));
            c0 = c0 + 2;
        } else if (pcode.get(c0).equals("LDV")) {
            spx++;
            pilex.set(spx, pilex.get(Integer.parseInt(pcode.get(c0 + 1))));
            c0 = c0 + 2;
        } else if (pcode.get(c0).equals("LDC")) {
            spx++;
            pilex.set(spx, Integer.parseInt(pcode.get(c0 + 1)));
            c0 = c0 + 2;
        } else if (pcode.get(c0).equals("RD")) {
            spx++;
            pilex.set(spx, scan.nextInt());
            c0 = c0 + 1;
        } else if (pcode.get(c0).equals("AFF")) {
            pilex.set(pilex.get(spx - 1), pilex.get(spx));
            c0 = c0 + 1;
            spx = spx - 2;
        } else if (pcode.get(c0).equals("WRT")) {
            System.out.print(pilex.get(spx));
            spx = spx - 1;
            c0 = c0 + 1;
        } else if (pcode.get(c0).equals("WRTLN")) {
            System.out.println(pilex.get(spx));
            spx = spx - 1;
            c0 = c0 + 1;
        } else if (pcode.get(c0).equals("JMP")) {
            c0 = Integer.parseInt(pcode.get(c0 + 1));
        } else if (pcode.get(c0).equals("JIF")) {
            if (pilex.get(spx) == 1) {
                c0 = c0 + 2;
                spx = spx - 1;
            } else if (pilex.get(spx) == 0) {
                c0 = Integer.parseInt(pcode.get(c0 + 1));
                spx = spx - 1;
            } else {
                throw new Exception("JIF : pilex devait contenir la valeur 0 " +
                        "ou 1, valeur reçu : " + pilex.get(spx));
            }
        } else if (pcode.get(c0).equals("INFE")) {
            if (pilex.get(spx - 1) <= pilex.get(spx)) {
                spx = spx - 1;
                pilex.set(spx, 1);
            } else {
                spx = spx - 1;
                pilex.set(spx, 0);
            }
            c0 = c0 + 1;
        } else if (pcode.get(c0).equals("INF")) {
            if (pilex.get(spx - 1) < pilex.get(spx)) {
                spx = spx - 1;
                pilex.set(spx, 1);
            } else {
                spx = spx - 1;
                pilex.set(spx, 0);
            }
            c0 = c0 + 1;
        } else if (pcode.get(c0).equals("SUPE")) {
            if (pilex.get(spx - 1) >= pilex.get(spx)) {
                spx = spx - 1;
                pilex.set(spx, 1);
            } else {
                spx = spx - 1;
                pilex.set(spx, 0);
            }
            c0 = c0 + 1;
        } else if (pcode.get(c0).equals("SUP")) {
            if (pilex.get(spx - 1) > pilex.get(spx)) {
                spx = spx - 1;
                pilex.set(spx, 1);
            } else {
                spx = spx - 1;
                pilex.set(spx, 0);
            }
            c0 = c0 + 1;
        } else if (pcode.get(c0).equals("EG")) {
            if (pilex.get(spx - 1) == pilex.get(spx)) {
                spx = spx - 1;
                pilex.set(spx, 1);
            } else {
                spx = spx - 1;
                pilex.set(spx, 0);
            }
            c0 = c0 + 1;
        } else if (pcode.get(c0).equals("ADD")) {
            int add = pilex.get(spx)+pilex.get(spx-1);
            pilex.set(spx - 1, add);
            c0 = c0 + 1;
            spx = spx - 1;
        } else if (pcode.get(c0).equals("SUB")) {
            int add = pilex.get(spx-1)-pilex.get(spx);
            pilex.set(spx - 1, add);
            c0 = c0 + 1;
            spx = spx - 1;
        } else if (pcode.get(c0).equals("INC")) {
            pilex.set(spx, pilex.get(spx)+1);
            c0 = c0 + 1;
        }
         else if (pcode.get(c0).equals("DEC")) {
            pilex.set(spx, pilex.get(spx)-1);
            c0 = c0 + 1;
        }
        else{
            throw new Exception("Instruction inconnu : "+pcode.get(c0));
        }
    }

    public static void main(String[] args) {
        List<String> pcode = new ArrayList<String>(Arrays.asList("LDA","2","RD",
                "AFF","LDA","1","LDC","0","AFF","LDA","0","LDC","1","AFF",
                "LDV","0","LDV","2","INFE","JIF","37","LDA","1","LDV","0",
                "LDV","1","ADD","AFF","LDA","0","LDV","0","INC","AFF","JMP",
                "14","LDV","1","WRTLN","STOP"));

        List<Integer> pilex = new ArrayList<Integer>(Arrays.asList(0,0,0,0,0,0,0,0,
                0,0,0,0,0,0,0,0,0,0,0,0,0,0));

        int spx = 2;

        Interpreteur i = new Interpreteur(pcode,pilex,spx);
        try {
            i.exec();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
