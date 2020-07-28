package Services;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Model.Horario;
import Views.Janela;
import dao.HorarioDao;
import dao.PersistenciaXML;

public class OuvinteVerificarHorasExtras implements ActionListener {
	
	private Janela janela;
	
	public OuvinteVerificarHorasExtras(Janela janela) {
		this.janela = janela;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Controler controler = new Controler();
		PersistenciaXML per = new PersistenciaXML();
		HorarioDao dao = per.recuperarHorario();
		ArrayList<Horario> horariosDeTrabalhos = controler.listarHorariosDeTrabalhos();
		ArrayList<Horario> marcacoesFeitas = controler.listarMarcacoesFeitas();
		ArrayList<Horario> horasExtras = controler.diferencaEntreHorarios(horariosDeTrabalhos, marcacoesFeitas);
		
		dao.setHorasExtras(horasExtras);
		per.salvarHorario(dao);
		
		janela.dispose();
		janela = new Janela();
		
	}

}
