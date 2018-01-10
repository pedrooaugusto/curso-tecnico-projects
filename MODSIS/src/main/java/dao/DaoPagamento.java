package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import filter.HibernateUtil;
import modelo.Pagamento;

public class DaoPagamento {
	private Session sessao = null;
	private Transaction transacao = null;
	
	public DaoPagamento() throws Exception{
		this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
	}
	public void incluir(Pagamento u) {
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

	public void alterar(Pagamento u) throws Exception {
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

	public void excluir(Pagamento u) throws Exception {
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

	public Pagamento consultar(int idPagamento) {
		Pagamento u = null;
		try {
			this.sessao.beginTransaction();
			Query consulta = sessao.createQuery("from pagamento where codigo = :parametro");
			consulta.setInteger("parametro", idPagamento);
			u = (Pagamento) consulta.uniqueResult();
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
	public List<Pagamento> listar() {
		List<Pagamento> pagamentos = null;
		try {
			this.sessao.beginTransaction();
			Query consulta = sessao.createQuery("from pagamento");
			pagamentos = consulta.list();
			this.sessao.getTransaction().commit();
			if (this.sessao.isOpen()){
				this.sessao.close();
			}
		} catch (Exception e) {
			System.out.println("Erro na consulta."+e);
		} 
		return pagamentos;
	}

}
    