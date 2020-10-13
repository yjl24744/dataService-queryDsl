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
@Table ( name ="t_bi_datamart_catalog" )
public class BiDatamartCatalog  implements Serializable {

	private static final long serialVersionUID =  3667947357009928604L;

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FID" )
	private String id;

   	@Column(name = "FNAME" )
	private String name;

   	@Column(name = "FDESC" )
	private String desc;

   	@Column(name = "FPATH" )
	private String path;

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

}
