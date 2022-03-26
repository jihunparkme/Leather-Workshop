function toJson(formData) {
    let object = {};
    formData.forEach((value, key) => object[key] = value);

    return object;
}