package Services;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Views.Janela;

public class OuvinteLimparHorarios implements ActionListener {
	
	private Janela janela;
	
	public OuvinteLimparHorarios(Janela janela) {
		this.janela = janela;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		janela.getTextEntrada().setText("");
		janela.getTextSaida().setText("");
		janela.repaint();	
	}

}
