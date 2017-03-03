package com.lyun.estate.biz.fang.domian;

import com.google.common.collect.Lists;
import com.lyun.estate.biz.fang.def.Decorate;
import com.lyun.estate.biz.fang.def.HouseTag;
import com.lyun.estate.biz.fang.def.Showing;
import com.lyun.estate.core.supports.types.YN;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

/**
 * Created by Jeffrey on 2017-02-24.
 */
public class TagSelector {
    private List<Showing> showings;
    private YN isOnly;
    private Integer overYears;
    private List<Decorate> decorates;
    private YN nearLine;

    public List<Showing> getShowings() {
        return showings;
    }

    public TagSelector setShowings(List<Showing> showings) {
        this.showings = showings;
        return this;
    }

    public YN getIsOnly() {
        return isOnly;
    }

    public TagSelector setIsOnly(YN isOnly) {
        this.isOnly = isOnly;
        return this;
    }

    public Integer getOverYears() {
        return overYears;
    }

    public TagSelector setOverYears(Integer overYears) {
        this.overYears = overYears;
        return this;
    }

    public List<Decorate> getDecorates() {
        return decorates;
    }

    public TagSelector setDecorates(List<Decorate> decorates) {
        this.decorates = decorates;
        return this;
    }

    public YN getNearLine() {
        return nearLine;
    }

    public TagSelector setNearLine(YN nearLine) {
        this.nearLine = nearLine;
        return this;
    }

    public void decorateFromHouseTags(List<HouseTag> houseTags) {
        if (!CollectionUtils.isEmpty(houseTags)) {
            houseTags.forEach(houseTag -> {
                switch (houseTag) {
                    case ONLY:
                        this.setIsOnly(YN.Y);
                        break;
                    case OVER_2:
                        this.setOverYears(Math.max(2, Optional.ofNullable(this.getOverYears()).orElse(0)));
                        break;
                    case OVER_5:
                        this.setOverYears(Math.max(5, Optional.ofNullable(this.getOverYears()).orElse(0)));
                        break;
                    case DECORATE_JING:
                        this.setDecorates(Lists.newArrayList(Decorate.JING, Decorate.HAO));
                        break;
                    case HAS_KEY:
                        if (this.getShowings() == null) {
                            this.setShowings(Lists.newArrayList(Showing.HAS_KEY));
                        } else {
                            this.getShowings().add(Showing.HAS_KEY);
                        }
                        break;
                    case ANY_TIME:
                        if (this.getShowings() == null) {
                            this.setShowings(Lists.newArrayList(Showing.ANY_TIME));
                        } else {
                            this.getShowings().add(Showing.ANY_TIME);
                        }
                        break;
                    case NEAR_LINE:
                        this.setNearLine(YN.Y);
                        break;
                }
            });
        }
    }
}
