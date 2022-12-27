package Graphics;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Logic.Data;


/**
 * Classe che gestisce il cambio dei ruoli e la creazione delle rispettive classi grafice
 */
public class HomePage {
    private JFrame frame;
    private JButton responsabileDiCassaButton;
    private JButton cameriereButton;
    private JButton cuocoButton;
    private JButton chefButton;
    private JPanel HomePagePanel;
    private JButton esciXButton;

    public HomePage() {
        frame = new JFrame("HomePage");
        frame.setBounds(600,300,500,500);
        frame.setContentPane(HomePagePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        /**
         * Listener per il bottone per chiudere l'applicazione
         */
        esciXButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        /**
         *  Listener per il bottone per passare allo chef
         */
        chefButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Chef chef= new Chef();
                chef.setVisible(true);

            }
        });

        /**
         *  Listener per il bottone per passare al cuoco
         */
        cuocoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Cuoco cuoco = new Cuoco();
                cuoco.setVisible(true);
            }
        });

        /**
         *  Listener per il bottone per passare al cameriere
         */
        cameriereButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Cameriere cameriere= new Cameriere();
                cameriere.setVisible(true);
            }
        });

        /**
         *  Listener per il bottone per passare al responsabile di cassa
         */
        responsabileDiCassaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Gestore_Cassa gestore_cassa= new Gestore_Cassa();
                gestore_cassa.setVisible(true);
            }
        });
    }

    /**
     * Main per l'avvio dell'applicazione
     * @param args nessun argomento da passare in input
     */
    public static void main(String[] args) {
        Data data = new Data();
        HomePage homePage= new HomePage();
    }
}
