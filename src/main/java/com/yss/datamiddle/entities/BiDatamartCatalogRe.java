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
@Table ( name ="t_bi_datamart_catalog_re" )
public class BiDatamartCatalogRe  implements Serializable {

	private static final long serialVersionUID =  2341394891537315880L;

   	@Column(name = "FID" )
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

   	@Column(name = "FDATAMART_ID" )
	private String datamartId;

   	@Column(name = "FDATA_TYPE" )
	private String dataType;

   	@Column(name = "FREL_ID" )
	private String relId;

}
