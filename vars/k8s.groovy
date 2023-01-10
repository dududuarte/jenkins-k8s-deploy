def call(String deployName){
    stage('Deploying to K8s'){
        //  Se existir fazemos rollback restart
        if(deployName){
            sh """
            kubectl rollout restart deployment ${deployName}
            minikube service reactapp-service
            kubectl port-forward --address 0.0.0.0 service/reactapp-service 31110:5000
            """
        } //else {
         // criar deployment e colocar port-forward
        //}
    }
}