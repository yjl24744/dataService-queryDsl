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
@Table ( name ="t_bi_field" )
public class BiField  implements Serializable {

	private static final long serialVersionUID =  6594610627109619048L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   	@Column(name = "FID" )
	private String id;

   	@Column(name = "FTABLE_ID" )
	private String tableId;

   	@Column(name = "FFIELD_NAME" )
	private String fieldName;

   	@Column(name = "FFIELD_ALIAS" )
	private String fieldAlias;

   	@Column(name = "FSRC_FIELD" )
	private String srcField;

   	@Column(name = "FFIELD_TYPE" )
	private String fieldType;

   	@Column(name = "FLENGTH" )
	private Double length;

   	@Column(name = "FSCALE" )
	private Double scale;

   	@Column(name = "FPUBLIC_DIM" )
	private String publicDim;

   	@Column(name = "FLAG" )
	private String lag;

   	@Column(name = "FSHOW_FLAG" )
	private String showFlag;

   	@Column(name = "FGROUP_NAME" )
	private String groupName;

   	@Column(name = "SOURCETABLE_NAME" )
	private String sourcetableName;

}
