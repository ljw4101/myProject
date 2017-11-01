<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Pivot Demo</title>
        <link rel="stylesheet" type="text/css" href="./resources/dist/css/pivot.css">
        <script type="text/javascript" src="./resources/dist/js/jquery-1.8.3.min.js"></script>
        <script type="text/javascript" src="./resources/dist/js/jquery-ui-1.9.2.custom.min.js"></script>
        <script type="text/javascript" src="./resources/dist/js/pivot.js"></script>
    </head>
    <style>
        * {font-family: Verdana;}
    </style>
    <body>
        <script type="text/javascript">
            $(function(){
                        $("#output").pivotUI(
        [ 
            {color: "blue", shape: "circle"}, 
            {color: "red", shape: "triangle"}
        ], 
        { 
            rows: ["color"], 
            cols: ["shape"] 
        }
    );
             });
        </script>

        <p><a href="index.html">&laquo; back to examples</a></p>
        <div id="output" style="margin: 10px;"></div>

    </body>
</html>