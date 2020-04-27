package br.com.senac.projetointegrador.view;
import android.widget.*;
import android.database.*;
import android.view.*;
import br.com.senac.projetointegrador.*;

public class USB {
	String nome = "N/a";
	String sinopse = "N/a";
	int image = R.drawable.ic_launcher;
	
	public USB(String n, String s, int i) {
		nome = n;
		sinopse = s;
		image = i;
	}
	
	public USB(String n, String s) {
		nome = n;
		sinopse = s;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setImage(int image) {
		this.image = image;
	}

	public int getImage() {
		return image;
	}
}
