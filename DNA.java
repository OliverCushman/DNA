import java.util.Scanner;

public class DNA {
    
    private int CODON_LENGTH = 3;

    public DNA() {

    }

    public static void main(String[] args) {
        System.out.println("--Enter template DNA strand--");

        Scanner scanner =  new Scanner(System.in);
        String template = scanner.nextLine();
        scanner.close();

        DNA dna = new DNA();

        template = dna.process(template);

        String newDNA = dna.newStrand(template, true);
        String mRNA = dna.newStrand(template, false);
        String tRNA = dna.newStrand(mRNA, false);

        System.out.println("Template DNA strand:   " + template);
        System.out.println("Coding DNA strand:     " + newDNA);
        System.out.println("Processed mRNA strand: " + dna.separateCodons(mRNA));
        System.out.println("tRNA anticodons:       " + dna.separateCodons(tRNA));
    }

    public String newStrand(String sequence, boolean isDNA) {
        String base;
        String strand = "";

        for (int i = 0; i < sequence.length(); i++) {
            base = sequence.substring(i, i + 1);
            strand += complementaryBase(base, isDNA);
        }

        return strand;
    }

    public String process(String sequence) {
        String[] sequenceArr = sequence.split("");
        String base;

        for (int i = 0; i < sequence.length(); i++) {
            base = sequenceArr[i];

            if (complementaryBase(base, true).equals("")) {
                sequenceArr[i] = "";
            }
        }

        return String.join("", sequenceArr);
    }

    public String complementaryBase(String base, boolean isDNA) {
        if (base.equals("A") && isDNA) return "T";
        else if (base.equals("A") && !isDNA) return "U";
        else if (base.equals("T") || base.equals("U")) return "A";
        else if (base.equals("C")) return "G";
        else if (base.equals("G")) return "C";
        return "";
    }

    public String separateCodons(String sequence) {
        String separatedStrand = "";
        for (int i = 0; i < sequence.length(); i++) {
            separatedStrand += sequence.substring(i, i + 1);
            if ((i + 1) % CODON_LENGTH == 0 && i < sequence.length() - 1) separatedStrand += "-";
        }
        return separatedStrand;
    }
}