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
@Table ( name ="t_bi_table" )
public class BiTable  implements Serializable {

	private static final long serialVersionUID =  1763043563354406841L;

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FID" )
	private String id;

   	@Column(name = "FTABLE_NAME" )
	private String tableName;

   	@Column(name = "FTABLE_ALIAS" )
	private String tableAlias;

   	@Column(name = "FDS_ID" )
	private String dsId;

   	@Column(name = "FISFACT" )
	private String isfact;

   	@Column(name = "FTITLE_ROW" )
	private String titleRow;

   	@Column(name = "FGROUP" )
	private String group;

	/**
	 * 是否表
	 */
   	@Column(name = "TABLE_FLAG" )
	private String tableFlag;

}
