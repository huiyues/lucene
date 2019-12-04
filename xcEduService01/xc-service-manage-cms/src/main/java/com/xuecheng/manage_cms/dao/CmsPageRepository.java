package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Author: heiye
 * @Date: 2019/12/4
 */
public interface CmsPageRepository extends MongoRepository<CmsPage,String> {

    /**
     * 根据页面名查询
     * @param pageName
     * @return
     */
    CmsPage findByPageName(String pageName);

    /**
     * 根据页面名和页面类型查询
     * @param pageName
     * @param pageType
     * @return
     */
    CmsPage findByPageNameAndPageType(String pageName,String pageType);

    /**
     * 根据站点id和页面类型查询总记录数
     * @param siteId
     * @param pageType
     * @return
     */
    int findBySiteIdAndPageType(String siteId,String pageType);

    /**
     * 根据站点 ID和页面类型进行分页查询
     * @param siteId
     * @param pageType
     * @param pageable
     * @return
     */
    Page<CmsPage> findBySiteIdAndPageType(String siteId, String pageType, Pageable pageable);
}
