package br.org.funcate.fachadas;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ImportadorFotosFachadas {
	
	public static final String EXTENSAO_FOTOS = ".JPG";

	public static void main(String[] args) throws IOException {
	
		File path = new File("/dados/biomas-saovicente/fotos-fachadas/Fachadas/");
		
		ArrayList<FotoFachada> fotos = new ArrayList<FotoFachada>();
		
		File[] files = path.listFiles();
		
		// for (File dir : files) 
		// {
		// 	System.out.println("Listando diretorio: " + dir.getAbsolutePath());
		// 	if(dir.isDirectory())
		// 	{
				for(File fotoFile : files)
				{
				 	System.out.println("Processando arquivo: " + fotoFile.getAbsolutePath());
					if(fotoFile.getName().toUpperCase().contains(ImportadorFotosFachadas.EXTENSAO_FOTOS))
					{
						String filename = fotoFile.getName().toUpperCase().replace(ImportadorFotosFachadas.EXTENSAO_FOTOS, "");
						FotoFachada foto = new FotoFachada();
						
						String[] filenameSplit = filename.split("_");
						
						if(filenameSplit.length>0)
						{
							try{
								foto.setInscricao(filenameSplit[0]);
								foto.setFoto(fotoFile);
								fotos.add(foto);
	
							} catch(Exception e)
							{
								System.out.println("Problema decodificando filename: " + filenameSplit[0] + " - " + e.getMessage());
							}
							
							
							//System.out.println(foto);
						}
					}
					
				}
		// 	}
			
		// }
		
		System.out.println("Quantidade de Fotos Processadas: " + fotos.size());
		
		//new FotoFachadaDAO().createTable();
		
		int inserted = new FotoFachadaDAO().insertFotosFachadas(fotos);
		
		System.out.println("Quantidade de Fotos Importadas: " + inserted);
		
		
		
	}

}
