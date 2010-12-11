function getElementsByClassAndName(elementName, className)
{
    var classElements = [];
    var elements= document.getElementsByTagName(elementName);
    for(i = 0; i < elements.length; i++) {
          if(elements[i].className == className) {
                 classElements.push(elements[i]);
          }
     }
     return classElements ;
}

function getElementsByElementNameAndName(elementName, name)
{
    var classElements = [];
    var elements= document.getElementsByTagName(elementName);
    for(i = 0; i < elements.length; i++) {
          if(elements[i]. name == name) {
                 classElements.push(elements[i]);
          }
     }
     return classElements ;
}

function getFormElementByName(form, name)
{
   for(i=0; i< form.elements.length; i++)
   {
      if(form.elements[i].name.indexOf(name) != -1)
      {
         return form.elements[i];
      }
   }
}

function removeEmptyMagicThumb()
{

     var inputElements = getElementsByClassAndName("a", "Thumb MagicThumb");

     for(i = 0; i < inputElements.length; i++) {
           var inputElement = inputElements [i];
           if(inputElement.firstChild.src == null)
           {
                inputElement.parentNode.removeChild(inputElement); 
           }
     }
}

function getStock(form, option)
{
   
   var stockQuantityElement;
   
   if(option == null)
   {
      stockQuantityElement = document.getElementById("stock");
   }
   else
   {
      stockQuantityElement = document.getElementById("stock_" + option);
   }

   if(stockQuantityElement == null)
      return -1;
   else
      return stockQuantityElement.value;
}

function getItemCode(form, option)
{
   var itemCodeElement;
   
   if(option == null)
   {
      itemCodeElement = document.getElementById("itemCode");
   }
   else
   {
      itemCodeElement = document.getElementById("itemCode_" + option);
   }

   return itemCodeElement.value;
}


function checkProductForm ( form)
{
   var optionElement = getFormElementByName(form, "option0");
   var option = null;
   if(optionElement != null)
      option = optionElement.value;

   var stockQuantity = getStock(form, option );

   var itemCode =  getItemCode(form, option );

   if(stockQuantity == -1)
       return true;

   var formQuantity = getFormElementByName(form, "amount").value;
   var cartQuantity = 0;
   var cartQuantityElement = document.getElementById( "cart_quantity_" + itemCode );

   if(cartQuantityElement  == null)
       cartQuantity = 0;
   else
       cartQuantity  = cartQuantityElement.value; 

   var totalQuantity = parseInt (formQuantity ) + parseInt (cartQuantity );

   if( totalQuantity  > parseInt    (stockQuantity  ))
   {
       alert("U probeert " + totalQuantity  + " stuk(s) van dit product te bestellen en er zijn van dit product maar " + stockQuantity   + " stuk(s) in voorraad.");
       return false ;
   }
   return true ;
}

function checkCartForm ( form)
{
   var optionElement = getFormElementByName(form, "option0");
   var option = null;
   if(optionElement != null)
      option = optionElement.value;

   var stockQuantity = getStock(form, option );

   var itemCode =  getItemCode(form, option );

   if(stockQuantity == -1)
       return true;

   var formQuantity = getFormElementByName(form, "amount").value;
   var cartQuantity = 0;
   var cartQuantityElement = document.getElementById( "cart_quantity_" + itemCode );

   if(cartQuantityElement  == null)
       cartQuantity = 0;
   else
       cartQuantity  = cartQuantityElement.value; 

   var totalQuantity = parseInt (formQuantity ) + parseInt (cartQuantity );

   if( totalQuantity  > parseInt    (stockQuantity  ))
   {
       alert("U probeerd " + totalQuantity  + " stuks van dit product te bestellen en er zijn van dit product maar " + stockQuantity   + " stuks in voorraad.");
       return false ;
   }
   return true ;
}

function addHover(inputElementName,  srcNormal, srcHover)
{
     var inputElements = getElementsByClassAndName('input', inputElementName);

     for(i = 0; i < inputElements.length; i++) {
           var inputElement = inputElements [i];

           var id = inputElementName + i;
           inputElement.onmouseover = function() {
                setImage(this, srcHover);
           };
           inputElement.onmouseout = function() {
                setImage(this, srcNormal);
           };
           inputElement.id = id;
           inputElement.setAttribute('src', srcNormal);
    }
}



