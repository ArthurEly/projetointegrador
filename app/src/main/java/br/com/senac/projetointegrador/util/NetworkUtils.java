package br.com.senac.projetointegrador.util;

import java.sql.*;

public class NetworkUtils
{
    public static String status = "Não conectou...";

    public NetworkUtils(){}

    public static java.sql.Connection getConexaoBD()
    {
        Connection connection = null;
        try
        {
          String driverName = "com.mysql.jbdc.Driver";
          Class.forName(driverName);

          String caminhoServidor = "https://databases.000webhost.com";
          String nomeBD = "id12053257_users";
          String url = "jbdc:mysql://" + caminhoServidor + "/" + nomeBD;
          String nomeUsuario = "id12053257_eduapps";
          String senhaUsuario = "Bancodedados$2";

          connection = DriverManager.getConnection(url,nomeUsuario,senhaUsuario);

          if (connection != null)
          {
              status = ("Conectado com sucesso");
          }
          else
          {
              status = ("Falha na conexão");
          }

          return connection;

        }
        catch (ClassNotFoundException e)
        {
            System.out.println("O driver expecificado não foi encontrado.");
            return null;

        }
        catch (SQLException e)
        {
            System.out.println("Não foi possível se conectar com o banco de dados.");
            return null;
        }

    }

    public static String statusDaConexao() {

        return status;

    }

    public static boolean FecharConexao() {

        try
        {
            NetworkUtils.getConexaoBD().close();
            return true;
        }
        catch (SQLException e) {

            return false;
        }
    }

    public static java.sql.Connection ReiniciarConexao()
    {
        FecharConexao();
        return NetworkUtils.getConexaoBD();
    }
}


