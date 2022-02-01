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