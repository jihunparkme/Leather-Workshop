function saveProduct() {

    if (!confirm("저장하시겠습니까?")) {
        return;
    }

    let formData = new FormData($("#form")[0]);

    formData.append("contents", CKEDITOR.instances.contents.getData());
    $("#form").submit();
}

$(function () {
    $("#btnSave").click(function () {
       saveProduct();
    });

});
