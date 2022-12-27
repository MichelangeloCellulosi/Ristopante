package Logic;

import java.util.ArrayList;

/**
 * classe che implementa il ruolo del cameriere(aggiungere, modificare, eliminare piatti dall'ordine)
 */
public class CameriereLogic {

    private static Data data = new Data(); //creo un istanza di data

    /**
     * costruttore di cameriere, chiama Load per caricare il menu
     */
    public CameriereLogic(){
        Load();
    }

    /**
     * creo ordine del tavolo e lo restituisco
     * @param numTavolo ID del tavolo associato all'ordine
     * @return
     */
    public Ordine CreaOrdine(int numTavolo){
        Ordine nuovoOrdine = new Ordine(numTavolo);
        return nuovoOrdine;
    }

    /**
     * aggiunge un piatto all'ordine
     * @param ordine ordine alla quale aggiungere il piatto
     * @param piatto piatto da aggiungere
     */
    public void AggiungiPiatto(Ordine ordine, Piatto piatto){
        ordine.AggiungiPiatto(piatto);
    }

    /**
     * rimuove un piatto dall'ordine
     * @param ordine ordine da cui rimuovere il piatto
     * @param piatto piatto da rimuovere
     */
    public void RimuoviPiatto(Ordine ordine, Piatto piatto){
        ordine.RimuoviPiatto(piatto);
    }

    /**
     * modifica un piatto dell'ordine
     * @param ordine ordine che contiene il piatto da modificare
     * @param piattoVecchio piatto da modificare
     * @param piattoNuovo piatto modificato
     */
    public void ModificaPiatto(Ordine ordine, Piatto piattoVecchio, Piatto piattoNuovo){
        ordine.RimuoviPiatto(piattoVecchio);
        ordine.AggiungiPiatto(piattoNuovo);
    }

    /**
     * modifica quantita di un dato piatto
     * @param piatto piatto da cui modificare la quantita
     * @param nuovaQuantita quantita da inserire al piatto
     */
    public void ModificaQuantita(Piatto piatto, int nuovaQuantita){
        piatto.setQuantita(nuovaQuantita);
    }

    /**
     * finalizza l'ordine cambiandone lo stato ad inserito e lo passa a data
     * @param ordine ordine da finalizzare
     */
    public void FinalizzaOrdine(Ordine ordine){
        ordine.setStato(1);
        Data.loadOrdini(ordine);
    }

    /**
     * getter del menu
     * @return menu
     */
    public ArrayList<Piatto> getMenu(){
        return data.getMenu();
    }

    /**
     * esegue il caricamento del menu
     */
    public void Load(){
        data.loadMenu();
    }
}
