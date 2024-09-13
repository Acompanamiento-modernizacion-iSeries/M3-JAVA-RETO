import banco.Banco;
import db.ClienteDBArrayList;
import db.TransaccionDBArrayList;
import menu.Menu;

public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco(new ClienteDBArrayList(), new TransaccionDBArrayList());
        Menu menu = new Menu(banco);
        menu.mostrarMenu();
    }
}
