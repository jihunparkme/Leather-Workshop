$(function() {

});

function searchNoticeList(page, size) {
    if (page == undefined) {
        page = 1;
    }
    if (size == undefined) {
        size = 5;
    }

    $("#page").val(page);
    $("#size").val(size);
    $("#form").submit();
}