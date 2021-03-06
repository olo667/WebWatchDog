package online.webwatcher.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the threads database table.
 * 
 */
@Embeddable
public class ThreadPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String url;

	@Column(name="user_mail", insertable=false, updatable=false)
	private String userMail;

	public ThreadPK() {
	}
	public String getUrl() {
		return this.url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUserMail() {
		return this.userMail;
	}
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ThreadPK)) {
			return false;
		}
		ThreadPK castOther = (ThreadPK)other;
		return 
			this.url.equals(castOther.url)
			&& this.userMail.equals(castOther.userMail);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.url.hashCode();
		hash = hash * prime + this.userMail.hashCode();
		
		return hash;
	}
}