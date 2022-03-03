$(function() {
});

function btnPageNumber(_this) {
    searchReviewList($(_this).text() - 1)
}

function searchReviewList(page) {
    if (page == undefined) {
        page = 0;
    }

    $("#page").val(page);
    $("#form").submit();
}

function btnEdit(id) {
    location.href = "/review/" + id + "/edit";
}

function resize(obj) {
    obj.style.height = "1px";
    obj.style.height = (12+obj.scrollHeight)+"px";
}

function fnDelete(id) {
    if (confirm("정말로 삭제하시겠습니까?")) {
        $.ajax({
            type: 'DELETE',
            url: "/review/" + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function(result) {
            if (result.code == '200') {
                alert(result.message);
                window.location.href = '/review';
            } else {
                alert(result.message);
                return;
            }
        }).fail(function (error) {
            alert('후기 삭제를 실패하였습니다.\n관리자에게 문의해 주세요.');
        });
    }
}