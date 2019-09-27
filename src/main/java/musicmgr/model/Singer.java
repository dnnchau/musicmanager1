package musicmgr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "singer")
public class Singer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "singerID")
	private Integer singerID;
	@Column(name = "singerName")
	private String singerName;
	@Column(name = "singerDesc")
	private String singerDesc;

	public Singer() {
	}

	public Singer(Integer singerID, String singerName, String singerDesc) {
		super();
		this.singerID = singerID;
		this.singerName = singerName;
		this.singerDesc = singerDesc;
	}

	public Integer getSingerID() {
		return singerID;
	}

	public void setSingerID(Integer singerID) {
		this.singerID = singerID;
	}

	public String getSingerName() {
		return singerName;
	}

	public void setSingerName(String singerName) {
		this.singerName = singerName;
	}

	public String getSingerDesc() {
		return singerDesc;
	}

	public void setSingerDesc(String singerDesc) {
		this.singerDesc = singerDesc;
	}

}
