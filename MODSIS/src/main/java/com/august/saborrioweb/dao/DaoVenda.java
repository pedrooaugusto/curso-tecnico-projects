package com.august.saborrioweb.dao;

import java.util.List;

import com.august.saborrioweb.modelo.Venda;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.august.saborrioweb.filter.HibernateUtil;

public class DaoVenda {
	private Session sessao = null;
	private Transaction transacao = null;
	
	public DaoVenda() throws Exception{
		this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
	}
	public void incluir(Venda u) {
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

	public void alterar(Venda u) {
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

	public void excluir(Venda u) {
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

	public Venda consultar(int idVenda) {
		Venda u = null;
		try {
			this.sessao.beginTransaction();
			Query consulta = sessao.createQuery("from venda where ID = :parametro");
			consulta.setInteger("parametro", idVenda);
			u = (Venda) consulta.uniqueResult();
			this.sessao.getTransaction().commit();
			if (this.sessao.isOpen()){
				this.sessao.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro na consulta."+e);
		} 
		return u;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Venda> listar() {
		List<Venda> produtos = null;
		try {
			this.sessao.beginTransaction();
			Query consulta = sessao.createQuery("from venda");
			produtos = consulta.list();
			this.sessao.getTransaction().commit();
			if (this.sessao.isOpen()){
				this.sessao.close();
			}
		} catch (Exception e) {
			System.out.println("Erro na consulta."+e);
		} 
		return produtos;
	}

}
