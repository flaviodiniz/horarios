package Services;

import java.sql.Time;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Model.Horario;
import dao.HorarioDao;
import dao.PersistenciaXML;

public class Controler {
	
	private ArrayList<Horario> horariosDeTrabalhos;
	private ArrayList<Horario> marcacoesFeitas;
	private ArrayList<Horario> atrasos;
	private ArrayList<Horario> horasExtras;

	public ArrayList<Horario> listarMarcacoesFeitas() {
		PersistenciaXML per = new PersistenciaXML();
		HorarioDao dao = per.recuperarHorario();
		marcacoesFeitas = dao.getMarcacoesFeitas();
		return marcacoesFeitas;
	}
	
	public ArrayList<Horario> listarHorariosDeTrabalhos() {
		PersistenciaXML per = new PersistenciaXML();
		HorarioDao dao = per.recuperarHorario();	
		horariosDeTrabalhos = dao.getListaDeHorarios();
		return horariosDeTrabalhos;
	}
	
	public ArrayList<Horario> listarAtrasos() {
		PersistenciaXML per = new PersistenciaXML();
		HorarioDao dao = per.recuperarHorario();	
		atrasos = dao.getAtrasos();
		return atrasos;
	}
	
	public ArrayList<Horario> listarHorasExtras() {
		PersistenciaXML per = new PersistenciaXML();
		HorarioDao dao = per.recuperarHorario();
		horasExtras = dao.getHorasExtras();
		return horasExtras;
	}
	
	public void novoCadastroHorario(Time entrada, Time saida) {
		PersistenciaXML per = new PersistenciaXML();
		HorarioDao dao = per.recuperarHorario();	
		Horario horario = new Horario(entrada, saida);
		if (dao.getListaDeHorarios().size() <= 2) {			
			dao.cadastrarHorario(horario);
			per.salvarHorario(dao);
			//System.out.println("horário adicionado. Entrada = " + entrada + ", saida =" + saida);
		} else {
			JOptionPane.showMessageDialog(null, "Quantidade de horários atinginda. Igual a 3.");
		}
	}
	
	public void novoCadastroMarcacoes(Time entrada, Time saida) {
		PersistenciaXML per = new PersistenciaXML();
		HorarioDao dao = per.recuperarHorario();
		Horario horario = new Horario(entrada, saida);
		dao.cadastrarMarcacoes(horario);
		per.salvarHorario(dao);
		//System.out.println("horário adicionado. Entrada = " + entrada + ", saida =" + saida);
	}
	
	public ArrayList<Horario> subtracaoEntreHorarios(ArrayList<Horario> HorarioDeTrabalho, ArrayList<Horario> marcacoesFeitas){
		ArrayList<Horario> atrasos = new ArrayList<Horario>();
		int aux = 0;
		
		for (Horario h : HorarioDeTrabalho) {
			for (Horario m : marcacoesFeitas) {
				if (m.getEntrada().after(h.getEntrada()) && m.getEntrada().before(h.getSaida()) ) {
					Horario atraso = new Horario(h.getEntrada(), m.getEntrada());
					atrasos.add(atraso);
				}
				
				if (m.getSaida().before(h.getSaida()) && m.getSaida().after(h.getEntrada())) {
					Horario atraso = new Horario(m.getSaida(), h.getSaida());
					atrasos.add(atraso);
				}
				
				if (m.getEntrada().before(h.getEntrada()) && m.getSaida().equals(h.getEntrada())) {
					Horario atraso = new Horario(m.getSaida(), h.getSaida());
					atrasos.add(atraso);
					if (marcacoesFeitas.size() == 1) {
						aux = 1;
					}
				}
				
				if (m.getEntrada().before(h.getEntrada()) && m.getSaida().before(h.getEntrada()) && aux == 1)  {
					Horario atraso = new Horario(h.getEntrada(), h.getSaida());
					atrasos.add(atraso);
				}
				
				if (h.getEntrada().after(h.getSaida())) {
				
					if (m.getEntrada().after(h.getEntrada())) {
						Horario atraso = new Horario(h.getEntrada(), m.getEntrada());
						atrasos.add(atraso);
					}
					if (m.getEntrada().before(h.getEntrada()) && m.getEntrada().before(h.getSaida())) {
						Horario atraso = new Horario(h.getEntrada(), m.getEntrada());
						atrasos.add(atraso);
					}
					if (m.getSaida().before(h.getSaida())) {
						Horario atraso = new Horario(m.getSaida(), h.getSaida());
						atrasos.add(atraso);
					}
				}		
			}	
		}
		return atrasos;
	}
	
