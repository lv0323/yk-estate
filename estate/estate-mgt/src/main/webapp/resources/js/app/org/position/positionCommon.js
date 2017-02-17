/**
 * Created by yanghong on 2/17/17.
 */
define(contextPath+'/js/app/org/position/positionCommon.js',
    ['main-app', contextPath + '/js/service/position-service.js', 'locationUtil', 'dropdown'],
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

        return PositionCommon;

    });