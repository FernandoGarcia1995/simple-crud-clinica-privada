$( document ).ready(function() {
	$(".cod-mask").inputmask();
	$(".decimal-mask").inputmask('decimal',{min:1, max:10000});
});