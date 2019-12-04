package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.ext.QueryResultVo;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: heiye
 * @Date: 2019/12/4
 * @Time: new Date()
 * Description: No Description
 */
@Service
public class PageService {

    @Autowired
    private CmsPageRepository cmsPageRepository;

    /**
     * 分页查询所有页面
     * @param page
     * @param size
     * @param queryPageRequest
     * @return
     */
    public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest){
        if (queryPageRequest == null){
            queryPageRequest = new QueryPageRequest();
        }
        //页码处理
        if (page <= 0){
            page = 1;
        }
        page = page - 1;
        if (size <= 0){
            page = 10;
        }

        //构造分页
        Pageable pageable = PageRequest.of(page,size );
        Page<CmsPage> cmsPages = cmsPageRepository.findAll(pageable);

        //返回数据
        return QueryResultVo
                .getQueryResponseResult(cmsPages.getTotalElements(), cmsPages.getContent(), CommonCode.SUCCESS);
    }
}
