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
@Table ( name ="t_bi_service_field" )
public class BiServiceField  implements Serializable {

	private static final long serialVersionUID =  2153878795830072686L;

   	@Column(name = "FID" )
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

   	@Column(name = "FSERVICE_ID" )
	private String serviceId;

   	@Column(name = "FNAME" )
	private String name;

   	@Column(name = "FINNER_NAME" )
	private String innerName;

   	@Column(name = "FTYPE" )
	private String type;

   	@Column(name = "FALIAS" )
	private String alias;

}
