/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Pedro
 */
public class Diversos
{
    public static boolean HABILITAR = true;
    public static String diretorio = "C:/Users/Pedro/Desktop/August/EntregadorIMG/";
    
    public static String leDados(String mensagem, String titulo){
      setLookAndFeel(1);
      String dado="";
      do{
       dado=JOptionPane.showInputDialog(null,mensagem,titulo,JOptionPane.QUESTION_MESSAGE);
      }
      while(dado==null||dado.isEmpty()); 
      dado=dado.replace(",",".");
      return(dado);
  }
	
  public static boolean testaNum(String dado,String titulo) {
      boolean teste;
	
      try{
          teste= true;
          Double.parseDouble(dado); 
      }
      catch(NumberFormatException erro) {
	   mostrarDados("Houve erro na conversão \n"+
           "Digite apenas caracteres numéricos "+erro.getMessage(), titulo, false);
            teste=false;
      }//Fim catch
      return(teste);
  }//Fim do testa num
  public static String testaNum(String num, int tipo)//Devolve a string sem o caractere q não é número
  {
      String teste = "";  
      try
      {
        if(tipo == 1)
          Float.parseFloat(num);
        else
            Integer.parseInt(num);
        teste = num;
      }catch(Exception e)
      {
        teste = num.substring(0, num.length() - (num.length() == 0 ? 0 : 1));
      }
      return teste;
  }

  public static boolean confirmar (String mensagem, String titulo) 
  {
      setLookAndFeel(1);
      int resp = JOptionPane.showConfirmDialog(null,mensagem,
                  titulo,JOptionPane.YES_NO_OPTION);
        return(resp == 0); 
  }

  public static void mostrarDados(String resposta, String titulo, boolean icone) {
     if(icone)
     {
       setLookAndFeel(1);
       JOptionPane.showMessageDialog(null,resposta,titulo,JOptionPane.INFORMATION_MESSAGE);
     }
     else
     {
       setLookAndFeel(2);
       JOptionPane.showMessageDialog(null, resposta,titulo,JOptionPane.ERROR_MESSAGE);
     }		
  }
  
  public static DefaultFormatterFactory FormatoMascara(int qual) {
        MaskFormatter mascara = null;
        try
        {
            switch(qual)
            {
                case 1: 
                  mascara = new MaskFormatter("##/##/####");
                  break;
                case 2:
                  mascara = new MaskFormatter("###.###.###-##");
                  break;
                case 3:
                  mascara = new MaskFormatter("####-####");
                  break;
                default:
                  mascara = new MaskFormatter("");
            }
        }
        catch (ParseException e){
            mostrarDados("Data Formatada" + e.getMessage(), "Projeto", true);
        }
        DefaultFormatterFactory formato = new DefaultFormatterFactory(mascara, mascara);
        return (formato);
  }
  public static boolean intervalo(double n, double n1, double n2, String titulo) 
  {
     if (n<=0) {
         mostrarDados("Favor digitar valores superiores a zero", titulo, false);
         return(false);
     } else if (n1!=n2 && (n<n1 || n>n2)){
                mostrarDados("Valores fora do intervalo de " + n1 + " e " + n2, titulo, false);
	        return(false);
	    } else 
                return(true);
  }
  public static String tempoDecorrido(String inicio)/*
           getDate() retorna o número em milisegundos de 1 janeiro de 1970
            até a que passou como parametro.
          */
  {
      String tempoFormato = "";//00:00:00
      DateFormat dF = DateFormat.getDateTimeInstance();// Passar string para date
      Date inicioD = null;
        try
        {
            inicioD = dF.parse(inicio);
        } catch (ParseException ex)
        {
            System.out.println("Tentativa de conversão de string para date falhou");
            ex.printStackTrace();
        }
      long diferenca = new Date().getTime() - inicioD.getTime();//diferencao em ml entre as duas datas
      long segu = diferenca / 1000 % 60;// 1mls = 1/1000s
      long minu = diferenca / (60 * 1000) % 60;// 1mls = 1/(1000 * 60)m
      long hora = diferenca / (60 * 60 * 1000);//...
      tempoFormato = formatarTempo(hora)+":"+formatarTempo(minu)+":"+formatarTempo(segu);
      return tempoFormato;
  }
  public static String formatarTempo(long valor)//Auxuliar da de cima
  {
      return String.valueOf((valor <= 9) ? "0"+valor : ""+valor);
  }
  public static String listarArray(String[] array)
  {
    String lista="";
    for(int i = 0; i < array.length; i++)
    {
        lista += i+1+"-"+array[i]+"\n";
    }
    return lista;
  }
   public static String parseIngles(String datatBrazilian)//Passa o formato 00/00/0000 para 0000-00-00
   {
        String data = datatBrazilian;
        String dia = data.substring(0, 2);
        String mes = data.substring(3, 5);
        String ano = data.substring(6, 10);
        return ano+"-"+mes+"-"+dia;
   }
   public static String parseBrazil(String datatEnglish)// ! de acima
   {
        String data = datatEnglish;
        String ano = data.substring(0, 4);
        String mes = data.substring(5, 7);
        String dia = data.substring(8, 10);
        return dia+"/"+mes+"/"+ano;
   }
    public static void setNomeOs()
    {
        setLookAndFeel(1);
        String os="";
        do
        {            
            os = JOptionPane.showInputDialog(
              "Informe o diretório em que as imgs devem ser salvas:\n"
              + "Exemplo:\n"
              + "Linux      --> /root/media/user/...\n"
              + "Windows --> C:/programas/.../August/EntregadorIMG/\n"
              + "\nDiretorio atual: "+diretorio, diretorio);
            if((new File((os==null||os.isEmpty()) ? "no" : os)).isDirectory())
            {
                break;
            }
            else
            {
                if(!Diversos.confirmar("'"+os+"' Não é um diretório valido\nTentar novamente?", "Blue Jay Way"))
                    break;
            }
        } while(true);
        diretorio = os;
        HABILITAR = true;
    }
  public static void setLookAndFeel(int op)//Cor dos JOptionPane
  {
      switch(op)
      {
          case 1:
                UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Segoe UI Light", 1, 18)));
                UIManager.put("OptionPane.messageForeground", Color.WHITE);
                UIManager.put("OptionPane.background", new Color(22, 73, 154));
                UIManager.put("Panel.background", new Color(22, 73, 154));
                break;
          case 2:
                UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Segoe UI Light", 1, 18)));
                UIManager.put("OptionPane.messageForeground", new Color(234, 54, 53));
                UIManager.put("OptionPane.background", new Color(22, 73, 154));
                UIManager.put("Panel.background", new Color(22, 73, 154));
                break;
      }
  }
}
