/**
 * Copy the value of an input field's title attribute to its value attribute.
 * Clear the input field on focus if its value is the same as its title.
 * Repopulate the input field on blur if it is empty.
 * Hide the input field's associated label if it has one.
 */
var autoPopulate = {
	sInputClass:'ProductSearchParameter', // Class name for input elements to autopopulate
        sDefaultValue:' Zoeken...', // Class name for input elements to autopopulate
	/**
	 * Main function
	 */
	init:function() {
		
		// Check for DOM support
		if (!document.getElementById || !document.createTextNode) {return;}
		// Find all input elements with the given className
		var arrInputs = autoPopulate.getElementsByClassName(document, 'input', autoPopulate.sInputClass);
		var iInputs = arrInputs.length;
		var oInput;
		for (var i=0; i<iInputs; i++) {
			oInput = arrInputs[i];
			// Make sure it's a text input
			if (oInput.type != 'text') { continue; }

                        oInput.value = searchValue;

			if ((oInput.value == '') && (oInput.title != '')) { oInput.value = oInput.title; }

			// Add event handlers for focus and blur
			autoPopulate.addEvent(oInput, 'focus', function() {
				// If value and title are equal on focus, clear value
				if (this.value ==  searchValue) {
					this.value = '';
					this.select(); // Make input caret visible in IE
				}
			});
			autoPopulate.addEvent(oInput, 'blur', function() {
				// If the field is empty on blur, assign title to value
				if (!this.value.length) { this.value = searchValue; }
			});
		}
	},
	/**
	 * getElementsByClassName function included here for portability.
	 * Remove if you are already using one.
	 * Written by Jonathan Snook, http://www.snook.ca/jonathan
	 * Add-ons by Robert Nyman, http://www.robertnyman.com
	 */
	getElementsByClassName:function(oElm, strTagName, strClassName) {
	    var arrElements = (strTagName == "*" && document.all)? document.all : oElm.getElementsByTagName(strTagName);
	    var arrReturnElements = new Array();
	    strClassName = strClassName.replace(/\-/g, "\\-");
	    var oRegExp = new RegExp("(^|\\s)" + strClassName + "(\\s|$)");
	    var oElement;
	    for(var i=0; i<arrElements.length; i++){

	        oElement = arrElements[i];      
	        if(oRegExp.test(oElement.className)){
	            arrReturnElements.push(oElement);

	        }   
	    }
	    return (arrReturnElements)
	},
	/**
	 * addEvent function included here for portability.
	 * Remove if you are already using an addEvent/DOMReady function.
	 * Found at http://www.quirksmode.org/blog/archives/2005/10/_and_the_winner_1.html
	 */
	addEvent:function(obj, type, fn) {
		if (obj.addEventListener)
			obj.addEventListener(type, fn, false);
		else if (obj.attachEvent) {
			obj["e"+type+fn] = fn;
			obj[type+fn] = function() {obj["e"+type+fn](window.event);}
			obj.attachEvent("on"+type, obj[type+fn]);
		}
	}
};

/**
 * Init on window load.
 * Replace this with a call to your own addEvent/DOMReady function if you use one.
 */
autoPopulate.addEvent(window, 'load', autoPopulate.init);
