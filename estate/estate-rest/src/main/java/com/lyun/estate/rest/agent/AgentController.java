package com.lyun.estate.rest.agent;

import com.lyun.estate.biz.agent.entity.Agent;
import com.lyun.estate.biz.agent.service.AgentService;
import com.lyun.estate.biz.support.def.DomainType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Jeffrey on 2017-03-21.
 */


@RestController
@RequestMapping("agents")
public class AgentController {

    @Autowired
    private AgentService agentService;

    @GetMapping("")
    List<Agent> getAgents(@RequestParam Long targetId, @RequestParam DomainType domainType) {
        return agentService.getAgents(targetId, domainType);
    }
}
