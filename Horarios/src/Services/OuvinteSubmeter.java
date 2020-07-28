package Services;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Views.Janela;

public class OuvinteSubmeter implements ActionListener {
	
	private Janela janela;
	private Time entrada;
	private Time saida;

	public OuvinteSubmeter(Janela janela) {
		this.janela = janela;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String str = janela.getTextEntrada().getText();
		String str2 = janela.getTextSaida().getText();
		SimpleDateFormat formatador = new SimpleDateFormat("HH:mm"); 
		Date data = null;
		Date data2 = null;
		try {
			data = formatador.parse(str);
			data2 = formatador.parse(str2);
			entrada = new Time(data.getTime());
			saida = new Time(data2.getTime());
		} catch (ParseException e1) {
			e1.printStackTrace();
		} 
		
		Controler controler = new Controler();
		controler.novoCadastroHorario(entrada, saida);
		
		janela.getTextEntrada().setText("");
		janela.getTextSaida().setText("");
		janela.repaint();
		janela.dispose();
		janela = new Janela();
	}

}
