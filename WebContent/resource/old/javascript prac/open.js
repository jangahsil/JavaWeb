var win = null; 

function btnNewTabClick() {
	win = open("open2.html");
	/*win = open("http://www.naver.com", "_blank");*/
}

function btnNewWinClick() {
	win = open("open2.html", "_blank", "width=500px, height=400px");
}

function btnCloseWinClick() {
	/*win.close();*/
	/*close();*/ // window.close();와 동일 
	window.close();
}

window.addEventListener("load", function(){
	var btnNewTab = document.getElementById("btn-new-tab");
	var btnNewWin = document.getElementById("btn-new-win");
	var btnCloseWin = document.getElementById("btn-close-win");

	btnNewTab.onclick = btnNewTabClick;
	btnNewWin.onclick = btnNewWinClick;
	btnCloseWin.onclick = btnCloseWinClick;
});