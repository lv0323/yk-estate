package com.lyun.estate.biz.spec.fang.domain;

import com.google.common.collect.Lists;
import com.lyun.estate.biz.fang.def.Decorate;
import com.lyun.estate.biz.fang.def.HouseTag;
import com.lyun.estate.biz.fang.def.Showing;
import com.lyun.estate.core.supports.types.YN;

import java.util.*;

/**
 * Created by Jeffrey on 2017-02-24.
 */
public class FangForTag {
    private YN isOnly;
    private Integer overYears;
    private Showing showing;
    private YN nearLine;
    private Decorate decorate;
    private List<HouseTag> tags;

    public YN getIsOnly() {
        return isOnly;
    }

    public FangForTag setIsOnly(YN isOnly) {
        this.isOnly = isOnly;
        return this;
    }

    public Integer getOverYears() {
        return overYears;
    }

    public FangForTag setOverYears(Integer overYears) {
        this.overYears = overYears;
        return this;
    }

    public Showing getShowing() {
        return showing;
    }

    public FangForTag setShowing(Showing showing) {
        this.showing = showing;
        return this;
    }

    public YN getNearLine() {
        return nearLine;
    }

    public FangForTag setNearLine(YN nearLine) {
        this.nearLine = nearLine;
        return this;
    }

    public Decorate getDecorate() {
        return decorate;
    }

    public FangForTag setDecorate(Decorate decorate) {
        this.decorate = decorate;
        return this;
    }

    public List<HouseTag> getTags() {
        return tags;
    }

    public FangForTag setTags(List<HouseTag> tags) {
        this.tags = tags;
        return this;
    }

    public FangForTag decorateTags() {
        Set<HouseTag> tags = new HashSet<>(Optional.ofNullable(this.getTags()).orElse(new ArrayList<>()));

        if (this.getIsOnly() == YN.Y) {
            tags.add(HouseTag.ONLY);
        }
        if (Optional.ofNullable(this.getOverYears()).orElse(0) >= 5) {
            tags.add(HouseTag.OVER_5);
        } else if (Optional.ofNullable(this.getOverYears()).orElse(0) >= 2) {
            tags.add(HouseTag.OVER_2);
        }
        if (this.getDecorate() == Decorate.JING) {
            tags.add(HouseTag.DECORATE_JING);
        }
        if (this.getShowing() == Showing.ANY_TIME) {
            tags.add(HouseTag.ANY_TIME);
        } else if (this.getShowing() == Showing.HAS_KEY) {
            tags.add(HouseTag.HAS_KEY);
        }
        if (this.getNearLine() == YN.Y) {
            tags.add(HouseTag.NEAR_LINE);
        }
        this.setTags(Lists.newArrayList(tags));
        return this;
    }
}
