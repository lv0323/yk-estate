package com.lyun.estate.biz.housedict.domain;


import com.lyun.estate.biz.housedict.entity.SubDistrict;
import com.lyun.estate.biz.support.def.DomainType;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class DistrictWithSubs {

    private Long id;
    private Long cityId;
    private DomainType domainType;
    private String abbr;
    private String name;
    private List<SimpleSubDistrict> subs;

    public Long getId() {
        return id;
    }

    public DistrictWithSubs setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getCityId() {
        return cityId;
    }

    public DistrictWithSubs setCityId(Long cityId) {
        this.cityId = cityId;
        return this;
    }

    public String getAbbr() {
        return abbr;
    }

    public DistrictWithSubs setAbbr(String abbr) {
        this.abbr = abbr;
        return this;
    }

    public String getName() {
        return name;
    }

    public DistrictWithSubs setName(String name) {
        this.name = name;
        return this;
    }

    public DomainType getDomainType() {
        return domainType;
    }

    public DistrictWithSubs setDomainType(DomainType domainType) {
        this.domainType = domainType;
        return this;
    }

    public List<SimpleSubDistrict> getSubs() {
        return subs;
    }

    public DistrictWithSubs setSubs(List<SubDistrict> subs) {
        this.subs = subs.stream().map(t -> {
            SimpleSubDistrict entity = new SimpleSubDistrict();
            BeanUtils.copyProperties(t, entity);
            entity.setDomainType(DomainType.SUB_DISTRICT);
            return entity;
        }).collect(Collectors.toList());
        return this;
    }

    public class SimpleSubDistrict {
        private Long id;
        private String abbr;
        private String name;
        private DomainType domainType;

        public Long getId() {
            return id;
        }

        public SimpleSubDistrict setId(Long id) {
            this.id = id;
            return this;
        }

        public String getAbbr() {
            return abbr;
        }

        public SimpleSubDistrict setAbbr(String abbr) {
            this.abbr = abbr;
            return this;
        }

        public String getName() {
            return name;
        }

        public SimpleSubDistrict setName(String name) {
            this.name = name;
            return this;
        }

        public DomainType getDomainType() {
            return domainType;
        }

        public SimpleSubDistrict setDomainType(DomainType domainType) {
            this.domainType = domainType;
            return this;
        }
    }
}
