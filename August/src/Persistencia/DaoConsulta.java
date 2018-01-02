/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Pedro
 */
public class DaoConsulta
{

    public DefaultTableModel retornaLista(int op, String texto)
    {
        DefaultTableModel modelo = null;
        DaoManipular dM = new DaoManipular();
        String inst = "";
        ResultSet rS = null;
        switch (op)
        {
            case 1://Total em estoque rs
                inst = "select ID_ing as Codigo, Nome_ing as Produto, concat('R$ ',format(PrecoUnitario_ing, 2))"
                        + " as Preco, concat(EstoqueAtual_ing, ' ', Unidade_ing) as Estoque,"
                        + " concat ('R$ ', format(EstoqueAtual_ing * PrecoUnitario_ing, 2)) Total"
                        + " from Ingrediente"
                        + " UNION "
                        + " select '---xxx---', '---xxx---', '---xxx---', '---xxx---', '---xxx---'"
                        + " UNION"
                        + " select 'SOMATÓRIO TOTAL__', '', '', '', "
                        + " concat('R$ ',format(sum(EstoqueAtual_ing * PrecoUnitario_ing),2))"
                        + " from Ingrediente";
				//format(EstoqueAtual_ing * PrecoUnitario_ing, 'N', 'pt-br');
                //CONVERT varchar(10), CONVERT(money, salary))
                break;
            case 2://Listar pratos sem secao me
                inst = "select ID_pra as ID, Nome_pra as Nome, concat('R$ ',format(Preco_pra, 2)) as Preco from Prato";
                break;
				
            case 3://Listar ingrediente usados em seus respectivos pratos me
                inst = "select ID_ing as 'ID Ingrediente', Nome_ing as Ingrediente, Nome_pra as Prato"
                        + " from Prato join Prato_Ingrediente on ID_pra = Prato_pin join Ingrediente on "
                        + "ID_ing = Ingrediente_pin";
                break;
            case 4://Listar pedidos me
                inst = "select ID_ped as 'ID Pedido', NomeCliente_ped as 'Nome Cliente', Data_ped as Data,"
                        + " concat('R$ ', format(ValorTotal_ped, 2)) as Total, Nome_ent as Entregador from Pedido join Entregador"
                        + " on Entregador_ped = ID_ent";
                break;
            case 5://Listar todas as vendas me
                inst ="select Numero_ven as Numero, concat('R$ ', format(Preco_ven, 2)) as Total, Data_Ven as Data from Venda "+
                        "UNION "+
                      "select '---xxx---', '---xxx---', '---xxx---' "+
                        "UNION "+
                       "select 'TOTAL --->', concat('R$ ', format(sum(Preco_ven), 2)), ' ' from venda";
                break;
            case 6://Ingredientes fora da validade me
                inst = "select Numero_lot as Numero, Nome_ing as Produto, date_format(Validade_lot,'%d/%m/%Y') "
                        + "as Validade from Lote join Ingrediente on Produto_lot = ID_ing where date(Validade_lot) <  "
                        + "date(now())";
                break;
            case 7://Produtos abaixo do estoque Minimmo
                inst = "select ID_ing as ID, Nome_ing as Nome, Categoria_ing as Categoria,"
                        + " EstoqueAtual_ing as 'Estoque Atual', EstoqueMinimo_ing as 'Estoque Minimo' "
                        + "from Ingrediente where EstoqueAtual_ing < EstoqueMinimo_ing";
                break;
            case 8://Listar pratos em que o ingrediente x é usado
                inst = "select ID_ing as 'ID Ingrediente', Nome_ing as Ingrediente, Nome_pra as Prato"
                        + " from Prato join Prato_Ingrediente on ID_pra = Prato_pin join Ingrediente on "
                        + "ID_ing = Ingrediente_pin where ID_ing = " + texto;
                break;
            case 9://Llistar pratos por secao
                inst = "select ID_pra as ID, Nome_pra as Nome, concat('R$ ',format(Preco_pra, 2)) as Preco from Prato where"
                        + " Secao_pra = '" + texto+"'";
                break;
            case 10://Listar pedidos concluídos
                inst = "select ID_ped as 'ID Pedido', NomeCliente_ped as 'Nome Cliente', Data_ped as Data,"
                        + " concat('R$ ', format(ValorTotal_ped, 2)) as Total, Nome_ent from Pedido join Entregador"
                        + " on Entregador_ped = ID_ent where Status_ped = 'Concluído'";
                break;
            case 11://Listar pedidos por entregador
                inst = "select ID_ped as 'ID Pedido', NomeCliente_ped as 'Nome Cliente', Data_ped as Data,"
                        + " concat('R$ ', format(ValorTotal_ped, 2)) as Total, Nome_ent as Entregador from Pedido join Entregador"
                        + " on Entregador_ped = ID_ent where Entregador_ped  = " + texto;
                break;
            case 12://Listar Lotes
                inst = "select Numero_lot as Numero, Nome_ing as Nome, QuantidadeAtual_lot as "
                        + "'Quantidade Atual', Validade_lot as Validade from Lote join Ingrediente"
                        + " on ID_ing = Produto_lot";
                break;
            case 13://Listar vendas do mês do x. Substring tansforma a posicao que vc escolheu na 1. Logo x + 2(mes)
                inst = "select Numero_ven as Numero, concat('R$ ', format(Preco_ven, 2)) as Total, Data_Ven as Data from Venda where "
                        + "substring(Data_ven, 4, 2) = " + texto+" "
                        + "UNION "
                        + "select '---xxx---', '---xxx---', '---xxx---' "
                        + "UNION "
                        + "select 'TOTAL --->', concat('R$ ', format(sum(Preco_ven), 2)), ' ' from venda where "
                        + "substring(Data_ven, 4, 2) = "+texto;
                break;
            case 14://Listar pedidos em andamento
                inst = "select ID_ped as Numero, NomeCliente_ped as Cliente, Telefone_ped as Telefone, concat"
                        + "('R$ ', format(ValorTotal_ped, 2)) as Total, Nome_ent as Entregador, Data_ped as 'Tempo Decorrido' "
                        + "from Pedido join Entregador on ID_ent = Entregador_ped where Status_ped = 'Em Andamento' "
                        + "order by Data_ped";
                break;
        }
        rS = dM.getConsultar(inst);
        if (rS != null)
        {
            modelo = dM.apresentaDados(rS);
        }
        return (rS != null ? modelo : null);
    }
}
