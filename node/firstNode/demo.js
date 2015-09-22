function say(word) {
	console.log(word);
}

function say2(word) {
	console.log("This is say2:" + word);
}

function execute(somefunction, value) {
	somefunction(value);
}

execute(say, "hello");
execute(say2, "hello");

var fs = require('fs');
fs.readFile('demo.js', 'utf-8', function(err, data) {
	if (err) {
	console.error(err);
	} else {
	console.log(data);
	}
});
console.log('end.');