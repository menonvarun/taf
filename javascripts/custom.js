$(document).ready(function(){
	
	$('a.hover').each(function(){
		var url = $(this).attr('url-value');
		var selector = $(this).attr('child-selector');
		$(selector).load(url);
		$(selector).hide();
		$(this).qtip({
			content:{
				text: $(selector)
			},
			position: {
					at: 'center center',
					my: 'center center'
			},
			show: {
				solo:true,
				modal: { // Requires Modal plugin
					
					escape: true // Hide tooltip when Esc pressed
				}
			},
			hide:{
				event: false,
				
			},
			style: {
			classes: "myCustomClass",
				width: "auto"
				
			}
			});

	});
});