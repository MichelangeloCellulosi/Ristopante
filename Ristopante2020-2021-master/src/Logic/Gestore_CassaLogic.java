package Logic;



import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterJob;
import java.util.ArrayList;

/**
 * Classe che implementa logicamente la figura del Gestore Cassa
 */
public class Gestore_CassaLogic {
    private Data data = new Data();


    public ArrayList<Ordine> GetOrdini(){
        return data.getOrdini();
    }

    /**
     * Metodo per creare il format della ricevuta utilizzata per il pagamento dei tavoli
     * @param pj printerjob corrispondente per la creazione della ricevuta
     * @return pf pageformat format della ricevuta
     */
    public PageFormat CreaPageFormat(PrinterJob pj){
        PageFormat pf = new PageFormat();
        Paper paper = pf.getPaper();
        double centroAltezza = 8.0;
        double sopraAltezza = 2.0;
        double bassoAltezza= 2.0;
        double larghezza = (8*0.393600787)*72d;
        double altezza= ((centroAltezza+sopraAltezza+bassoAltezza)*0.393600787)*72d;
        paper.setSize(larghezza,altezza);
        paper.setImageableArea(
                20,
                10,
                larghezza,
                altezza-((1*0.393600787)*72d)
        );
        pf.setOrientation(PageFormat.PORTRAIT);
        pf.setPaper(paper);
        return pf;
    }

}
