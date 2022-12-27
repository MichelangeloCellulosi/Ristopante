package Logic;

import java.io.*;
import java.util.ArrayList;


/**
 * Classe che implementa la gestione dei dati dell'applicazione (menù, ordini) e i relativi metodi di scrittura su file
 */
public class Data {

    private static File inputfile= new File("menu.txt");
    private static ArrayList<Piatto> menu= new ArrayList<>();
    private static ArrayList<Ordine> ordini = new ArrayList<>();

    public Data(){

    }

    /**
     * Metodo che legge il file di testo menù.txt e crea un arraylist di piatti
     */
    public  void loadMenu(){
            try {
                BufferedReader reader= new BufferedReader(new FileReader(inputfile));
                String linea = reader.readLine();

                while(linea!= null){
                    String[] record = linea.split(",");
                    String Nome = record[0].trim();
                    Double Prezzo = Double.parseDouble(record[1].trim());
                    String type = record[2].trim();
                    Piatto.Type tipologia = Piatto.Type.valueOf(type);
                    Piatto nuovo= new Piatto(Nome,Prezzo, tipologia);
                    menu.add(nuovo);
                    linea= reader.readLine();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }


    public ArrayList<Piatto> getMenu() {
        return  menu;
    }

    /**
     * Metodo che scrive su file le modifiche effettuate al menù
     */
    public  void WriteToFile(){

        try{
            BufferedWriter out = new BufferedWriter(new FileWriter(inputfile,false));
            for(Piatto h: menu){
                out.write(h.getNome()+","+h.getPrezzo()+","+h.getTipologia());
                out.write("\n");
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo che aggiunge alla lista di ordini un ordine dato in input
     * @param ordine ordine da aggiungere
     */
    public static void loadOrdini(Ordine ordine){
        ordini.add(ordine);
    }


    public ArrayList<Ordine> getOrdini() {
        return ordini;
    }

}


