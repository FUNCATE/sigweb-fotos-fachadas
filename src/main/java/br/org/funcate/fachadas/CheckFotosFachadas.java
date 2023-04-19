package br.org.funcate.fachadas;

import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;



public class CheckFotosFachadas {
	
	public static void main(String[] args) throws IOException 
	{
		int exists = 0;	
		int missing = 0;

		System.out.println("Checking for missing fotos fachadas");
		
		ArrayList<FotoFachada> fotos = (new FotoFachadaDAO()).getFotos("/pmsv/dados/fotos-fachadas/Fachadas/");

		for (FotoFachada foto : fotos) 
		{
//			System.out.println(foto.getFoto().getPath());
			if(foto.getFoto().exists())	
			{
				exists++;
			}
			else
			{
				missing++;
				File file = new File("missing-fotos.txt");
				FileWriter fr = new FileWriter(file, true);
				String ic = foto.getInscricao();
				String filePath = foto.getFoto().getPath();
				fr.write(ic+";"+filePath+"\n");
				fr.close();
			}
		}
		
		System.out.println("Missing: " + missing);
		System.out.println("Exists: " + exists);
		
		
	}	
	
}
