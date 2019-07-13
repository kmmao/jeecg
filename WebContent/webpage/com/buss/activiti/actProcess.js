$(function () {
	var from = $("#formobj");
    $('#fileupload').fileupload({
    	formData: function (form) {
    		console.log($("#formobj").serializeArray())
            return $("#formobj").serializeArray();
        },
        dataType: 'json',
        done: function (e, data) {
        	console.log(data);
            $.each(data.result, function (index, file) {
            	$('#filePath').val(file.filePath);

            	$("#fileName").val(file.fileName);

            }); 
        }
    });
});