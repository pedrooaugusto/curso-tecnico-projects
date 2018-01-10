/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import filter.HibernateUtil;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Pedido;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Pedro
 */
public class DaoPedido {
    private Session sessao = null;
	private Transaction transacao = null;
    
	public DaoPedido() throws Exception{
		this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
	}
	public void incluir(Pedido u) {
		try {
			this.transacao = this.sessao.beginTransaction();
			this.sessao.save(u);
			this.transacao.commit();
			if (this.sessao.isOpen()){
				this.sessao.close();
			}
		} catch (Exception e) {
			System.out.println("Erro na inclusao." + e);
		} 
	}

	public void alterar(Pedido u) throws Exception {
		try {
			this.sessao.beginTransaction();
			this.sessao.update(u);
			this.sessao.getTransaction().commit();
			if (this.sessao.isOpen()){
				this.sessao.close();
			}
		} catch (Exception e) {
                    e.printStackTrace();
		}
	}

	public void excluir(Pedido u) throws Exception {
		try {
			this.sessao.beginTransaction();
			this.sessao.delete(u);
			this.sessao.getTransaction().commit();
			if (this.sessao.isOpen()){
				this.sessao.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	public Pedido consultar(int idPedido) {
		Pedido u = null;
		try {
			this.sessao.beginTransaction();
			Query consulta = sessao.createQuery("from pedido where ID = :parametro");
			consulta.setInteger("parametro", idPedido);
			u = (Pedido) consulta.uniqueResult();
			u.setCodCliente(u.getCliente().getCodigo());
			consulta = sessao.createQuery("from pedido_produto where pedido = :parametro order by numeracao");
            consulta.setInteger("parametro", idPedido);
            u.setItems(consulta.list());
			this.sessao.getTransaction().commit();
			if (this.sessao.isOpen()){
				this.sessao.close();
			}
		} catch (Exception e) {
                    e.printStackTrace();
		} 
		return u;
	}
	public Pedido consultarPedidoAtivoMesa(int mesa) {
		Pedido u = null;
		try {
			this.sessao.beginTransaction();
			Query consulta = sessao.createQuery("from pedido where mesa = :parametro and aberto = true");
                        consulta.setInteger("parametro", mesa);
                        if(consulta.uniqueResult() != null)
                            u = (Pedido) consulta.uniqueResult();
			this.sessao.getTransaction().commit();
			if (this.sessao.isOpen()){
				this.sessao.close();
			}
		} catch (Exception e) {
                    e.printStackTrace();
		} 
		return u;
	}

	@SuppressWarnings("unchecked")
	public Pedido consultarUltimoPedido() {
		Pedido u = null;
		List<Pedido> pedidos = null;
		try {
			this.sessao.beginTransaction();
			Query consulta = sessao.createQuery("from pedido order by ID desc");
			pedidos = consulta.list();
			u = pedidos.get(0);
			u.setCodCliente(u.getCliente().getCodigo());
			consulta = sessao.createQuery("from pedido_produto where pedido = :parametro order by numeracao");
            consulta.setInteger("parametro", u.getID());
            u.setItems(consulta.list());
			this.sessao.getTransaction().commit();
			if (this.sessao.isOpen()){
				this.sessao.close();
			}
		} catch (Exception e) {
                    e.printStackTrace();
		} 
		return u;
	}
	
	@SuppressWarnings("unchecked")
	public List<Pedido> listar() {
		List<Pedido> clientes = null;
		try {
			this.sessao.beginTransaction();
			Query consulta = sessao.createQuery("from pedido");
			clientes = consulta.list();
			for(int i = 0; i < clientes.size(); i++)
			{
				clientes.get(i).setCodCliente(clientes.get(i).getCliente().getCodigo());
				consulta = sessao.createQuery("from pedido_produto where pedido = :parametro order by numeracao");
	            consulta.setInteger("parametro", clientes.get(i).getID());
	            clientes.get(i).setItems(consulta.list());
			}
			this.sessao.getTransaction().commit();
			if (this.sessao.isOpen()){
				this.sessao.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return clientes;
	}

}
