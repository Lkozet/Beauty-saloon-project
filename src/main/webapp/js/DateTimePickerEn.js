$(function () {
    $('#datetimepicker2').datetimepicker({
        locale: 'en',
        format:'YYYY-MM-DD HH:mm:ss',
        minDate: moment()
    });
    $('#datetimepicker2').data('DateTimePicker').stepping(60);
    $('#datetimepicker2').data('DateTimePicker').disabledHours([0, 1, 2, 3, 4, 5, 6, 7, 8, 19, 20, 21, 22, 23, 24]);
    $('#datetimepicker2').data('DateTimePicker')
        .onselect(document.getElementById("timeStamp")
            .value = $(this).datetimepicker('getDate'))
});