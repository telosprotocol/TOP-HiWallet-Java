//package com.topnetwork.system.controller;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.base.core.context.annotation.RestfulCheck;
//import com.base.core.context.mvc.BaseController;
//import com.base.service.logger.dao.OperatorLogDao;
//
//import io.swagger.v3.oas.annotations.tags.Tag;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.Parameter;
//import com.gitee.magic.framework.base.result.ResultResponse;
//import com.gitee.magic.framework.head.utils.TimeUtils;
//
//@RestController
//@RequestMapping("/log")
//@Tag(name = "日志-临时接口后期可删除")
//public class LogController extends BaseController {
//
//    @Autowired
//    private OperatorLogDao operatorLogDao;
//
//    @Operation(summary="列表")
//    @RestfulCheck
//    @GetMapping("/requestip/{startDate}/{endDate}")
//    public ResultResponse<List<String>> requestIp(
//    		@PathVariable @Parameter(description="开始日期(yyyyMMdd)")String startDate,
//    		@PathVariable @Parameter(description="结束日期(yyyyMMdd)")String endDate) {
//    	Date start=TimeUtils.format(startDate+"000000", TimeUtils.YYYYMMDDHHMMSS);
//    	Date end=TimeUtils.format(endDate+"235959", TimeUtils.YYYYMMDDHHMMSS);
//    	StringBuilder sbSql=new StringBuilder();
//    	sbSql.append("SELECT requestIP,ifnull(count(1),0) FROM log_operator_log WHERE accessId = '92bfca8adf52f522f418d052ceec74ff' AND serverTime >= ? AND serverTime <= ? GROUP BY requestIP");
//    	List<Map<String,Object>> queryMap=operatorLogDao.queryForMap(sbSql.toString(), start,end);
//    	Set<String> requestIps=new HashSet<>();
//    	for(Map<String,Object> p:queryMap) {
//    		try {
//        		String ip=p.get("requestip").toString();
//        		String[] ips=ip.split(",");
//    			requestIps.add(ips[0]);
//    		}catch(Exception e) {
//
//    		}
//    	}
//    	return response(new ArrayList<>(requestIps));
//    }
//
//
//}
