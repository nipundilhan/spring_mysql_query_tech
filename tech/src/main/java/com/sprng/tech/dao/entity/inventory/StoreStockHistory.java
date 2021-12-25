package com.sprng.tech.dao.entity.inventory;

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
import com.sprng.tech.dao.entity.order.OrderProduct;
import com.sprng.tech.dao.entity.order.Product;
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
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "product_id", nullable=false)
	private Product product;

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
	private Long available_quantity;
	
	@Column(name = "quantity")
	private Long quantity;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "order_product_id",  nullable=true)
	private OrderProduct orderProduct;
	
	
	@Column(name = "order_date")
	private Date expireDate;
	
	@Column(name = "description")
	private String description;

}
