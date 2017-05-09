package com.cidic.equipment.model;
// default package
// Generated 2017-4-14 17:05:01 by Hibernate Tools 4.3.1.Final

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
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * VehicleInfo generated by hbm2java
 */
@Entity
@Table(name = "vehicle_info", catalog = "equipment")
public class VehicleInfo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private int categoryId;
	private String categoryName;
	private int brandId;
	private String brandName;
	private byte entry;
	private String imageUrl1;
	private String imageUrl2;
	private String style;
	private String modelUrl;
	private String onSaleDate;
	private Date createTime;
	private String componentInfo;
	private String productCategory;
	private String videoUrl;
	private Set<VehicleColor> vehicleColors = new HashSet<VehicleColor>(0);
	private Set<VehicleTexture> vehicleTextures = new HashSet<VehicleTexture>(0);

	public VehicleInfo() {
	}

	public VehicleInfo(int categoryId, int brandId, byte entry, String style, Date createTime, String componentInfo) {
		this.categoryId = categoryId;
		this.brandId = brandId;
		this.entry = entry;
		this.style = style;
		this.createTime = createTime;
		this.componentInfo = componentInfo;
	}

	public VehicleInfo(int categoryId, int brandId, byte entry, String imageUrl1, String imageUrl2, String style,
			String modelUrl, String onSaleDate, Date createTime, String componentInfo, Set<VehicleColor> vehicleColors,
			Set<VehicleTexture> vehicleTextures) {
		this.categoryId = categoryId;
		this.brandId = brandId;
		this.entry = entry;
		this.imageUrl1 = imageUrl1;
		this.imageUrl2 = imageUrl2;
		this.style = style;
		this.modelUrl = modelUrl;
		this.onSaleDate = onSaleDate;
		this.createTime = createTime;
		this.componentInfo = componentInfo;
		this.vehicleColors = vehicleColors;
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

	@Column(name = "categoryId", nullable = false)
	public int getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name = "brandId", nullable = false)
	public int getBrandId() {
		return this.brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	@Column(name = "entry", nullable = false)
	public byte getEntry() {
		return this.entry;
	}

	public void setEntry(byte entry) {
		this.entry = entry;
	}

	@Column(name = "imageUrl1")
	public String getImageUrl1() {
		return this.imageUrl1;
	}

	public void setImageUrl1(String imageUrl1) {
		this.imageUrl1 = imageUrl1;
	}

	@Column(name = "imageUrl2")
	public String getImageUrl2() {
		return this.imageUrl2;
	}

	public void setImageUrl2(String imageUrl2) {
		this.imageUrl2 = imageUrl2;
	}

	@Column(name = "style", nullable = false)
	public String getStyle() {
		return this.style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	@Column(name = "modelUrl")
	public String getModelUrl() {
		return this.modelUrl;
	}

	public void setModelUrl(String modelUrl) {
		this.modelUrl = modelUrl;
	}

	@Column(name = "on_sale_date", length = 4)
	public String getOnSaleDate() {
		return this.onSaleDate;
	}

	public void setOnSaleDate(String onSaleDate) {
		this.onSaleDate = onSaleDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", nullable = false, length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "component_info", nullable = false, length = 65535)
	public String getComponentInfo() {
		return this.componentInfo;
	}

	public void setComponentInfo(String componentInfo) {
		this.componentInfo = componentInfo;
	}

	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy = "vehicleInfo")
	@LazyCollection(LazyCollectionOption.EXTRA)
	public Set<VehicleColor> getVehicleColors() {
		return this.vehicleColors;
	}

	public void setVehicleColors(Set<VehicleColor> vehicleColors) {
		this.vehicleColors = vehicleColors;
	}

	@OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.ALL}, mappedBy = "vehicleInfo")
	@LazyCollection(LazyCollectionOption.EXTRA)
	public Set<VehicleTexture> getVehicleTextures() {
		return this.vehicleTextures;
	}

	public void setVehicleTextures(Set<VehicleTexture> vehicleTextures) {
		this.vehicleTextures = vehicleTextures;
	}

	@Transient
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Transient
	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	@Column(name = "product_category")
	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	@Column(name = "videoUrl")
	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	
}
