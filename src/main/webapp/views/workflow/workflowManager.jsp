<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title></title>

<!-- Bootstrap -->
<link href="${pageContext.request.contextPath }/plugins/bootstarp3.3/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath }/plugins/DataTables-1.10.12/media/css/dataTables.bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/plugins/DataTables-1.10.12/media/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<h3 class="text-info">流程部署信息</h3>
				<table class="table table-bordered" id="processDeployment">
					<thead>
						<tr>
							<th>流程ID</th>
							<th>流程名称</th>
							<th>发布时间</th>
							<th>种类</th>
							<th>使用者</th>
						</tr>
					</thead>
					
				</table>
			</div>
		</div>
		<div class="text-info">
			<div class="span12">
				<h3>流程定义信息</h3>
				<table class="table table-bordered" id="processDefinition">
					<thead>
						<tr>
							<th>ID</th>
							<th>关键字</th>
							<th>种类</th>
							<th>流程图片</th>
							<th>流程定义</th>
							<th>版本号</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
		<div class="row-fluid">
		    <h3 class="text-info">部署流程</h3>
			<form role="form" class="form-horizontal"  enctype="multipart/form-data">
				<div class="form-group">
					<label class="col-sm-2 control-label" for="fileName">流程名称：</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" id="fileName" name="fileName"
							placeholder="请输入流程名称"></input>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label" for="deployFile">流程压缩包：</label>
					<div class="col-sm-2">
						<input type="file" id="deployFile" name="deployFile" style="display: inline-block;"></input>
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-2 col-sm-offset-2">
						<button type="submit" class="btn-info btn-lg">部署</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<script src="${pageContext.request.contextPath }/plugins/jquery/jquery1.12.3.min.js"></script>
	<script src="${pageContext.request.contextPath }/plugins/jquery/jquery.form.js"></script>
	<script src="${pageContext.request.contextPath }/plugins/bootstarp3.3/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath }/plugins/DataTables-1.10.12/media/js/dataTables.bootstrap.js"></script>
	<script src="${pageContext.request.contextPath }/plugins/DataTables-1.10.12/media/js/jquery.dataTables.js"></script>
	<script src="${pageContext.request.contextPath }/views/workflow/js/workflowManager.js"></script>
</body>
</html>
