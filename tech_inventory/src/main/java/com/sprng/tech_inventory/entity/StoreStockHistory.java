package com.sprng.tech_inventory.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sprng.tech_comman.enums.StockActionEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "store_stock_history")
@Entity
public class StoreStockHistory {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "store_stock_id", nullable=false)
	private StoreStock storeStock;

	@Column(name = "product_id")
	private Long productId;
	
	@Column(name = "order_product_id", nullable=true)
	private Long orderProductId;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "store_id", nullable=false)
	private Store store;
	
	@Enumerated(value = EnumType.STRING)
	@Column(name = "action", length=30, nullable=false)
	private StockActionEnum action;
	
	@Column(name = "initial_quantity")
	private Long initialQuantity;
	
	@Column(name = "available_quantity")
	private Long availableQuantity;
	
	@Column(name = "quantity")
	private Long quantity;	
	
	@Column(name = "order_date")
	private Date expireDate;
	
	@Column(name = "description")
	private String description;

}
