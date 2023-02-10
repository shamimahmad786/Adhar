package com.example.Adhar.modal;

import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.vladmihalcea.hibernate.type.json.JsonType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MOE_UDISE_AAdharHistory")
@TypeDefs({ @TypeDef(name = "json", typeClass = JsonType.class) })
public class AadharHistory {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aadharIdSeq")
	@SequenceGenerator(name = "aadharIdSeq", sequenceName = "aadhar_id_seq", allocationSize = 100)
	private Long id;
	
//	@Type(type = "json")
//    @Column(name = "input_xml", columnDefinition = "json")
	private String inputXml;
	@Column(name = "txn")
	private String txn;
//	@Type(type = "json")
//    @Column(name = "response_xml", columnDefinition = "json")
	private String responseXml;
	@Column(name = "final_response")
	private String finalResponse;
	

}
