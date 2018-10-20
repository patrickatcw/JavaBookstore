/**
 *
 */

$(document).ready(function(){
	$(".basketItemQty").on('change', function(){
		var id=this.id;
        console.log("test for changing qty, called");
		$('#update-item-'+id).css('display', 'inline-block');
	});
});


/*this file and code allows for user to change qty associated with qty in
shopping basket, lines 102 - 110 in shoppingBasket*/

//and don't forget we need to add a scripts reference in our header section
