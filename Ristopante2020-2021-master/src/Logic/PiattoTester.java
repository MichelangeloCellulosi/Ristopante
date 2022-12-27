package Logic;

public class PiattoTester {
    public static void main(String[] args) {

        Piatto piatto1 = new Piatto("carbonara", 12.50,Piatto.Type.PRIMI);
        Piatto piatto2 = new Piatto("suppli", 5.00, 2, Piatto.Type.ANTIPASTI);
        Piatto piatto3 = new Piatto("tiramisu", 4.50, 4, Piatto.Type.DOLCI);

        System.out.println("piatto 1 = nome: " + piatto1.getNome() + " prezzo: " + piatto1.getPrezzo() + " quantita: " + piatto1.getQuantita() + " tipo: " + piatto1.getTipologia());
        System.out.println("Expected: piatto 1 = nome: carbonara prezzo: 12.50 quantita: 1 tipo: PRIMI");

        System.out.println("piatto 2 = nome: " + piatto2.getNome() + " prezzo: " + piatto2.getPrezzo() + " quantita: " + piatto2.getQuantita() + " tipo: " + piatto2.getTipologia());
        System.out.println("Expected: piatto 2 = nome: suppli prezzo: 5.00 quantita: 2, tipo: ANTIPASTI");

        System.out.println("piatto 3 = nome: " + piatto3.getNome() + " prezzo: " + piatto3.getPrezzo() + " quantita: " + piatto3.getQuantita() + " tipo: " + piatto3.getTipologia());
        System.out.println("Expected: piatto 3 = nome: tiramisu prezzo: 4.50 quantita: 4, tipo: DOLCI");

        piatto3.setNome("Pollo alla cacciatora");
        piatto3.setPrezzo(14.90);
        piatto3.setQuantita(3);
        piatto3.setTipologia(Piatto.Type.SECONDI);

        System.out.println("piatto 3 = nome: " + piatto3.getNome() + " prezzo: " + piatto3.getPrezzo() + " quantita: " + piatto3.getQuantita() + " tipo: " + piatto3.getTipologia());
        System.out.println("Expected: piatto 3 = nome: Pollo alla cacciatora prezzo: 14.90 quantita: 3, tipo: SECONDI");
    }
}
