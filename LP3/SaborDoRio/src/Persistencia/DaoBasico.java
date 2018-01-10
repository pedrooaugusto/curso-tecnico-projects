package Persistencia;

import java.util.List;

public interface DaoBasico
{
    public boolean incluir(Object o);
    public boolean alterar(Object o);
    public boolean excluir(Object o);
    public Object busca(int chave1, int chave2);
    public List<Object> carregarLista(String filtro);
}
