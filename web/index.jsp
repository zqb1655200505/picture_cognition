<%--
  Created by IntelliJ IDEA.
  User: huangfugui
  Date: 2017/4/27
  Time: 22:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    <title>接口测试</title>
  </head>
  <body>
  <form>
    用户名：<input type="text" id="username" />
    密码：<input type="text" id="password">
    用户类型：<input type="text" id="userType">
    <input type="button" value="提交测试" id="btn1" /><hr />
    昵称：<input type="text" id="nickname">
    <input type="button" value="提交测试" id="btn2" /><hr />
    <form id="uploadForm">
      <input type="text" id="uploadText">
      <input type="file" id="uploadFiles" multiple="multiple">
      <input type="button" value="提交测试" id="uploadBtn">
    </form><hr />
    性别：<input type="text" id="sexString" />
    <input type="button" value="提交测试" id="btn3" /><hr />
    旧密码：<input type="text" id="oldPassword" />
    新密码：<input type="text" id="newPassword">
    <input type="button" value="提交测试" id="btn4" /><hr />
    <input type="button" value="查看所有图片初始标签情况" id="btn5"><hr />
    图片id：<input type="text" id="tagPicId">
    标签id列表：<input type="text" id="tagLabelIdList">
    自定义输入：<input type="text" id="tagLabelInput">
    是否已自定义输入：<input type="text" id="tagInputFlag">
    <input type="button" value="选定标签提交" id="btn6"><hr />
    输入图片id：<input type="text" id="picId">
    <input type="button" value="查看打标签情况" id="btn7"><hr />
  </form>
  </body>

  <script src="jquery-2.0.0.min.js" type="text/javascript"></script>
  <script type="text/javascript" charset="UTF-8">
    $("#btn1").on('click', function () {
      var username = $("#username").val();
      var password = $("#password").val();
      var userType = $("#userType").val();
      $.ajax({
        url: "<%=basePath%>basic/login",
        type: "POST",
        data: {username: username, password: password, userType:userType},
        success: function (result) {
            console.log(result);
          alert(result);
        },
        error: function () {
          alert("System error!");
        }
      });
    });

    $("#btn2").on('click', function () {
      var nickname = $("#nickname").val();
      $.ajax({
        url: "<%=basePath%>basic/updateNickname",
        type: "POST",
        data: {nickname: nickname},
        success: function (result) {
          alert(result);
        },
        error: function () {
          alert("System error!");
        }
      });
    });

    $("#uploadBtn").on('click', function () {
      var formData = new FormData($("#uploadForm")[0]);
      formData.append("uploadText", $("#uploadText").val());
      for(var i=0;i<$('#uploadFiles')[0].files.length;i++){
        formData.append("uploadFiles",$('#uploadFiles')[0].files[i]);
      }
      $.ajax({
        url: "<%=basePath%>basic/upload",
        type: "POST",
        data: formData,
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        success: function (result) {
          alert(result);
        },
        error: function () {
          alert("System error!");
        }
      });
    });

    $("#btn3").on('click', function () {
      var sexString = $("#sexString").val();
      $.ajax({
        url: "<%=basePath%>basic/updateSex",
        type: "POST",
        data: {sexString:sexString},
        success: function (result) {
          alert(result);
        },
        error: function () {
          alert("System error!");
        }
      });
    });

    $("#btn4").on('click', function () {
      var oldPassword = $("#oldPassword").val();
      var newPassword = $("#newPassword").val();
      $.ajax({
        url: "<%=basePath%>basic/updatePassword",
        type: "POST",
        data: {oldPassword:oldPassword,newPassword:newPassword},
        success: function (result) {
          alert(result);
        },
        error: function () {
          alert("System error!");
        }
      });
    });

    $("#btn5").on('click', function () {
      $.ajax({
        url: "<%=basePath%>basic/allPicWaitForTag",
        type: "POST",
        data: {},
        success: function (result) {
          alert(result);
        },
        error: function () {
          alert("System error!");
        }
      });
    });

    $("#btn6").on('click', function () {
      var picId = $("#tagPicId").val();
      var labelIdList = $("#tagLabelIdList").val();
      var labelInput = $("#tagLabelInput").val();
      var inputFlag = $("#tagInputFlag").val();
      $.ajax({
        url: "<%=basePath%>basic/doTag",
        type: "POST",
        data: {picId:picId,labelIdList:labelIdList,labelInput:labelInput,inputFlag:inputFlag},
        success: function (result) {
          alert(result);
        },
        error: function () {
          alert("System error!");
        }
      });
    });
    $("#btn7").on('click', function () {
      var picId = $("#picId").val();
      $.ajax({
        url: "<%=basePath%>basic/showTagOfPic",
        type: "POST",
        data: {picId:picId},
        success: function (result) {
          alert(result);
        },
        error: function () {
          alert("System error!");
        }
      });
    });
  </script>
</html>