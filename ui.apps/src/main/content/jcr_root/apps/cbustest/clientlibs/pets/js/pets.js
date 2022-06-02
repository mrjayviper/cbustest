$( document ).ready(function() {
    fetchData();

    $("#search").click(function() {
        fetchData();
    });

    function fetchData() {
        console.log("inside fetchData");

        $("#target").empty();

        $.ajax({
            method: "GET"
            , url: "/bin/getpets"
            , data: { type : $("#type").val() }
        }).done(function (data){
            keys = Object.keys(data);
            values = Object.values(data);

            console.log(keys);
            console.log(values);

            html = "<hr>";

            for (i=0; i<keys.length; i++) {
                html += "<h3>" + keys[i] + "</h3>";

                for (j=0; j<values[i].length; j++ ) {
                    html += "<li>" + values[i][j]["name"] + "</li>";
                }

                html += "<hr>"
            }

            $("#target").append(html);
        }).fail(function () {
            $("#target").append("<h3>failed to retrieve data from API. Please try again<h3>");
        });
    }
});
