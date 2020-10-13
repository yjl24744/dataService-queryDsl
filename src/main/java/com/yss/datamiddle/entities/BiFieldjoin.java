package com.yss.datamiddle.entities;

import javax.persistence.*;
import java.io.Serializable;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @Description  
 * @Author  zhulei
 * @Date 2020-09-28 
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table ( name ="t_bi_fieldjoin" )
public class BiFieldjoin  implements Serializable {

	private static final long serialVersionUID =  1680945947020681739L;

   	@Column(name = "FID" )
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

   	@Column(name = "FDS_ID" )
	private String dsId;

   	@Column(name = "FTAR_DS_ID" )
	private String tarDsId;

   	@Column(name = "FSOURCE_TABLE" )
	private String sourceTable;

   	@Column(name = "FSOURCE_FIELD" )
	private String sourceField;

   	@Column(name = "FTARGET_TABLE" )
	private String targetTable;

   	@Column(name = "FTARGET_FIELD" )
	private String targetField;

   	@Column(name = "FRELATION" )
	private String relation;

   	@Column(name = "FOPERATION" )
	private String operation;

   	@Column(name = "FCREATE_TIME" )
	private String createTime;

}
