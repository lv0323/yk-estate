
<div class="form-board box box-solid">
    <div class="board-header box-header">
        <div class="box-title">出差信息表</div>
    </div>
    <div class="box-body text-light-blue">
        <form class="form-horizontal biz-trip-form" name="bizTripForm" ng-submit="ctrl.submit('bizTrip')">
            <div class="form-group clearfix">
                <label class="control-label">出差时间</label>
                <div class="col-lg-9 col-md-9 col-sm-12">
                <div class="input-group date form_date" format="yyyy-mm-dd hh:ii" datetimepicker key="startTime" change="datePickChange" owner="bizTrip">
                    <input name="buildYear" class="form-control" size="16" placeholder="出差时间" type="text" ng-model="ctrl.data.bizTrip.startTime" required ng-pattern="ctrl.config.dateTimeReg">
                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                </div>
                </div>
            </div>
            <div class="form-group clearfix">
                <label class="control-label">返回时间</label>
                <div class="col-lg-9 col-md-9 col-sm-12">
                    <div class="input-group date form_date " format="yyyy-mm-dd hh:ii" datetimepicker key="endTime" change="datePickChange" owner="bizTrip">
                        <input name="buildYear" class="form-control" size="16" placeholder="返回时间" type="text" ng-model="ctrl.data.bizTrip.endTime" required ng-pattern="ctrl.config.dateTimeReg">
                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                    </div>
                </div>
            </div>
            <div class="form-group clearfix">
                <label class="control-label">出差天数</label>
                <div class="col-lg-9 col-md-9 col-sm-12">
                    <input name="location" type="number" placeholder="出差天数" autocomplete="off" class="form-control" ng-model="ctrl.data.bizTrip.days" required/>
                </div>
            </div>
            <div class="form-group clearfix">
                <label class="control-label">出差事由</label>
                <div class="col-lg-9 col-md-9 col-sm-12">
                    <input name="location" type="text" placeholder="出差事由" autocomplete="off" class="form-control" ng-model="ctrl.data.bizTrip.reason" required/>
                </div>
            </div>
            <div class="form-group clearfix">
                <label class="control-label">出差成果</label>
                <div class="col-lg-9 col-md-9 col-sm-12">
                    <textarea name="note" cols="30" rows="3" class="form-control" ng-model="ctrl.data.bizTrip.outcome" required></textarea>
                </div>
            </div>
            <div class="form-group clearfix">
                <label class="control-label">待解决问题</label>
                <div class="col-lg-9 col-md-9 col-sm-12">
                    <textarea name="note" cols="30" rows="3" class="form-control" ng-model="ctrl.data.bizTrip.problem" required></textarea>
                </div>
            </div>
            <div class="form-group clearfix">
                <label class="control-label">对接资源信息</label>
                <div class="col-lg-9 col-md-9 col-sm-12">
                    <textarea name="note" cols="30" rows="3" class="form-control" ng-model="ctrl.data.bizTrip.resource" required></textarea>
                </div>
            </div>
            <div class="form-group clearfix">
                <label class="control-label">出差费用(元)</label>
                <div class="col-lg-9 col-md-9 col-sm-12">
                    <input name="location" type="number" placeholder="出差费用" autocomplete="off" class="form-control" ng-model="ctrl.data.bizTrip.costs" required/>
                </div>
            </div>
            <div class="text-center">
                <input type="submit" class="btn btn-primary" value="提交">
            </div>
        </form>
    </div>
</div>


