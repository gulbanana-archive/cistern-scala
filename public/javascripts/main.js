/* hold opacity on focusables as long as either there's keyboard OR mouse focus */
$(document).ready(function(){
	var keyboard = false;
	var mouse = false;
	
	var focus = function(div) {
		$(div).stop().animate({opacity: 1 }, { duration: 100 });
	}
	
	var unfocus = function(div) {
		$(div).stop().animate({opacity: 0.5 }, { duration: 100 });
	}
	
    $(".unfocused").hover(function() {
    	mouse = true;
    	if (keyboard == false) focus(this);
    },function() {
    	mouse = false;
    	if (keyboard == false) unfocus(this);
    });
    
    $(".unfocused").focusin(function() {
    	keyboard = true;
    	if (mouse == false) focus(this);
    })
    
    $(".unfocused").focusout(function() {
    	keyboard = false;
    	if (mouse == false) unfocus(this);
    })
});
