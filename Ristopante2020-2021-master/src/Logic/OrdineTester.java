package Logic;

/**
 * tester per la classe Ordine
 */
public class OrdineTester {

    public static void main(String[] args) {

        Ordine ordine1 = new Ordine(12);
        Piatto piatto1 = new Piatto("carbonara", 12.50,Piatto.Type.PRIMI );
        Piatto piatto2 = new Piatto("suppli", 5.00, 2, Piatto.Type.ANTIPASTI);
        Piatto piatto3 = new Piatto("tiramisu", 4.50, 4, Piatto.Type.DOLCI);
        Piatto piatto4 = new Piatto("Pollo alla cacciatora", 14.90, 3, Piatto.Type.SECONDI);

        ordine1.AggiungiPiatto(piatto1);
        ordine1.AggiungiPiatto(piatto2);
        ordine1.AggiungiPiatto(piatto3);

        System.out.println(ordine1);
        System.out.println("Expected: tavolo n: 12 stato: 0 piatti: [carbonara 12.5€ , 2 suppli 5.00€ , 4 tiramisu 4.50€ ]");

        System.out.println(ordine1.cercaPiatto(piatto1));
        System.out.println("Expected:carbonara 12.50€");

        System.out.println(ordine1.cercaPiatto(piatto2));
        System.out.println("Expected:2 suppli 5.00€");

        System.out.println(ordine1.cercaPiatto(piatto4));
        System.out.println("null");

        ordine1.RimuoviPiatto(piatto1);
        System.out.println(ordine1);
        System.out.println("Expected: tavolo n: 12 stato: 0 piatti: [2 suppli 5.00€, 4 tiramisu 4.50€]");

        ordine1.RimuoviPiatto(piatto4);
        System.out.println("Expected: Piatto non trovato!");

        System.out.println(ordine1.contienePiatto(piatto2));
        System.out.println("Expected: true");

        System.out.println(ordine1.contienePiatto(piatto4));
        System.out.println("Expected: false");

        System.out.println(ordine1.contienePiatto(piatto1));
        System.out.println("Expected: false");

        ordine1.SvuotaOrdine();
        System.out.println(ordine1);
        System.out.println("Expected: tavolo n: 12 stato: 0 piatti: []");
    }

}
