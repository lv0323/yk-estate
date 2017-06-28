
<div class="form-board box box-solid">
    <div class="board-header box-header">
        <div class="box-title">陌生客户拜访表</div>
    </div>
    <div class="box-body text-light-blue">
        <form class="form-horizontal cold-visit-form" name="coldVisitForm" ng-submit="ctrl.submit('coldVisit')">
            <div class="form-group clearfix">
                <label class="control-label">客户公司名称</label>
                <div class="col-lg-9 col-md-9 col-sm-12">
                    <input name="location" type="text" placeholder="客户公司名称" autocomplete="off" class="form-control" ng-model="ctrl.data.coldVisit.companyName" required/>
                </div>
            </div>
            <div class="form-group clearfix">
                <label class="control-label">对方负责人姓名</label>
                <div class="col-lg-9 col-md-9 col-sm-12">
                    <input name="location" type="text" placeholder="对方负责人姓名" autocomplete="off" class="form-control" ng-model="ctrl.data.coldVisit.bossName" required/>
                </div>
            </div>
            <div class="form-group clearfix form-inline">
                <label class="control-label">负责人身份</label>
                <div class="col-lg-9 col-md-9 col-sm-12 text-left">
                    <div class="radio radio-nice " ng-repeat="type in ctrl.config.bossType" >
                        <input type="radio" name="bossType" ng-model="ctrl.data.coldVisit.bossType" ng-value="type.value" id="{{'bossType'+$index}}"/>
                        <label for="{{'bossType'+$index}}" ng-bind="type.label"></label>
                    </div>
                </div>
            </div>

            <div class="form-group clearfix">
                <label class="control-label">联系方式</label>
                <div class="col-lg-9 col-md-9 col-sm-12">
                    <input name="location" type="tel" placeholder="联系方式" autocomplete="off" class="form-control" ng-model="ctrl.data.coldVisit.contactInfo" ng-pattern="/1\d{10}/" required/>
                </div>
            </div>

            <div class="form-group clearfix">
                <label class="control-label">门店地址</label>
                <div class="col-lg-9 col-md-9 col-sm-12">
                    <input name="location" type="text" placeholder="门店地址" autocomplete="off" class="form-control" ng-model="ctrl.data.coldVisit.address" required/>
                </div>
            </div>


            <div class="form-group clearfix">
                <label class="control-label">首次拜访时间</label>
                <div class="col-lg-9 col-md-9 col-sm-12">
                    <div class="input-group date form_date" format="yyyy-mm-dd hh:ii" datetimepicker key="visitTime1" change="datePickChange" owner="coldVisit">
                        <input name="buildYear" class="form-control" size="16" placeholder="首次拜访时间" type="text" ng-model="ctrl.data.coldVisit.visitTime1" required ng-pattern="ctrl.config.dateTimeReg">
                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                    </div>
                </div>
            </div>
            <div class="form-group clearfix">
                <label class="control-label">首次拜访情况说明</label>
                <div class="col-lg-9 col-md-9 col-sm-12">
                    <textarea name="note" cols="30" rows="3" class="form-control" ng-model="ctrl.data.coldVisit.report1" required></textarea>
                </div>
            </div>

            <div class="form-group clearfix">
                <label class="control-label">二次拜访时间</label>
                <div class="col-lg-9 col-md-9 col-sm-12">
                    <div class="input-group date form_date " format="yyyy-mm-dd hh:ii" datetimepicker key="visitTime2" change="datePickChange" owner="coldVisit">
                        <input name="buildYear" class="form-control" size="16" placeholder="二次拜访时间" type="text" ng-model="ctrl.data.coldVisit.visitTime2" ng-pattern="ctrl.config.dateTimeReg">
                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                    </div>
                </div>
            </div>
            <div class="form-group clearfix">
                <label class="control-label">二次拜访情况说明</label>
                <div class="col-lg-9 col-md-9 col-sm-12">
                    <textarea name="note" cols="30" rows="3" class="form-control" ng-model="ctrl.data.coldVisit.report2"></textarea>
                </div>
            </div>
            <div class="form-group clearfix">
                <label class="control-label">三次拜访时间</label>
                <div class="col-lg-9 col-md-9 col-sm-12">
                    <div class="input-group date form_date " format="yyyy-mm-dd hh:ii" datetimepicker key="visitTime3" change="datePickChange" owner="coldVisit">
                        <input name="buildYear" class="form-control" size="16" placeholder="二次拜访时间" type="text" ng-model="ctrl.data.coldVisit.visitTime3" ng-pattern="ctrl.config.dateTimeReg">
                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                    </div>
                </div>
            </div>
            <div class="form-group clearfix">
                <label class="control-label">三次拜访情况说明</label>
                <div class="col-lg-9 col-md-9 col-sm-12">
                    <textarea name="note" cols="30" rows="3" class="form-control" ng-model="ctrl.data.coldVisit.report3"></textarea>
                </div>
            </div>

            <div class="form-group clearfix">
                <label class="control-label">去访人员姓名</label>
                <div class="col-lg-9 col-md-9 col-sm-12">
                    <input name="location" type="text" placeholder="去访人员姓名" autocomplete="off" class="form-control" ng-model="ctrl.data.coldVisit.followers" required/>
                </div>
            </div>
            <div class="text-center">
                <input type="submit" class="btn btn-primary" value="提交">
            </div>
        </form>
    </div>
</div>


