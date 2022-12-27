package Logic;

import java.util.ArrayList;

/**
 * classe rappresentante un ordine fatto ad un tavolo del ristorante
 */
public class Ordine {

    private int stato;    // stato dell'ordine 1: inserito, 2: evaso, 3: pagato
    private int tavoloID; //identificatore del tavolo a cui l'ordine appartiene
    private ArrayList<Piatto> piatti; // lista dei piatti contenuti nell'ordine

    /**
     * costruttore della classe ordine, stato verra inizializzato a 0
     * @param tavoloID identificatore del tavolo
     */
    public Ordine(int tavoloID){
        this.stato = 0;
        this.tavoloID = tavoloID;
        this.piatti = new ArrayList<>();
    }

    /**
     * metodo per aggiungere un piatto all'ordine
     * @param piatto piatto da aggiungere
     */
    public void AggiungiPiatto(Piatto piatto){
        piatti.add(piatto);
    }

    /**
     * cerca se esiste un dato piatto nell'ordine e lo ritorna, se non esiste ritorna null
     * @param piattoDaCercare piatto da cerca all'interno dell'ordine
     * @return oggetto piatto se lo trova oppure null
     */
    public Object cercaPiatto(Piatto piattoDaCercare){
        String nomePiatto = piattoDaCercare.getNome();

        for(Piatto piatto : this.piatti){
            if(piatto.getNome().equals(nomePiatto)){
                return piatto;
            }
        }
        return null;
    }

    /**
     * controlla se un piatto e' contenuto
     * @param piattoDaCercare
     * @return true se trova il piatto senno false
     */
    public boolean contienePiatto(Piatto piattoDaCercare){
        String nomePiatto = piattoDaCercare.getNome();

        for(Piatto piatto : this.piatti){
            if(piatto.getNome().equals(nomePiatto)){
                return true;
            }
        }
        return false;
    }

    /**
     * rimuove un piatto dall'ordine
     * @param piatto piatto da rimuovere
     */
    public void RimuoviPiatto(Piatto piatto){

        var obj = this.cercaPiatto(piatto);

        if(obj != null){
            piatti.remove(obj);
        }
        else{
            System.out.println("Piatto non trovato!");
        }
    }

    /**
     * svuota un ordine
     */
    public void SvuotaOrdine(){
        this.piatti.clear();
    }

    @Override
    public String toString(){
        return "tavolo n: " + tavoloID + " stato: " + stato + " piatti: " + piatti;
    }

    public int getStato() {
        return stato;
    }

    public void setStato(int stato) {
        this.stato = stato;
    }

    public int getTavoloID() {
        return tavoloID;
    }

    public void setTavoloID(int tavoloID) {
        this.tavoloID = tavoloID;
    }

    public ArrayList<Piatto> getPiatti() {
        return piatti;
    }

    public void setPiatti(ArrayList<Piatto> piatti) {
        this.piatti = piatti;
    }
}
