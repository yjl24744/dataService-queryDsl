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
@Table ( name ="t_bi_service" )
public class BiService  implements Serializable {

	private static final long serialVersionUID =  705536207702223693L;

   	@Column(name = "FID" )
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

   	@Column(name = "FCODE" )
	private String code;

   	@Column(name = "FVERSION" )
	private String version;

   	@Column(name = "FNAME" )
	private String name;

   	@Column(name = "FDESC" )
	private String desc;

   	@Column(name = "FTYPE" )
	private String type;

   	@Column(name = "FREL_ID" )
	private String relId;

   	@Column(name = "FURL" )
	private String url;

   	@Column(name = "FCREATOR_ID" )
	private String creatorId;

   	@Column(name = "FCREATE_TIME" )
	private String createTime;

   	@Column(name = "FLAST_EDITOR_ID" )
	private String lastEditorId;

   	@Column(name = "FLAST_EDIT_TIME" )
	private String lastEditTime;

   	@Column(name = "FCHECKER_ID" )
	private String checkerId;

   	@Column(name = "FCHECK_STATE" )
	private String checkState;

   	@Column(name = "FCHECK_TIME" )
	private String checkTime;

}
