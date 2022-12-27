package Logic;

public class DataTester {

    public static void main(String[] args) {
        Data data = new Data();

        data.loadMenu();
        System.out.println("menu.txt = ");
        System.out.println(data.getMenu());

        data.getMenu().add(new Piatto("piatto prova", 12.50, Piatto.Type.ANTIPASTI));

        data.WriteToFile();

        data.loadMenu();

        System.out.println("new menu.txt = ");
        System.out.println(data.getMenu());

        Data.loadOrdini(new Ordine(12));

    }
}
