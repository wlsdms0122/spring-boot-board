function getURL(target, page, category, option, search) {
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

function _attachFiles() {
    var formData = new FormData();
    $.each($("#file")[0].files, function(index, value) {
        formData.append("files", value);
    });

    $.ajax({
        url: "file/attach.process",
        type: "post",
        enctype: "multipart/form-data",
        processData: false,
        contentType: false,
        cache: false,
        data: formData,
        success: function(data) {
            var files = "";
            $.each(data, function(index, value) {
                files += "<tr>\n";
                files += "<td class='p-1'>\n";
                files += "<input type='hidden' name='file_id' value='" + value.id + "'>\n"
                files += "<small>" + value.origin + "." + value.ext + "</small>\n"
                files += "</td>\n"
                files += "<td class='align-middle p-1'>\n";
                files += "<button type='button' class='close pb-1 pr-2' data-id='" + value.id + "' onclick='_detachFile(this)'>\n";
                files += "<span aria-hidden='true'>&times;</span>\n";
                files += "</button>\n";
                files += "</td>\n";
            });

            $("#files").append(files);
        }
    });

    $("#file")[0].value = "";
}

function _detachFiles() {
    var data = $("form").serializeArray();

    $.ajax({
        url: "file/detach.process",
        type: "post",
        data: data,
        success: function(data) {
            console.log(data);
        }
    });
}

function _detachFile(btn) {
    $.ajax({
        url: "file/detach.process",
        type: "post",
        data: { file_id: $(btn).data("id") },
        success: function(data) {
            $(btn).parent().parent().remove();
        }
    });
}

Date.prototype.parse = function(dateString) {
    var date = dateString.split(' ');
    return new Date(date[0] + "T" + date[1]);
}