package com.cidic.equipment.model;
// default package
// Generated 2017-4-17 10:51:18 by Hibernate Tools 4.3.1.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Texture generated by hbm2java
 */
@Entity
@Table(name = "texture", catalog = "equipment")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","operations","vehicleTextures"})
public class Texture implements java.io.Serializable {

	private Integer id;
	private String name;
	private String icon;
	private String description;
	private Date createTime;
	private Set<VehicleTexture> vehicleTextures = new HashSet<VehicleTexture>(0);

	public Texture() {
	}

	public Texture(String name, String icon, Date createTime) {
		this.name = name;
		this.icon = icon;
		this.createTime = createTime;
	}

	public Texture(String name, String icon, String description, Date createTime, Set<VehicleTexture> vehicleTextures) {
		this.name = name;
		this.icon = icon;
		this.description = description;
		this.createTime = createTime;
		this.vehicleTextures = vehicleTextures;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "Id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false, length = 15)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "icon", nullable = false)
	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", nullable = false, length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "texture")
	@LazyCollection(LazyCollectionOption.EXTRA)
	@JsonIgnore
	public Set<VehicleTexture> getVehicleTextures() {
		return this.vehicleTextures;
	}

	public void setVehicleTextures(Set<VehicleTexture> vehicleTextures) {
		this.vehicleTextures = vehicleTextures;
	}

}
