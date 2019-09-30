package musicmgr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "genre")
public class Genre {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "genreID")
	private Long genreID;
	@Column(name = "genreName")
	private String genreName;
	@Column(name = "genreDesc")
	private String genreDesc;

	public Genre() {
	}

	public Genre(Long genreID, String genreName, String genreDesc) {
		this.genreID = genreID;
		this.genreName = genreName;
		this.genreDesc = genreDesc;
	}

	
	public Long getGenreId() {
		return genreID;
	}

	public void setGenreId(Long genreId) {
		this.genreID = genreId;
	}

	
	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}

	
	public String getGenreDesc() {
		return genreDesc;
	}

	public void setGenreDesc(String genreDesc) {
		this.genreDesc = genreDesc;
	}
}
