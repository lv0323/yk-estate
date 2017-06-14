
<div class="form-board box box-solid">
    <div class="board-header box-header">
        <div class="box-title">签约客户表</div>
    </div>
    <div class="box-body text-light-blue">
        <form class="form-horizontal signing-form" name="signingForm" ng-submit="ctrl.submit('signing')">
            <div class="form-group clearfix form-inline">
                <label class="control-label">签约类型</label>
                <div class="col-lg-9 col-md-9 col-sm-12">
                    <select id="companyType" ng-model="ctrl.data.signing.companyType" class="selected_1 show-menu-arrow form-control" select-picker>
                        <option value="">选择加盟类型</option>
                    <#list companyType ? if_exists as type>
                        <#if type != 'YK'>
                            <option value="${type.name()}" >${type.getLabel()}</option>
                        </#if>
                    </#list>
                    </select>
                </div>
            </div>
            <div class="form-group clearfix">
                <label class="control-label">客户公司名称</label>
                <div class="col-lg-9 col-md-9 col-sm-12">
                    <input name="location" type="text" placeholder="客户公司名称" autocomplete="false" class="form-control" ng-model="ctrl.data.signing.companyName" required/>
                </div>
            </div>
            <div class="form-group clearfix">
                <label class="control-label">客户公司简称</label>
                <div class="col-lg-9 col-md-9 col-sm-12">
                    <input name="location" type="text" placeholder="客户公司名称" autocomplete="false" class="form-control" ng-model="ctrl.data.signing.companyAbbr" required/>
                </div>
            </div>
            <div class="form-group clearfix">
                <label class="control-label">所在城市</label>
                <div class="col-lg-9 col-md-9 col-sm-12">
                <select id="cityListDrop" ng-model="create.cityId" class="chosen-select-dep">
                    <option value="">选择城市</option>
                    <option ng-repeat="city in ctrl.config.cityList" ng-value="city.id" repeat-done="ctrl.initChosen('#cityListDrop', 'cityId')">{{city.name}}</option>
                </select>
                </div>
            </div>
            <div class="form-group clearfix">
                <label class="control-label">详细地址</label>
                <div class="col-lg-9 col-md-9 col-sm-12">
                    <input name="location" type="text" placeholder="详细地址" autocomplete="off" class="form-control" ng-model="ctrl.data.signing.address" required/>
                </div>
            </div>
            <div class="form-group clearfix">
                <label class="control-label">对方负责人姓名</label>
                <div class="col-lg-9 col-md-9 col-sm-12">
                    <input name="location" type="text" placeholder="对方负责人姓名" autocomplete="off" class="form-control" ng-model="ctrl.data.signing.bossName" required/>
                </div>
            </div>
            <div class="form-group clearfix">
                <label class="control-label">联系方式</label>
                <div class="col-lg-9 col-md-9 col-sm-12">
                    <input name="location" type="tel" placeholder="联系方式" autocomplete="off" class="form-control" ng-model="ctrl.data.signing.bossMobile" ng-pattern="/1\d{10}/" required/>
                </div>
            </div>
            <div class="form-group clearfix">
                <label class="control-label">本公司负责人</label>
                <div class="col-lg-9 col-md-9 col-sm-12">
                    <div class="col-lg-3 col-md-3 col-sm-12 part-a-select">
                        <select id="companyListDrop">
                            <option value="">签约公司</option>
                            <option ng-repeat="company in ctrl.config.companyList" ng-value="company.id" repeat-done="ctrl.initPartAChosen('#companyListDrop', 'parentId')">{{company.name}}</option>
                        </select>
                    </div>
                    <div class="col-lg-3 col-md-3 col-sm-12 part-a-select">
                        <select id="signatureDepListDrop">
                            <option value="">公司部门</option>
                            <option ng-repeat="signatureDep in ctrl.config.signatureDepList" ng-value="signatureDep.id" repeat-done="ctrl.initPartAChosen('#signatureDepListDrop', 'signatureDepId')">{{signatureDep.name}}</option>
                        </select>
                    </div>
                    <div class="col-lg-3 col-md-3 col-sm-12 part-a-select">
                        <select id="signatureRepListDrop">
                            <option value="">部门员工</option>
                            <option ng-repeat="signatureRep in ctrl.config.signatureRepList" ng-value="signatureRep.id" repeat-done="ctrl.initPartAChosen('#signatureRepListDrop', 'partAInChargeId')">{{signatureRep.name}}</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="form-group clearfix">
                <label class="control-label">备注</label>
                <div class="col-lg-9 col-md-9 col-sm-12">
                    <textarea name="note" cols="30" rows="3" class="form-control" ng-model="ctrl.data.signing.note"></textarea>
                </div>
            </div>

            <div class="form-group clearfix">
                <label class="control-label">签约年限</label>
                <div class="col-lg-9 col-md-9 col-sm-12">
                    <input name="location" type="number" placeholder="签约年限" class="form-control" ng-model="ctrl.data.signing.years" required/>
                </div>
            </div>
            <div class="form-group clearfix">
                <label class="control-label">签约店数</label>
                <div class="col-lg-9 col-md-9 col-sm-12">
                    <input name="location" type="number" placeholder="签约店数" class="form-control" ng-model="ctrl.data.signing.storeCount" required/>
                </div>
            </div>
            <div class="form-group clearfix">
                <label class="control-label">签约起始日期</label>
                <div class="col-lg-9 col-md-9 col-sm-12">
                    <div class="input-group date form_date " format="yyyy-mm-dd" datetimepicker key="startDate" change="datePickChange" owner="signing">
                        <input name="buildYear" class="form-control" size="16" placeholder="开始时间" type="text" ng-model="ctrl.data.signing.startDate" ng-pattern="ctrl.config.dateReg">
                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                    </div>
                </div>
            </div>
            <div class="form-group clearfix">
                <label class="control-label">签约结束日期</label>
                <div class="col-lg-9 col-md-9 col-sm-12">
                    <div class="input-group date form_date " format="yyyy-mm-dd" datetimepicker key="endDate" change="datePickChange" owner="signing">
                        <input name="buildYear" class="form-control" size="16" placeholder="结束时间" type="text" ng-model="ctrl.data.signing.endDate" ng-pattern="ctrl.config.dateReg">
                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                    </div>
                </div>
            </div>
            <div class="form-group clearfix">
                <label class="control-label">签约金额</label>
                <div class="col-lg-9 col-md-9 col-sm-12">
                    <input name="location" type="number" placeholder="签约金额" autocomplete="off" class="form-control" ng-model="ctrl.data.signing.price" required/>
                </div>
            </div>
            <div class="text-center">
                <input type="submit" class="btn btn-primary" ng-disbale="ctrl.config.uploading" value="提交">
            </div>
        </form>
    </div>
</div>


