package br.org.funcate.fachadas;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ImportadorFotosFachadas {
	
	public static final String[] EXTENSOES_FOTOS = {".JPG", ".PNG"};

	public static void main(String[] args) throws IOException {
	
		File path = new File("/dados/projetos/pmsv/Fachadas/");
		
		ArrayList<String> ignoredFiles = new ArrayList<String>(); 
		
		ArrayList<FotoFachada> fotos = new ArrayList<FotoFachada>();
		
		File[] files = path.listFiles();
		

				for(File fotoFile : files)
				{
					String fileExtension = ImportadorFotosFachadas.getFileExtensionIfCompatible(fotoFile.getName());
					if(fileExtension!=null)
					{
						String filenameWOExt=fotoFile.getName().toUpperCase().replace(fileExtension.toUpperCase(), "");
						
						FotoFachada foto = new FotoFachada();
						
						String[] filenameSplit = filenameWOExt.split("_");
						
						if(filenameSplit.length>0)
						{
							foto.setInscricao(filenameSplit[0]);
							foto.setFoto(fotoFile);
							
							fotos.add(foto);
							
						}
						else
						{
							ignoredFiles.add(fotoFile.getName());
						}
					}
					else
					{
						ignoredFiles.add(fotoFile.getName());
					}
					
				}

		
		System.out.println("Quantidade de Fotos Processadas: " + fotos.size());
		System.out.println("Quantidade de Fotos Ignoradas: " + ignoredFiles.size());
		
		//new FotoFachadaDAO().createTable();
		
		int inserted = new FotoFachadaDAO().insertFichas(fotos);
		
		System.out.println("Quantidade de Fotos Importadas: " + inserted);
		
		
		
	}
	
	public static String getFileExtensionIfCompatible(String filename)
	{
		String compatible = null;
		for (String extension : EXTENSOES_FOTOS) 
		{
			if(filename.toUpperCase().contains(extension.toUpperCase()))
			{
				return extension.toUpperCase();
			}
		}
		
		return compatible;

	}

}
