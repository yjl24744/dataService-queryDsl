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
@Table ( name ="t_bi_datamodel" )
public class BiDatamodel  implements Serializable {

	private static final long serialVersionUID =  2629442970628795099L;

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FID" )
	private String id;

   	@Column(name = "FCODE" )
	private String code;

   	@Column(name = "FVERSION" )
	private String version;

   	@Column(name = "FNAME" )
	private String name;

   	@Column(name = "FDESC" )
	private String desc;

   	@Column(name = "FSCHEMA" )
	private String schema;

   	@Column(name = "FCONTENT" )
	private String content;

   	@Column(name = "FPARAMETERS" )
	private String parameters;

   	@Column(name = "FDATA_TYPE" )
	private String dataType;

   	@Column(name = "FDS_ID" )
	private String dsId;

   	@Column(name = "FSRC_DS_ID" )
	private String srcDsId;

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

   	@Column(name = "FDM_STATE" )
	private String dmState;

   	@Column(name = "FEXTENSION" )
	private String extension;

   	@Column(name = "FSQL" )
	private String sql;

   	@Column(name = "FEXTDATA_SERVICE_STATE" )
	private String extdataServiceState;

   	@Column(name = "FSERVICEPARAMS" )
	private String serviceparams;

}
