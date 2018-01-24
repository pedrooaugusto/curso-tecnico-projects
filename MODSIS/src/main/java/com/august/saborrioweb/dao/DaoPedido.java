package com.august.saborrioweb.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.august.saborrioweb.filter.HibernateUtil;
import com.august.saborrioweb.modelo.MesaCard;
import com.august.saborrioweb.modelo.Pedido;

public class DaoPedido {
	private Session sessao = null;
	private Transaction transacao = null;
	
	public DaoPedido() throws Exception{
		this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<MesaCard> makeCards(){
		List<MesaCard> mesas = new ArrayList<>();
		try
		{
			int existingMesa[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
			this.sessao.beginTransaction();
			List<Object> activeMesatList = sessao.createQuery(
					"select pdp.mesa, " +
					"pdp.ID, " +
					"sum(pdt.preco * pp.quant) " +
					"from pedido_produto pp inner join pp.pedido pdp " +
					"inner join pp.produto pdt "+
					"where pdp.aberto = true group by pdp.ID order by pdp.mesa").list();
			for(Object obj : activeMesatList)
			{
				Object[] result = (Object[]) obj;
				MesaCard mesaCard = new MesaCard();
				mesaCard.setNumero((Integer)result[0]);
				mesaCard.setPedidoId((Integer) result[1]);
				mesaCard.setTotalPrice((Double) result[2]);
				mesas.add(mesaCard);
				existingMesa[((Integer)result[0]) - 1] = -1;
			}
			for(int i = 0; i < 10; i++){
				if(existingMesa[i] != -1){
					MesaCard mesaCard = new MesaCard();
					mesaCard.setNumero(i+1);
					mesaCard.setPedidoId(0);
					mesaCard.setTotalPrice(0);
					mesas.add(mesaCard);
				}
			}
			Collections.sort(mesas, new Comparator<MesaCard>(){
				@Override
				public int compare(MesaCard o1, MesaCard o2) {
					return o1.getNumero() - o2.getNumero();
				}});
		}catch(Exception e){
			e.printStackTrace();
		}
		return mesas;
	}

	public Pedido consultar(int idPedido) {
		Pedido u = null;
		try {
			this.sessao.beginTransaction();
			Query consulta = sessao.createQuery("from pedido where ID = :parametro");
			consulta.setInteger("parametro", idPedido);
			u = (Pedido) consulta.uniqueResult();
			if(u != null){
				consulta = sessao.createQuery("from pedido_produto where pedido = :parametro order by numeracao");
				consulta.setInteger("parametro", idPedido);
				u.setItems(consulta.list());
			}
			this.sessao.getTransaction().commit();
			if (this.sessao.isOpen()){
				this.sessao.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
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

	public void alterar(Pedido u) {
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
}
