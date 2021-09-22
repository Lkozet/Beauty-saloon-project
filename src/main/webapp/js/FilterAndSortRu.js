$(document).ready(function () {

    var table = $('#example').DataTable({
        "language": {
            "url": "https://cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Russian.json"
        },
    });

    $("#example thead th").each(function (i) {

        if ($(this).text() !== '') {
            var select = $('<select><option value=""></option></select>')
                .appendTo($(this).empty())
                .on('change', function () {
                    var val = $(this).val();

                    table.column(i)
                        .search(val ? '^' + $(this).val() + '$' : val, true, false)
                        .draw();
                });


            // All other non-Status columns (like the example)
            table.column(i).data().unique().sort().each(function (d, j) {
                select.append('<option value="' + d + '">' + d + '</option>');
            });

        }
    });

});