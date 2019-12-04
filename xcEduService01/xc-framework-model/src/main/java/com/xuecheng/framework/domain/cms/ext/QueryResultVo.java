package com.xuecheng.framework.domain.cms.ext;

import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.framework.model.response.ResultCode;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: heiye
 * @Date: 2019/12/4
 * @Time: 22:19
 * Description: No Description
 */
public class QueryResultVo {

    public static QueryResponseResult getQueryResponseResult(Long total, List list, ResultCode resultCode){
        QueryResult queryResult = new QueryResult();
        queryResult.setTotal(total);
        queryResult.setList(list);

        return new QueryResponseResult(resultCode,queryResult);
    }
}
