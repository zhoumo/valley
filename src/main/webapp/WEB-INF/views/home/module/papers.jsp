<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div>
	<ol class="breadcrumb">
		<li>
			<a href="#">首页</a>
		</li>
		<li class="active">我的笔试题</li>
	</ol>
	<div ng-repeat="paper in paperList">
		<div class="list-item">
			<div class="list-title">{{$index+1}}、{{paper.name}}</div>
			<div>
				题目总数： {{(paper.questions|getQuestions:0|size)+(paper.questions|getQuestions:1|size)+(paper.questions|getQuestions:2|size)}}
				<span class="space"></span>
				单选题： {{paper.questions|getQuestions:0|size}}
				<span class="space"></span>
				多选题： {{paper.questions|getQuestions:1|size}}
				<span class="space"></span>
				问答题： {{paper.questions|getQuestions:2|size}}
			</div>
			<div>最后更新时间：{{paper.updateTime|date:"yyyy-MM-dd hh:mm:ss"}}</div>
			<div>附加消息：</div>
			<div class="list-button">
				<button type="button" class="btn btn-primary" ng-click="startAnswer(paper.id)" ng-show="type=='mine'">开始答题</button>
				<button type="button" class="btn btn-primary" ng-click="continueAnswer(paper.id)" ng-show="type=='mine'">继续答题</button>
				<button type="button" class="btn btn-primary" ng-click="auditPaper(paper.id)" ng-show="type=='audit'">审核试卷</button>
				<button type="button" class="btn btn-primary" ng-click="editPaper(paper.id)" ng-show="type=='create'">编辑试卷</button>
				<button type="button" class="btn btn-primary" ng-click="submitPaper(paper.id)" ng-show="type=='create'">提交试卷</button>
				<button type="button" class="btn btn-default">给HR发消息</button>
			</div>
		</div>
	</div>
</div>