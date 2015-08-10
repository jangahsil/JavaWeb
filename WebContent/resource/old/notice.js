	function prtResult() {
		var x = prompt("x값을 입력하세요", 0);
		var y = prompt("y값을 입력하세요", 0);
		alert(x + y);
	}

	function init() {
		var btn1 = document.getElementById("searchbutton");
		btn1.onclick = prtResult;

	}
	/* window.onload = init; */
	/*window.addEventListener("load", init);*/
	window.onload = function() {
		alert("test1");
	} 
	window.onload = function() {
		alert("test2");
	} 
	window.onload = function() {
		alert("test3");
	} 
	
	window.addEventListener("load", function() {
		alert("test1");
	});
	window.addEventListener("load", function() {
		alert("test2");
	});
	window.addEventListener("load", function() {
		alert("test3");
	});
