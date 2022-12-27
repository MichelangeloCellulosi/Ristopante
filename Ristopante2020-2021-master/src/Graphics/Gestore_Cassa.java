package Graphics;

import Logic.Gestore_CassaLogic;
import Logic.Ordine;
import Logic.Piatto;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.*;
import java.util.ArrayList;


/**
 *  Classe che implementa graficamente la figura del Gestore Cassa
 */

public class Gestore_Cassa extends JPanel{
    private Gestore_CassaLogic gestore_cassaLogic= new Gestore_CassaLogic();
    private JPanel GestoreCassaPanel;
    private JList TavoliList;
    private JPanel TavoliPanel;
    private JButton checkOutTavoloSelezionatoButton;
    private JButton pagaButton;
    private JButton indietroButton;
    private JPanel PiattiPanel;

    private JLabel Tot;
    private JFrame frame;
    private final DefaultListModel<Integer> OrdiniModel= new DefaultListModel<>();



    public Gestore_Cassa(){
        frame= new JFrame("GestoreCassa");
        frame.setBounds(400, 300, 1000, 600);
        frame.setContentPane(GestoreCassaPanel);
        LoadOrdiniList();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        PiattiPanel.setLayout(new BoxLayout(PiattiPanel,BoxLayout.Y_AXIS));

        /**
         * Listener del bottone per tornare alla HomePage
         */
        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                HomePage homePage= new HomePage();
            }
        });

        /**
         * Listener del bottone per caricare e visuallizare  i piatti del tavolo selezionato
         */
        checkOutTavoloSelezionatoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!(OrdiniModel.isEmpty())){
                    if(!(TavoliList.isSelectionEmpty())) {
                        if (PiattiPanel.getComponents().length == 0) {
                            ArrayList<Piatto> PiattiTavolo = LoadPiattiOrdine((Integer) TavoliList.getSelectedValue());

                            Tot.setText(String.valueOf(CalcolaTot(PiattiTavolo))+"€");
                        } else {
                            JOptionPane.showMessageDialog(frame, "Piatti già caricati!");
                        }
                    }
                }else{
                    JOptionPane.showMessageDialog(frame, "Nessun piatto da pagare");
                }

            }
        });

        /**
         * Listener dell bottone per procedere al pagamento del tavolo e la creazione della rispettiva ricevuta
         */
        pagaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrinterJob pj = PrinterJob.getPrinterJob();
                pj.setPrintable(new Ricevuta(), gestore_cassaLogic.CreaPageFormat(pj));
                if(pj.printDialog()) {
                    try {
                        pj.print();
                        OrdiniModel.removeElement(TavoliList.getSelectedValue());
                        PiattiPanel.removeAll();
                        frame.revalidate();
                        frame.repaint();
                        Tot.setText("");
                    } catch (PrinterException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * Metodo che crea la lista degli ordini pronti per eseere evasi
     */
    public void LoadOrdiniList(){
        if (!(gestore_cassaLogic.GetOrdini().isEmpty())) {
            for (Ordine o : gestore_cassaLogic.GetOrdini()) {
                if (o.getStato() == 2) {
                    OrdiniModel.addElement(o.getTavoloID());
                }
            }
            TavoliList = new JList();
            TavoliList.setModel(OrdiniModel);
            TavoliPanel.add(TavoliList);
        }
    }

    /**
     * Metodo per aggiungere al panel i piatti del tavolo selezionato
     * @param NTavolo int Id del tavolo selezionato
     * @return arraylist<Piatto> lista dei piatti del tavolo selezionato
     */
    public ArrayList<Piatto> LoadPiattiOrdine(int NTavolo){
        for(Ordine o: gestore_cassaLogic.GetOrdini()){
            if(o.getTavoloID()==NTavolo){
                for(Piatto p: o.getPiatti()){
                    PiattiPanel.add(Box.createVerticalStrut(20));
                    PiattiPanel.add(new JLabel(p.getNome()+"   "+p.getPrezzo()+"€"));
                    PiattiPanel.add(Box.createVerticalStrut(20));
                }
                return o.getPiatti();
            }
        }
        return null;
    }

    /**
     * Metodo che calcola il totale da pagare del tavolo selezionato
     * @return tot double totale da pagare
     */
    public double CalcolaTot(ArrayList<Piatto> piatti){
        double tot=0;
        for(int i=0; i<piatti.size();i++){
            tot+=piatti.get(i).getPrezzo();
        }
        return tot;
    }

    /**
     * Classe che si occupa di creare graficamente la ricevuta del tavolo selezionato
     */
    public class Ricevuta implements Printable {
        private final int iva=22;
        @Override
        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
            int ris = NO_SUCH_PAGE;
            if (pageIndex == 0) {
                Graphics g2d = (Graphics2D) graphics;
                g2d.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());
                try {
                    int y = 20;
                    int yShift = 10;
                    int headerRectHeight = 10;
                    double sum = 0;
                    g2d.setFont(new Font("Monospaced", Font.PLAIN, 9));
                    g2d.drawString("-------------------------------------", 12, y);
                    y += yShift;
                    g2d.drawString("        Ricevuta Ristopante        ", 12, y);
                    y += yShift;
                    g2d.drawString("-------------------------------------", 12, y);
                    y += headerRectHeight;

                    g2d.drawString("-------------------------------------", 10, y);
                    y += yShift;
                    g2d.drawString("   Piatto                 Prezzo   ", 10, y);
                    y += yShift;
                    g2d.drawString("-------------------------------------", 10, y);
                    y += headerRectHeight;
                    ArrayList<Piatto> Piatti = getPiatti((Integer) TavoliList.getSelectedValue());
                    for(int i=0; i<Piatti.size();i++){
                        g2d.drawString(" " + Piatti.get(i).getNome() + "                     ",10, y);
                        g2d.drawString("     "+Piatti.get(i).getPrezzo()+"€",130,y);
                        sum+=Piatti.get(i).getPrezzo();
                        y += yShift;
                    }
                    g2d.drawString("-------------------------------------", 10, y);
                    y += yShift;
                    g2d.drawString(" Totale: " + sum + "€               ", 10, y);
                    y += yShift;
                    g2d.drawString(" Di cui IVA 22%: " + (sum*iva)/100 + "€              ", 10, y);
                    y += yShift;
                    g2d.drawString("-------------------------------------", 10, y);
                    y += yShift;
                    g2d.drawString("    Contatti: ristopante@gmail.com   ", 10, y);
                    y += yShift;
                    g2d.drawString("*************************************", 10, y);
                    y += yShift;
                    g2d.drawString("          GRAZIE E ARRIVEDERCI   ", 10, y);
                    y += yShift;
                    g2d.drawString("*************************************", 10, y);
                    y += yShift;
                } catch (Exception r) {
                    r.printStackTrace();
                }
                ris = PAGE_EXISTS;
            }
            return ris;
        }
    }


    public ArrayList<Piatto> getPiatti(int tavoloID) {
        if (!(gestore_cassaLogic.GetOrdini().isEmpty())) {
            for (Ordine o : gestore_cassaLogic.GetOrdini()) {
                if (o.getTavoloID() == tavoloID) {
                    return o.getPiatti();
                }
            }
        }
        return null;
    }

}
