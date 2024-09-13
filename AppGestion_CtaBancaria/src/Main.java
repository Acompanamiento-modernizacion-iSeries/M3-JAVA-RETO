import resourses.Banco;
import resourses.Menu;

public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco();
        banco.opciones(Menu.setUp());
    }
}
