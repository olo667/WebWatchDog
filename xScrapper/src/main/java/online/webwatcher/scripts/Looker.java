package online.webwatcher.scripts;

import online.webwatcher.model.ThreadData;

public class Looker extends Thread {
	
	private ThreadData data;

	public Looker(ThreadData data) {
		super();
		this.data = data;
	}
	
	public void run() {
		
		try {
			sleep(this.data.getDelay());
			if(this.look()) {
				this.send();
			}
			this.data.descheduleNext();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void send() {
		MailSender.send(this.data.getId().getUrl());
	}

	private boolean look() {
		return false;
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
