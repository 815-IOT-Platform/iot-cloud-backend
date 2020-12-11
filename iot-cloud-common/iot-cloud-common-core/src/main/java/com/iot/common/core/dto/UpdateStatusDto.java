
package com.iot.common.core.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * The class Modify status dto.
 *
 * @author ananops.net@gmail.com
 */
@Data
public class UpdateStatusDto implements Serializable {

	private static final long serialVersionUID = 1494899235149813850L;
	/**
	 * 角色ID
	 */
	private Long id;

	/**
	 * 状态
	 */
	private Integer status;
}
