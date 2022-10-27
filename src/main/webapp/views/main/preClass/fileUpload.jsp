<%--
  Created by IntelliJ IDEA.
  User: DB400TDA
  Date: 2022-10-14
  Time: 오후 3:39
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>fileUpload</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <title>AI Admin</title>
    <script type="text/javascript" src="//code.jquery.com/jquery-1.11.3.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.16.9/xlsx.full.min.js"></script>


    <style>
        .dragAndDropDiv {
            border: 2px dashed #92AAB0;
            width: 650px;
            height: 200px;
            color: #92AAB0;
            text-align: center;
            vertical-align: middle;
            padding: 10px 0px 10px 10px;
            font-size:200%;
            display: table-cell;
        }
        .progressBar {
            width: 200px;
            height: 22px;
            border: 1px solid #ddd;
            border-radius: 5px;
            overflow: hidden;
            display:inline-block;
            margin:0px 10px 5px 5px;
            vertical-align:top;
        }

        .progressBar div {
            height: 100%;
            color: #fff;
            text-align: right;
            line-height: 22px; /* same as #progressBar height if we want text middle aligned */
            width: 0;
            background-color: #0ba1b5; border-radius: 3px;
        }
        .statusbar{
            border-top:1px solid #A9CCD1;
            min-height:25px;
            width:99%;
            padding:10px 10px 0px 10px;
            vertical-align:top;
        }
        .statusbar:nth-child(odd){
            background:#EBEFF0;
        }
        .filename{
            display:inline-block;
            vertical-align:top;
            width:250px;
        }
        .filesize{
            display:inline-block;
            vertical-align:top;
            color:#30693D;
            width:100px;
            margin-left:10px;
            margin-right:5px;
        }
        .abort{
            background-color:#A8352F;
            -moz-border-radius:4px;
            -webkit-border-radius:4px;
            border-radius:4px;display:inline-block;
            color:#fff;
            font-family:arial;font-size:13px;font-weight:normal;
            padding:4px 15px;
            cursor:pointer;
            vertical-align:top
        }
    </style>
</head>
<body>

1. file Upload (drag&drop)<br>
<!--
<form id='upload' name='upload' enctype='multipart/form-data' action="">
    <input type="button" id="writeBtn" onclick="writetest()" value="글작성">
</form>
-->
<!--
<div id="hakwon_img" class="dragAndDropDiv">Drag & Drop Files Here or Browse Files</div>
<input type="file" name="hakwon_img_Upload" id="hakwon_img_Upload" style="display:none;" multiple/>

<div id='status1'>
-->
<br><br><br>
<div id="holder" style="border: 10px dashed #eee">
2. 업로드 리스트<br/><br/><br/><br/><br/><br/><br/><br/><br/>

</div>

<!--<form action="/main/fileUpload"  method="post"  enctype="multipart/form-data">-->
<form name='fupload' id='fupload' method="post"  enctype="multipart/form-data" >
    name: <input type="text" name="name" id="name" value="test" /></br>
    <!--fileDragData: <input type="file"  name="files" id="fileDragData" multiple></br>-->
    file: <input type="file"  name="file" id="file"></br>
    <!--<input type="submit" value="Submit">-->
    <input type="button" value="submit" onclick="fnSubmit()">
    </br>
</form>
</div>
</body>

