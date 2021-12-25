package com.sprng.tech.dao.entity.table;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sprng.tech.dao.entity.table.meta.MstArea;
import com.sprng.tech.dao.entity.table.meta.MstCivilStatus;
import com.sprng.tech.dao.entity.table.meta.MstProfession;
import com.sprng.tech.dao.entity.table.meta.MstProfessionArea;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "person")
@Entity
public class Person {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nic")
	private String nic;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "mobile")
	private String mobile;
	
	@Column(name = "dob")
	private Date dob;
	
	@Column(name = "bmi" , nullable=true)
	private BigDecimal bmi;
	
	@JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "person_id") 
	public List<PersonContact> personContacts;
	
	@JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "person_id") 
	public List<PersonAddress> personAddressList;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "mst_prfession_id", nullable=false)
	private MstProfession mstProfession;
	
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "mst_civil_status_id", nullable=true)
	private MstCivilStatus mstCivilStatus;

}
