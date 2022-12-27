package Logic;


import java.util.ArrayList;

/**
 * Classe che implementa la figura del cuoco (evadi tavolo)
 */
public class CuocoLogic {


    private Data data = new Data();


    public ArrayList<Ordine> GetOrdiniCuoco(){
        return data.getOrdini();
    }

    /**
     * Metodo per evadere un tavolo dopo che tutti i suoi piatti sono stati serviti
     * @param Tavolo tavolo da evadere
     */
    public void EvadiTavolo(String Tavolo){
        for(int i=0;i<data.getOrdini().size();i++){
            if(data.getOrdini().get(i).getTavoloID()==Integer.parseInt(Tavolo)){
                data.getOrdini().get(i).setStato(2);
                break;
            }
        }
    }

}
