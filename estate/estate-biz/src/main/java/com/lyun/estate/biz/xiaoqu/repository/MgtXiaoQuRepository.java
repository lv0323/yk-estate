package com.lyun.estate.biz.xiaoqu.repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.spec.xiaoqu.mgt.entity.MgtXiaoQuDetail;
import com.lyun.estate.biz.spec.xiaoqu.mgt.entity.MgtXiaoQuFilter;
import com.lyun.estate.biz.spec.xiaoqu.mgt.entity.MgtXiaoQuSummary;
import com.lyun.estate.biz.xiaoqu.entity.XiaoQu;
import com.lyun.estate.biz.xiaoqu.repository.provider.MgtXiaoQuSqlProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

/**
 * Created by Jeffrey on 2017-02-21.
 */
@Repository
public interface MgtXiaoQuRepository {
    @Select("select xq.*,c.address, c.name from t_xiao_qu xq left join t_community c on xq.community_id = c.id where xq.id =#{id}")
    XiaoQu findOne(Long id);

    @SelectProvider(type = MgtXiaoQuSqlProvider.class, method = "list")
    PageList<MgtXiaoQuSummary> list(MgtXiaoQuFilter filter, PageBounds pageBounds);


    @SelectProvider(type = MgtXiaoQuSqlProvider.class, method = "detail")
    MgtXiaoQuDetail detail(Long xiaoQuId);
}
