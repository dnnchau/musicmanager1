package musicmgr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "song")
public class Song {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "songID")
	private Integer SongID;
	@Column(name = "songName")
	private String songName;
	@Column(name = "lyrics")
	private String lyrics;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "genreID")
	private Genre genre;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "composerID")
	private Composer composer;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "singerID")
	private Singer singer;

	public Song() {
	}

	public Song(Integer songID, String songName, String lyrics, Genre genre, Composer composer, Singer singer) {
		super();
		SongID = songID;
		this.songName = songName;
		this.lyrics = lyrics;
		this.genre = genre;
		this.composer = composer;
		this.singer = singer;
	}

	public Integer getSongID() {
		return SongID;
	}

	public void setSongID(Integer songID) {
		SongID = songID;
	}

	public String getSongName() {
		return songName;
	}

	public void setSongName(String songName) {
		this.songName = songName;
	}

	public String getLyrics() {
		return lyrics;
	}

	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public Composer getComposer() {
		return composer;
	}

	public void setComposer(Composer composer) {
		this.composer = composer;
	}

	public Singer getSinger() {
		return singer;
	}

	public void setSinger(Singer singer) {
		this.singer = singer;
	}

}
