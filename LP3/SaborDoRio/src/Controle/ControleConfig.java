package Controle;

import Negocio.Config;
import Persistencia.DaoBasico;
import Persistencia.DaoConfig;
import java.util.List;

public class ControleConfig implements ControleBasico {

    DaoConfig dC = new DaoConfig();
    public static Config config = null;

    @Override
    public int setManipular(Object o, char task) {
        int result = 2;
        if (dC instanceof DaoBasico) {
            switch (task) {
                case 'A':
                    result = (dC.alterar(o) ? 0 : 1);
                    if (result == 0) {
                        config = (Config) getBusca(1, 0);
                    }
                    break;
                case 'E':
                    result = (dC.excluir(o) ? 0 : 1);
                    break;
                case 'I':
                    result = (dC.incluir(o) ? 0 : 1);
                    break;
            }
        }
        return result;
    }

    @Override
    public Object getBusca(int iD1, int iD2) {
        Object o = null;
        if (dC instanceof DaoBasico) {
            o = dC.busca(iD1, iD2);
        }
        return o;
    }

    public void checkConfig() {
        try {
            config = (Config) getBusca(1, 0);
        } catch (Exception e) {
            createNewConfig();
        }
    }

    private void createNewConfig() {
        setManipular(getEmptyConfig(), 'I');
        config = (Config) getBusca(1, 0);
    }

    private Config getEmptyConfig() {
        Config c = new Config();
        c.setAnimacoes(true);
        c.setCod(1);
        c.setCodSeguro(true);
        c.setnCliPMesa(10);
        c.setnMesas(10);
        return c;
    }

    public Config getDefaultConfig() {
        Config c = new Config();

        c.setCod(1);
        c.setAnimacoes(false);
        c.setCodSeguro(false);
        c.setnMesas(12);
        c.setnCliPMesa(10);

        return c;
    }

    @Override
    public List<Object> lista(String filtro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean criaRelatorio(String texto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
