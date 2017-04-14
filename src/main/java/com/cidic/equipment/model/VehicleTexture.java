package com.cidic.equipment.model;
// default package
// Generated 2017-4-14 17:05:01 by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * VehicleTexture generated by hbm2java
 */
@Entity
@Table(name = "vehicle_texture", catalog = "equipment")
public class VehicleTexture implements java.io.Serializable {

	private Integer id;
	private VehicleInfo vehicleInfo;
	private int textureId;

	public VehicleTexture() {
	}

	public VehicleTexture(VehicleInfo vehicleInfo, int textureId) {
		this.vehicleInfo = vehicleInfo;
		this.textureId = textureId;
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
	@JoinColumn(name = "vehicleId", nullable = false)
	public VehicleInfo getVehicleInfo() {
		return this.vehicleInfo;
	}

	public void setVehicleInfo(VehicleInfo vehicleInfo) {
		this.vehicleInfo = vehicleInfo;
	}

	@Column(name = "textureId", nullable = false)
	public int getTextureId() {
		return this.textureId;
	}

	public void setTextureId(int textureId) {
		this.textureId = textureId;
	}

}
