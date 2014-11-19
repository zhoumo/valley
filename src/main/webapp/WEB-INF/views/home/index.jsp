<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div style="height: 640px">
	<p>欢迎来到秀才谷个人中心</p>
	<panel title="我的笔试题" type="my-exam" color="#01caa0">
		<div class="panel-content"></div>
	</panel>
	<panel title="系统消息" type="system-message" color="#01caa0">
		<div class="panel-top-button">
			<a>全部清除</a>
		</div>
		<div class="panel-content"></div>
	</panel>
	<panel title="申请成为题库生产者，贡献你的智慧！" type="apply-producer" color="#00aeef">
		<div class="panel-content">
			<a href="#/apply-producer">现在申请</a>
		</div>
	</panel>
	<panel title="申请成为题库审核人，为题库把关！" type="apply-auditor" color="#00aeef">
		<div class="panel-content">
			<a href="#/apply-auditor">现在申请</a>
		</div>
	</panel>
	<panel title="我要出题" type="produce" color="#6f8ba9">
		<div class="panel-top-button">
			<a href="#/my-paper-list">我的题库</a>
		</div>
		<div class="panel-content">
			<select class="form-control" style="width: 200px">
				<option value="0" selected="selected">请选择出题职位</option>
			</select>
			<button class="btn btn-primary" type="button" onclick="location.href='#/create-paper'">现在去出题</button>
		</div>
	</panel>
	<panel title="我要审题" type="audit" color="#6f8ba9">
		<div class="panel-top-button">
			<a href="#/audited-paper-list">已审题库</a>
		</div>
	</panel>
	<panel title="账户管理" type="account" color="#15467a">
		<div class="panel-content">
			<a href="#/edit-user-info">修改个人资料</a>
		</div>
	</panel>
</div>