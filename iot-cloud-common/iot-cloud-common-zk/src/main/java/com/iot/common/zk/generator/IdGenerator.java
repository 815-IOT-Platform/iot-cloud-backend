

package com.iot.common.zk.generator;

/**
 * The interface Id generator.
 *
 * @author ananops.com @gmail.com
 */
public interface IdGenerator {

	/**
	 * 生成下一个ID
	 *
	 * @return the string
	 */
	Long nextId();
}
