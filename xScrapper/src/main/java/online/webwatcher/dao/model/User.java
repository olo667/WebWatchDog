package online.webwatcher.dao.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String email;

	private BigDecimal balance;

	@Column(name="password_hash")
	private String passwordHash;

	//bi-directional many-to-one association to Thread
	@OneToMany(mappedBy="user")
	private List<Thread> threads;

	public User() {
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BigDecimal getBalance() {
		return this.balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getPasswordHash() {
		return this.passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public List<Thread> getThreads() {
		return this.threads;
	}

	public void setThreads(List<Thread> threads) {
		this.threads = threads;
	}

	public Thread addThread(Thread thread) {
		getThreads().add(thread);
		thread.setUser(this);

		return thread;
	}

	public Thread removeThread(Thread thread) {
		getThreads().remove(thread);
		thread.setUser(null);

		return thread;
	}

}