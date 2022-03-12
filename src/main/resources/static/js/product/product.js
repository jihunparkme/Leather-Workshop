$(function () {

    activeCategory();

    //Click category
    $("#portfolio-flters li").click(function (e) {

        if ($("#category").val() == $(this).text()) {
            return;
        }

        changedCategory($(this).text());
    });
});

function activeCategory() {
    $('#portfolio-flters').children('li').each(function () {
        if ($("#category").val() == $(this).text()) {
            $(this).addClass('filter-active');
            return;
        }
    });
}

function changedCategory(ctgy) {

    $("#category").val(ctgy);
    $("#page").val(0);

    searchProductList(ctgy, 0)
}

function btnPageNumber(_this) {
    searchProductList($("#category").val(), $(_this).text() - 1)
}

function searchProductList(ctgy, page) {

    if (ctgy == undefined) {
        ctgy = "ALL";
    }

    if (page == undefined) {
        page = 0;
    }

    $("#category").val(ctgy);
    $("#page").val(page);
    $("#form").submit();
}
