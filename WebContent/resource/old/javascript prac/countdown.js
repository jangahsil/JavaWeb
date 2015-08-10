
var timerID = null;

function init() {
	var btnCountdown = document.getElementById("btn-countdown");
	btnCountdown.onclick = btnCountdownClick;
}

function count() {

	var lblCount = document.getElementById("lbl-count");	
	var count = parseInt(lblCount.innerText);

	if(count > 0){
		lblCount.innerText = --count;
	}
	else{
		clearInterval(timerID);
		timerID = null;
		alert("timer님 가심")
	}
}

function btnCountdownClick() {	
	// count();
	// 1초 후에 호출 : 1000ms
	//setTimeout(count, 1000);
	if(timerID == null){
		timerID = setInterval(count, 1000); // 1초마다 반복
	}
}
window.onload = init; 