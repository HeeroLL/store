// 从本地存储中取数据
export function getItem(key) {
	return localStorage.getItem(key);
}

// 往本地存储中放数据
export function setItem(key, value) {
	return localStorage.setItem(key, value);
}

export function removeItem(key) {
	return localStorage.removeItem(key);
}