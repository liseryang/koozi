function checkUsernameForLength(whatYouTyped) {
	var fieldset = whatYouTyped.parentNode;
	var txt = whatYouTyped.value;
	if (txt.length > 5) {
		fieldset.className = "welldone";

	}
	else {
		fieldset.className = "nogood";
	}
}
 
function checkNotEmpty(whatYouTyped) {
	var fieldset = whatYouTyped.parentNode;
	var txt = whatYouTyped.value;
	if (txt.length > 0) {
		fieldset.className = "welldone";
	}
	else {
		fieldset.className = "nogood";
	}
}
 
 function checkNothing(whatYouTyped) {
	var fieldset = whatYouTyped.parentNode;
	var txt = whatYouTyped.value;
	fieldset.className = "welldone";
}
 
 
// If the password is at least 4 characters long, the containing 
// fieldset is assigned class="kindagood".
// If it's at least 8 characters long, the containing
// fieldset is assigned class="welldone", to give the user
// the indication that they've selected a harder-to-crack
// password.
 
function checkPassword(whatYouTyped) {
	var fieldset = whatYouTyped.parentNode;
	var txt = whatYouTyped.value;
	if (txt.length > 3 && txt.length < 8) {
		fieldset.className = "nogood";
	} else if (txt.length > 7 ) {
		fieldset.className = "welldone";
	} else {
		fieldset.className = "nogood";
	}
}

function checkPassword2(whatYouTyped, whatYouTyped2 ) {
	var fieldset = whatYouTyped.parentNode;
	var txt = whatYouTyped.value;
	if (txt.length > 3 && txt.length < 8) {
		fieldset.className = "nogood";
	} else if (whatYouTyped.value == whatYouTyped2.value ) {
		fieldset.className = "welldone";
	} else {
		fieldset.className = "nogood";
	}
}
 
// This function checks the email address to be sure
// it follows a certain pattern:
// blah@blah.blah
// If so, it assigns class="welldone" to the containing
// fieldset.
 
function checkEmail(whatYouTyped) {
	var fieldset = whatYouTyped.parentNode;
	var txt = whatYouTyped.value;
	if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(txt)) {
		fieldset.className = "welldone";
	} else {
		fieldset.className = "nogood";
	}
}

function checkKiala(whatYouTyped) {
	var fieldset = whatYouTyped.parentNode;
	var txt = whatYouTyped.value;
	if (/^\b[K]{1}[0-9]{4}\b$/.test(txt)) {
		fieldset.className = "welldone";
	} else {
		fieldset.className = "nogood";
	}
}

function checkPostcode2(whatYouTyped) {
	var fieldset = whatYouTyped.parentNode;
	var txt = whatYouTyped.value;
        var regex =  /^\b[0-9]{4}\b$/;
	if (regex.test(txt) || /^\b[0-9]{4}[A-Z]{2}\b$/.test(txt)) {
		fieldset.className = "welldone";
	} else {
		fieldset.className = "nogood";
	}


}

function checkPostcodeKiala(whatYouTyped) {
	var fieldset = whatYouTyped.parentNode;
	var txt = whatYouTyped.value;
        var regex =  /^\b[0-9]{4}\b$/;
	if (regex.test(txt) || /^\b[0-9]{4}[A-Z]{2}\b$/.test(txt) || txt.length == 0) {
		fieldset.className = "welldone";
	} else {
		fieldset.className = "nogood";
	}
}

function checkPostcode(whatYouTyped) {
	var fieldset = whatYouTyped.parentNode;
	var txt = whatYouTyped.value;
	if (txt.length > 3) {
		fieldset.className = "welldone";

	}
	else {
		fieldset.className = "nogood";
	}
}

function checkTrue(whatYouTyped) {
	var fieldset = whatYouTyped.parentNode;
	var txt = whatYouTyped.checked ;

	if (txt == true ) {
		fieldset.className = "welldone";
	} else {
		fieldset.className = "nogood";
	}
}
 
 
// this part is for the form field hints to display
// only on the condition that the text input has focus.
// otherwise, it stays hidden.
 
function addLoadEvent(func) {
  var oldonload = window.onload;
  if (typeof window.onload != 'function') {
    window.onload = func;
  } else {
    window.onload = function() {
      oldonload();
      func();
    }
  }
}
 
function prepareInputsForVallidation(input, functionName) {
          var parent;
          var fieldSetElement;
          var fontElement;
          var spanElements ;
          var divElement;

          parent = input.parentNode;

          divElement = document.createElement("div");

         

        
         
          fontElement  = parent.getElementsByTagName("font") ;
          
           if(fontElement.length > 0)
               parent.removeChild(fontElement[0]  );


 

          fieldSetElement = document.createElement("fieldSet");
          fieldSetElement.setAttribute ("style", "display:block; z-index=11;");
          fieldSetElement.setAttribute ("class", "welldone");
    //      fieldSetElement.setAttribute ("className", "weldone");


         
          parent.removeChild(input);
          fieldSetElement.appendChild(input);
          fieldSetElement.appendChild(divElement);
          parent.appendChild(fieldSetElement);
          
          input.setAttribute ('onKeyUp', functionName);
          input.onkeyup = functionName;

          input.onblur = function () {
                functionName();
          }

         spanElements =   parent.getElementsByTagName("span") ;
         if(spanElements.length > 0)
          {
               var errorElement;
               errorElement = spanElements[0];
               parent.removeChild(errorElement );
              
              errorElement.setAttribute ("class", "error"); 
              divElement.appendChild(errorElement );
              

          }
}

 
function prepareInputsForHints(input, functionName, text) {
          var parent;
          var fieldSetElement;
          var fontElement;
          var divElement;
          var spanElements ;

          parent = input.parentNode;

          fontElement  = parent.getElementsByTagName("font") ;
          if(fontElement.length > 0)
          {
               parent.removeChild(fontElement[0]  );
          }
 

          fieldSetElement = document.createElement("fieldSet");
          fieldSetElement.setAttribute ("style", "display:block; z-index=11;");
          fieldSetElement.setAttribute ("class", "weldone");
         // fieldSetElement.setAttribute ("className", "weldone");

          divElement = document.createElement("div");

spanElements =   parent.getElementsByTagName("span") ;

          if(spanElements.length > 0)
          {
               //fieldSetElement.setAttribute ("class", "error");

              var errorElement;
               errorElement = spanElements[0];
               parent.removeChild(errorElement );
               
           //   var spanElement2 = document.createElement("span");
           ////   spanElement2 .setAttribute ("class", "error");
           //   spanElement2 .setAttribute ("className", "hint");
           //   spanElement2 .setAttribute ("style", "display:none; z-index=20;");
              
               errorElement.setAttribute ("class", "error"); 

              divElement.appendChild(errorElement );
           //   
           //    spanElement2 .appendChild(errorElement );
            //   spanElement2 .style.display = "inline";

          }


          parent.removeChild(input);
          fieldSetElement.appendChild(input);
          fieldSetElement.appendChild(divElement);

          parent.appendChild(fieldSetElement);

          
          input.setAttribute ('onKeyUp', functionName);
 
          input.onkeyup = functionName;

          var spanElement = document.createElement("span");
          spanElement.setAttribute ("class", "hint");
          spanElement.setAttribute ("className", "hint");
          spanElement.setAttribute ("style", "display:none; z-index=20;");

          
      

          divElement.appendChild(spanElement);
 

         

             spanElement.innerHTML = text  ;
            input.onfocus = function () {
              spanElement.style.display = "inline";
  
          }
            input.onblur = function () {
               functionName();
               spanElement.style.display = "none";  
               
           } 
        
}
