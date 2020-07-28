package Services;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Views.Janela;

public class OuvinteLimparMarcacoes implements ActionListener {
	
	private Janela janela;
	
	public OuvinteLimparMarcacoes(Janela janela) {
		this.janela = janela;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		janela.getTextEntrada2().setText("");
		janela.getTextSaida2().setText("");
		janela.repaint();	
	}

}
