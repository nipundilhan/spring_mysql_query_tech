package com.sprng.tech.dao.entity.table;

import java.math.BigDecimal;

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
import com.sprng.tech.enums.TransactionTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "loyality_point_history")
@Entity
public class LoyalityPointHistory {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "loyality_point_id", nullable=false)
	private LoyalityPoint loyalityPoint;
	

	@Enumerated(value = EnumType.STRING)
	@Column(name = "action", length=30, nullable=false)
	private TransactionTypeEnum action;
	
	@Column(name = "amount")
	private BigDecimal amount;
	
	@Column(name = "percentage")
	private BigDecimal percentage;	
	
	@Column(name = "points")
	private BigDecimal points;
	
	@Column(name = "total_points")
	private BigDecimal totalPoints;

}
