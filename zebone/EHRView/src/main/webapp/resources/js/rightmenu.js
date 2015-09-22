if(!document.all) document.captureEvents(Event.MOUSEDOWN); 
var _Tmenu = 0; 
var _Amenu = 0; 
var _Type = 'DIV,BODY,IMG,LI,A'; 
var _rightObj;
document.onclick = _Hidden; 
function _Hidden(){ 
	if(_Tmenu==0) return; 
	document.getElementById(_Tmenu).style.display='none'; 
	_Tmenu=0; 
} 
document.oncontextmenu = function (e){ 
	_Hidden(); 
	_rightObj = document.all ? event.srcElement : e.target; 
	//document.title=_rightObj.className;
	if(_Type.indexOf(_rightObj.tagName) == -1) return; 
	_Amenu = _rightObj.getAttribute('menu');//取出元素menu属性
	if(_Amenu == null||_Amenu == 'null') return; 
	if(document.all) e = event; 
	_ShowMenu(_Amenu, e); 
	return false; 
} 
function _ShowMenu(Eid, event){
	var _Menu = document.getElementById(Eid); 
	var _Left = event.clientX + document.body.scrollLeft; 
	var _Top = event.clientY + document.body.scrollTop; 
	_Menu.style.left = _Left.toString() + 'px'; 
	_Menu.style.top = _Top.toString() + 'px'; 
	_Menu.style.display = 'block'; 
	_Tmenu = Eid; 
} 