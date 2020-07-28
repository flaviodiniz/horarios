package dao;

import java.util.ArrayList;

import Model.Horario;

public class HorarioDao {
	
	private ArrayList<Horario> listaDeHorarios = new ArrayList<Horario>();
	private ArrayList<Horario> marcacoesFeitas = new ArrayList<Horario>();
	private ArrayList<Horario> atrasos = new ArrayList<Horario>();
	private ArrayList<Horario> horasExtras = new ArrayList<Horario>();
	
	public ArrayList<Horario> getListaDeHorarios() {
		return listaDeHorarios;
	}

	public void setListaDeHorarios(ArrayList<Horario> listaDeHorarios) {
		this.listaDeHorarios = listaDeHorarios;
	}

	public ArrayList<Horario> getMarcacoesFeitas() {
		return marcacoesFeitas;
	}

	public void setMarcacoesFeitas(ArrayList<Horario> marcacoesFeitas) {
		this.marcacoesFeitas = marcacoesFeitas;
	}

	public ArrayList<Horario> getAtrasos() {
		return atrasos;
	}

	public void setAtrasos(ArrayList<Horario> atrasos) {
		this.atrasos = atrasos;
	}

	public ArrayList<Horario> getHorasExtras() {
		return horasExtras;
	}

	public void setHorasExtras(ArrayList<Horario> horasExtras) {
		this.horasExtras = horasExtras;
	}

	public void cadastrarHorario(Horario horario) {
		listaDeHorarios.add(horario);
	}
	
	public void cadastrarMarcacoes(Horario horario) {
		marcacoesFeitas.add(horario);
	}

	
}
