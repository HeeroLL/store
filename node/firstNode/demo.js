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