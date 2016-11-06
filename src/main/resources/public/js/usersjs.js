angular.module('myAppuser',[])
    .controller('myUserController',['$scope','$http',
           function ($scope,$http) {


        $scope.finduser=function(){
            $scope.finduserform=true;
                       $scope.userform=false;
                   }
        $scope.adduser=function () {
            $scope.userform=true;
            $scope.finduserform=false;
        }
               $scope.updateAuthor2=function (w) {
                   $scope.updateuser2=true;
                   $scope.userrespf=false;
                   $scope.userObject=w;
               }
               $scope.cancel=function () {
                   $scope.userrespf=true;
                   $scope.updateuser2=false;

               }
           

               $scope.adduser2=function() {
                   $scope.formname = $scope.formname == undefined ? "" : $scope.formname;
                   $scope.formlastname = $scope.formlastname == undefined ? "" : $scope.formlastname;
                   $scope.formpassword = $scope.formpassword == undefined ? "" : $scope.formpassword;
                   
                   $scope.formconfirmpassword = $scope.formconfirmpassword == undefined ? "" : $scope.formconfirmpassword;
                   $scope.formusername = $scope.formusername == undefined ? "" : $scope.formusername;
                   if (($scope.formname == "") || ($scope.formlastname == "") || ($scope.formpassword == "") || ($scope.formconfirmpassword == "")||($scope.formusername=="")) {
                       alert("Enter all elements");
                       return false;
                   }
                   if($scope.formpassword!==$scope.formconfirmpassword){
                       alert("The passwords did not match")
                       return false;
                   }
                   $http.post('http://localhost:8082/api/user/new?name=' + $scope.formname + '&lastname=' + $scope.formlastname + '&password=' + $scope.formpassword + '&username=' + $scope.formusername + '&role=' + $scope.formrole).success(function (a) {

                       // alert(JSON.stringify($scope.a));
                       alert(a.event);
                           $scope.formname = undefined;
                           $scope.formlastname = undefined;
                           $scope.formpassword = undefined;
                           $scope.formconfirmpassword = undefined;
                           $scope.formusername = undefined;
                           $scope.formrole= undefined;
                       return false;
                       // }
                       // else {
                       //     alert("ERROR");
                       // }
                       // $scope.result=a;

                   })
               }

               $scope.finduser2=function () {
                   if (($scope.fformname == "")||($scope.fformname == undefined)){
                       alert("Enter the element");
                       return false;
                   }

                   $http.get('http://localhost:8082/api/user/findbyname?name='+$scope.fformname).
                   success(function (data) {
                       $scope.findeddata=data;
                       if ($scope.findeddata.length>0){
                           $scope.userrespf=true;
                       }else {
                           $scope.authorresp=false;
                           alert("The search does not throw results");
                           return false;
                       }
                   })

               }
               $scope.updateuser3=function(userObject)
               {if (($scope.uformpassword==undefined)||($scope.uformconfirmpassword==undefined)){
                   alert("Enter all elements");
                   return false;
               }
                   if($scope.uformpassword!==$scope.uformconfirmpassword){
                       alert("The passwords did not match");
                       return false;

                   }


                   $http.put('http://localhost:8082/api/user/update?newpassword='+$scope.uformpassword+'&username='+userObject.username).
                   success(function (a) {
                       alert(a.event);
                       // if (a==200){
                       //     $scope.authorresp=true;
                       //     $scope.updateauthor2=false;
                       $scope.userrespf=true;
                       $scope.updateuser2=false;
                          // alert("Author updated");

                       //     $scope.uformname=undefined;
                       //     $scope.uformlastname=undefined;
                       //     $scope.uformauthority=undefined;
                       //     $scope.uformnewuri=undefined;
                       //     return false;
                       // }
                       // else {
                       //     alert("ERROR");}
                       // $scope.result=a;
                       $scope.uformpassword=undefined;
                       $scope.uformconfirmpassword=undefined;

                   })
               }
               $scope.deleteAuthor3=function (recivedusername) {
                   $http.delete('http://localhost:8082/api/user/delete?&username='+recivedusername.username).
                   success(function (a) {
                       alert(a.event);
                       // if (a==200){
                   //         alert("Author deleted");
                   // // }
                   //     // else {
                   //         alert("ERROR");
               // }
                   })
               }
               
}])