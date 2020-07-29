package Services;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Model.Horario;
import Views.Janela;
import dao.HorarioDao;
import dao.PersistenciaXML;

public class OuvinteLimparTabelaHorario implements ActionListener {
	
	private Janela janela;
	
	public OuvinteLimparTabelaHorario(Janela janela) {
		this.janela = janela;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		PersistenciaXML per = new PersistenciaXML();
		HorarioDao dao = per.recuperarHorario();
		ArrayList<Horario> listaDeHorarios = new ArrayList<Horario>();
		dao.setListaDeHorarios(listaDeHorarios);
		per.salvarHorario(dao);
		janela.dispose();
		janela = new Janela();		
	}

}
