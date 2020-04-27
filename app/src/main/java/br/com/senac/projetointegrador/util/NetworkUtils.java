package br.com.senac.projetointegrador.util;

import java.sql.*;
import android.util.*;
import edos.app.*;
import android.app.Activity;
import br.com.senac.projetointegrador.*;
import android.widget.*;
import java.io.*;
import org.json.*;
import android.content.*;
import java.util.*;
import java.net.*;
import android.os.*;

public class NetworkUtils {
	public static final String TABLE_USERS = "usuario";
	public static final String TABLE_SERIES = "series";
	public static final String TABLE_EPISODES = "episodios";
	
    public NetworkUtils(){}

	public static String sqlGet(String table) {
		String vars = buildURL(new String[] {
			"table"
		}, new String[] {
			table
		});
		
		String url = "https://up-team.000webhostapp.com/get.php?" + vars;
		
		return curl(url);
	}
	
	public static String sqlGet(String table,String where,String equals) {
		String vars = buildURL(new String[] {
			"table",
			"where",
			"equals"
		}, new String[] {
			table,
			where,
			equals
		});

		String url = "https://up-team.000webhostapp.com/get.php?" + vars;

		return curl(url);
	}
	
	public static void sqlPostUser(int id, String name, String email, String password, int idade, boolean pro) {
		String vars = buildURL(new String[] {
			"table",
			"authName",
			"authPass",
			"usuario_id",
			"usuario_nome",
			"usuario_email",
			"usuario_senha",
			"usuario_pro",
			"usuario_idade"
		}, new String[] {
			"usuario",
			"id12053257_eduapps",
			"Bancodedados$2",
			Integer.toString(id),
			name,
			email,
			password,
			Boolean.toString(pro),
			Integer.toString(idade)
		});

		String url = "https://up-team.000webhostapp.com/post.php?" + vars;
		
		execWebPage(url);
	}
	
	public static void sqlPostUser(String name, String email, String password, int idade, boolean pro) {
		String vars = buildURL(new String[] {
			"table",
			"authName",
			"authPass",
			"usuario_nome",
			"usuario_email",
			"usuario_senha",
			"usuario_pro",
			"usuario_idade",
		}, new String[] {
			"usuario",
			"id12053257_eduapps",
			"Bancodedados$2",
			name,
			email,
			password,
			Boolean.toString(pro),
			Integer.toString(idade),
		});

		String url = "https://up-team.000webhostapp.com/post.php?" + vars;

		execWebPage(url);
	}
	
	public static void execWebPage(String url, ExceptionUtils.ExceptionListener listener) {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

		StrictMode.setThreadPolicy(policy);
		try {
			URL yahoo = new URL(url);
    	    URLConnection c = yahoo.openConnection();
        	BufferedReader in = new BufferedReader(
			new InputStreamReader(c.getInputStream()));
        	String inputLine;

        while ((inputLine = in.readLine()) != null) {
			System.out.println(inputLine);
		}
        in.close();
		} catch(MalformedURLException e) {
			listener.onException(e);
		} catch(IOException e) {
			listener.onException(e);
		}
	}
	
