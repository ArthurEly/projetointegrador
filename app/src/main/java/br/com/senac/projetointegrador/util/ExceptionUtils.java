package br.com.senac.projetointegrador.util;
import java.io.*;

public class ExceptionUtils {
	public static String getErrorText(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		return sw.toString();
	}
	
	public interface ExceptionListener {
		public void onException(Exception e);
	}
}
