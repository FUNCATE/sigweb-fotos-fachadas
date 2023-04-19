package br.org.funcate.fachadas;

import java.io.IOException;
import java.util.ArrayList;

public class CheckFotosFachadas {
	
	public static void main(String[] args) throws IOException 
	{
		int exists = 0;	
		int missing = 0;

		System.out.println("Checking for missing fotos fachadas");
		
		ArrayList<FotoFachada> fotos = (new FotoFachadaDAO()).getFotos("/dados/projetos/pmsv/Fachadas/");

		for (FotoFachada foto : fotos) 
		{
			if(foto.getFoto().exists())	
			{
				exists++;
			}
			else
			{
				missing++;
			}
		}
		
		System.out.println("Missing: " + missing);
		System.out.println("Exists: " + exists);
		
		
	}	
	
}
