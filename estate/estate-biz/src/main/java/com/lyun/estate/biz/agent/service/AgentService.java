package com.lyun.estate.biz.agent.service;

import com.google.common.base.Strings;
import com.lyun.estate.biz.agent.entity.Agent;
import com.lyun.estate.biz.agent.repo.AgentRepo;
import com.lyun.estate.biz.file.entity.FileDescription;
import com.lyun.estate.biz.file.service.FileService;
import com.lyun.estate.biz.showing.service.ShowingService;
import com.lyun.estate.biz.support.def.DomainType;
import com.lyun.estate.core.supports.exceptions.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by Jeffrey on 2017-03-21.
 */
@Service
public class AgentService {
    @Autowired
    private AgentRepo agentRepo;

    @Autowired
    private ShowingService showingService;

    @Autowired
    private FileService fileService;

    public Agent getFangAgent(Long fangId) {
        return agentRepo.getFangAgent(fangId);
    }


    public List<Agent> getAgents(long targetId, DomainType domainType) {
        ExceptionUtil.checkIllegal(domainType == DomainType.FANG || domainType == DomainType.XIAO_QU,
                "领域类型",
                domainType);
        List<Agent> agents = new ArrayList<>();

        if (domainType == DomainType.FANG) {
            Agent agent = getFangAgent(targetId);
            if (agent != null) {
                agent.setShowingCount(showingService.countSucceedShowing(targetId, agent.getEmployeeId()));
                agents.add(agent);
            }
        }
        if (domainType == DomainType.XIAO_QU) {
            agents.addAll(agentRepo.getXiaoQuAgents(targetId));
        }

        agents.stream().filter(Objects::nonNull).forEach(
                agent -> {
                    if (Strings.isNullOrEmpty(agent.getOpenContact())) {
                        agent.setOpenContact(agent.getMobile());
                    }
                    if (agent.getAvatarId() != null) {
                        agent.setAvatarURI(Optional.ofNullable(fileService.findOne(agent.getAvatarId()))
                                .map(FileDescription::getFileURI).orElse(null));
                    }
                }
        );
        return agents;
    }
}
