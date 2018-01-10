package Controle;

import java.awt.Color;
import java.awt.Font;
import java.text.ParseException;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

public class Diversos {
    
  public static String leDados(String mensagem, String titulo){
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

  public static boolean confirmar (String mensagem, String titulo) {
      UIManager.put("OptionPane.messageForeground", new Color(255,255,255));
      UIManager.put("OptionPane.background", new Color(88,19,198));
      UIManager.put("Panel.background", new Color(88,19,198));
     UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", 1, 18))); 
      int resp = JOptionPane.showConfirmDialog(null,mensagem,
                  titulo,JOptionPane.YES_NO_OPTION);
      
      return(resp == 0); 
  }

  public static void mostrarDados(String resposta, String titulo, boolean icone) {
     UIManager.put("OptionPane.messageForeground", new Color(255,255,255));
     UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", 1, 18)));
     if(icone){
        UIManager.put("OptionPane.background", new Color(19,89,198));
        UIManager.put("Panel.background", new Color(19,89,198));
        JOptionPane.showMessageDialog(null,resposta,titulo,JOptionPane.INFORMATION_MESSAGE);
        
     }else{
        UIManager.put("OptionPane.background", new Color(193,34,34));
        UIManager.put("Panel.background", new Color(193,34,34));
        JOptionPane.showMessageDialog(null, resposta,titulo,JOptionPane.ERROR_MESSAGE);
     }
			
  }
  
  public static DefaultFormatterFactory FormatoMascara(String how) {
        MaskFormatter mascara = null;
        try
        {          
            mascara = new MaskFormatter(how);
        }
        catch (ParseException e){
            mostrarDados("Data Formatada" + e.getMessage(), "Projeto", true);
        }
        DefaultFormatterFactory formato = new DefaultFormatterFactory(mascara, mascara);
        return (formato);
  }
  
  public static boolean intervalo(double n, double n1, double n2, String titulo) {
     if (n<=0) {
         mostrarDados("Favor digitar valores superiores a zero", titulo, false);
         return(false);
     } else if (n1!=n2 && (n<n1 || n>n2)){
                mostrarDados("Valores fora do intervalo de " + n1 + " e " + n2, titulo, false);
	        return(false);
	    } else 
                return(true);
  }
  
  public static void setPlaceholder(boolean on, JTextField tf, String text){
        if(on){
            if(tf.getText().equals(text)){
                tf.setText("");
                tf.setForeground(Color.black);
            }
        }else{
            if(tf.getText().isEmpty()){
                tf.setText(text);
                tf.setForeground(new Color(102,102,102));
            }
        }
    }

}
