package com.shangyong.mongo.common;

import com.mongodb.BasicDBObject;
import com.mongodb.WriteResult;
import com.shangyong.mongo.entity.BaseEntity;
import com.shangyong.mongo.entity.page.PageReqInfo;
import com.shangyong.mongo.entity.page.PageRspInfo;
import com.shangyong.utils.RedisUtils;
import com.shangyong.utils.UUIDUtils;
import org.apache.commons.lang3.StringUtils;
import org.bson.BSONObject;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

@Service
public class MongoUtils{
	
	private static Logger logger = LoggerFactory.getLogger("mongo");
	
	private static String errorRedKey = "MONGO_SAVE_ERROR_KEY:";
	public static final String UP  = "up";
	public static final String DN  = "dn";

	@Autowired
    protected MongoOperations mongoTemplate;
	
    /////////////////////////////////////////////////////////
    ////////////////////////保存/////////////////////////////
    /////////////////////////////////////////////////////////
    /**
     * 根据实体对象保存数据到mongodb中
     * @param clazz
     */
    public void saveByClazzTo(Object clazz){
    	mongoTemplate.save(clazz);
    }

    public void saveByClazz(BaseEntity clazz){
    	
    	if(clazz.getDocSize() > 15){
    		String redisKey = errorRedKey + clazz.getClass().getName() + ":" + UUIDUtils.getUUID();
    		//如果存在相同数据，删除原来的，保留最新一条
    		if(RedisUtils.exists(redisKey)){
    			RedisUtils.del(redisKey);
    		}
    		RedisUtils.set(redisKey,JacksonUtils.ObjectToJson(clazz.getJsonInfo()));
    		
    		clazz.setErrorRedKey(redisKey);
    		clazz.nSetJsonInfoTo(null);
    		mongoTemplate.save(clazz);
    	}else{
    		mongoTemplate.save(clazz);
    	}
    }
    
	/////////////////////////////////////////////////////////
	////////////////////////删除/////////////////////////////
	/////////////////////////////////////////////////////////
    /**
     * 根据K,V的形式先查找删除一条数据集合
     * @param paramMap K.V 参数 
     * @param clazz 对象（mongodb集合名）必传
     * @return 
     */
    public void removeByClazz(Map<String, Object> paramMap, Class<?> clazz){
    	if(paramMap == null || paramMap.isEmpty()){
    		throw new RuntimeException("查询参数不能为空");
    	}
    	Query query = this.buildQuery(paramMap, null, clazz);
    	mongoTemplate.findAndRemove(query, clazz);
    }
    
    
    /**
     * 根据K,V的形式查找删除所有相关数据集合（慎用）
     * @param paramMap K.V 参数 切记 参数不可以为空
     * @param clazz 对象（mongodb集合名）必传
     * @return 
     */
    public void removeAllByClazz(Map<String, Object> paramMap, Class<?> clazz){
    	if(paramMap == null || paramMap.isEmpty()){
    		throw new RuntimeException("查询参数不能为空");
    	}
    	Query query = this.buildQuery(paramMap, null, clazz);
    	mongoTemplate.findAllAndRemove(query, clazz);
    }
    
	/////////////////////////////////////////////////////////
	////////////////////////修改/////////////////////////////
	/////////////////////////////////////////////////////////
    
    /**
     * 根据K,V的形式查找修改一条匹配的数据集合
     * @param paramMap K.V 参数 切记 参数不可以为空
     * @param update 修改的对象
     * @param clazz 对象（mongodb集合名）必传
     */
    public WriteResult updateByClazz(Map<String, Object> paramMap, Update update, Class<?> clazz){
    	if(paramMap == null || paramMap.isEmpty()){
    		throw new RuntimeException("查询参数不能为空");
    	}
    	Query query = this.buildQuery(paramMap, null, clazz);
    	return mongoTemplate.updateFirst(query, update, clazz);
    }
    
    /**
     * 根据K,V的形式查找修改所有匹配的数据集合
     * @param paramMap K.V 参数 切记 参数不可以为空
     * @param update 修改的对象
     * @param clazz 对象（mongodb集合名）必传
     */
    public WriteResult updateAllByClazz(Map<String, Object> paramMap, Update update, Class<?> clazz){
    	if(paramMap == null || paramMap.isEmpty()){
    		throw new RuntimeException("查询参数不能为空");
    	}
    	Query query = this.buildQuery(paramMap, null, clazz);
    	return mongoTemplate.updateMulti(query, update, clazz);
    }
    
