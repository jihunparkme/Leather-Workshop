$(function() {
});

function btnPageNumber(_this) {
    searchNoticeList($(_this).text() - 1)
}

function searchNoticeList(page) {
    if (page == undefined) {
        page = 0;
    }

    $("#page").val(page);
    $("#form").submit();
}

function fnCancel(id) {
    location.href = "/notice/" + id;
}

function fnDelete(id) {
    if (confirm("정말로 삭제하시겠습니까?")) {
        $.ajax({
            type: 'DELETE',
            url: "/notice/" + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function() {
            alert('공지사항이 삭제되었습니다.');
            window.location.href = '/notice';
        }).fail(function (error) {
            alert('공지사항 삭제를 실패하였습니다.\n관리자에게 문의해 주세요.');
        });
    }
}

function goEdit(id) {
    location.href = "/notice/" + id + "/edit";
}