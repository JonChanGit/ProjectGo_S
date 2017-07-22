export default(text="hello word123")=>{
	const div = document.createElement("div");
	div.innerHTML = text;
	return div;
}