function setImage(inputElement,   src) { 
       //var inputElement = document.getElementById(id);
       inputElement.setAttribute('src', src);
}

function addHover2(inputElementName, srcNormal, srcHover)
{
     var inputElements = getElementsByClassAndName('input', inputElementName);

     for(i = 0; i < inputElements.length; i++) {
     var inputElement = inputElements [i];
     
     var aElement = document.createElement("a");
     aElement.setAttribute ('href', '#');
     aElement.setAttribute ('nowrap', 'nowrap');
     

    var divElement = document.createElement("div");
    divElement.setAttribute('class', 'button');
    
aElement.setAttribute('onMouseOut', 'MM_swapImgRestore()');
    aElement.setAttribute('onMouseOver', "MM_swapImage('" + inputElementName + "_a' ,'','" + srcHover + "',1)");



    aElement .setAttribute('onClick', 'commitFormByInput(this,"' +       inputElement.name + '", "' + inputElement.value + '")');



     divElement.appendChild(aElement);

     var imgElement = createNamedElement('img', inputElementName  + '_a' );
     imgElement .setAttribute ('src', srcNormal);

     aElement.appendChild(imgElement );

     var parentElement = inputElement.parentNode; 
     parentElement.appendChild(divElement);
     
     parentElement.removeChild(inputElement);


     
     }

}

function addHover3(inputElementName, text, srcNormal)
{
     var inputElements = getElementsByClassAndName('input', inputElementName);

     for(i = 0; i < inputElements.length; i++) {
     var inputElement = inputElements [i];
     
     var aElement = document.createElement("a");
     aElement.setAttribute ('href', '#');
     aElement.setAttribute ('nowrap', 'nowrap');
     aElement.innerText = text;
     aElement.textContent  = text;
 
    var divElement = document.createElement("div");
    divElement.setAttribute('class', 'button');
    aElement .setAttribute('onClick', 'commitFormByInput(this,"' +  inputElement.name + '", "' + inputElement.value + '")');

     divElement.appendChild(aElement);

     var parentElement = inputElement.parentNode; 
     parentElement.appendChild(divElement);
     
     parentElement.removeChild(inputElement);
     }

}

function commitFormByInput( element, name, value)
{
var inputX =  createNamedElement('input', name + '.x' );
inputX .setAttribute('value', value);
inputX .setAttribute('type', 'hidden');
element.parentNode.appendChild(inputX );

var inputY =  createNamedElement('input', name + '.y' );
inputY .setAttribute('value', value);
inputY .setAttribute('type', 'hidden');
element.parentNode.appendChild(inputY);

inputY.form.submit();

}

function createNamedElement(type, name) {
   var element = null;
   // Try the IE way; this fails on standards-compliant browsers
   try {
      element = document.createElement('<'+type+' name="'+name+'">');
   } catch (e) {
   }
   if (!element) {
      // Non-IE browser; use canonical method to create named element
      element = document.createElement(type);
      element.name = name;
   }
   return element;
}

function addLoadEvent(func) { 
  var oldonload = window.onload; 
  if (typeof window.onload != 'function') { 
    window.onload = func; 
  } else { 
    window.onload = function() { 
             func();
      if (oldonload) { 
        oldonload(); 
      } 


    } 
  } 
} 

function checkProductFormOption ( form, optionElement)
{
   var option = null;
   if(optionElement != null)
      option = optionElement.value;
 
   var stockQuantity = getStock(form, option );
 
   var itemCode =  getItemCode(form, option );
 
   if(stockQuantity == -1)
       return true;
 
   var formQuantity = getFormElementByName(form, "amount").value;
   var cartQuantity = 0;
   var cartQuantityElement = document.getElementById( "cart_quantity_" + itemCode );
 
   if(cartQuantityElement  == null)
       cartQuantity = 0;
   else
       cartQuantity  = cartQuantityElement.value; 
 
   var totalQuantity = parseInt (formQuantity ) + parseInt (cartQuantity );
 
   if( totalQuantity  > parseInt    (stockQuantity  ))
   {
       return false ;
   }
   return true ;
}
