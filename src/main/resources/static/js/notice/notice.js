$(function() {
});

function btnPageNumber(_this) {
    searchNoticeList($(_this).text() - 1)
}

function searchNoticeList(page, size) {
    if (page == undefined) {
        page = 0;
    }
    if (size == undefined) {
        size = 5;
    }

    $("#page").val(page);
    $("#size").val(size);
    $("#form").submit();
}