	public ArrayList<Horario> diferencaEntreHorarios(ArrayList<Horario> HorarioDeTrabalho, ArrayList<Horario> marcacoesFeitas){
		ArrayList<Horario> horasExtras = new ArrayList<Horario>();	
		Time entrada = null;
		Time saida = null;
		int quantidade = 0;
		int aux = 0;
		int antes = 0;
		
		for (Horario h : HorarioDeTrabalho) {
			for (Horario m : marcacoesFeitas) {
				/**
				 * Verifica se a marcacao foi antes e depois do horario
				 */
				if (m.getEntrada().before(h.getEntrada()) && m.getSaida().after(h.getSaida())) {
					if (h.getEntrada().before(m.getSaida()) && quantidade != 0 && aux == 0) {
						Horario horaExtra = new Horario(m.getEntrada(), h.getEntrada());
						horasExtras.add(horaExtra);
						aux = 1;
					}
					
					if (h.getEntrada().before(m.getSaida()) && quantidade == 0) {
						Horario horaExtra = new Horario(m.getEntrada(), h.getEntrada());
						horasExtras.add(horaExtra);
						entrada = h.getSaida();
						quantidade = 1;
					}
		
					if ( (m.getEntrada().equals(h.getEntrada()) || m.getEntrada().before(h.getEntrada())) && (h.getEntrada().after(entrada) || h.getEntrada().equals(entrada))) {
						Horario horaExtra = new Horario(h.getSaida(), m.getSaida());
						horasExtras.add(horaExtra);
						saida = h.getEntrada();
					}
					
					if (entrada != null && saida != null) {
						Horario horaExtra = new Horario(entrada, saida);
						horasExtras.add(horaExtra);
					}
					
				/**
				 * Verifica se a entrada e a saida da marcacao estão antes do horario 	
				 */
				} else if (m.getEntrada().before(h.getEntrada()) && m.getSaida().before(h.getEntrada())) {
					if (antes == 0) {
						Horario horaExtra = new Horario(m.getEntrada(), m.getSaida());
						horasExtras.add(horaExtra);
						antes = 1;
					}
				} else {
					/**
					 * Verificar se a marcação de encaixa antes do horario de entrada
					 */ 
					if (m.getEntrada().before(h.getEntrada()) && h.getEntrada().before(m.getSaida()) && quantidade == 0) {
						Horario horaExtra = new Horario(m.getEntrada(), h.getEntrada());
						horasExtras.add(horaExtra);
						antes = 1;
					}
					
					/**
					 * Verifica se a marcação se encaixa entre horarios
					 */
					if (m.getSaida().equals(h.getEntrada())) {
						Horario horaExtra = new Horario(entrada, m.getSaida());//verificar se é a marcação da saída
						if (entrada != null) {					
							horasExtras.add(horaExtra);
						}				
					}
					
					if (m.getSaida().after(h.getSaida()) && m.getEntrada().before(h.getEntrada())) {
						entrada = h.getSaida();
						aux = 1;
						quantidade = 1;
						System.out.println(entrada);
					}
					
					if (m.getEntrada().before(h.getSaida()) && entrada != null && m.getSaida().equals(h.getSaida())) {
						Horario horaExtra = new Horario(entrada, h.getEntrada());
						horasExtras.add(horaExtra);
					}
					
					if (m.getSaida().after(h.getEntrada()) && entrada != null && aux == 0) {
						if (entrada.before(h.getEntrada())) {
							Horario horaExtra = new Horario(entrada, h.getEntrada());
							horasExtras.add(horaExtra);	
						}
					}
					
					/**
					 * Verifica se a marcação se encaixa depois do horario
					 */
					if (m.getSaida().after(h.getSaida()) && m.getEntrada().equals(h.getEntrada()) && aux == 0) {
						Horario horaExtra = new Horario(h.getSaida(), m.getSaida());
						horasExtras.add(horaExtra);
					}
					
					if (m.getSaida().after(h.getSaida()) && m.getEntrada().before(h.getEntrada()) ) {
						Horario horaExtra = new Horario(h.getSaida(), m.getSaida());
						horasExtras.add(horaExtra);
					}
					
					if (m.getSaida().after(h.getSaida()) && m.getEntrada().after(h.getEntrada()) && m.getEntrada().before(h.getSaida()) ) {
						Horario horaExtra = new Horario(h.getSaida(), m.getSaida());
						horasExtras.add(horaExtra);
					}
					
					/**
					 * Verifica se a marcação se encaixa entre horarios nortunos
					 */
					if (h.getEntrada().after(h.getSaida())) {
						if (m.getEntrada().before(h.getEntrada()) && m.getEntrada().after(h.getSaida())) {
							Horario horaExtra = new Horario(m.getEntrada(), h.getEntrada());
							horasExtras.add(horaExtra);
						}
					}
				}
			}
		}
		
		return horasExtras;
	}
		
}
