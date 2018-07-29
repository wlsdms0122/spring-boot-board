var getURL = function(target, page, category, option, search) {
	var url = target + "?";
	if (page != null)
		url += "page=" + page;
	if (category != null)
		url += "&category=" + category;
	if (option != null)
		url += "&option=" + option;
	if (search != null)
		url += "&search=" + search;

	return url;
}