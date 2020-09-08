package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RedesController {

	public RedesController (){
		super();
	}
	
	//Retornar o sistema operacional ativo na máquina.
	public String os() {
		String os = System.getProperty("os.name");
		String version = System.getProperty("os.version");
		return os + version;
	}
	
	public void callProcess(String process){
		try {
			Runtime.getRuntime().exec(process);
		} catch (IOException e) {
			String msgErro = e.getMessage();
			//System.err.println(msgErro);
			if (msgErro.contains("740")){
				// para dar acesso de administrador no windows > //cmd /c caminho_do_processo
				StringBuffer buffer = new StringBuffer();
				buffer.append("cmd /c");
				buffer.append(" ");
				buffer.append(process);
				try {
					Runtime.getRuntime().exec(buffer.toString());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else{
				System.err.println(msgErro);
			}
		}
	}
	
	public void readProcess(String process, String os){
		try {
			Process p = Runtime.getRuntime().exec(process);
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();

			if (os.contains("Mac")){
				while (linha != null){
					String aux = "";
					do {
						if (linha.contains("mtu")){
							aux = linha;
						}
						if (linha.contains("inet")&&!linha.contains("inet6")){
							System.out.println(aux);
							System.out.println(linha);
							}
						linha = buffer.readLine();
					
					}while (!linha.contains("mtu"));
				}
			}else{
				while (linha != null){
					String aux = "";
					do {
						if (linha.contains("Adaptador")){
							aux = linha;
						}
						if (linha.contains("v4")){
							System.out.println(aux);
							System.out.println(linha);
							}
						linha = buffer.readLine();
					
					}while (!linha.contains("Adaptador"));
				}
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}

	
	

	public void readPing(String process, String os){
		try {
			Process p = Runtime.getRuntime().exec(process);
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();

			if (os.contains("Mac")){
				while (linha != null){
					String vt[] = new String[7];
					String aux = "";
						if (linha.contains("ms")){
							vt = linha.split(" ");
							aux = "www.google.com " + vt[6] + vt[7];
							System.out.println(aux);
						}
						
						linha = buffer.readLine();
				}

			}else{
				while (linha != null){
					String vt[] = new String[6];
					String aux = "";
					
						if (linha.contains("ms")&&linha.contains("bytes")){
							vt = linha.split(" ");
							aux = "www.google.com " + vt[4];
							System.out.println(aux);
						}
						linha = buffer.readLine();
						
				}
			}
		}catch (Exception e) {
			//e.printStackTrace();
		}
	}
}