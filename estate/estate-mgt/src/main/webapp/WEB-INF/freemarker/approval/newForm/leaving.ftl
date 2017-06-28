
<div class="form-board box box-solid">
    <div class="board-header box-header">
        <div class="box-title">外出登记表</div>
    </div>
    <div class="box-body text-light-blue">
        <form class="form-horizontal leaving-form" name="leavingForm" ng-submit="ctrl.submit('leaving')">
            <div class="form-group clearfix">
                <label class="control-label">外出时间</label>
                <div class="col-lg-9 col-md-9 col-sm-12">
                <div class="input-group date form_date" format="yyyy-mm-dd hh:ii" datetimepicker key="startTime" change="datePickChange" owner="leaving">
                    <input name="buildYear" class="form-control" size="16" placeholder="外出时间" type="text" ng-model="ctrl.data.leaving.startTime" required ng-pattern="ctrl.config.dateTimeReg">
                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                </div>
                </div>
            </div>
            <div class="form-group clearfix">
                <label class="control-label">返回时间</label>
                <div class="col-lg-9 col-md-9 col-sm-12">
                    <div class="input-group date form_date " format="yyyy-mm-dd hh:ii" datetimepicker key="endTime" change="datePickChange" owner="leaving">
                        <input name="buildYear" class="form-control" size="16" placeholder="返回时间" type="text" ng-model="ctrl.data.leaving.endTime" required ng-pattern="ctrl.config.dateTimeReg">
                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                    </div>
                </div>
            </div>
            <div class="form-group clearfix">
                <label class="control-label">外出地点</label>
                <div class="col-lg-9 col-md-9 col-sm-12">
                    <input name="location"  placeholder="外出地点" autocomplete="off" class="form-control" ng-model="ctrl.data.leaving.location" required/>
                </div>
            </div>
            <div class="form-group clearfix">
                <label class="control-label">外出事由</label>
                <div class="col-lg-9 col-md-9 col-sm-12">
                    <textarea name="note" cols="30" rows="3" class="form-control" ng-model="ctrl.data.leaving.reason" required></textarea>
                </div>
            </div>
            <div class="form-group clearfix">
                <label class="control-label">未打卡事由</label>
                <div class="col-lg-9 col-md-9 col-sm-12">
                    <textarea name="note" cols="30" rows="3" class="form-control" ng-model="ctrl.data.leaving.noClockReason" required></textarea>
                </div>
            </div>
            <div class="text-center">
                <input type="submit" class="btn btn-primary" ng-disable="ctrl.config.uploading" value="提交">
            </div>
        </form>
    </div>
</div>


