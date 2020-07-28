package Model;

import java.sql.Time;

public class Horario {
	
	public Time entrada;
	public Time saida;
	
	public Time getEntrada() {
		return entrada;
	}
	public void setEntrada(Time entrada) {
		this.entrada = entrada;
	}
	public Time getSaida() {
		return saida;
	}
	public void setSaida(Time saida) {
		this.saida = saida;
	}
	
	public Horario(Time entrada, Time saida) {
		super();
		this.entrada = entrada;
		this.saida = saida;
	}	

}
