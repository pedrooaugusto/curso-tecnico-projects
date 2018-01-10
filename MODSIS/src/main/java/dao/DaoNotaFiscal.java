package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import filter.HibernateUtil;
import modelo.NotaFiscal;

public class DaoNotaFiscal {
	private Session sessao = null;
	private Transaction transacao = null;
	
	public DaoNotaFiscal() throws Exception{
		this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
	}
	public void incluir(NotaFiscal u) {
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

	public void alterar(NotaFiscal u) throws Exception {
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

	public void excluir(NotaFiscal u) throws Exception {
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

	public NotaFiscal consultar(int idNotaFiscal) {
		NotaFiscal u = null;
		try {
			this.sessao.beginTransaction();
			Query consulta = sessao.createQuery("from notafiscal where codigo = :parametro");
			consulta.setInteger("parametro", idNotaFiscal);
			u = (NotaFiscal) consulta.uniqueResult();
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
	public List<NotaFiscal> listar() {
		List<NotaFiscal> notafiscals = null;
		try {
			this.sessao.beginTransaction();
			Query consulta = sessao.createQuery("from notafiscal");
			notafiscals = consulta.list();
			this.sessao.getTransaction().commit();
			if (this.sessao.isOpen()){
				this.sessao.close();
			}
		} catch (Exception e) {
			System.out.println("Erro na consulta."+e);
		} 
		return notafiscals;
	}

}