    /////////////////////////////////////////////////////////
    ////////////////////////查询/////////////////////////////
    /////////////////////////////////////////////////////////
    
    /**
     * 根据K,V的形式查询数据集合
     * @param paramMap K.V 参数(没有参数就传null)
     * @param orderList 排序(如果不排序就传null)
     * @param clazz 对象（mongodb集合名）必传
     * @return 返回数据集合
     */
    @SuppressWarnings("rawtypes")
	public List findListByClazz(Map<String, Object> paramMap, List<Order> orderList, Class<?> clazz) {
	    Query query = this.buildQuery(paramMap, orderList, clazz);
	    return mongoTemplate.find(query, clazz);
	}
    
    
    /**
     * 根据K,V的形式查询单个数据对象
     * @param paramMap K.V 参数(没有参数就传null)
     * @param orderList 排序(如果不排序就传null)
     * @param clazz 对象（mongodb集合名）必传
     * @return 返回单个数据对象
     */
    public Object findByClazz(Map<String, Object> paramMap, List<Order> orderList, Class<?> clazz) {
    	
    	Query query = this.buildQuery(paramMap, orderList, clazz);
	    Object oMongo = null;
	    try {
			oMongo = mongoTemplate.findOne(query, clazz);
			
			if(oMongo != null) {
				Method mErrorRedKey = clazz.getMethod("getErrorRedKey", null);
				Object oErrorRedKey = mErrorRedKey.invoke(oMongo, null);
				if(oErrorRedKey != null) {
					//存储异常 从Redis中读取数据
					if(RedisUtils.exists(oErrorRedKey.toString())){
						
						String jsonInfo = RedisUtils.get(oErrorRedKey.toString());
						//重新赋值
						Method msJsonInfo = clazz.getMethod("nSetJsonInfoTo", Object.class);
						msJsonInfo.invoke(oMongo, jsonInfo);
		    			}else {
		    				throw new RuntimeException("MongoDB从Redis获取数据失败：key不存在");
		    			}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return oMongo;
	}
    
    
    /**
     * 根据K,V的形式查询匹配的对象数量
     * @param paramMap K.V 参数(没有参数就传null)
     * @param clazz clazz 对象（mongodb集合名）必传
     * @return 返回匹配的数量（type:long）
     */
    public long findCountByClazz(Map<String, Object> paramMap, Class<?> clazz){
    	Query query = this.buildQuery(paramMap, null, clazz);
	    return mongoTemplate.count(query, clazz);
    }
    
    
    
    
    //////////////////////////////////////////////////
    //////////////////////分页查询/////////////////////
    //////////////////////////////////////////////////
    /**
     * 最新分页查询（根据指定的时间段进行查询）
     * @param pageReqInfo 请求参数对象
     * @param clazz 请求对象
     * @return 返回查询结果集
     */
    public PageRspInfo findListForPage(PageReqInfo pageReqInfo, Class<?> clazz) {

    		PageRspInfo rsp = new PageRspInfo();
    	
    		long startTime = pageReqInfo.getStartTime();//开始时间
    		long endTime = pageReqInfo.getEndTime();//结束时间
    		String pageType = pageReqInfo.getPageType();//排序标识
    		String lastId = pageReqInfo.getLastId();//主键id
    		int pageSize = pageReqInfo.getPageSize();//查询条数
    	
    		if(pageSize == 0) {
    			throw new RuntimeException("查询记录条数不能为空");
    		}
    		if(startTime == 0) {
    			throw new RuntimeException("查询开始时间不能为空");
    		}
    		if(endTime == 0) {
    			throw new RuntimeException("查询结束时间不能为空");
    		}
    		if(StringUtils.isNotBlank(pageType)) {
    			if(StringUtils.isBlank(lastId)) {
    				throw new RuntimeException("上一页或下一页查询时lastId不能为空");
    			}
    		}
    		Query query = this.buildQuery(pageReqInfo.getParamMap(), null, clazz);//查询条件
    		Criteria cDateTime = Criteria.where("createTimeLong").gte(startTime).lt(endTime);//添加日期查询条件
    		Criteria cLastId = null;//添加主键id查询条件
    		
    		query.addCriteria(cDateTime);
    		query.limit(pageSize);
        query.with(new Sort(new Order(Direction.DESC,"createTimeLong")));//默认按照时间排列倒序
    		
    		int totalRsults = (int)mongoTemplate.count(query, clazz);//总条数
    		
    		if(UP.equals(pageType)){
    			ObjectId id = new ObjectId(lastId);
    			cLastId = Criteria.where("_id").gte(id);
    			query.addCriteria(cLastId);
    		}
    		if(DN.equals(pageType)) {
    			ObjectId id = new ObjectId(lastId);
    			cLastId = Criteria.where("_id").lt(id);
    			query.addCriteria(cLastId);
    		}
    		
    		int pageTotal = totalRsults % pageSize > 0 ? totalRsults / pageSize + 1 : totalRsults / pageSize;//总页数
    		List list =  mongoTemplate.find(query, clazz);//查询记录数
    		int pageLength = list.size();//当前页数
    		
    		rsp.setPageLength(pageLength);
    		rsp.setPageTotal(pageTotal);
    		rsp.setTotalRsults(totalRsults);
    		rsp.setResultList(list);
    		
    		return rsp;
    }
    
    protected Query buildQuery(Map<String, Object> paramMap, List<Order> orderList, Class<?> clazz) {
        Query query = new Query();
        if (paramMap != null && paramMap.size() > 0) {
            for (String key : paramMap.keySet()) {
                Object value = paramMap.get(key);
                query.addCriteria(Criteria.where(key).is(value));
            }
        }

        if (orderList != null && orderList.size() > 0) {
            Sort sort = new Sort(orderList);
            query.with(sort);
        }

        return query;
    }


    /**
     *
     * 高级查询
     * 可指定显示字段，查询条件，排序
     * 采用原生mongo的查询方式获取数据
     * @param showFields 显示字段
     * @param paramMap 参数
     * @param orderList 排序
     * @param clazz 文档
     * @return 查询对象
     */
    @SuppressWarnings("rawtypes")
    public List findFieldsByClazzPage(Map<String, Object> showFields, Map<String, Object> paramMap, List<Order> orderList, Class<?> clazz) {
        long beginTime = System.currentTimeMillis();
        //获取查询对象
        Query query = this.buildQuery(showFields, paramMap, orderList, clazz);
        List list =  mongoTemplate.find(query, clazz);
        long endTime = System.currentTimeMillis();
        logger.info("查询用时(ms)：" + (endTime-beginTime));
        logger.info("查询个数:" + list.size());
        return list;
    }

    /**
     * 设置查询参数
     * @param showFields 显示字段
     * @param paramMap 参数
     * @param orderList 排序
     * @param clazz 文档
     * @return 查询对象
     */
    protected Query buildQuery(Map<String, Object> showFields, Map<String, Object> paramMap, List<Order> orderList, Class<?> clazz) {
        //定义查询显示字段
        BasicDBObject fieldsObject = new BasicDBObject();
        //查询参数
        BasicDBObject queryParams = new BasicDBObject();

        boolean isPage = false;
        int pageSize = 10;
        Object lastId = null;

        //设置查询参数
        if (paramMap != null && paramMap.size() > 0) {
            for (String key : paramMap.keySet()) {
                if ("createTime".equals(key)) {
                    BSONObject bsonObject = new BasicDBObject();
                    bsonObject.put("$lte", paramMap.get(key));
                    queryParams.put(key, bsonObject);
                }else if ("createTime".equals(key)) {
                    BSONObject bsonObject = new BasicDBObject();
                    bsonObject.put("$lte", paramMap.get(key));
                    queryParams.put(key, bsonObject);
                }else if ("lastId".equals(key)) {
                    lastId = paramMap.get(key);
                } else if ("pageSize".equals(key)) {
                    try {
                        pageSize = Integer.parseInt(paramMap.get(key).toString());
                    } catch (Exception e) {
                        logger.info("分页大小非数字，已初始化为10");
                    }
                    isPage = true;
                }   else {
                    queryParams.put(key, paramMap.get(key));
                }
            }
        }

        //设置查询显示的字段
        if (showFields != null && showFields.size() > 0) {
            for (String key: showFields.keySet()
                    ) {
                fieldsObject.put(key, showFields.get(key));
            }

        }
        Query query = new BasicQuery(queryParams, fieldsObject);

        if (isPage) {
            if (lastId != null) {
                ObjectId id = new ObjectId(lastId.toString());
                query.addCriteria(Criteria.where("_id").gte(id));
            }
            query.limit(pageSize);
        }
        //设置排序
        if (orderList != null && orderList.size() > 0) {
            Sort sort = new Sort(orderList);
            query.with(sort);
        }

        return query;
    }
}
