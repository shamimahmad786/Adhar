package com.example.Adhar.modal;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.vladmihalcea.hibernate.type.json.JsonType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "MOE_UDISE_AAdharTxnHistory")
//@TypeDefs({ @TypeDef(name = "json", typeClass = JsonType.class) })
public class AadharTxnHistory {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aadharTxnIdSeq")
	@SequenceGenerator(name = "aadharTxnIdSeq", sequenceName = "aadhar_txn_id_seq", allocationSize = 1)
	private Long id;
	private Integer studentId;
	private String studentName;
	private String aadharResponseTxnId;
	private String aadharResponseInfo;
	private String aadharResponseCode;
	private String aadharResponseError;
	private String aadharResponseRet;
	private String aadharRequestPid;
	private Date aadharAuthDate;

}
