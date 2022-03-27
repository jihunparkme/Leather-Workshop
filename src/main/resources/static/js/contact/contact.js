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

function checkValid() {
    if ($("#name").val().length > 30) {
        alert("이름은 최대 30자 이내로 작성해주세요.")
        $("#name").focus();
        return false;
    }

    if ($("#email").val().length > 30) {
        alert("이메일은 최대 30자 이내로 작성해주세요.")
        $("#email").focus();
        return false;
    }

    var regPhone = /^[0-9]{3}-[0-9]{3,4}-[0-9]{4}/;
    if (regPhone.test($("#phoneNumber").val()) != true) {
        alert("전화번호 형식이 올바르지 않습니다.")
        $("#phoneNumber").focus();
        return false;
    }


    if ($("#title").val().length > 100) {
        alert("제목은 최대 100자 이내로 작성해주세요.")
        $("#title").focus();
        return false;
    }

    if ($("#contents").val().length > 20000) {
        alert("내용은 최대 20000자 이내로 작성해주세요.")
        $("#contents").focus();
        return false;
    }

    return true;
}

function resetFormData() {
    $("#name").val("");
    $("#email").val("");
    $("#phoneNumber").val("");
    $("#title").val("");
    $("#contents").val("");
}

function fnSendContact() {

    if(!checkValid()) {
        return;
    }

    $(".loading").addClass('d-block');
    $(".sent-message").removeClass('d-block');
    $(".error-message").removeClass('d-block');

    $.ajax({
        type: 'POST',
        url: "/contact/add",
        dataType: 'json',
        data: $("#contact").serialize(),
    }).done(function(result) {
        $(".loading").removeClass('d-block');
        $(".sent-message").addClass('d-block');
        resetFormData();
    }).fail(function (error) {
        $(".loading").removeClass('d-block');
        $(".error-message").addClass('d-block');
    });
}