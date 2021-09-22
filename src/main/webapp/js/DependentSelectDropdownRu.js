$(document).ready(function () {
    $.ajax({
        url: "MasterSelectDropdown",
        method: "GET",
        data: {operation: 'services'},
        success: function (data, textStatus, jqXHR) {
            let obj = $.parseJSON(data);
            $.each(obj, function (key, value) {
                $('#services').append('<option value="' + value.id + '">' + value.nameRu + '</option>')
            });
            $('select').formSelect();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            $('#services').append('<option>   </option>');
        },
        cache: false
    });


    $('#services').change(function () {
        $('#masters').find('option').remove();
        $('#masters').append('<option>     </option>');

        let cid = $('#services').val();
        let data = {
            operation: "masters",
            id: cid
        };

        $.ajax({
            url: "MasterSelectDropdown",
            method: "GET",
            data: data,
            success: function (data, textStatus, jqXHR) {
                let obj = $.parseJSON(data);
                $.each(obj, function (key, value) {
                    $('#masters').append('<option value="' + value.id + '">' + value.nameRu + '</option>')
                });
                $('select').formSelect();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $('#masters').append('<option>   </option>');
            },
            cache: false
        });
    });
});