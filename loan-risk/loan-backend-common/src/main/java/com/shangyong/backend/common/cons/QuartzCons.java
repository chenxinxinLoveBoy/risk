package com.shangyong.backend.common.cons;

/**
 * 定时任务常量类
 * @author zhangze
 *
 */
public class QuartzCons {
	/** job 运行状态 1-运行 **/
	public static final String STATUS_RUNNING = "1";
	/** job 运行状态 0-非运行 **/
	public static final String STATUS_NOT_RUNNING = "0";
	
	/** job 运行状态start-运行 **/
	public static final String STATUS_RUNNING_CMD = "start";
	/** job 运行状态 stop-非运行 **/
	public static final String STATUS_NOT_RUNNING_CMD = "stop";
	
	/** job 是否同步 1-是 (无状态) **/
	public static final String CONCURRENT_IS = "1";
	/** job 是否同步 0-否 (有状态) **/
	public static final String CONCURRENT_NOT = "0";
	
}