	public static void execWebPage(String url) {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

		StrictMode.setThreadPolicy(policy);
		try {
			URL yahoo = new URL(url);
    	    URLConnection c = yahoo.openConnection();
        	BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
        	String inputLine;

			while ((inputLine = in.readLine()) != null) {
				System.out.println(inputLine);
			}
			in.close();
		} catch(MalformedURLException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
    
	public static String buildURL(String[] vars, String[] values) {
		String url = "";
		for (int x = 0;x < vars.length;x++) {
			url += vars[x] + "=" + values[x] + "&";
		}
		
		url.replace(" ", "%20");
		
		if (url.charAt(url.length() - 1) == '&') {
			return url.substring(0,url.length() - 1);
		} else {
			return url;
		}
	}
	
	public static String curl(String[] params, String url) {
		try {
			String args = String.join(" ", params);
			String fullcommand = "";
			fullcommand = "curl " + args + " " + url;
			Runtime r = Runtime.getRuntime();
			java.lang.Process p = r.exec(fullcommand);
			System.out.println("Run: " + fullcommand);
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String response = null;
			String output = "";
			for (int x = 0; (response = stdInput.readLine()) != null; x++) {
				output += response + "\n";
			}
			while (p.isAlive()) {
				// NOTHING!
			}
			if (!p.isAlive()) {
				return String.join("\n", output);
			} else {
				return "this will never appear";
			}
		} catch (IOException e) {
			e.printStackTrace();
			return "Error: " + e.getMessage();
		}
	}
	
	public static String curl(String url) {
		try {
			String fullcommand = "";
			fullcommand = "curl " + url;
			Runtime r = Runtime.getRuntime();
			java.lang.Process p = r.exec(fullcommand);
			System.out.println("Run: " + fullcommand);
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String response = null;
			String output = "";
			for (int x = 0; (response = stdInput.readLine()) != null; x++) {
				output += response + "\n";
			}
			while (p.isAlive()) {
				// NOTHING!
			}
			if (!p.isAlive()) {
				return String.join("\n", output);
			} else {
				return "this will never appear";
			}
		} catch (IOException e) {
			e.printStackTrace();
			return "Error: " + e.getMessage();
		}
	}

	public static String wget(String[] params, String url) {
		try {
			String args = String.join(" ", params);
			String fullcommand = "";
			fullcommand = "wget " + args + " \"" + url + "\"";
			Runtime r = Runtime.getRuntime();
			java.lang.Process p = r.exec(fullcommand);
			System.out.println("Run: " + fullcommand);
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String response = null;
			String output = "";
			for (int x = 0; (response = stdInput.readLine()) != null; x++) {
				output += response + "\n";
			}
			while (p.isAlive()) {
				// NOTHING!
			}
			if (!p.isAlive()) {
				return String.join("\n", output);
			} else {
				return "this will never appear";
			}
		} catch (IOException e) {
			e.printStackTrace();
			return "Error: " + e.getMessage();
		}
	}
	
	public static JSONObject parseDataBase(String sqlResponse,int linha) {
		try {
			JSONArray json = new JSONArray(sqlResponse);
			return json.getJSONObject(linha);
		} catch(JSONException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void cacheUser(Context act, JSONObject usuario) throws JSONException {
		boolean pro = false;
		SharedPreferences.Editor c = AndroidUtils.getCache(act).edit();
		
		if (usuario.getInt("usuario_pro") == 1) {
			pro = true;
		} else {
			pro = false;
		}
		
		c.putInt("usuario_id",usuario.getInt("usuario_id"));
		c.putString("usuario_nome",usuario.getString("usuario_nome"));
		c.putString("usuario_email",usuario.getString("usuario_email"));
		c.putString("usuario_senha",usuario.getString("usuario_senha"));
		c.putBoolean("usuario_pro",pro);
		c.putString("usuario_series",usuario.getJSONArray("usuario_series").toString());
		c.putString("usuario_ultimo_episodio",usuario.getString("usuario_ultimo_episodio"));
		c.putInt("usuario_idade",usuario.getInt("usuario_idade"));
		
		c.commit();
	}
	
	public static void cacheUser(Context act,int id, String nome, String email, int idade, String senha, boolean pro,String[] series, String ultimoEp) {
		SharedPreferences.Editor c = AndroidUtils.getCache(act).edit();
		String seriesString = "[" + String.join(", ",series) + "]";
		
		c.putInt("usuario_id",id);
		c.putString("usuario_nome",nome);
		c.putString("usuario_email",email);
		c.putString("usuario_senha",senha);
		c.putBoolean("usuario_pro",pro);
		c.putString("usuario_series",seriesString);
		c.putString("usuario_ultimo_episodio",ultimoEp);
		c.putInt("usuario_idade",idade);

		c.commit();
	}
	

	public static void cacheUser(Context act,int id) throws JSONException {
		SharedPreferences.Editor c = AndroidUtils.getCache(act).edit();
		String db = sqlGet(TABLE_USERS,"usuario_id","" + id);
		JSONObject j = new JSONArray(db).getJSONObject(0);
		boolean pro = false;
		
		if (j.getInt("usuario_pro") == 1) {
			pro = true;
		} else {
			pro = false;
		}
		c.putInt("usuario_id",id);
		c.putString("usuario_nome",j.getString("usuario_nome"));
		c.putString("usuario_email",j.getString("usuario_email"));
		c.putString("usuario_senha",j.getString("usuario_senha"));
		c.putBoolean("usuario_pro",pro);
		c.putString("usuario_series",j.getString("usuario_pro"));
		c.putString("usuario_ultimo_episodio",j.getString("usuario_ultimo_episodio"));
		c.putInt("usuario_idade",j.getInt("usuario_idade"));

		c.commit();
	}
}


