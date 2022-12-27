package Graphics;

import Logic.ChefLogic;
import Logic.Piatto;
import static Logic.Piatto.Type.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * classe che implementa graficamente la figura dello Chef
 */

public class Chef extends JPanel{
    ChefLogic chefLogic = new ChefLogic();
    private JFrame frame;
    private JPanel ChefPanel;
    private JTextArea PiattoNome;
    private JTextArea PiattoPrezzo;
    private JButton aggiungiPiattoButton;
    private JButton confermaAggiunteButton;
    private JButton modificaPiattoButton;
    private JButton rimuoviPiattoButton;
    private JPanel PrimiPanel;
    private JButton indietroButton;
    private JList SecondiList;
    private JList DolciList;
    private JPanel SecondiPanel;
    private JPanel DolciPanel;
    private JList BevandeList;
    private JPanel BevandePanel;
    private JComboBox<String> TypeBox;
    private JPanel TypeBoxPanel;
    private JList AntipastiList;
    private JPanel AntipastiPanel;
    private JList<Piatto> PrimiList;
    private final DefaultListModel<Piatto> BevandeModel= new DefaultListModel<>();
    private final DefaultListModel<Piatto> PrimiModel= new DefaultListModel<>();
    private final DefaultListModel<Piatto> SecondiModel= new DefaultListModel<>();
    private final DefaultListModel<Piatto> DolciModel= new DefaultListModel<>();
    private final DefaultListModel<Piatto> AntiPastiModel= new DefaultListModel<>();
    private ArrayList<Piatto> changed = new ArrayList<>(chefLogic.getMenu());


