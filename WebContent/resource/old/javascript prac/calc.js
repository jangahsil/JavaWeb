function init() {
	var btnSum = document.getElementById("btn-sum");
	btnSum.onclick = btnSumClick;
}

function btnSumClick() {
	var txtX = document.getElementById("txt-x");
	var txtY = document.getElementById("txt-y");
	var txtSum = document.getElementById("txt-sum");
	
	var sum = parseInt(txtX.value) + parseInt(txtY.value);
	/*txtSum.setAttribute("value", sum);*/

	txtSum.value = sum;
	}
window.onload = init; 