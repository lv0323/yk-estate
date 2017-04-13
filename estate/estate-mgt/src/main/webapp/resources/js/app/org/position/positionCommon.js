/**
 * Created by yanghong on 2/17/17.
 */
define(contextPath+'/js/app/org/position/positionCommon.js',
    ['main-app', contextPath + '/js/service/position-service.js', 'dropdown'],
    function (mainApp, PositionService) {

        var header = {};
        var PositionCommon = {};

        PositionCommon.initPositionSelector = function (currentPositionId) {
            PositionService.getAllPosition(header).done(function (data) {
                $('.employeePosition').html(data.map(function (item, index) {
                    return '<option id='+item.id+'>'+item.name+'</option>';
                }));

                if(typeof(currentPositionId) != 'undefined'){
                    $('.employeePosition').find('option[id='+currentPositionId+']').attr('selected','selected');
                }
            });
        };

        PositionCommon.verifyPositionInput = function (actionType, toSubmitPosition) {
            var flag = true;
            $('.form-group').find('.invalid-input').removeClass('invalid-input');
            if (toSubmitPosition.name==="" || typeof(toSubmitPosition.name)==='undefined') {
                flag = false;
                $('#'+actionType+'PositionName').addClass('invalid-input');
            }
            if (toSubmitPosition.type==="" || typeof(toSubmitPosition.type)==='undefined') {
                flag = false;
                $('#'+actionType+'PositionType').addClass('invalid-input');
            }

            return flag;
        };

        return PositionCommon;

    });