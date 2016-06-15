//= require jquery-2.2.0.min
//= require_self
if (typeof jQuery !== 'undefined') {
    (function($) {
        $(window).bind("load", function() {

            //find all the ahrefs (one-to-many) and remove them from the create form
            var allAhrefs = $("form").find('a');
            allAhrefs.each(function( index ) {
                console.log( index + ": " + $( this ).text() );
            });
        });
    })(jQuery);
}