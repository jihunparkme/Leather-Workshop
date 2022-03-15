function saveProduct() {

    if (!confirm("저장하시겠습니까?")) {
        return;
    }

    let formData = new FormData($("#form")[0]);

    formData.append("contents", CKEDITOR.instances.contents.getData());
    $("#form").submit();
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
        }).fail(function (error) {
            alert('공지사항 삭제를 실패하였습니다.\n관리자에게 문의해 주세요.');
        });
    }
}

$(function () {
    $("#btnSave").click(function () {
       saveProduct();
    });

});
