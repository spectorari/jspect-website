package com.joelmvc.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="work")
public class Work {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private String artworkcategory;
	private String artworksubcategory;
	private String imgurlhi;
	private String imgurllo;
	private String date;
	private int sizeheight;
	private int sizewidth;
	private String description;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getArtworkCategory() {
		return artworkcategory;
	}
	public void setArtworkCategory(String artworkcategory) {
		this.artworkcategory = artworkcategory;
	}
	public String getArtworkSubcategory() {
		return artworksubcategory;
	}
	public void setArtworkSubcategory(String artworksubcategory) {
		this.artworksubcategory = artworksubcategory;
	}
	public String getImgUrlHi() {
		return imgurlhi;
	}
	public void setImgUrlHi(String imgurlhi) {
		this.imgurlhi = imgurlhi;
	}
	public String getImgUrlLo() {
		return imgurllo;
	}
	public void setImgUrlLo(String imgurllo) {
		this.imgurllo = imgurllo;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getSizeHeight() {
		return sizeheight;
	}
	public void setSizeHeight(int sizeheight) {
		this.sizeheight = sizeheight;
	}
	public int getSizeWidth() {
		return sizewidth;
	}
	public void setSizeWidth(int sizewidth) {
		this.sizewidth = sizewidth;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
