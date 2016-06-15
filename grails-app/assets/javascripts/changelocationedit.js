//= require jquery-2.2.0.min
//= require_self
if (typeof jQuery !== 'undefined') {
    (function($) {
        $(window).bind("load", function() {
            var queryDict = {};
            location.search.substr(1).split("&").forEach(function(item) {queryDict[item.split("=")[0]] = item.split("=")[1]});
            var document_id = queryDict['document.id'];
            if (document_id) {
                $("#document").prop("hidden", "hidden");
            }
        });
    })(jQuery);
}