/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistencia;

import java.util.List;

/**
 *
 * @author aluno
 */
 public interface DaoFabricaObjetos {
   public boolean incluir(Object o);
   public boolean alterar(Object o);
   public boolean excluir(int chave1, int chave2);
   public Object busca(int chave1, int chave2);
   public List<Object> carrega();
   public int verificaChave(int chave); 
}
