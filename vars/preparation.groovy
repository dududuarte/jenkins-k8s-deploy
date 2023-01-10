def call (Map config = [:]){
    stage('Preparation'){
            sh 'if [ -z "$(minikube status | grep -i Running)" ]; then echo "Starting Minikube..."; minikube start; else echo Minikube is running; fi'
            def deployName = sh(
                returnStdout: true,
                script: 'kubectl get deploy -o=jsonpath=\'{.items[?(@.metadata.labels.app=="reactapp")].metadata.name}\''
                )
            sh "rm -rf ${config.repoName}"
            sh "git clone https://github.com/${config.repoOwner}/${config.repoName}.git"
            return deployName
    }
}
