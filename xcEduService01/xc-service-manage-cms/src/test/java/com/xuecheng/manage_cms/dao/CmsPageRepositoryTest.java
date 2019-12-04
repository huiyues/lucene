package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsPage;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;


@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class CmsPageRepositoryTest {

    @Autowired
    private CmsPageRepository cmsPageRepository;

    @Test
    public void findList(){
        Pageable pageable = PageRequest.of(1, 10);
        List<CmsPage> pageLIst = cmsPageRepository.findAll();
        log.info("【页面查询】{}",pageLIst);
    }

    @Test
    public void findByPageName(){
        CmsPage cmsPage = cmsPageRepository.findByPageName("index.html");
        log.info("【页面查询】{}",cmsPage );
    }

    @Test
    public void findByPageNameAndPageType() {
        CmsPage cmsPage = cmsPageRepository.findByPageNameAndPageType("index.html", "1");
        System.out.println(cmsPage);
    }

    @Test
    public void findBySiteIdAndPageType() {
        int count = cmsPageRepository.findBySiteIdAndPageType("1001", "1");
        System.out.println(count);
    }

    @Test
    public void findBySiteIdAndPageType1() {
        Pageable pageable = PageRequest.of(1, 10);
        Page<CmsPage> cmsPages = cmsPageRepository.findBySiteIdAndPageType("1001", "2", pageable);
        System.out.println(cmsPages.getContent());
    }
}