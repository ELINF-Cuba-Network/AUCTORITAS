angular.module('myApp', ['ngAnimate'])
    .controller('myController', ['$scope', '$http',
            function ($scope, $http) {

                var ENDPOINT = 'http://localhost:8082/api';

                $scope.addAuthor=function (index) {
                    $scope.addauthor=true;
                    $scope.findauthor=false;
                    $scope.authorresp=false;
                    $scope.deleteauthor=false;
                    $scope.updateauthor=false;
                    $scope.datasource=index;

                }
                $scope.findAuthor=function (index) {
                    $scope.findauthor=true;
                    $scope.addauthor=false;
                    $scope.deleteauthor=false;
                    $scope.updateauthor=false;
                    $scope.datasource=index;
                }
                $scope.deleteAuthor=function (index) {
                    $scope.deleteauthor=true;
                    $scope.addauthor=false;
                    $scope.findauthor=false;
                    $scope.authorresp=false;
                    $scope.updateauthor=false;
                    $scope.datasource=index;

                }
                $scope.updateAuthor=function (index) {
                    $scope.updateauthor=true;
                    $scope.deleteauthor=false;
                    $scope.addauthor=false;
                    $scope.findauthor=false;
                    $scope.authorresp=false;
                    $scope.datasource=index;
                }

                var oldAuthorUri = '';

                $scope.updateAuthor2=function (datasource,w) {
                    $scope.updateauthor2=true;
                    $scope.authorresp=false;
                    $scope.datasource=datasource;
                    $scope.authorObject=w;
                    oldAuthorUri = w.uri;
                }
                $scope.cancel=function () {
                    $scope.updateauthor2=false;
                    $scope.authorresp=true;

                }
                $scope.searchS=function () {
                    $scope.buscar=true;
                    $scope.insert=false;

                }
                $scope.insertS=function () {
                    $scope.insert=true;
                    $scope.buscar=false;
                }

                $scope.agregar=function()
                {
                    $scope.personas.push({"nombre":$scope.nombre});
                    $scope.nombre="";
                }

                $scope.selectDS=function () {
                    $http.get(ENDPOINT + '/datasource/listPersonalAuthor?type=jdbc').success(function (data) {
                        $scope.datasourcename=data;
                    })
                    $scope.datasources=true;
                }

                $scope.findauthor2=function (index) {
                    $scope.fformname = $scope.fformname==undefined ? "" : $scope.fformname;
                    $scope.fformlastname = $scope.fformlastname==undefined ? "" : $scope.fformlastname;
                    $scope.fformauthority = $scope.fformauthority==undefined ? "" : $scope.fformauthority;
                    $scope.fformuri = $scope.fformuri==undefined ? "" : $scope.fformuri;
                    if (($scope.fformname=="")&&($scope.fformlastname=="")&&($scope.fformauthority=="")&&($scope.fformuri=="")){
                        alert("You need to specify at least one element");
                        return false;
                    }

                    
                    $http.get(ENDPOINT + '/localauthors/personalauthor/findby?name='+$scope.fformname+'&lastname='+$scope.fformlastname+'&authority='+$scope.fformauthority+'&uri='+$scope.fformuri+'&datasourceindex='+index).
                success(function (data) {
                        $scope.findeddata=data;
                        if ($scope.findeddata.length>0){
                            $scope.authorresp=true;
                        }else {
                            $scope.authorresp=false;
                            alert("The search does not throw results");
                            return null;
                        }
                    })

                }
                $scope.moreauthor=function(index)
                {
                    $scope.formname = $scope.formname==undefined ? "" : $scope.formname;
                    $scope.formlastname = $scope.formlastname==undefined ? "" : $scope.formlastname;
                    $scope.formauthority = $scope.formauthority==undefined ? "" : $scope.formauthority;
                    $scope.formuri = $scope.formuri==undefined ? "" : $scope.formuri;

                    $http.post(ENDPOINT + '/localauthors/personalauthor/new?name='+$scope.formname+'&lastname='+$scope.formlastname+'&authority='+$scope.formauthority+'&uri='+$scope.formuri+'&datasourceindex='+index).
                        success(function (a) {
                        alert(a.event);
                     
                          $scope.formname=undefined;
                          $scope.formlastname=undefined;
                          $scope.formauthority=undefined;
                          $scope.formuri=undefined;
                  
                    })
                }
                
                $scope.reset = function () {
                    $scope.formname=undefined;
                    $scope.formlastname=undefined;
                    $scope.formauthority=undefined;
                    $scope.formuri=undefined;
                    $scope.uformauthority=undefined;
                    $scope.delformuri=undefined;
                    $scope.authorObject = undefined;
                }
                
                $scope.deleteAuthor2=function(index)
                { $scope.delformuri = $scope.delformuri==undefined ? "" : $scope.delformuri;
                    if ($scope.delformuri==""){
                        alert("Please insert the Uri");
                        return false;
                    }
                    $http.delete(ENDPOINT + '/localauthors/personalauthor/delete?&uri='+$scope.delformuri+'&datasourceindex='+index).
                    success(function (a) {
                        alert(a.event);
                            $scope.delformuri=undefined;
                            return false;
                    })
                }
                
                $scope.deleteAuthor3=function (datasource, w) {
                    $http.delete(ENDPOINT + '/localauthors/personalauthor/delete?&uri='+w.uri+'&datasourceindex='+datasource).
                    success(function (a) {
                        alert(a.event);
                    })
                }
                
                
                
                $scope.updateauthor3=function(datasource,authorObject){
                    $http.put(ENDPOINT + '/localauthors/personalauthor/update?name='+$scope.authorObject.name+'&lastname='+$scope.authorObject.name+'&authority='+$scope.authorObject.authority+'&uri='+oldAuthorUri+'&newuri='+$scope.authorObject.uri+'&datasourceindex='+datasource).
                    success(function (a) {
                        alert(a.event);
                            $scope.authorresp=true;
                            $scope.updateauthor2=false;
                            return false;
                    })
                }
            }
        ]
    )

