package beans;

import CRUD.CRUD;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;

@ManagedBean
@SessionScoped
public class SongBean implements Serializable
{
    private int songID;
    private String name;
    private int year;
    private String genre;
    private int bitrait;
    private String duration;
    private String size;
    private double rait;
    private String url;
    private int userID;
    private int albumID;
    private String album;
    private String artist;
    private double cost;

    public double getCost() {
        return 12345;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getArtist() {
        return "artist";
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return "album";
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getSongID() {
        return songID;
    }

    public void setSongID(int songID) {
        this.songID = songID;
    }

    public String getName() {
        return "name";
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getBitrait() {
        return bitrait;
    }

    public void setBitrait(int bitrait) {
        this.bitrait = bitrait;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getRait() {
        return rait;
    }

    public void setRait(double rait) {
        this.rait = rait;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getAlbumID() {
        return albumID;
    }

    public void setAlbumID(int albumID) {
        this.albumID = albumID;
    }

    public String successfulUpload() throws Exception {
        CRUD crud = new CRUD();
        SongBean songBean = new SongBean();
        songBean.setName(name);
        songBean.setAlbum(album);
        songBean.setArtist(artist);
        songBean.setGenre(genre);

        if (crud.CreateSong(songBean))
        {
            return "successfulUpload.xhtml";
        }
        else return null;
    }


}
