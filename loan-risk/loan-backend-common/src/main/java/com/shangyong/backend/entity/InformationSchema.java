package com.shangyong.backend.entity;
 
/**
 * 数据库及表查询bean
 * @author hc
 *
 */
public class InformationSchema{

	private String dataLength;//数据长度
	
	private String indexLength;//索引长度
	
	private String tableCatalog;//目录日志
	
	private String tableSchema;//实例名/库名
	
	private String tableName;//表名
	
	private String tableType;//表类型
	
	private String engine;//引擎
	
	private String version;//版本
	
	private String rowFormat;//记录格式
	
	private String tableRow;//表记录数
	
	private String avgRowLength; //平均行数
	
	private String maxDataLength;//最大数据长度
	  
	private String dataFree;//空闲数据
	
	private String autoIncrement;//自增量
	
	private String createTime;//创建时间
	
	private String updateTime;//更新时间
	
	private String checkTime;//检查时间
	
	private String tableCollation;//表字符集
	
	private String checksum;//校验码
	
	private String createOptions;//创建选项
	
	private String tableComment;//表注释
	
	private Integer pageIndex;
	
	private Integer pageSize;
	 
	public String getDataLength() {
		return dataLength;
	}

	public void setDataLength(String dataLength) {
		this.dataLength = dataLength;
	}

	public String getIndexLength() {
		return indexLength;
	}

	public void setIndexLength(String indexLength) {
		this.indexLength = indexLength;
	}

	public String getTableCatalog() {
		return tableCatalog;
	}

	public void setTableCatalog(String tableCatalog) {
		this.tableCatalog = tableCatalog;
	}

	public String getTableSchema() {
		return tableSchema;
	}

	public void setTableSchema(String tableSchema) {
		this.tableSchema = tableSchema;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableType() {
		return tableType;
	}

	public void setTableType(String tableType) {
		this.tableType = tableType;
	}

	public String getEngine() {
		return engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getRowFormat() {
		return rowFormat;
	}

	public void setRowFormat(String rowFormat) {
		this.rowFormat = rowFormat;
	}

	public String getTableRow() {
		return tableRow;
	}

	public void setTableRow(String tableRow) {
		this.tableRow = tableRow;
	}

	public String getAvgRowLength() {
		return avgRowLength;
	}

	public void setAvgRowLength(String avgRowLength) {
		this.avgRowLength = avgRowLength;
	}

	public String getMaxDataLength() {
		return maxDataLength;
	}

	public void setMaxDataLength(String maxDataLength) {
		this.maxDataLength = maxDataLength;
	}

	public String getDataFree() {
		return dataFree;
	}

	public void setDataFree(String dataFree) {
		this.dataFree = dataFree;
	}

	public String getAutoIncrement() {
		return autoIncrement;
	}

	public void setAutoIncrement(String autoIncrement) {
		this.autoIncrement = autoIncrement;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}

	public String getTableCollation() {
		return tableCollation;
	}

	public void setTableCollation(String tableCollation) {
		this.tableCollation = tableCollation;
	}

	public String getChecksum() {
		return checksum;
	}

	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}

	public String getCreateOptions() {
		return createOptions;
	}

	public void setCreateOptions(String createOptions) {
		this.createOptions = createOptions;
	}

	public String getTableComment() {
		return tableComment;
	}

	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}

	
	
	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		if(pageIndex != null && pageIndex >=1){// 当前页
			this.pageIndex = (pageIndex - 1 )*10;
		}else{
			this.pageIndex = pageIndex;
		}
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public InformationSchema() {
		super(); 
	}

	public InformationSchema(String dataLength, String indexLength, String tableCatalog, String tableSchema,
			String tableName, String tableType, String engine, String version, String rowFormat, String tableRow,
			String avgRowLength, String maxDataLength, String dataFree, String autoIncrement, String createTime,
			String updateTime, String checkTime, String tableCollation, String checksum, String createOptions,
			String tableComment) {
		super();
		this.dataLength = dataLength;
		this.indexLength = indexLength;
		this.tableCatalog = tableCatalog;
		this.tableSchema = tableSchema;
		this.tableName = tableName;
		this.tableType = tableType;
		this.engine = engine;
		this.version = version;
		this.rowFormat = rowFormat;
		this.tableRow = tableRow;
		this.avgRowLength = avgRowLength;
		this.maxDataLength = maxDataLength;
		this.dataFree = dataFree;
		this.autoIncrement = autoIncrement;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.checkTime = checkTime;
		this.tableCollation = tableCollation;
		this.checksum = checksum;
		this.createOptions = createOptions;
		this.tableComment = tableComment;
	}

	@Override
	public String toString() {
		return "InformationSchema [dataLength=" + dataLength + ", indexLength=" + indexLength + ", tableCatalog="
				+ tableCatalog + ", tableSchema=" + tableSchema + ", tableName=" + tableName + ", tableType="
				+ tableType + ", engine=" + engine + ", version=" + version + ", rowFormat=" + rowFormat + ", tableRow="
				+ tableRow + ", avgRowLength=" + avgRowLength + ", maxDataLength=" + maxDataLength + ", dataFree="
				+ dataFree + ", autoIncrement=" + autoIncrement + ", createTime=" + createTime + ", updateTime="
				+ updateTime + ", checkTime=" + checkTime + ", tableCollation=" + tableCollation + ", checksum="
				+ checksum + ", createOptions=" + createOptions + ", tableComment=" + tableComment + "]";
	}
	
	
	
	
	
}
