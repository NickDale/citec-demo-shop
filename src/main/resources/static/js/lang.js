function changeLanguage(lang) {
    let currentUrl = window.location.toString();
    let cleanUrl = currentUrl.split("?")[0];
    window.location.replace(cleanUrl + "?lang=" + lang);
}

$("#select-lang").change(function(){
    changeLanguage($(this).val());
});