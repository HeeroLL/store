/***
 * author: charmyin
 * datetime: 2013-2-23 21:08
 * title: Extend more validate rules ~
 ***/

define(function () {
	return function loadCss(url) {
		var link = document.createElement('link');
		link.type = 'text/css';
		link.rel = 'stylesheet';
		link.href = url;
		document.getElementByTagName("head")[0].appendChild(link);
	};
});