<script>
    console.log("fileUpload123456789");

    function writetest(){
        //alert('write');
    }

    $(document).ready(function(){
        var objDragAndDrop = $(".dragAndDropDiv");

        $(document).on("dragenter",".dragAndDropDiv",function(e){
            e.stopPropagation();
            e.preventDefault();
            $(this).css('border', '2px solid #0B85A1');
        });
        $(document).on("dragover",".dragAndDropDiv",function(e){
            e.stopPropagation();
            e.preventDefault();
        });
        $(document).on("drop",".dragAndDropDiv",function(e){

            $(this).css('border', '2px dotted #0B85A1');
            e.preventDefault();
            var files = e.originalEvent.dataTransfer.files;

            handleFileUpload(files,objDragAndDrop);
        });

        $(document).on('dragenter', function (e){
            e.stopPropagation();
            e.preventDefault();
        });
        $(document).on('dragover', function (e){
            e.stopPropagation();
            e.preventDefault();
            objDragAndDrop.css('border', '2px dotted #0B85A1');
        });
        $(document).on('drop', function (e){
            e.stopPropagation();
            e.preventDefault();
        });
        //drag 영역 클릭시 파일 선택창
        objDragAndDrop.on('click',function (e){
            $('input[type=file]').trigger('click');
        });

        $('input[type=file]').on('change', function(e) {
            var files = e.originalEvent.target.files;
            handleFileUpload(files,objDragAndDrop);
        });

        function handleFileUpload(files,obj)
        {
            for (var i = 0; i < files.length; i++)
            {
                var fd = new FormData();
                fd.append('file', files[i]);

                var status = new createStatusbar(obj); //Using this we can set progress.
                status.setFileNameSize(files[i].name,files[i].size);
                sendFileToServer(fd,status);

            }
        }

        var rowCount=0;
        function createStatusbar(obj){

            rowCount++;
            var row="odd";
            if(rowCount %2 ==0) row ="even";
            this.statusbar = $("<div class='statusbar "+row+"'></div>");
            this.filename = $("<div class='filename'></div>").appendTo(this.statusbar);
            this.size = $("<div class='filesize'></div>").appendTo(this.statusbar);
            this.progressBar = $("<div class='progressBar'><div></div></div>").appendTo(this.statusbar);
            this.abort = $("<div class='abort'>중지</div>").appendTo(this.statusbar);

            obj.after(this.statusbar);

            this.setFileNameSize = function(name,size){
                var sizeStr="";
                var sizeKB = size/1024;
                if(parseInt(sizeKB) > 1024){
                    var sizeMB = sizeKB/1024;
                    sizeStr = sizeMB.toFixed(2)+" MB";
                }else{
                    sizeStr = sizeKB.toFixed(2)+" KB";
                }

                this.filename.html(name);
                this.size.html(sizeStr);
            }

            this.setProgress = function(progress){
                var progressBarWidth =progress*this.progressBar.width()/ 100;
                this.progressBar.find('div').animate({ width: progressBarWidth }, 10).html(progress + "% ");
                if(parseInt(progress) >= 100)
                {
                    this.abort.hide();
                }
            }

            this.setAbort = function(jqxhr){
                var sb = this.statusbar;
                this.abort.click(function()
                {
                    jqxhr.abort();
                    sb.hide();
                });
            }
        }

        function sendFileToServer(formData,status)
        {
            //var uploadURL = "/fileUpload/post"; //Upload URL
            var uploadURL = "/main/fileUpload"; //Upload URL
            var extraData ={}; //Extra Data.
            var jqXHR=$.ajax({
                xhr: function() {
                    var xhrobj = $.ajaxSettings.xhr();
                    if (xhrobj.upload) {
                        xhrobj.upload.addEventListener('progress', function(event) {
                            var percent = 0;
                            var position = event.loaded || event.position;
                            var total = event.total;
                            if (event.lengthComputable) {
                                percent = Math.ceil(position / total * 100);
                            }
                            //Set progress
                            status.setProgress(percent);
                        }, false);
                    }
                    return xhrobj;
                },
                url: uploadURL,
                type: "POST",
                contentType:false,
                processData: false,
                cache: false,
                data: formData,
                success: function(data){
                    status.setProgress(100);

                    $("#status1").append("File upload Done<br>");
                }
            })
                .fail(function(data, textStatus, errorThrown){
                    console.log("fail in get addr");
                    $("#status1").append("File upload Fail<br>");
                });
            ;

            status.setAbort(jqXHR);
        }

    });

    // new add
    var holder = document.getElementById("holder");

    function fileDragDrop(event)
    {
        event.stopPropagation();
        event.preventDefault();
        if (event.type == "dragover")
        {
            // Drag Over
            event.target.style.border = '10px dashed #c00';
        }
        else
        {
            // File Drop
            event.target.style.border = '10px dashed #eee';
        }
    }

    function fileUpload(e)
    {
        e.stopPropagation();
        e.preventDefault();
        fileDragDrop(e); // (e.type != "dragover") 캔슬을위해 [여기서 별도로 작업해줘도 상관없음]
        console.log('zz');

        document.getElementById('holder').innerHTML = "";
        var files = e.target.files || e.dataTransfer.files;

        for (var i = 0 ; i < files.length ; i++)
        {
            var file = files[i];

            document.getElementById('holder').innerHTML +=
                (
                    "업로드 리스트<br/></br>"
                    + (i+1) + ". "
                    + " *[FileName] : " + file.name
                    + " *[FileSize] : " + file.size
                    + " *[FileType] : " + file.type
                );

            reader = new FileReader();
            reader.onload = function(event) {
                document.getElementById('fileDragData').files = files;
            }

            reader.readAsDataURL(file);
        }
    }

    holder.addEventListener("dragover", fileDragDrop, false);
    holder.addEventListener("dragleave", fileDragDrop, false);
    holder.addEventListener("drop", fileUpload, false);

    // form submit
    function fnSubmit() {
        const name = document.getElementById("name").value;
        //const files =  document.forms['fupload']['files'].files[0];
        const file =  document.forms['fupload']['file'].files[0];
        console.log(name);
        //console.log(files);
        console.log(file);

        const fileData = {"name": name, "file": file, };
        $.ajax({
            type: "POST",
            //url: '/main/fileUpload',
            url: '/main/fileUpload',
            dataType : "json",
            data: JSON.stringify(fileData),
        }).done(function (datas){
            console.log('success');
        }).fail(function (error){
            console.log('fail');
        });

    }

</script>

</html>
