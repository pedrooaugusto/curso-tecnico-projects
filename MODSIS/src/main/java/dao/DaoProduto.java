package dao;

import java.util.List;

import modelo.Produto;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import filter.HibernateUtil;

public class DaoProduto {
	private Session sessao = null;
	private Transaction transacao = null;
	
	public DaoProduto() throws Exception{
		this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
	}
	public void incluir(Produto u) {
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

	public void alterar(Produto u) throws Exception {
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

	public void excluir(Produto u) throws Exception {
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

	public Produto consultar(int idProduto) {
		Produto u = null;
		try {
			this.sessao.beginTransaction();
			Query consulta = sessao.createQuery("from produto where ID = :parametro");
			consulta.setInteger("parametro", idProduto);
			u = (Produto) consulta.uniqueResult();
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
	public List<Produto> listar() {
		List<Produto> produtos = null;
		try {
			this.sessao.beginTransaction();
			Query consulta = sessao.createQuery("from produto");
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
