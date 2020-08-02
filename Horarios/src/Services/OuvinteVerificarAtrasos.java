package Services;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Model.Horario;
import Views.Janela;
import dao.HorarioDao;
import dao.PersistenciaXML;

public class OuvinteVerificarAtrasos implements ActionListener {
	
	private Janela janela;
	
	public OuvinteVerificarAtrasos(Janela janela) {
		this.janela = janela;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Controler controler = new Controler();
		PersistenciaXML per = new PersistenciaXML();
		HorarioDao dao = per.recuperarHorario();
		ArrayList<Horario> horariosDeTrabalhos = dao.getListaDeHorarios();
		ArrayList<Horario> marcacoesFeitas = dao.getMarcacoesFeitas() ;
		ArrayList<Horario> atrasos = controler.subtracaoEntreHorarios(horariosDeTrabalhos, marcacoesFeitas);
		ArrayList<Horario> horasExtras = controler.diferencaEntreHorarios(horariosDeTrabalhos, marcacoesFeitas);
		
		dao.setAtrasos(atrasos);
		dao.setHorasExtras(horasExtras);
		per.salvarHorario(dao);
		
		janela.dispose();
		janela = new Janela();
	}

}
