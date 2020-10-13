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
@Table ( name ="t_bi_tableview" )
public class BiTableview  implements Serializable {

	private static final long serialVersionUID =  8667170498886474183L;

   	@Column(name = "FID" )
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

   	@Column(name = "FVIEW_ALIAS" )
	private String viewAlias;

   	@Column(name = "FNAME" )
	private String name;

   	@Column(name = "FDESC" )
	private String desc;

   	@Column(name = "FDS_ID" )
	private String dsId;

   	@Column(name = "FSQL" )
	private String sql;

   	@Column(name = "FCONTENT" )
	private String content;

   	@Column(name = "FINLINE_DIM" )
	private String inlineDim;

   	@Column(name = "FRELATION_FIELD" )
	private String relationField;

   	@Column(name = "FCREATOR_ID" )
	private String creatorId;

   	@Column(name = "FCREATE_TIME" )
	private String createTime;

   	@Column(name = "FLAST_EDITOR_ID" )
	private String lastEditorId;

   	@Column(name = "FLAST_EDIT_TIME" )
	private String lastEditTime;

   	@Column(name = "FDELETE_TIME" )
	private String deleteTime;

   	@Column(name = "FDELETE_STATE" )
	private String deleteState;

   	@Column(name = "FCHECK_STATE" )
	private String checkState;

   	@Column(name = "FCHECKER_ID" )
	private String checkerId;

   	@Column(name = "FCHECK_TIME" )
	private String checkTime;

   	@Column(name = "FEXTDATA_SERVICE_STATE" )
	private String extdataServiceState;

   	@Column(name = "FCREATE_VIEW" )
	private String createView;

	/**
	 * 相关表
	 */
   	@Column(name = "TABLE_NAMES" )
	private String tableNames;

}
