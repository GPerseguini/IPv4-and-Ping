package view;

import javax.swing.JOptionPane;
import controller.RedesController;

public class Principal {

	public static void main(String[] args) {
		int opc=0;
		while (opc!=3){
			RedesController procController = new RedesController();
			String os = procController.os();
			opc = Integer.parseInt(JOptionPane.showInputDialog("Bem-vindo! Selecione a opção desejada.\n\n 1 - Visualizar Adaptadores Ethernet e IPv4\n\n 2 - Visualizar ping para google.com\n\n 3 - Sair\n "));
			switch(opc){
				case 1:
					if(os.contains("Windows")){
						String process = "ipconfig";
						procController.readProcess(process, os);
					}else{
						String process = "ifconfig";
						procController.readProcess(process, os);
					}
					break;
				case 2:
					if(os.contains("Windows")){
						String process = "ping www.google.com.br -n 10";
						procController.readPing(process, os);
					}else{
						String process = "ping www.google.com -c 10";
						procController.readPing(process, os);
					}
					break;
				}
		}
	}
}