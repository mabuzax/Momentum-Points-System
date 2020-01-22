<html>
<head>
<title>Momentum Shop</title>

<script src="webjars/jquery/3.2.1/jquery.min.js"></script>
<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
<script>
	
$(document).on("click", ".view-products button", function(){
	
	    var finalUrl = window.location.origin + "/momentum/api/v1/product/list"; 
	    $.ajax({
	        url: finalUrl,
	        cache: false,
	        success: function (data) {
	        	
	        	var html = "<table class='table'>" +
	        				"	<thead><tr>" +
	        				"		<th>Product Code</th>" +
	        				"		<th>Product Name</th>" +
	        				"		<th>Points Cost</th>" +
	        				"	</tr></thead><tbody>";
	        				
	        				var i=0;
	        				for(i in data) {
	        					html += "	<tr>" +
		        				"		<td>" + data[i].productCode + "</td>" +
		        				"		<td>" + data[i].productName + "</td>" +
		        				"		<td>" + data[i].pointsCost + "</td>" +
		        				"	</tr>";
	        				}
	        				
	        				
	        				html += "</tbody></table> ";
	        	
	        	
	        	$( "div.resprods" )
	        	  .html( html );
	        
	        }
	    });
	});
	
$(document).on("click", ".view-customers button", function(){
	
    var finalUrl = window.location.origin + "/momentum/api/v1/customer/list"; 
    $.ajax({
        url: finalUrl,
        cache: false,
        success: function (data) {
        	
        	var html = "<table class='table'>" +
						"	<thead><tr>" +
						"		<th>customer Id</th>" +
						"		<th>Customer Name</th>" +						
						"	</tr></thead><tbody>";
        				
        				var i=0;
        				for(i in data) {
        					html += "	<tr>" +
	        				"		<td>" + data[i].customerId + "</td>" +
	        				"		<td>" + data[i].customerName + "</td>" +	        				
	        				"	</tr>";
        				}        				
        				
        				html += "</tbody></table> ";
        	
        	
        	$( "div.rescust" )
        	  .html( html );
        
        }
    });
});

$(document).on("click", ".do-purchase button", function(){
	var finalUrl = window.location.origin + "/momentum/api/v1/purchase";
	
	if($( "#prodid1").val().trim().length>0 && $( "#custid").val().trim().length>0) {
		
		finalUrl += "?customerId=" + $("#custid").val().trim();
	    
	    var i=1;
	    finalUrl += "&product=" +  $( "#prodid" + i).val();
	    
	    for(i=2; i<4; i++) {
	    	if($( "#prodid" + i).val().trim().length>0) {
	    		finalUrl += "&product=" +  $( "#prodid" + i).val().trim();
	    	}
	    }
	    
	    $.ajax({
	        url: finalUrl,
	        type: 'POST',
	        cache: false,
	        success: function (data) {
	        	
	        	$( "div.purchaseresult" )
	        	  .html( data.message );        
	        }
	    });
	} else {
		$( "div.purchaseresult" )
  	  .html( "<p style='color:red; font-weight:600'>Please supply customer ID and atleast ONE product to purchase</span>" );  
	}
});

</script>

</head>

<body>

<div class="container">
	<center>
	<h3>Welcome to Momentum Shop API tester</h3><br><hr><br><br>
	
	<div class="view-products">
		View a list of products returned from an API call.<br><br>
		<button class="btn btn-primary">Show Products</button>	
		
		<div class='resprods'></div>
	</div>
	
	<br><hr><br><br>
	<div class="view-customers">
		View a list of Customers returned from an API call.<br><br>
		<button class="btn btn-primary">Show Customers</button>
			
		
		<div class='rescust'></div>
	</div>
	
	<br><hr><br><br>
	<div class="do-purchase">
		Purchase Product(s)<br><br>
		
		<table >
			<tr>
				<td><input id='custid' name='custid' placeholder='Enter CustomerId' required/></td>
				
			</tr>
			<tr>
				<td><input id='prodid1' name='prodid1' placeholder='Enter productId to purchase' required/></td>
			</tr>
			<tr>
				<td><input id='prodid2' name='prodid2' placeholder='Enter productId to purchase'/></td>
			</tr>
			<tr>
				<td><input id='prodid3' name='prodid3' placeholder='Enter productId to purchase'/></td>
			</tr>
			<tr>
				<td><input id='prodid4' name='prodid4' placeholder='Enter productId to purchase'/></td>
			</tr>
		
		</table>
		<br>
		<button class="btn btn-primary">Buy Now</button>
			
		
		<div class='purchaseresult'></div>
	</div>	
	
	
	</center>
	</div>
	
</body>
</html>