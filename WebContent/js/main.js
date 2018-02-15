function park(){
	var carNum = document.getElementById("carNum").value;
	var carSize = document.getElementById("carSize").value;
	
	$.post("http://localhost:8080/park",
		    {
		        size: carSize,
		        carNum: carNum
		    },
		    function(data, status){
		        alert("Data: " + data + "\nStatus: " + status);
		        var len = data.length;
		        if(len>12){
		        	document.getElementById("park").innerHTML = data;
		        }else{
		        document.getElementById("park").innerHTML = "Your Parking Number is "+data;
		        }
		    });
}


function checkout(){
	
	var carNum = document.getElementById("carNum").value;
	var parkNum = document.getElementById("parkNum").value;
	
	$.post("http://localhost:8080/checkout",
		    {
		        parkNum: parkNum,
		        carNum: carNum
		    },
		    function(data, status){
		        alert("Data: " + data + "\nStatus: " + status);
		        var len = data.length;
		        if(len>12){
		        	document.getElementById("checkout").innerHTML = data;
		        }else{
		        document.getElementById("checkout").innerHTML = "Your Parking Amount is "+data;
		        }
		    });
	
}

	function blockSpecialChar(e){
    var k;
    document.all ? k = e.keyCode : k = e.which;
    return ((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 32 || (k >= 48 && k <= 57));
    }
	
	function isNumber(evt) {
	    evt = (evt) ? evt : window.event;
	    var charCode = (evt.which) ? evt.which : evt.keyCode;
	    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
	        return false;
	    }
	    return true;
	}