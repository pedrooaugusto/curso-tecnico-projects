package Controle;

import java.util.List;

public interface ControleBasico {
    
    public int setManipular(Object o, char task);
    public Object getBusca(int iD1, int iD2);
    public List<Object> lista(String filtro);
    public boolean criaRelatorio(String texto);
}
