package br.org.funcate.fachadas;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

public class FotoFachadaDAO 
{
	public void createTable() 
	{
		Connection c = null;

		Statement stmt = null;

		try {

			Class.forName("org.postgresql.Driver");

			c = DriverManager.getConnection("jdbc:postgresql://192.168.3.152:5432/saovicente", "postgres", "postgres");

//		     c.setAutoCommit(false);

			//System.out.println("Successfully Connected.");

			stmt = c.createStatement();

			stmt.execute("DROP TABLE IF EXISTS public.fotos_fachada;"
					+ "CREATE TABLE public.fotos_fachada"
					+ "(\n"
					+ "    id serial NOT NULL,\n"
					+ "    ic text COLLATE pg_catalog.\"default\" NOT NULL,\n"
					+ "    setor text COLLATE pg_catalog.\"default\" NOT NULL,\n"
					+ "    quadra text COLLATE pg_catalog.\"default\" NOT NULL,\n"
					+ "    logradouro text COLLATE pg_catalog.\"default\" NOT NULL,\n"
					+ "    lote text COLLATE pg_catalog.\"default\" NOT NULL,\n"
					+ "    sublote text COLLATE pg_catalog.\"default\" NOT NULL,\n"
					+ "    origem text COLLATE pg_catalog.\"default\" NOT NULL,\n"
					+ "    data bytea NOT NULL,\n"
					+ "    CONSTRAINT fotos_fachada_pkey PRIMARY KEY (id)\n"
					+ ");\n"
					+ "");
			

			stmt.close();

		//	c.commit();
			c.close();

		} catch (Exception e) {

			System.err.println(e.getClass().getName() + ": " + e.getMessage());

		}
	}
	
	public int insertFichas(ArrayList<FotoFachada> fotos) 
	{
		Connection c = null;
		int inserted = 0;

		try {

			Class.forName("org.postgresql.Driver");

			c = DriverManager.getConnection("jdbc:postgresql://192.168.3.152:5432/saovicente", "postgres", "postgres");

			
			
			for (FotoFachada foto : fotos) 
			{

				String sql = "insert into public.fotos_fachada (ic, setor, quadra, logradouro, lote, sublote, origem, data) values (?, ?, ?, ?, ?, ?, ?, ?)";
				
				PreparedStatement pstmt  = c.prepareStatement(sql);

				pstmt.setString(1, foto.getInscricao());
				pstmt.setString(2, foto.getSetor());
				pstmt.setString(3, foto.getQuadra());
				pstmt.setString(4, foto.getLogradouro());
				pstmt.setString(5, foto.getLote());
				pstmt.setString(6, foto.getSublote());
				pstmt.setString(7, foto.getFoto().getName());
				pstmt.setBinaryStream(8, new FileInputStream(foto.getFoto()));
				
				pstmt.execute();
				
				inserted++;				
				
				pstmt.close();

			}
			c.close();
			
			
		} catch (Exception e) {

			System.err.println(e.getClass().getName() + ": " + e.getMessage());

		} 
		return inserted;
	}
}
