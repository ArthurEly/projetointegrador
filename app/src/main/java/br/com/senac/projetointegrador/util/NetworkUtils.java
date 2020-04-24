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
    
	public static String buildURL(String[] vars, String[] values) {
		String url = "";
		for (int x = 0;x < vars.length;x++) {
			url += vars[x] + "=" + values[x] + "&";
		}
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
			Process p = r.exec(fullcommand);
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
			Process p = r.exec(fullcommand);
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
			Process p = r.exec(fullcommand);
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
		
		c.commit();
	}
}


