/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import controle.ControleCliente;
import controle.ControlePedido;
import controle.ControlePedidoProduto;
import controle.ControleProduto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import modelo.BotaoMesa;
import modelo.Pedido;
import modelo.Pedido_Produto;

/**
 *
 * @author Pedro
 */

@ManagedBean(name = "MesaBean")
@ViewScoped
public class MesaBean implements Serializable{
    private Map<Integer, BotaoMesa> listamesas = new HashMap<>();
    private BotaoMesa currentBotao;
    private int nMesa = -1;
    private Pedido_Produto item = new Pedido_Produto();
    private int idProduto;
    public MesaBean()
    {
        init();
    }
    public void init()
    {
        for(int i = 1; i < 9; i++)
        {
            ControlePedido cP = new ControlePedido();
            BotaoMesa b = new BotaoMesa(i);
            Pedido p = null;
            p = cP.consultarOndeMesa(i);
            if(p != null){
                ControlePedidoProduto cPP = new ControlePedidoProduto();
                b.setItems2(cPP.listarItemsPedido(p.getID()));
                b.setPedido(p);
            }
            listamesas.put(i, b);
        }
    }

    public String pagarConta()
    {
        return "fecharConta.xhtml?nMesa="+nMesa;
    }
    public String pagarContal()
    {
        return "fecharConta.xhtml?nMesa="+nMesa;
    }
        
    public Map<Integer, BotaoMesa> getListamesas()
    {
        return listamesas;
    }

    public void setListamesas(Map<Integer, BotaoMesa> listamesas)
    {
        this.listamesas = listamesas;
    }

    public int getIdProduto()
    {
        return idProduto;
    }

    public void setIdProduto(int idProduto)
    {
        this.idProduto = idProduto;
    }

    
    public int getnMesa()
    {
        return nMesa;
    }

    public void setnMesa(int nMesa)
    {
        this.nMesa = nMesa;
    }

    public Pedido_Produto getItem()
    {
        return item;
    }

    public void setItem(Pedido_Produto item)
    {
        this.item = item;
    }
    
    public void abrirPedido() throws Exception
    {
        Pedido p = new Pedido();
        p.setAberto(true);
        p.setMesa(nMesa);
        ControlePedido cP = new ControlePedido();
        listamesas.get(nMesa).setPedido(p);
        cP.incluir(p);
        currentBotao = listamesas.get(nMesa);
     }
    public List<BotaoMesa> pares()
    {
        List<BotaoMesa> par = new ArrayList();
        for(Map.Entry<Integer, BotaoMesa> a : listamesas.entrySet())
            if(a.getKey() % 2 == 0)
                par.add(a.getValue());
        return par;
    }

    public List<BotaoMesa> impares()
    {
        List<BotaoMesa> impar = new ArrayList();
        for(Map.Entry<Integer, BotaoMesa> a : listamesas.entrySet())
            if(a.getKey() % 2 != 0)
                impar.add(a.getValue());
        return impar;
    }
    public String newPedido()
    {
        if(currentBotao == null){
            System.out.println("\ncurren ta nulo\n");
            return "mVisible";
        }
        return currentBotao.getPedido() == null ? "mVisible" : "mInvisible";
    }
    public void clickBotaoMesa(int n)
    {
        currentBotao = listamesas.get(n);
        nMesa = n;
    }
    public void incluirItem() throws Exception
    {
        if(item.getQuant() == 0)
            return;
        ControleProduto cP = new ControleProduto();
        item.setProduto(cP.consultar(idProduto));
        item.setPedido(currentBotao.getPedido());
        ControlePedidoProduto cC = new ControlePedidoProduto();
        item.setNumeracao(currentBotao.getItems().size()+1);
        cC.incluir(item);
        currentBotao.getItems().add(item);
        currentBotao.addItemPrice(item.getQuant() * item.getProduto().getPreco());
        //item = new Pedido_Produto();
    }
    public String vazio()
    {
        return "";
    }

    public BotaoMesa getCurrentBotao()
    {
        return currentBotao;
    }

    public void setCurrentBotao(BotaoMesa currentBotao)
    {
        this.currentBotao = currentBotao;
    }
    
}
