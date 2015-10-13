


function initStat(){
	
	$("#export").on('click', function(){
		//$("#exportValue").value = exportData()
		$("#exportValue").value = "fdger";
	});
	
}


function exportData(){
	var that = this;
	var csv = []
	var csvString = "";
	
	var table = $('.queryResult');
	$('.queryResult tr').each(function()
	{
		
	    $(this).find('td').each(function()
	    {
	    	
	    	csvString = csvString.concat($(this).text());
	    	csvString = csvString.concat('\t');
	    	
	    	
	       
	    })
	    csvString = csvString.concat('\n');
	    
	    console.log(csvString);
	})
		
	$("#exportValue").value = csvString;
	return csvString;
}

function upload(csv){
	

	     
	    $.ajax({
	       url : 'statistiques',
	       type : 'POST',
	       dataType : 'html', // On désire recevoir du HTML
	       success : function(code_html, statut){ // code_html contient le HTML renvoyé
	           
	       }
	    });
	   
	    

	
	
}




	

	
	
//	for (row in $('.queryResult'))
//	{
//		for(col in row){
//			csv.push(col.find('td, th').join(',').join('\n'));
//			console.log(row.find('td, th').join(',').join('\n'));
//		}
//	}
		


initStat();

