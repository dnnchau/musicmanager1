package musicmgr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "composer")
public class Composer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "composerID")
	private Long composerID;
	@Column(name = "composerName")
	private String composerName;
	@Column(name = "composerDesc")
	private String composerDesc;

	public Composer() {
	}

	public Composer(Long composerID, String composerName, String composerDesc) {
		super();
		this.composerID = composerID;
		this.composerName = composerName;
		this.composerDesc = composerDesc;
	}

	
	public Long getComposerID() {
		return composerID;
	}

	public void setComposerID(Long composerID) {
		this.composerID = composerID;
	}

	
	public String getComposerName() {
		return composerName;
	}

	public void setComposerName(String composerName) {
		this.composerName = composerName;
	}

	
	public String getComposerDesc() {
		return composerDesc;
	}

	public void setComposerDesc(String composerDesc) {
		this.composerDesc = composerDesc;
	}
	 
}
