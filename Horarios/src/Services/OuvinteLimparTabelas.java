package Services;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Model.Horario;
import Views.Janela;
import dao.HorarioDao;
import dao.PersistenciaXML;

public class OuvinteLimparTabelas implements ActionListener {
	
private Janela janela;
	
	public OuvinteLimparTabelas(Janela janela) {
		this.janela = janela;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		PersistenciaXML per = new PersistenciaXML();
		HorarioDao dao = per.recuperarHorario();
		//ArrayList<Horario> listaDeHorarios = new ArrayList<Horario>();
		ArrayList<Horario> marcacoesFeitas = new ArrayList<Horario>();
		ArrayList<Horario> atrasos = new ArrayList<Horario>();
		ArrayList<Horario> extras = new ArrayList<Horario>();
		
		//dao.setListaDeHorarios(listaDeHorarios);
		dao.setMarcacoesFeitas(marcacoesFeitas);
		dao.setAtrasos(atrasos);
		dao.setHorasExtras(extras);

		per.salvarHorario(dao);
		
		janela.dispose();
		janela = new Janela();
		
	}

}
