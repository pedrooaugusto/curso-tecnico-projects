/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *
 */

package com.august.saborrioweb.dao;

import com.august.saborrioweb.filter.HibernateUtil;
import com.august.saborrioweb.modelo.Pedido_Produto;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
/**
 *
 * @author Pedro
 */
public class DaoPedidoProduto {
    
	private Session sessao = null;
	private Transaction transacao = null;

    
	public DaoPedidoProduto() throws Exception{
		this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
	}
	
    public void incluir(Pedido_Produto u) {
		try {
			this.transacao = this.sessao.beginTransaction();
			this.sessao.save(u);
			this.transacao.commit();
			if (this.sessao.isOpen()){
				this.sessao.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	public void alterar(Pedido_Produto u) {
		try {
			this.sessao.beginTransaction();
			this.sessao.update(u);
			this.sessao.getTransaction().commit();
			if (this.sessao.isOpen()){
				this.sessao.close();
			}
		} catch (Exception e) {
			System.out.println("Erro na alteracao.");
		}
	}

	public void excluir(Pedido_Produto u){
		try {
			this.sessao.beginTransaction();
			this.sessao.delete(u);
			this.sessao.getTransaction().commit();
			if (this.sessao.isOpen()){
				this.sessao.close();
			}
		} catch (Exception e) {
			System.out.println("Erro na exclusao.");
		} 
	}
	
	public Pedido_Produto consultar(int idPedido) {
		Pedido_Produto u = null;
		try {
			this.sessao.beginTransaction();
			Query consulta = sessao.createQuery("from pedido_produto where ID = :parametro");
			consulta.setInteger("parametro", idPedido);
			u = (Pedido_Produto) consulta.uniqueResult();
			this.sessao.getTransaction().commit();
			if (this.sessao.isOpen()){
				this.sessao.close();
			}
		} catch (Exception e) {
			System.out.println("Erro na consulta."+e);
		} 
		return u;
        }

        @SuppressWarnings("unchecked")
	public List<Pedido_Produto> listarDoPedido(int ped) {
		List<Pedido_Produto> items = null;
		try {
			this.sessao.beginTransaction();
			Query consulta = sessao.createQuery("from pedido_produto where pedido = :parametro");
            consulta.setInteger("parametro", ped);
			items = consulta.list();
			this.sessao.getTransaction().commit();
			if (this.sessao.isOpen()){
				this.sessao.close();
			}
		} catch (Exception e) {
                    e.printStackTrace();
		} 
		return items;
	}


	@SuppressWarnings("unchecked")
	public List<Pedido_Produto> listar() {
		List<Pedido_Produto> items = null;
		try {
			this.sessao.beginTransaction();
			Query consulta = sessao.createQuery("from pedido_produto");
			items = consulta.list();
			this.sessao.getTransaction().commit();
			if (this.sessao.isOpen()){
				this.sessao.close();
			}
		} catch (Exception e) {
			System.out.println("Erro na consulta."+e);
		} 
		return items;
	}

}
