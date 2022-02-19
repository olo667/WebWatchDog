package online.webwatcher.scripts;



import org.springframework.web.reactive.function.client.WebClient;

import online.webwatcher.model.ThreadData;
import reactor.core.publisher.Mono;

public class Looker extends Thread {
	
	private ThreadData data;
	private WebClient client;
	
	public Looker(ThreadData data) {
		super();
		this.data = data;
		
		this.client = WebClient.create(data.getId().getUrl());
		
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
		Mono<String> mono = client.get().retrieve().bodyToMono(String.class).;
		
	}
	
	
	
	
}
