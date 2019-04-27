package com.geccocrawler.gecco.spring;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.alibaba.fastjson.JSON;
import com.geccocrawler.gecco.pipeline.Pipeline;


import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.scheduler.DeriveSchedulerContext;
import org.apache.http.util.TextUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * //由前面的htmlBean子类装载后交给这个pipelines处理
 */
@Service(value = "doutusheIndexPipeline")
public class doutusheIndexPipeline implements Pipeline<DoutusheIndex> {
    
    @Override
    public void process(DoutusheIndex doutusheIndex) {
        System.out.println(JSON.toJSONString(doutusheIndex));
        System.out.println("++++++++++++++++++++++++++++++++");
        
        
        List<String> idList = doutusheIndex.getIdList();
        
        List<IndexPageEntity> pageList = doutusheIndex.getPageList();
        //解析下一个界面，交由另外一个doutusheEntityPipeline处理
        for (String url : idList) {
            if (!TextUtils.isEmpty(url)) {
                HttpRequest httpRequest = doutusheIndex.getRequest();
                //很多帖子上的SchedulerContext.into()函数被弃用了，
                // 换成了DeriveSchedulerContext.into()更替之后可以在运行时将请求放入派生队列
                DeriveSchedulerContext.into(httpRequest.subRequest(url));
            }
        }
        //解析下一页，还是交由doutusheIndexPipeline处理
        for (IndexPageEntity indexPageEntity : pageList) {
            String pageName = indexPageEntity.getPageName();
            if (pageName != null &&("下一页").equals(pageName)) {
                String url = indexPageEntity.getPageUrl();
                HttpRequest httpRequest = doutusheIndex.getRequest();
                DeriveSchedulerContext.into(httpRequest.subRequest(url));
            }
        }
        
    }
    
}
