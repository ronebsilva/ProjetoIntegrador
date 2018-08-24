package br.senac.tads.pi3b.agenda;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rbezerra
 */
public class Agenda
{
    private Connection obterConexao () throws SQLException, ClassNotFoundException
    {
        Connection conn = null;
        //Passo 1 - Registrar driver JDBC
        
        Class.forName("com.mysql.jdbc.Driver");
        
        //Passo 2 - Obter a Conexao
        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/agendabd",
                "root",
                "");
        
        return conn;
    }
    
    public void executar()
    {
        String querySql = "SELECT ID, NOME, DTNASCIMENTO FROM PESSOA";
        
        try (Connection conn = obterConexao();
                PreparedStatement stmt = conn.prepareStatement(querySql);
                ResultSet resultados = stmt.executeQuery())
        {
            while (resultados.next())
            {
                long id = resultados.getLong("ID");
                String nome = resultados.getString("NOME");
                Date dtNascimento = resultados.getDate("DTNASCIMENTO");
                
                System.out.println(id + " " + nome + " " + dtNascimento);
            }
        }
        
        catch (SQLException ex)
        {
            Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) 
    {
        Agenda agenda = new Agenda();
        agenda.executar();
        
        for (int i = 0; i < 100; i++) 
        {
            System.out.println("Número " + i);
        }
    }
}
