<div class="modal fade" id="editBasicModel" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">编辑基本信息</h4>

            </div>
            <div class="modal-body">
                <form class="form-horizontal" name="detailInfo">
                    <div class="form-group clearfix">
                        <label class="pull-left control-label">名称</label>
                        <div class="col-lg-5 col-md-5 col-sm-5">
                            <input ng-model="modifyDetail.name" type="text" class="form-control" required>
                        </div>
                    </div>
                    <div class="form-group clearfix">
                        <label class="pull-left control-label">简称</label>
                        <div class="col-lg-5 col-md-5 col-sm-5">
                            <input ng-model="modifyDetail.abbr" type="text" class="form-control" required>
                        </div>
                    </div>
                    <div class="form-group clearfix">
                        <label class="pull-left control-label">地址</label>
                        <div class="col-lg-5 col-md-5 col-sm-5">
                            <input ng-model="modifyDetail.address" type="text" class="form-control" required>
                        </div>
                    </div>
                    <div class="form-group clearfix">
                        <label class="pull-left control-label">开始日期</label>
                        <div class="col-lg-5 col-md-5 col-sm-5">
                            <div class="input-group date form_date" datetimepicker key="startDate" change="detailDatePickChange">
                                <input class="form-control bgWhite" ng-model="modifyDetail.startDate" required ng-pattern="/\d{4}-\d{2}-\d{2}$/" type="text" placeholder="开始日期" name="delegateStart"/>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group clearfix">
                        <label class="pull-left control-label">结束日期</label>
                        <div class="col-lg-5 col-md-5 col-sm-5">
                            <div class="input-group date form_date" datetimepicker key="endDate" change="detailDatePickChange">
                                <input class="form-control bgWhite" ng-model="modifyDetail.endDate" required ng-pattern="/\d{4}-\d{2}-\d{2}$/" type="text" placeholder="开始日期" name="delegateStart"/>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group clearfix">
                        <label class="pull-left control-label">简介</label>
                        <div class="col-lg-9 col-md-9 col-sm-9">
                                <textarea ng-model="modifyDetail.introduction" class="form-control" rows="3" required>

                                </textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" ng-click="updateDetail()">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="editContractModel" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">编辑签约信息</h4>

            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group clearfix">

                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" ng-click="editContract()">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="renewSigning" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" ng-bind="newSign.id?'更新签约信息':'新增签约信息'"></h4>

            </div>
            <div class="modal-body">
                <form class="form-horizontal" name="newSignForm">
                    <div class="form-group clearfix">
                        <label class="pull-left control-label">签约公司</label>
                        <div class="col-lg-5 col-md-5 col-sm-5">
                            <input ng-model="newSign.companyName" type="text" class="form-control" required disabled>
                        </div>
                    </div>
                    <div class="form-group clearfix" ng-hide="newSign.hideCompanyListDrop">
                        <label class="pull-left control-label">签约信息</label>
                        <div class="col-lg-3 col-md-3 col-sm-3">
                            <select id="companyListDrop">
                                <option value="">签约公司</option>
                                <option ng-repeat="company in companyList" ng-value="company.id" repeat-done="initChosen('#companyListDrop', 'parentId')">{{company.name}}</option>
                            </select>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-3">
                            <select id="signatureDepListDrop">
                                <option value="">公司部门</option>
                                <option ng-repeat="signatureDep in signatureDepList" ng-value="signatureDep.id" repeat-done="initChosen('#signatureDepListDrop', 'signatureDepId')">{{signatureDep.name}}</option>
                            </select>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-3">
                            <select id="signatureRepListDrop">
                                <option value="">部门员工</option>
                                <option ng-repeat="signatureRep in signatureRepList" ng-value="signatureRep.id" repeat-done="initChosen('#signatureRepListDrop', 'partAId')">{{signatureRep.name}}</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group clearfix">
                        <label class="pull-left control-label">签约年限</label>
                        <div class="col-lg-5 col-md-5 col-sm-5">
                            <input ng-model="newSign.years" type="text" class="form-control" required>
                        </div>
                    </div>
                    <div class="form-group clearfix">
                        <label class="pull-left control-label">签约店数</label>
                        <div class="col-lg-5 col-md-5 col-sm-5">
                            <input ng-model="newSign.storeCount" type="text" class="form-control" required>
                        </div>
                    </div>
                    <div class="form-group clearfix">
                        <label class="pull-left control-label">开始日期</label>
                        <div class="col-lg-5 col-md-5 col-sm-5">
                            <div class="input-group date form_date" datetimepicker key="startDate" change="renewSigningDatePickChange">
                                <input class="form-control bgWhite" ng-model="newSign.startDate" required ng-pattern="/\d{4}-\d{2}-\d{2}$/" type="text" placeholder="开始日期" name="delegateStart"/>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group clearfix">
                        <label class="pull-left control-label">结束日期</label>
                        <div class="col-lg-5 col-md-5 col-sm-5">
                            <div class="input-group date form_date" datetimepicker key="endDate" change="renewSigningDatePickChange">
                                <input class="form-control bgWhite" ng-model="newSign.endDate" required ng-pattern="/\d{4}-\d{2}-\d{2}$/" type="text" placeholder="开始日期" name="delegateStart"/>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group clearfix">
                        <label class="pull-left control-label">签约价格</label>
                        <div class="col-lg-5 col-md-5 col-sm-5">
                            <input ng-model="newSign.price" type="number" class="form-control" required>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" ng-click="addNewSign()">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>