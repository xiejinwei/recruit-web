package com.recruit.exception;

/**
 * 自定义异常类，专用于针对需要进行异常抛出
 * @author xiejinwei
 *
 */

public class RBCException extends RuntimeException {

	private static final long serialVersionUID = 7901239953613935941L;

	public RBCException(String message) {
		super(message);
	}
}
