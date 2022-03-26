$(function() {
    $('#phoneNumber').keydown(function(event) {
        var key = event.charCode || event.keyCode || 0;
        $text = $(this);
        if (key !== 8 && key !== 9) {
            if ($text.val().length === 3) {
                $text.val($text.val() + '-');
            }
            if ($text.val().length === 8) {
                $text.val($text.val() + '-');
            }
        }

        return (key == 8 || key == 9 || key == 46 || (key >= 48 && key <= 57) || (key >= 96 && key <= 105));
    });
});

function fnSendContact() {

    let formData = $("#form").serialize();

    $.ajax({
        type: 'POST',
        url: "/contact/add",
        dataType: 'json',
        data: formData,
        contentType: 'application/json; charset=utf-8'
    }).done(function(result) {
    }).fail(function (error) {
    });
}