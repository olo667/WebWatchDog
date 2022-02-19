package online.webwatcher.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.persistence.*;

import ch.qos.logback.core.util.Duration;


/**
 * The persistent class for the threads database table.
 * 
 */
@Entity
@Table(name="threads")
@NamedQuery(name="ThreadData.findAll", query="SELECT t FROM ThreadData t")
@NamedQuery(name="ThreadData.findById", query="SELECT t FROM ThreadData t WHERE t.id = :id")
public class ThreadData implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ThreadPK id;

	private List<LocalDateTime> schedule;

	private List<LocalDateTime> oldSchedule;
	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user_mail")
	private User user;

	public ThreadData() {
	}

	public ThreadPK getId() {
		return this.id;
	}

	public void setId(ThreadPK id) {
		this.id = id;
	}

	public Long getDelay() {
		return ChronoUnit.MILLIS.between(LocalDateTime.now(), this.schedule.get(0));
	}


	public Integer getRefreshesLeft() {
		return this.schedule.size();
	}

	public void addSchedule(LocalDateTime ldt) {
		this.schedule.add(ldt);
	}
	
	public void addSchedule(ZonedDateTime zdt) {
		this.addSchedule(zdt.toLocalDateTime());
	}
	
	public LocalDateTime getNextScheduled() {
		return this.schedule.get(0);
	}
	
	public List<LocalDateTime> getSchedule(){
		return this.schedule;
	}
	
	public List<LocalDateTime> getOldSchedule(){
		return this.oldSchedule;
	}
	
	public void descheduleNext() {
		this.oldSchedule.add(this.getNextScheduled());
		
		this.schedule.remove(0);
	}
	
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}