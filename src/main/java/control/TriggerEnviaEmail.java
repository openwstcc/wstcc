package control;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;

public class TriggerEnviaEmail {
	public static void main(String[] args) throws Exception {

		JobDetail job = new JobDetail();
		job.setName("EnviaEmai");
    	job.setJobClass(Notificacao.class);
    	
    	CronTrigger trigger = new CronTrigger();
    	trigger.setName("TriggerEnviaEmai");
    	trigger.setCronExpression("0 0 11 1/1 * ? *");
    	
    	Scheduler scheduler = new StdSchedulerFactory().getScheduler();
    	scheduler.start();
    	scheduler.scheduleJob(job, trigger);
	}

}