    public Chef() {
        /**
         * Costruttore della base grafica
         */
        frame = new JFrame("Chef");
        LoadMenuList();
        frame.setBounds(400, 300, 1000, 600);
        frame.setContentPane(ChefPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);



        /**
         * Listener per aggiungere un nuovo piatto al menù
         */
        aggiungiPiattoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ComboBoxSelection= TypeBox.getSelectedItem().toString();
                if (!(PiattoNome.getText().equals("")&&PiattoPrezzo.getText().equals(""))) {
                    if (PiattoNome.getText().equals("")) {
                        JOptionPane.showMessageDialog(frame, "Inserisci il nome del piatto!");
                    } else if (PiattoPrezzo.getText().equals("")) {
                        JOptionPane.showMessageDialog(frame, "Inserisci il prezzo del piatto!");
                    }else if (ComboBoxSelection.equals("SELEZIONA")) {
                        JOptionPane.showMessageDialog(frame, "Inserisci la tipologia del piatto!");
                            JOptionPane.showMessageDialog(frame, "Il prezzo del piatto non può essere 0 o negativo!");
                    }else if(Double.parseDouble(PiattoPrezzo.getText())<=0) {
                        JOptionPane.showMessageDialog(frame, "Il prezzo del piatto non può essere 0 o negativo!");
                    }else{
                        String nome= PiattoNome.getText();
                        Double prezzo= Double.parseDouble(PiattoPrezzo.getText());
                        Enum tipo = (Enum) TypeBox.getSelectedItem();
                        if(!chefLogic.addFood(new Piatto(nome,prezzo, (Piatto.Type) tipo))){
                            JOptionPane.showMessageDialog(frame,"Piatto gia esistente");
                            PiattoNome.setText("");
                            PiattoPrezzo.setText("");
                        }else {
                            switch (tipo.toString()) {
                                case "BEVANDE" -> Add(BevandeModel, new Piatto(nome, prezzo, (Piatto.Type) tipo));
                                case "PRIMI" -> Add(PrimiModel, new Piatto(nome, prezzo, (Piatto.Type) tipo));
                                case "SECONDI" -> Add(SecondiModel, new Piatto(nome, prezzo, (Piatto.Type) tipo));
                                case "DOLCI" -> Add(DolciModel, new Piatto(nome, prezzo, (Piatto.Type) tipo));
                                case "ANTIPASTI" -> Add(AntiPastiModel, new Piatto(nome, prezzo, (Piatto.Type) tipo));
                            }
                            PiattoNome.setText("");
                            PiattoPrezzo.setText("");
                            JOptionPane.showMessageDialog(frame,"Piatto aggiunto correttamente","Aggiunta piatto",JOptionPane.INFORMATION_MESSAGE);
                        }

                    }
                }else{
                    JOptionPane.showMessageDialog(frame, "Inserisci le informazioni per il piatto!");

                }
            }
        });

        /**
         * Listener per confermare le modifiche effettuate al menù
         */

        /**
         * Listener per rimuovere un piatto dal menù
         */
        rimuoviPiattoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!(FindRemoveModels()==null)) {
                    JList lista = FindRemoveModels();
                    int risposta= JOptionPane.showConfirmDialog(frame,"Sei sicuro di voler eliminare"+lista.getSelectedValue(),"Elimina piatto",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                    if(risposta==JOptionPane.YES_OPTION){
                        chefLogic.removeFood((Piatto) lista.getSelectedValue());
                        DefaultListModel modello = (DefaultListModel) lista.getModel();
                        Delete(modello, (Piatto) lista.getSelectedValue());
                        lista.clearSelection();
                        JOptionPane.showMessageDialog(frame,"Piatto rimosso correttamente","Rimuovi piatto",JOptionPane.INFORMATION_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(frame, "Seleziona il piatto da eliminare","Selezione Piatto",JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        /**
         * Listener per modificare un piatto dal menù
         */
        modificaPiattoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!(FindEditModels()==null)) {
                    JList lista = FindEditModels();
                    Piatto modifica = (Piatto) lista.getSelectedValue();
                    String nuovo_nome = JOptionPane.showInputDialog("Modifica il nome del piatto", modifica.getNome());
                    double nuovo_prezzo = Double.parseDouble(JOptionPane.showInputDialog("Modifica il prezzo del piatto", modifica.getPrezzo()));
                    Piatto piatto_modificato = chefLogic.editFood((Piatto) lista.getSelectedValue(), nuovo_nome, nuovo_prezzo);
                    DefaultListModel modello = (DefaultListModel) lista.getModel();
                    Edit(modello, lista.getSelectedIndex(), piatto_modificato);
                    JOptionPane.showMessageDialog(frame, "Piatto modificato correttamente", "Modifica piatto", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(frame, "Seleziona il piatto da modificare","Selezione Piatto",JOptionPane.WARNING_MESSAGE);
                }

            }
        });

        /**
         * Listener per tornare alla homepage
         */
        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConfermaBack();
            }
        });

        /**
         * Listener per la chiusura dell'applicazione
         */
       frame.addWindowListener(new WindowAdapter() {
           @Override
           public void windowClosing(WindowEvent e) {
               ConfermaClose();
           }
       });

        /**
         * Listener per la sincronizzazione delle Jlist (implementabile con le Collections)
         */
        BevandeList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                SecondiList.clearSelection();
                AntipastiList.clearSelection();
                PrimiList.clearSelection();
                DolciList.clearSelection();
                frame.repaint();
            }
        });

        /**
         * Listener per la sincronizzazione delle Jlist (implementabile con le Collections)
         */
        AntipastiList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                BevandeList.clearSelection();
                SecondiList.clearSelection();
                PrimiList.clearSelection();
                DolciList.clearSelection();
                frame.repaint();
            }
        });

        /**
         * Listener per la sincronizzazione delle Jlist (implementabile con le Collections)
         */
        PrimiList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                BevandeList.clearSelection();
                AntipastiList.clearSelection();
                SecondiList.clearSelection();
                DolciList.clearSelection();
                frame.repaint();
            }
        });

        /**
         * Listener per la sincronizzazione delle Jlist (implementabile con le Collections)
         */
        SecondiList.addListSelectionListener(new ListSelectionListener() {
           @Override
           public void valueChanged(ListSelectionEvent e) {
                   BevandeList.clearSelection();
                   AntipastiList.clearSelection();
                   PrimiList.clearSelection();
                   DolciList.clearSelection();
                   frame.repaint();
           }
       });

        /**
         * Listener per la sincronizzazione delle Jlist (implementabile con le Collections)
         */
        DolciList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                BevandeList.clearSelection();
                AntipastiList.clearSelection();
                PrimiList.clearSelection();
                SecondiList.clearSelection();
                frame.repaint();
            }
        });

    }


    /**
     * metodo per caricare il modello della lista con i piatti del menù
     */
    public void LoadMenuList(){
        for (Piatto p : chefLogic.getMenu()) {
            if(p.getTipologia().equals(BEVANDE)) {
                BevandeModel.addElement(p);
            }else if(p.getTipologia().equals(PRIMI)){
                PrimiModel.addElement(p);
            }else if(p.getTipologia().equals(SECONDI)){
                SecondiModel.addElement(p);
            }else if(p.getTipologia().equals(DOLCI)) {
                DolciModel.addElement(p);
            }else if(p.getTipologia().equals(ANTIPASTI)) {
                AntiPastiModel.addElement(p);
            }
        }

        TypeBox = new JComboBox(Piatto.Type.values());
        PrimiList = new JList();
        SecondiList = new JList();
        DolciList = new JList();
        BevandeList = new JList();
        AntipastiList = new JList();
        PrimiList.setModel(PrimiModel);
        SecondiList.setModel(SecondiModel);
        DolciList.setModel(DolciModel);
        BevandeList.setModel(BevandeModel);
        AntipastiList.setModel(AntiPastiModel);
        PrimiPanel.add(PrimiList);
        SecondiPanel.add(SecondiList);
        DolciPanel.add(DolciList);
        BevandePanel.add(BevandeList);
        AntipastiPanel.add(AntipastiList);
        TypeBoxPanel.add(TypeBox);

    }

    /**
     * Metodo per aggiungere un piatto al modello
     * @param piatto piatto da aggiungere
     */
    public void Add(DefaultListModel modello, Piatto piatto){
        modello.addElement(piatto);
    }

    /**
     * Metodo per modificare un piatto nel modello
     * @param index indice del piatto da modificare
     * @param piatto_nuovo piatto modificato
     */
    public void Edit(DefaultListModel modello,int index, Piatto piatto_nuovo){
        modello.set(index,piatto_nuovo);
    }

    /**
     * Metodo per eliminare un piatto dal modello
     * @param piatto piatto da eliminare
     */
    public void Delete(DefaultListModel modello ,Piatto piatto){
        modello.removeElement(piatto);
    }

    /**
     * Metodo per salvare le modifiche in caso di ritorno alla homepage
     */
    public void ConfermaBack(){
            int risposta= JOptionPane.showConfirmDialog(frame,"Non hai confermato le modifiche, intendi farlo?","Conferma modfiche",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
            if(risposta==JOptionPane.YES_OPTION){
                chefLogic.finalizzaMenu();
                frame.dispose();
                chefLogic.getMenu().clear();
                HomePage homePage = new HomePage();
            }else{
                frame.dispose();
                chefLogic.getMenu().clear();
                HomePage homePage = new HomePage();
            }
        }

    /**
     * Metodo per salvare le modifiche in caso di chiusura dell'applicazione
     */
    public void ConfermaClose(){
            int risposta= JOptionPane.showConfirmDialog(frame,"Non hai confermato le modifiche, intendi farlo?","Conferma modfiche",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
            if(risposta==JOptionPane.YES_OPTION){
                chefLogic.finalizzaMenu();
                frame.dispose();
                chefLogic.getMenu().clear();
            }else{
                frame.dispose();
                chefLogic.getMenu().clear();
            }
        }

    /**
     *  Metodo trovare la Jlist selezionata utilizzato nella modifica
      * @return jlist dell'elemento che l'utente ha selzionato
     */
    public JList FindEditModels(){
        DefaultListModel bmodel = (DefaultListModel) BevandeList.getModel();
        DefaultListModel pmodel = (DefaultListModel) PrimiList.getModel();
        DefaultListModel smodel = (DefaultListModel) SecondiList.getModel();
        DefaultListModel dmodel = (DefaultListModel) DolciList.getModel();
        DefaultListModel amodel = (DefaultListModel) AntipastiList.getModel();
        if(bmodel.contains(BevandeList.getSelectedValue())){
            return BevandeList;
        }else if(pmodel.contains(PrimiList.getSelectedValue())){
            return PrimiList;
        }else if(smodel.contains(SecondiList.getSelectedValue())){
            return SecondiList;
        }else if(dmodel.contains(DolciList.getSelectedValue())){
            return DolciList;
        }else if(amodel.contains(AntipastiList.getSelectedValue())){
            return AntipastiList;
        }
        return null;
    }

    /**
     * Metodo trovare la Jlist selezionata utilizzato nel rimuovi
     * @return jlist dell'elemento che l'utente ha selzionato
     */
    public JList FindRemoveModels(){
        DefaultListModel bmodel = (DefaultListModel) BevandeList.getModel();
        DefaultListModel pmodel = (DefaultListModel) PrimiList.getModel();
        DefaultListModel smodel = (DefaultListModel) SecondiList.getModel();
        DefaultListModel dmodel = (DefaultListModel) DolciList.getModel();
        DefaultListModel amodel = (DefaultListModel) AntipastiList.getModel();

        if(bmodel.contains(BevandeList.getSelectedValue())){
            return BevandeList;
        }else if(pmodel.contains(PrimiList.getSelectedValue())){
            return PrimiList;
        }else if(smodel.contains(SecondiList.getSelectedValue())){
            return SecondiList;
        }else if(dmodel.contains(DolciList.getSelectedValue())){
            return DolciList;
        }else if(amodel.contains(AntipastiList.getSelectedValue())){
            return AntipastiList;
        }
        return null;
    }

}
