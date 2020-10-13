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
@Table ( name ="t_bi_service_parameter" )
public class BiServiceParameter  implements Serializable {

	private static final long serialVersionUID =  1191528018229378505L;

   	@Column(name = "FID" )
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

   	@Column(name = "FSERVICE_ID" )
	private String serviceId;

   	@Column(name = "FNAME" )
	private String name;

   	@Column(name = "FTYPE" )
	private String type;

   	@Column(name = "FDEFAULT_VALUE" )
	private String defaultValue;

   	@Column(name = "FALIAS" )
	private String alias;

   	@Column(name = "FOPERATOR" )
	private String operator;

}
