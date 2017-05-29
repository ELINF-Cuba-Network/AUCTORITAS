angular.module('myApp', [])
    .controller('myController', ['$scope', '$http',
            function ($scope, $http) {

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
                $scope.updateAuthor2=function (datasource,w) {
                    $scope.updateauthor2=true;
                    $scope.authorresp=false;
                    $scope.datasource=datasource;
                    $scope.authorObject=w;
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

                $scope.selectDS=function () {
                    $http.get('http://localhost:8082/api/datasource/listCorporateAuthor?type=jdbc').success(function (data) {
                        $scope.datasourcename=data;
                    })
                    $scope.datasources=true;
                }

                $scope.findauthor2=function (index) {
                    $scope.fformname = $scope.fformname==undefined ? "" : $scope.fformname;
                    $scope.fformauthority = $scope.fformauthority==undefined ? "" : $scope.fformauthority;
                    $scope.fformuri = $scope.fformuri==undefined ? "" : $scope.fformuri;
                    if (($scope.fformname=="")&&($scope.fformauthority=="")&&($scope.fformuri=="")){
                        alert("Enter at least one element");
                        return false;
                    }
                    if(($scope.fformuri.length<8)&&($scope.fformuri!=="")) {
                        alert("The URI element should have at least 8 characters");
                        return false;
                    }
                    // $scope.$watch($scope.findauthor2(index));
                    
                    $http.get('http://localhost:8082/api/localauthors/corporateauthor/findby?name='+$scope.fformname+'&authority='+$scope.fformauthority+'&uri='+$scope.fformuri+'&datasourceindex='+index).
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
                    $scope.formauthority = $scope.formauthority==undefined ? "" : $scope.formauthority;
                    $scope.formuri = $scope.formuri==undefined ? "" : $scope.formuri;
                    if (($scope.formname=="")||($scope.formauthority=="")||($scope.formuri=="")){
                    alert("Enter all elements");
                        return false;
                    }
                    $http.post('http://localhost:8082/api/localauthors/corporateauthor/new?name='+$scope.formname+'&authority='+$scope.formauthority+'&uri='+$scope.formuri+'&datasourceindex='+index).
                        success(function (a) {
                        alert(a.event);
                          $scope.formname=undefined;
                          $scope.formauthority=undefined;
                          $scope.formuri=undefined;
                    })
                }
                $scope.deleteAuthor2=function(index)
                { $scope.delformuri = $scope.delformuri==undefined ? "" : $scope.delformuri;
                    if ($scope.delformuri==""){
                        alert("Please insert the Uri");
                        return false;
                    }
                    $http.delete('http://localhost:8082/api/localauthors/corporateauthor/delete?&uri='+$scope.delformuri+'&datasourceindex='+index).
                    success(function (a) {
                        alert(a.event);
                            $scope.delformuri=undefined;
                            return false;
                    })
                }

                $scope.deleteAuthor3=function (datasource, w) {
                    $http.delete('http://localhost:8082/api/localauthors/corporateauthor/delete?&uri='+w.uri+'&datasourceindex='+datasource).
                    success(function (a) {
                        alert(a.event);
                    })
                }
                $scope.updateauthor3=function(datasource,authorObject)
                {if (($scope.uformname==undefined)&&($scope.uformauthority==undefined)&&($scope.uformnewuri==undefined)){
                    alert("Enter at least one element");
                    return false;
                }
                    $scope.uformname = $scope.uformname==undefined ? authorObject.name : $scope.uformname;
                    $scope.uformauthority = $scope.uformauthority==undefined ? authorObject.authority : $scope.uformauthority;
                    $scope.uformnewuri = $scope.uformnewuri==undefined ? authorObject.uri : $scope.uformnewuri;


                    $http.put('http://localhost:8082/api/localauthors/personalauthor/update?name='+$scope.uformname+'&authority='+$scope.uformauthority+'&uri='+authorObject.uri+'&newuri='+$scope.uformnewuri+'&datasourceindex='+datasource).
                    success(function (a) {
                        alert(a.event);
                            $scope.authorresp=true;
                            $scope.updateauthor2=false;
                            $scope.uformname=undefined;
                            $scope.uformauthority=undefined;
                            $scope.uformnewuri=undefined;
                            return false;
                    })
                }
            }
        ]
    )

