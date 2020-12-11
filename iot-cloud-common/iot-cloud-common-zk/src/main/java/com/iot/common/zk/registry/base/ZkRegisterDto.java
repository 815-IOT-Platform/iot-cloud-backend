
package com.iot.common.zk.registry.base;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * The class Register dto.
 *
 * @author ananops.com @gmail.com
 */
@Data
@AllArgsConstructor
public class ZkRegisterDto {

	private String app;

	private String host;

	private CoordinatorRegistryCenter coordinatorRegistryCenter;

}
