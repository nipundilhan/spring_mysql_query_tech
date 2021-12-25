package com.sprng.tech.dao.entity.delivary;

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
import com.sprng.tech.dao.entity.order.Orders;
import com.sprng.tech_comman.enums.DelivaryStatusEnum;
import com.sprng.tech_comman.enums.StockActionEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "delivary_schedule_history")
@Entity
public class DelivaryScheduleHistory {
	
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "delivary_schedule_id", nullable=false)
	private DelivarySchedule delivarySchedule;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "order_id", nullable=false)
	private Orders order;
	
	@Enumerated(value = EnumType.STRING)
	@Column(name = "status", length=30, nullable=false)
	private DelivaryStatusEnum status;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "delivary_team_id", nullable=false)
	private DelivaryTeam delivaryTeam;
		
	@Column(name = "delivary_date")
	private Date delivaryDate;

}
