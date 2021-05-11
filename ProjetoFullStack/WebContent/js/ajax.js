desafioFullStack = new Object();
desafioFullStack.ajax = new Object();
function ajaxRequestDefault(){
	var def = {
			url: null,
			dataType: 'json',
			contentType: 'application/json; charset=UTF-8',
			type:'POST',
			success: function(){},
			error: function(err){
				alert("error = " + err.responseText);
			}
	};
	return def;
}
function verifyObjectData(cfg){
	if(cfg.data){
		if(isObject(cfg.data)){
			cfg.data = JSON.stringify(cfg.data);
		}
	}
	return cfg;
}
function isObject(o){
	return $.isArray(o) | $.isPlainObject(o) | $.isFunction(o);
};

desafioFullStack.ajax.post = function(cfg){
	var def = new ajaxRequestDefault();
	cfg = verifyObjectData(cfg);
	var config = $.extend(def,cfg);
	$.ajax(config);
};

desafioFullStack.ajax.get = function(cfg){
	var def = new ajaxRequestDefault();
	cfg.type = "GET";
	cfg = verifyObjectData(cfg);
	var config = $.extend(def, cfg);
	$.ajax(config);
};