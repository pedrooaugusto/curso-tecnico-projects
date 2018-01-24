package com.august.saborrioweb.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.august.saborrioweb.filter.HibernateUtil;
import com.august.saborrioweb.modelo.Cliente;

public class DaoCliente {

    private Session sessao = null;
    private Transaction transacao = null;

    public DaoCliente() throws Exception
    {
        this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    public void incluir(Cliente u)
    {
        try
        {
            this.transacao = this.sessao.beginTransaction();
            this.sessao.save(u);
            this.transacao.commit();
            if (this.sessao.isOpen())
            {
                this.sessao.close();
            }
        } catch (Exception e)
        {
            System.out.println("Erro na inclusao." + e);
        }
    }

    public void alterar(Cliente u) throws Exception
    {
        try
        {
            this.sessao.beginTransaction();
            this.sessao.update(u);
            this.sessao.getTransaction().commit();
            if (this.sessao.isOpen())
            {
                this.sessao.close();
            }
        } catch (Exception e)
        {
        	e.printStackTrace();
            System.out.println("Erro na alteracao.");
        }
    }

    public void excluir(Cliente u) throws Exception
    {
        try
        {
            this.sessao.beginTransaction();
            this.sessao.delete(u);
            this.sessao.getTransaction().commit();
            if (this.sessao.isOpen())
            {
                this.sessao.close();
            }
        } catch (Exception e)
        {
            System.out.println("Erro na exclusao.");
        }
    }

    public Cliente consultar(int idCliente)
    {
        Cliente u = null;
        try
        {
            this.sessao.beginTransaction();
            Query consulta = sessao.createQuery("from cliente where codigo = :parametro");
            consulta.setInteger("parametro", idCliente);
            u = (Cliente) consulta.uniqueResult();
            this.sessao.getTransaction().commit();
            if (this.sessao.isOpen())
            {
                this.sessao.close();
            }
        } catch (Exception e)
        {
        	e.printStackTrace();
            System.out.println("Erro na consulta." + e);
        }
        return u;
    }

    @SuppressWarnings("unchecked")
    public List<Cliente> listar()
    {
        List<Cliente> clientes = null;
        try
        {
            this.sessao.beginTransaction();
            Query consulta = sessao.createQuery("from cliente");
            clientes = consulta.list();
            this.sessao.getTransaction().commit();
            if (this.sessao.isOpen())
            {
                this.sessao.close();
            }
        } catch (Exception e)
        {
            System.out.println("Erro na consulta." + e);
        }
        return clientes;
    }

}
