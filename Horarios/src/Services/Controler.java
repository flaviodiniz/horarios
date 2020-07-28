package Services;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
		Time aux = null;
		try {
			String str = "00:00";
			SimpleDateFormat formatador = new SimpleDateFormat("HH:mm"); 
			Date data;
			data = formatador.parse(str);
			aux = new Time(data.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		
		for (Horario h : HorarioDeTrabalho) {
			for (Horario m : marcacoesFeitas) {
				if (m.getEntrada().before(h.getEntrada()) && h.getEntrada().before(m.getSaida())) {
					Horario horaExtra = new Horario(m.getEntrada(), h.getEntrada());
					horasExtras.add(horaExtra);
				}
				
				if (m.getSaida().after(h.getSaida()) && m.getEntrada().equals(h.getEntrada())) {
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
				
//				if (m.getEntrada().before(h.getEntrada()) && m.getSaida().before(h.getEntrada()) && m.getEntrada().after(aux)) {
//					Horario horaExtra = new Horario(m.getEntrada(), m.getSaida());
//					horasExtras.add(horaExtra);
//					aux = h.getSaida();
//				
//				}
				
				if (h.getEntrada().after(h.getSaida())) {
					if (m.getEntrada().before(h.getEntrada()) && m.getEntrada().after(h.getSaida())) {
						Horario horaExtra = new Horario(m.getEntrada(), h.getEntrada());
						horasExtras.add(horaExtra);
					}
					
//					if (m.getSaida().after(h.getSaida())) {
//						System.out.println(m.getEntrada());
//						Horario horaExtra = new Horario(h.getSaida(), m.getSaida());
//						horasExtras.add(horaExtra);
//					}
				}
			}
			
		}
		
		return horasExtras;
	}
		
}
