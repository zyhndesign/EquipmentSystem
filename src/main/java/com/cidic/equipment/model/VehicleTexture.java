package com.cidic.equipment.model;
// default package
// Generated 2017-4-17 10:51:18 by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * VehicleTexture generated by hbm2java
 */
@Entity
@Table(name = "vehicle_texture", catalog = "equipment")
public class VehicleTexture implements java.io.Serializable {

	private Integer id;
	private Texture texture;
	private VehicleInfo vehicleInfo;

	public VehicleTexture() {
	}

	public VehicleTexture(Texture texture, VehicleInfo vehicleInfo) {
		this.texture = texture;
		this.vehicleInfo = vehicleInfo;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "textureId", nullable = false)
	public Texture getTexture() {
		return this.texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vehicleId", nullable = false)
	@JsonIgnore
	public VehicleInfo getVehicleInfo() {
		return this.vehicleInfo;
	}

	public void setVehicleInfo(VehicleInfo vehicleInfo) {
		this.vehicleInfo = vehicleInfo;
	}

}
