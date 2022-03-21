function saveProduct() {

    if (!confirm("등록하시겠습니까?")) {
        return;
    }

    submit("form");
}

function editProduct() {

    if (!confirm("수정하시겠습니까?")) {
        return;
    }

    submit("product");
}

function submit(id) {
    let formData = new FormData($("#" + id)[0]);

    formData.append("contents", CKEDITOR.instances.contents.getData());
    $("#" + id).submit();
}

function fnCancel(id) {
    location.href = "/product/" + id;
}

function fnDelete(id) {
    if (confirm("정말로 삭제하시겠습니까?")) {
        $.ajax({
            type: 'DELETE',
            url: "/product/" + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function() {
            alert('공지사항이 삭제되었습니다.');
            window.location.href = '/product';
        }).fail(function () {
            alert('공지사항 삭제를 실패하였습니다.\n관리자에게 문의해 주세요.');
        });
    }
}

function deleteThumbnail() {
    $("#thumbnailDiv").empty();
    $("#isDeleteThumbnail").val("true");
}

$(function () {
    $("#btnSave").click(function () {
       saveProduct();
    });

    $("#btnEdit").click(function () {
        editProduct();
    });
});
