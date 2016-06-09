if (typeof jQuery !== 'undefined') {
    (function($) {
        $(window).bind("load", function() {

        tinymce.init({
            selector: 'textarea',
            plugin: 'a_tinymce_plugin',
            a_plugin_option: true,
            a_configuration_option: 400
        });
            // code here
        });
    })(jQuery);
}
