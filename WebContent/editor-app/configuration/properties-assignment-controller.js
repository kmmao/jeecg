/*
 * Activiti Modeler component part of the Activiti project
 * Copyright 2005-2014 Alfresco Software, Ltd. All rights reserved.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.

 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

/*
 * Assignment
 */
var KisBpmAssignmentCtrl = [ '$scope', '$modal', function($scope, $modal) {

    // Config for the modal window
    var opts = {
        template:  'editor-app/configuration/properties/assignment-popup.html?version=' + Date.now(),
        scope: $scope
    };
    // Open the dialog
    $modal(opts);
}];

var KisBpmAssignmentPopupCtrl = [ '$scope', function($scope) {
    // Put json representing assignment on scope
	if ($scope.property.value !== undefined && $scope.property.value !== null
        && $scope.property.value.assignment !== undefined
        && $scope.property.value.assignment !== null)
	{
        $scope.assignment = $scope.property.value.assignment;
    } else {
        $scope.assignment = {};
    }

    if ($scope.assignment.candidateUsers == undefined || $scope.assignment.candidateUsers.length == 0)
    {
    	$scope.assignment.candidateUsers = [{value: ''}];
    }else{
    	var usersArr = $scope.property.value.assignment.candidateUsers;
    	var values ="";
    	for(var i=0;i<usersArr.length;i++){
    		values += usersArr[i].value;
			if(i<usersArr.length-1){
				values += ",";
			}
    	}
    	$scope.assignment.candidateUsers.value= values;
    }
    
    // Click handler for + button after enum value
    var userValueIndex = 1;
    $scope.addCandidateUserValue = function(index) {
        $scope.assignment.candidateUsers.splice(index + 1, 0, {value: 'value ' + userValueIndex++});
    };
    // Click handler for - button after enum value
    $scope.removeCandidateUserValue = function(index) {
        $scope.assignment.candidateUsers.splice(index, 1);
    };
    
    if ($scope.assignment.candidateGroups == undefined || $scope.assignment.candidateGroups.length == 0)
    {
    	$scope.assignment.candidateGroups = [{value: ''}];
    }else{
    	var rolesArr = $scope.property.value.assignment.candidateGroups;
    	var values ="";
    	for(var i=0;i<rolesArr.length;i++){
    		values += rolesArr[i].value;
			if(i<rolesArr.length-1){
				values += ",";
			}
    	}
    	$scope.assignment.candidateGroups.value =  values;
    }
    
    
    var groupValueIndex = 1;
    $scope.addCandidateGroupValue = function(index) {
        $scope.assignment.candidateGroups.splice(index + 1, 0, {value: 'value ' + groupValueIndex++});
    };

    // Click handler for - button after enum value
    $scope.removeCandidateGroupValue = function(index) {
        $scope.assignment.candidateGroups.splice(index, 1);
    };

    $scope.save = function() {
        $scope.property.value = {};
    	//1.代理人
    	var assignees =document.getElementById("assigneeField").value;
    	//赋值
		$scope.assignment.assignee =assignees;
		$scope.assignment.candidateGroups.splice(0,$scope.assignment.candidateGroups.length);//清空数组 
		$scope.assignment.candidateUsers.splice(0,$scope.assignment.candidateUsers.length);//清空数组
		//2.候选人
		var users = document.getElementById("userField").innerHTML;
		if(users && users.length>0){
			var userArr = users.split(',');
    		$scope.assignment.candidateUsers.splice(0,$scope.assignment.candidateUsers.length);//清空数组 
    		for(var row=0;row<userArr.length;row++){
				var temp = {};
				temp.$$hashKey = row;
				temp.value = userArr[row];
				$scope.assignment.candidateUsers.push(temp);
    		} 
    	}
		//候选组
		var roles =document.getElementById("groupField").innerHTML;
		if(roles && roles.length>0){
			var roleArr = roles.split(',');
			$scope.assignment.candidateGroups.splice(0,$scope.assignment.candidateGroups.length);//清空数组 
    		for(var row=0;row<roleArr.length;row++){
    			var temp = {};
    			temp.$$hashKey = row;
    			temp.value = roleArr[row];
    			$scope.assignment.candidateGroups.push(temp);
    		} 
    	}
        handleAssignmentInput($scope);
        $scope.property.value.assignment = $scope.assignment;
        $scope.updatePropertyInModel($scope.property);
        $scope.close();
    };
    //人员选择器-单选
    $scope.openUserSelect=function (){
	    if(window.showModalDialog == undefined){
	        window.showModalDialog = function(url,mixedVar,features){
	            window.hasOpenWindow = true;
	            if(mixedVar) var mixedVar = mixedVar;
	            if(features) var features = features.replace(/(dialog)|(px)/ig,"").replace(/;/g,',').replace(/\:/g,"=");
	            window.myNewWindow = window.open(url,"_blank",features);
	        }
	    }
    	//打开选择人员界面
    	var rowsData = window.showModalDialog("userController.do?userSelectForActiviti",null, "dialogWidth:550px;dialogHeight:450px;help:no;status:no"); 
    	//获取数据
    	var codes = "";
    	if(rowsData!=null&&rowsData.length>0){
    		for(var row=0;row<rowsData.length;row++){
    			codes += rowsData[row].userName;
    			if(row<rowsData.length-1){
    				codes += ",";
    			}
    		} 
    		//赋值
    		$scope.assignment.assignee =codes;
    		$scope.assignment.candidateGroups.splice(0,$scope.assignment.candidateGroups.length);//清空数组 
    		$scope.assignment.candidateUsers.splice(0,$scope.assignment.candidateUsers.length);//清空数组 
    	}
    }
    //人员选择器-多选
    $scope.openUserSelectMutl = function(){
    	if(window.showModalDialog == undefined){
	        //alert("初始化的时候,判断window.showModalDialog是否存在")
	        window.showModalDialog = function(url,mixedVar,features){
	            window.hasOpenWindow = true;
	            if(mixedVar) var mixedVar = mixedVar;
	            if(features) var features = features.replace(/(dialog)|(px)/ig,"").replace(/;/g,',').replace(/\:/g,"=");
	            window.myNewWindow = window.open(url,"_blank",features);
	        }
	    }
    	//打开选择人员界面
    	var rowsData = window.showModalDialog("userController.do?userMutlSelectForActiviti",null,"dialogWidth:550px;dialogHeight:450px;help:no;status:no"); 
    	//获取数据
    	var codes = "";
    	if(rowsData!=null&&rowsData.length>0){
    		$scope.assignment.candidateUsers.splice(0,$scope.assignment.candidateUsers.length);//清空数组 
    		for(var row=0;row<rowsData.length;row++){
    			codes += rowsData[row].userName;
    			if(row<rowsData.length-1){
    				codes += ",";
    			}
				var temp = {};
				temp.$$hashKey = row;
				temp.value = rowsData[row].userName;
				$scope.assignment.candidateUsers.push(temp);
    		} 
			//赋值
			document.getElementById("userField").value=codes; 
    	} 
		
    }
    //角色选择器-多选
    $scope.openRoleSelect = function(){
    	//打开选择角色界面
    	if(window.showModalDialog == undefined){
	        //alert("初始化的时候,判断window.showModalDialog是否存在")
	        window.showModalDialog = function(url,mixedVar,features){
	            window.hasOpenWindow = true;
	            if(mixedVar) var mixedVar = mixedVar;
	            if(features) var features = features.replace(/(dialog)|(px)/ig,"").replace(/;/g,',').replace(/\:/g,"=");
	            window.myNewWindow = window.open(url,"_blank",features);
	        }
	    }
    	var rowsData = window.showModalDialog("roleController.do?roleForActiviti",null,"dialogWidth:550px;dialogHeight:450px;help:no;status:no"); 
    	//获取数据
    	var codes="";	
    	if(rowsData!=null&&rowsData.length>0){
    		$scope.assignment.candidateGroups.splice(0,$scope.assignment.candidateGroups.length);//清空数组 
    		for(var row=0;row<rowsData.length;row++){
    			codes += rowsData[row].roleCode;
    			if(row<rowsData.length-1){
    				codes += ",";
    			}
    			var temp = {};
    			temp.$$hashKey = row;
    			temp.value = rowsData[row].roleCode;
    			$scope.assignment.candidateGroups.push(temp);
    		} 
    		//赋值
    		document.getElementById("groupField").value=codes;
    	}
    }
    
    // Close button handler
    $scope.close = function() {
    	handleAssignmentInput($scope);
    	$scope.property.mode = 'read';
    	$scope.$hide();
    };
    
    var handleAssignmentInput = function($scope) {
    	if ($scope.assignment.candidateUsers){
	    	var emptyUsers = true;
	    	var toRemoveIndexes = [];
	        for (var i = 0; i < $scope.assignment.candidateUsers.length; i++)
	        {
	        	if ($scope.assignment.candidateUsers[i].value != '')
	        	{
	        		emptyUsers = false;
	        	}
	        	else
	        	{
	        		toRemoveIndexes[toRemoveIndexes.length] = i;
	        	}
	        }
	        
	        for (var i = 0; i < toRemoveIndexes.length; i++)
	        {
	        	$scope.assignment.candidateUsers.splice(toRemoveIndexes[i], 1);
	        }
	        
	        if (emptyUsers)
	        {
	        	$scope.assignment.candidateUsers = undefined;
	        }
    	}
        
    	if ($scope.assignment.candidateGroups)
    	{
	        var emptyGroups = true;
	        var toRemoveIndexes = [];
	        for (var i = 0; i < $scope.assignment.candidateGroups.length; i++)
	        {
	        	if ($scope.assignment.candidateGroups[i].value != '')
	        	{
	        		emptyGroups = false;
	        	}
	        	else
	        	{
	        		toRemoveIndexes[toRemoveIndexes.length] = i;
	        	}
	        }
	        
	        for (var i = 0; i < toRemoveIndexes.length; i++)
	        {
	        	$scope.assignment.candidateGroups.splice(toRemoveIndexes[i], 1);
	        }
	        
	        if (emptyGroups)
	        {
	        	$scope.assignment.candidateGroups = undefined;
	        }
    	}
    };
}];

  