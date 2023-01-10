def call(){
    stage('Building'){
        //  Se existir fazemos rollback restart
        if(deployName){
            sh """
            kubectl rollout restart deployment ${deployName}
            kubectl port-forward --address 0.0.0.0 service/reactapp-service 31110:4080
            """
        } //else {
         // criar deployment e colocar port-forward
        //}
    }
}