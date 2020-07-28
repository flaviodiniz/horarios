package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class PersistenciaXML {

	private XStream xstream = new XStream(new DomDriver());
	private File file = new File("horarioDao.xml");

	public void salvarHorario(HorarioDao horario) {
		String xml = xstream.toXML(horario);
		PrintWriter gravar = null;
		try {
			if (!file.exists())
				file.createNewFile();
			gravar = new PrintWriter(file);
			gravar.write(xml);
			gravar.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public HorarioDao recuperarHorario() {
		try {
			if (file.exists()) {
				FileInputStream recFile = new FileInputStream(file);
				return (HorarioDao) xstream.fromXML(recFile);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return new HorarioDao();
	}

}
