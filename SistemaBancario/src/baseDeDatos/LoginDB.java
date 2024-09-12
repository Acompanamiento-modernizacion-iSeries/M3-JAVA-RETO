package baseDeDatos;

import usuarios.*;
import java.util.ArrayList;
import java.util.List;

public class LoginDB {
    private static List<Usuario> usuarios = new ArrayList<>();

    //agregar nuevos usuarios a la lista.
    public static void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    //retornar la lista de usuarios.
    public static List<Usuario> getUsuarios() {
        return usuarios;
    }

    // Buscar un usuario por el número de cuenta.
    public static Usuario buscarUsuarioPorCuenta(String numeroCuenta) {
        for (Usuario usuario : usuarios) {
            if (usuario.getCuentaAsociada().getNumeroCuenta().equals(numeroCuenta)) {
                return usuario;
            }
        }
        return null;
    }
}
