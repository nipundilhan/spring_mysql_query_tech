package com.sprng.tech.dao.entity.order;

import java.math.BigDecimal;
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
import com.sprng.tech.dao.entity.table.Customer;
import com.sprng.tech_comman.enums.OrderTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "order_history")
@Entity
public class OrderHistory {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "order_id", nullable=false)
	private Orders order;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "customer_id", nullable=false)
	private Customer customer;
	
	@Enumerated(value = EnumType.STRING)
	@Column(name = "order_type", length=30, nullable=false)
	private OrderTypeEnum orderType;
	
	
	@Column(name = "order_date")
	private Date orderDate;
	
	
	@Column(name = "total_amount")
	private BigDecimal totalAmount;
	
	@Column(name = "description")
	private String description;

}
