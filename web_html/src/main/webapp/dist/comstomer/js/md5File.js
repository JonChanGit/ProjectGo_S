/**
 * md5º”√‹π§æﬂ
 */

function MD_kl(inStr){
	var charArray = inStr.split(""); 
	var value = 9;
	var index = 9099;
	for(var i = 0; i < charArray.length;i++){
		charArray[i] =String.fromCharCode( charArray[i].charCodeAt(0) ^ 'g'.charCodeAt(0));
	}
	return charArray.join("");
	
}