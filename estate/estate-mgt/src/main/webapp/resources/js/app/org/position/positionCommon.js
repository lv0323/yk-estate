/**
 * Created by yanghong on 2/17/17.
 */
define(contextPath+'/js/app/org/position/positionCommon.js',
    ['main-app', contextPath + '/js/service/position-service.js', 'dropdown', 'chosen'],
    function (mainApp, PositionService) {

        var header = {};
        var PositionCommon = {};

        PositionCommon.initPositionSelector = function (currentPositionId) {
            var defer  = $.Deferred();
            PositionService.getAllPosition(header).done(function (data) {
                $('.employeePosition').html(data.map(function (item, index) {
                    return '<option value='+item.id+'>'+item.name+'</option>';
                }));
                defer.resolve(data);

                if(typeof(currentPositionId) != 'undefined'){
                    $('.employeePosition option[value="'+currentPositionId+'"]').prop('selected', true).attr('selected','selected');
                    $(".employeePosition").trigger("chosen:updated");
                }
            });
            return defer;
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