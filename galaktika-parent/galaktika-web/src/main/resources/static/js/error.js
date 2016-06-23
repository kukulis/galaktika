var removeThisMessageBlock = function(event) {
	console.log("removeThisMessageBlock called");

	event.preventDefault();
	var me = event.target;

	// get parent parent. Then remove parent from parent parent.
	var parent = me.parentElement;
	var parentParent = parent.parentElement;
	parentParent.removeChild(parent);

	// me.appendChild ( document.createTextNode("additional text"));
}
