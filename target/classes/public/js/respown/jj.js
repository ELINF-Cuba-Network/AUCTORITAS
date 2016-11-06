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

                $scope.eliminar=function($index)
                {
                    $scope.personas.splice($index,1);
                }

                $scope.agregar=function()
                {
                    $scope.personas.push({"nombre":$scope.nombre});
                    $scope.nombre="";
                }

                $scope.selectDS=function () {
                    $http.get('http://localhost:8082/api/datasource/listPersonalAuthor?type=jdbc').success(function (data) {
                        $scope.datasourcename=data;
                    })
                    $scope.datasources=true;
                }

                $scope.find=function (index) {
                    $scope.iname = $scope.iname==undefined ? "" : $scope.iname;
                    $scope.ilastname = $scope.ilastname==undefined ? "" : $scope.ilastname;
                    $scope.iauthority = $scope.iauthority==undefined ? "" : $scope.iauthority;
                    $scope.iuri = $scope.iuri==undefined ? "" : $scope.iuri;
                    if (($scope.iname=="")&&($scope.ilastname=="")&&($scope.iauthority=="")&&($scope.iuri=="")){
                        alert("Enter at least one element");
                        return false;
                    }
                    if($scope.iuri.size<8) {
                        alert("the uri element should have at least 8 characters");
                        return false;
                    }
                    $http.get('http://localhost:8082/api/localauthors/personalauthor/findby?name='+$scope.iname+'&lastname='+$scope.ilastname+'&authority='+$scope.iauthority+'&uri='+$scope.iuri+'&datasourceindex='+index).
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
                $scope.add=function(index)
                {
                    $scope.iname = $scope.iname==undefined ? "" : $scope.iname;
                    $scope.ilastname = $scope.ilastname==undefined ? "" : $scope.ilastname;
                    $scope.iauthority = $scope.iauthority==undefined ? "" : $scope.iauthority;
                    $scope.iuri = $scope.iuri==undefined ? "" : $scope.iuri;
                    if (($scope.iname=="")||($scope.ilastname=="")||($scope.iauthority=="")||($scope.iuri=="")){
                    alert("Enter all elements");
                        return false;
                    }
                    $http.post('http://localhost:8082/api/localauthors/personalauthor/new?name='+$scope.iname+'&lastname='+$scope.ilastname+'&authority='+$scope.iauthority+'&uri='+$scope.iuri+'&datasourceindex='+index).
                        success(function (a) {
                      if (a==200){
                          alert("Author inserted");
                    }
                    else {
                    alert("ERROR");}
                        // $scope.result=a;
                        
                    })
                }
                $scope.deleteAuthor2=function(index)
                { $scope.iuri = $scope.iuri==undefined ? "" : $scope.iuri;
                    if ($scope.iuri==""){
                        alert("Please insert the Uri");
                        return false;
                    }
                    $http.delete('http://localhost:8082/api/localauthors/personalauthor/delete?&uri='+$scope.iuri+'&datasourceindex='+index).
                    success(function (a) {
                        if (a==200){
                            alert("Author deleted");}
                        else {
                            alert("ERROR");}
                    })
                }

                // $scope.update=function(index)
                // {
                //     $scope.iname = $scope.iname==undefined ? "" : $scope.iname;
                //     $scope.ilastname = $scope.ilastname==undefined ? "" : $scope.ilastname;
                //     $scope.iauthority = $scope.iauthority==undefined ? "" : $scope.iauthority;
                //     $scope.iuri = $scope.iuri==undefined ? "" : $scope.iuri;
                //     $scope.inewuri = $scope.inewuri==undefined ? "" : $scope.inewuri;
                //     if ($scope.iuri==""){
                //         alert("Enter the Uri");
                //         return false;
                //     }
                //     if (($scope.iname=="")&&($scope.ilastname=="")&&($scope.iauthority=="")&&($scope.inewuri=="")){
                //         alert("Enter at least one element");
                //         return false;
                //     }
                //     $http.put('http://localhost:8082/api/localauthors/personalauthor/update?name='+$scope.iname+'&lastname='+$scope.ilastname+'&authority='+$scope.iauthority+'&uri='+$scope.iuri+'&newuri='+$scope.inewuri+'&datasourceindex='+index).
                //     success(function (a) {
                //         if (a==200){
                //             alert("Author updated");
                //     }
                //     else {
                //     alert("ERROR");}
                //         // $scope.result=a;
                //
                //     })
                // }
                $scope.deleteAuthor3=function (datasource, w) {
                    $http.delete('http://localhost:8082/api/localauthors/personalauthor/delete?&uri='+w.uri+'&datasourceindex='+datasource).
                    success(function (a) {
                        if (a==200){
                            alert("Author deleted");}
                        else {
                            alert("ERROR");}
                    })
                }
                $scope.update3=function(datasource,authorObject)
                {
                    $scope.iname2 = $scope.iname2==undefined ? authorObject.name : $scope.iname2;
                    $scope.ilastname2 = $scope.ilastname2==undefined ? authorObject.lastname : $scope.ilastname2;
                    $scope.iauthority2 = $scope.iauthority2==undefined ? authorObject.authority : $scope.iauthority2;
                 //   $scope.iuri2 = $scope.iuri2==undefined ? "" : $scope.iuri2;
                    $scope.inewuri2 = $scope.inewuri2==undefined ? authorObject.uri : $scope.inewuri2;

                    if (($scope.iname2=="")&&($scope.ilastname2=="")&&($scope.iauthority2=="")&&($scope.inewuri2=="")){
                        alert("Enter at least one element");
                        return false;
                    }
                    $http.put('http://localhost:8082/api/localauthors/personalauthor/update?name='+$scope.iname2+'&lastname='+$scope.ilastname2+'&authority='+$scope.iauthority2+'&uri='+authorObject.uri+'&newuri='+$scope.inewuri2+'&datasourceindex='+datasource).
                    success(function (a) {
                        if (a==200){
                            $scope.authorresp=true;
                            $scope.updateauthor2=false;
                            alert("Author updated");
                        }
                        else {
                            alert("ERROR");}
                        // $scope.result=a;

                    })
                }
                $scope.getService=function()
                {
                    $http.get('http://localhost:8082/api/author/personal?name='+$scope.fname+'&lastname='+$scope.flastname).
                    success(function(data) {
                        $scope.result = data;

                    });
                }
                $scope.eliminarS=function (id) {
                    alert(id);
                    // $http.get('http://localhost:8082/api/author/personal?name='+$scope.fname+'&lastname='+$scope.flastname).
                    // success(function(data) {
                    //     $scope.result = data;
                    //
                    // });
                }

            }
        ]
    )

