package tests;


import org.quartz.JobExecutionException;

import control.Notificacao;


public class NotificacaoTeste {

	public static void main(String[] args) throws JobExecutionException {
		Notificacao n = new Notificacao();
			n.execute(null);	
	}

}
