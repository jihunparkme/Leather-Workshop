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