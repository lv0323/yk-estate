package com.lyun.estate.rest.agent;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.google.common.collect.Lists;
import com.lyun.estate.biz.fang.entity.Agent;
import com.lyun.estate.biz.spec.fang.rest.entity.FangSummary;
import com.lyun.estate.biz.spec.fang.rest.service.FangService;
import com.lyun.estate.biz.support.def.BizType;
import com.lyun.estate.biz.support.def.DomainType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Jeffrey on 2017-03-21.
 */
@RestController
@RequestMapping("agents")
public class AgentController {

    @Autowired
    FangService fangService;

    @GetMapping("")
    List<Agent> getAgent(@RequestParam Long targetId, @RequestParam DomainType domainType) {
        if (domainType == DomainType.FANG) {
            return Lists.newArrayList(fangService.getFangAgent(targetId));
        } else if (domainType == DomainType.XIAO_QU) {
            List<Agent> result = new ArrayList<>();

            PageList<FangSummary> sellFangs = fangService.findSummaryByXiaoQuId(targetId,
                    BizType.SELL,
                    new PageBounds(1, 3));

            Set<Long> agentIds = new HashSet<>();
            final int[] count = {0};

            sellFangs.forEach(t -> {
                Agent agent = fangService.getFangAgent(t.getId());
                if (!agentIds.contains(agent.getEmployeeId())) {
                    agentIds.add(agent.getEmployeeId());
                    count[0] = count[0] + 1;
                    result.add(agent);
                }
            });

            if (count[0] < 3) {
                PageList<FangSummary> rentFangs = fangService.findSummaryByXiaoQuId(targetId,
                        BizType.RENT,
                        new PageBounds(1, 3));
                rentFangs.forEach(t -> {
                    if (count[0] < 3) {
                        Agent agent = fangService.getFangAgent(t.getId());
                        if (!agentIds.contains(agent.getEmployeeId())) {
                            agentIds.add(agent.getEmployeeId());
                            count[0] = count[0] + 1;
                            result.add(agent);
                        }
                    }
                });
            }
            return result;
        } else {
            return new ArrayList<>();
        }
    }
}
