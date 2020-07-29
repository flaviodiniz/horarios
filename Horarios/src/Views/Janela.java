package Views;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import Model.Horario;
import Services.Controler;
import Services.OuvinteLimparHorarios;
import Services.OuvinteLimparMarcacoes;
import Services.OuvinteLimparTabelaHorario;
import Services.OuvinteLimparTabelas;
import Services.OuvinteSubmeter;
import Services.OuvinteSubmeterMarcacao;
import Services.OuvinteVerificarAtrasos;

public class Janela extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private Controler controler;
	private JTextField textEntrada;
	private JTextField textSaida;
	private JTextField textEntrada2;
	private JTextField textSaida2;
	private DefaultTableModel modelo;
	private JTable horarioDeTrabalho = new JTable(modelo);
	private JTable marcacoesFeitas = new JTable(modelo);
	private JTable atrasos = new JTable(modelo);
	
	public Janela() {
		try {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			definicoes();
			repaint();
		} catch (ParseException e) {	
			e.printStackTrace();
		}
	}
	
	public void definicoes() throws ParseException {
		Evento evento = new Evento();
		setVisible(true);
		setSize(700, 550);
		setTitle("Projeto Horários");
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.BLACK);
		repaint();
		
		JLabel label = new JLabel("Horário de trabalho");
		label.setForeground(Color.WHITE);
		label.setBounds(110, 21, 149, 14);
		getContentPane().add(label);
		repaint();
		
		JLabel lblentrada = new JLabel("Entrada");
		lblentrada.setForeground(Color.WHITE);
		lblentrada.setBounds(20, 46, 101, 14);
		getContentPane().add(lblentrada);
		repaint();
		
		JLabel lblSaida = new JLabel("Saída");
		lblSaida.setForeground(Color.WHITE);
		lblSaida.setBounds(20, 74, 101, 14);
		getContentPane().add(lblSaida);
		repaint();
		
		MaskFormatter ent = new MaskFormatter("##:##");
		
		textEntrada = new JFormattedTextField(ent);
		textEntrada.setBounds(73, 43, 67, 20);
		getContentPane().add(textEntrada);
		//textEntrada.setColumns(5);
		textEntrada.addKeyListener(evento);
		repaint();

		textSaida = new JFormattedTextField(ent);
		textSaida.setBounds(73, 71, 67, 20);
		getContentPane().add(textSaida);
		textSaida.setColumns(5);
		repaint();
		
		OuvinteLimparHorarios ouvinteLimpar = new OuvinteLimparHorarios(this);
		JButton btlLimpar = new JButton("Limpar");
		btlLimpar.setForeground(Color.BLUE);
		btlLimpar.setBounds(20, 99, 79, 23);
		getContentPane().add(btlLimpar);
		btlLimpar.addActionListener(ouvinteLimpar);
		repaint();
		
		OuvinteSubmeter ouvinte = new OuvinteSubmeter(this);
		JButton btnSubmeter = new JButton("Submeter");
		btnSubmeter.setForeground(Color.BLUE);
		btnSubmeter.setBounds(110, 99, 95, 23);
		getContentPane().add(btnSubmeter);
		btnSubmeter.addActionListener(ouvinte);
		
		JLabel label2 = new JLabel("Marcações feitas");
		label2.setForeground(Color.WHITE);
		label2.setBounds(455, 21, 101, 14);
		getContentPane().add(label2);
		repaint();
		
		JLabel lblentrada2 = new JLabel("Entrada");
		lblentrada2.setForeground(Color.WHITE);
		lblentrada2.setBounds(384, 46, 101, 14);
		getContentPane().add(lblentrada2);
		repaint();
		
		JLabel lblSaida2 = new JLabel("Saída");
		lblSaida2.setForeground(Color.WHITE);
		lblSaida2.setBounds(384, 74, 101, 14);
		getContentPane().add(lblSaida2);
		repaint();
		
		textEntrada2 = new JFormattedTextField(ent);
		textEntrada2.setColumns(5);
		textEntrada2.setBounds(444, 43, 67, 20);
		getContentPane().add(textEntrada2);
		textEntrada2.addKeyListener(evento);
		repaint();
		
		textSaida2 = new JFormattedTextField(ent);
		textSaida2.setColumns(5);
		textSaida2.setBounds(444, 71, 67, 20);
		getContentPane().add(textSaida2);
		repaint();
		
		OuvinteSubmeterMarcacao ouvinte2 = new OuvinteSubmeterMarcacao(this);
		JButton button = new JButton("Submeter");
		button.setForeground(Color.BLUE);
		button.setBounds(477, 99, 95, 23);
		getContentPane().add(button);
		button.addActionListener(ouvinte2);
		repaint();
		
		OuvinteLimparMarcacoes ouvinteLimparMarcacoes = new OuvinteLimparMarcacoes(this);
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setForeground(Color.BLUE);
		btnLimpar.setBounds(384, 99, 79, 23);
		getContentPane().add(btnLimpar);
		btnLimpar.addActionListener(ouvinteLimparMarcacoes);
		repaint();
		
		JLabel lblAtraso = new JLabel("Atraso");
		lblAtraso.setForeground(Color.WHITE);
		lblAtraso.setBounds(20, 307, 101, 14);
		getContentPane().add(lblAtraso);
		repaint();
		
		JLabel lblHoraExtra = new JLabel("Hora extra");
		lblHoraExtra.setForeground(Color.WHITE);
		lblHoraExtra.setBounds(384, 307, 101, 14);
		getContentPane().add(lblHoraExtra);
		repaint();
		
		OuvinteVerificarAtrasos ouvinteAtrasos = new OuvinteVerificarAtrasos(this);
		JButton btnVerificar = new JButton("Verificar Atrasos e Extras");
		btnVerificar.setForeground(Color.BLUE);
		btnVerificar.setBounds(20, 487, 202, 23);
		getContentPane().add(btnVerificar);
		btnVerificar.addActionListener(ouvinteAtrasos);
		repaint();
		
		OuvinteLimparTabelaHorario ouvinteLimparTabelaHorario = new OuvinteLimparTabelaHorario(this);
		JButton verificarHoras = new JButton("Limpar Tabela Horário");
		verificarHoras.setForeground(Color.BLUE);
		verificarHoras.setBounds(487, 487, 180, 23);
		getContentPane().add(verificarHoras);
		verificarHoras.addActionListener(ouvinteLimparTabelaHorario);
		repaint();
		
		OuvinteLimparTabelas ouvinteLimparTabelas = new OuvinteLimparTabelas(this);
		JButton btnLimparTabelas = new JButton("Limpar tabelas");
		btnLimparTabelas.setForeground(Color.BLUE);
		btnLimparTabelas.setBounds(290, 487, 130, 23);
		getContentPane().add(btnLimparTabelas);
		btnLimparTabelas.addActionListener(ouvinteLimparTabelas);
		repaint();
		
		horariosDeTrabalho();
		repaint();
		marcacoesFeitas();		
		repaint();
		atraso();
		repaint();
		horaExtra();
		repaint();
	}
	
	public void horariosDeTrabalho() {
		controler = new Controler();
		Object[] listaComConteudos = null;
		modelo = new DefaultTableModel(null, new String[] { "Entrada", "Saída"});
		
		ArrayList<Horario> listaHorarios = controler.listarHorariosDeTrabalhos();
		for (Horario h : listaHorarios) {
			listaComConteudos = new Object[] { h.getEntrada(), h.getSaida()};
			modelo.addRow(listaComConteudos);
		}
		JPanel pl = new JPanel();
		pl.setBounds(20, 151, 300, 132);
		setVisible(true);
		
		horarioDeTrabalho = new JTable();
		horarioDeTrabalho.setModel(modelo);
		
		JScrollPane visivel = new JScrollPane(horarioDeTrabalho);
		visivel.setBounds(20, 151, 300, 132);
		getContentPane().add(visivel);
		repaint();
	}
	
	public void atraso() {
		controler = new Controler();
		Object[] listaComConteudos = null;
		modelo = new DefaultTableModel(null, new String[] { "Entrada", "Saída"});
		
		ArrayList<Horario> listaAtrasos = controler.listarAtrasos();
		for (Horario h : listaAtrasos) {
			listaComConteudos = new Object[] { h.getEntrada(), h.getSaida()};
			modelo.addRow(listaComConteudos);
		}
		
		JPanel pl = new JPanel();
		pl.setBounds(20, 347, 300, 123);
		setVisible(true);
		
		atrasos = new JTable();
		atrasos.setModel(modelo);
		
		JScrollPane visivel = new JScrollPane(atrasos);
		visivel.setBounds(20, 332, 300, 123);
		getContentPane().add(visivel);
		repaint();
	}

	public void marcacoesFeitas() {
		controler = new Controler();
		Object[] listaComConteudos = null;
		modelo = new DefaultTableModel(null, new String[] { "Entrada", "Saída"});
		
		ArrayList<Horario> listaMarcacoes = controler.listarMarcacoesFeitas();
		for (Horario h : listaMarcacoes) {
			listaComConteudos = new Object[] { h.getEntrada(), h.getSaida()};
			modelo.addRow(listaComConteudos);
		}
		
		JPanel pl = new JPanel();
		pl.setBounds(376, 151, 291, 132);
		setVisible(true);
		
		marcacoesFeitas = new JTable();
		marcacoesFeitas.setModel(modelo);
		
		JScrollPane visivel = new JScrollPane(marcacoesFeitas);
		visivel.setBounds(376, 151, 291, 132);
		getContentPane().add(visivel);
		repaint();		
	}
	
	public void horaExtra() {
		controler = new Controler();
		Object[] listaComConteudos = null;
		modelo = new DefaultTableModel(null, new String[] { "Entrada", "Saída"});
		
		ArrayList<Horario> listaHorasExtras = controler.listarHorasExtras();
		for (Horario h : listaHorasExtras) {
			listaComConteudos = new Object[] { h.getEntrada(), h.getSaida()};
			modelo.addRow(listaComConteudos);
		}
		
		JPanel pl = new JPanel();
		pl.setBounds(376, 347, 291, 123);
		setVisible(true);
		
		atrasos = new JTable();
		atrasos.setModel(modelo);
		
		JScrollPane visivel = new JScrollPane(atrasos);
		visivel.setBounds(376, 332, 291, 123);
		getContentPane().add(visivel);
		repaint();
	}

	public JTextField getTextEntrada() {
		return textEntrada;
	}

	public JTextField getTextSaida() {
		return textSaida;
	}

	public JTextField getTextEntrada2() {
		return textEntrada2;
	}

	public JTextField getTextSaida2() {
		return textSaida2;
	}

	public JTable getHorarioDeTrabalho() {
		return horarioDeTrabalho;
	}

	public JTable getMarcacoesFeitas() {
		return marcacoesFeitas;
	}

	public void setTextEntrada(JTextField textEntrada) {
		this.textEntrada = textEntrada;
	}

	public void setTextSaida(JTextField textSaida) {
		this.textSaida = textSaida;
	}

	public void setTextEntrada2(JTextField textEntrada2) {
		this.textEntrada2 = textEntrada2;
	}

	public void setTextSaida2(JTextField textSaida2) {
		this.textSaida2 = textSaida2;
	}
	
	private class Evento implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
		}
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
		}
		@Override
		public void keyReleased(KeyEvent e) {
			if ((JTextField)e.getComponent() == textEntrada) {
				String valor = textEntrada.getText().toString();
				char valorFinal = valor.charAt(valor.length()-1);
				if (valorFinal != ' ') {
					//System.out.println(valorFinal);	
					textSaida.grabFocus();	
				}
			}
			if ((JTextField)e.getComponent() == textEntrada2) {
				String valor = textEntrada2.getText().toString();
				char valorFinal = valor.charAt(valor.length()-1);
				if (valorFinal != ' ') {
					//System.out.println(valorFinal);	
					textSaida2.grabFocus();	
				}	
			}
			
		}
    }
	
}
