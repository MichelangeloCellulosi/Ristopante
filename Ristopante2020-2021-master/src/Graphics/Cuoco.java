package Graphics;

import Logic.CuocoLogic;
import Logic.Ordine;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Classe che implementa graficamente la figura del cuoco
 */
public class Cuoco extends JPanel{
    private JPanel CuocoPanel;
    private JButton mostraPiatti;
    private JPanel CheckPanel;
    private JButton EvadiPiatto;
    private JButton indietroButton;
    private ArrayList<ArrayList<JCheckBox>> cb= new ArrayList<ArrayList<JCheckBox>>();
    private JFrame frame;
    private CuocoLogic cuoco = new CuocoLogic();
    private ActionListener actionListener;




    public Cuoco() {
        frame = new JFrame("Cuoco");
        frame.setBounds(400, 300, 1000, 600);
        frame.setContentPane(CuocoPanel);
        CheckPanel.setLayout(new BoxLayout(CheckPanel,BoxLayout.Y_AXIS));


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);

        /**
         * Listener del bottone per tornare al menù principale
         */
        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                HomePage homePage= new HomePage();
            }
        });

        /**
         * Listener delle checkbox dei vari piatti per rimuovere l'ordine una volta selezionate tutte le checkbox
         * dell'ordine corrispondente
         */
        actionListener= new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=0;i<cb.size();i++) {
                    for(int j=0;j<cb.get(i).size()-1;j++){
                        if(cb.get(i).get(j).isSelected()){
                            cb.get(i).get(j).setEnabled(false);
                        }
                    }
                    Stream<JCheckBox> stream = cb.get(i).stream();
                    boolean contains= stream.limit(cb.get(i).size()-1).anyMatch(Component::isEnabled);
                    if(!(contains)){
                        JOptionPane.showMessageDialog(frame, "Ordine del tavolo "+cb.get(i).get(cb.get(i).size()-1).getText()+" è stato completato, procedo all'evasione");
                        cuoco.EvadiTavolo(cb.get(i).get(cb.get(i).size()-1).getText());
                        Pulisci(cb.get(i),cb.get(i).get(cb.get(i).size()-1).getText());
                        cb.remove(i);
                        frame.revalidate();
                        frame.repaint();
                    }
                }
            }

        };

        loadPiatti();
    }


    /**
     * Metodo che carica nel panel tutti gli ordini creati dal cuoco pronti per essere serviti, con una
     * lista di checkbox di piatti per ogni ordine
     */
    public void loadPiatti(){
        String tav;
        for(Ordine o: cuoco.GetOrdiniCuoco()) {
            if (o.getStato() == 1) {
                ArrayList<JCheckBox> check = new ArrayList<>();
                tav = String.valueOf(o.getTavoloID());
                CheckPanel.add(new JLabel("Tavolo " + tav));
                for (int i = 0; i < o.getPiatti().size(); i++) {
                    JCheckBox c = new JCheckBox();
                    c.setText(o.getPiatti().get(i).getNome() + " x" + o.getPiatti().get(i).getQuantita());
                    check.add(c);
                    check.get(i).addActionListener(actionListener);
                    CheckPanel.add(c);
                    check.get(i).setVisible(true);
                }
                CheckPanel.add(new JLabel("-------------------------------"));
                JCheckBox tavolo = new JCheckBox(tav);
                tavolo.setVisible(false);
                check.add(tavolo);
                cb.add(check);
            }
        }
        //frame.pack();
    }

    /**
     * Metodo per rimuovere del panel gli ordini che sono stati compleatamente serviti
     * @param arr lista di checkbox dei piatti dell'ordine da rimuovere
     * @param tavolo tavolo dell'ordine da rimuovere
     */
    public void Pulisci(ArrayList<JCheckBox> arr, String tavolo){
        Component[] components= CheckPanel.getComponents();
        for(Component c: components){
            if(c instanceof JLabel){
                if(((JLabel) c).getText().equals("Tavolo " +tavolo)) {
                    CheckPanel.remove(c);
                }
            }
        }
        for(int i=0;i<arr.size()-1;i++){
            CheckPanel.remove(arr.get(i));
        }
        if(Arrays.stream(CheckPanel.getComponents()).noneMatch(c -> c instanceof JCheckBox)){
            CheckPanel.removeAll();
        }
    }

}
