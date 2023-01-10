def call(String repoName){
    stage('Building'){
        environment {
            def dockerimagename = "duartemcg/reactapp"
            def registryCredential = 'dockerhublogin'
            def dockerImage = ""
        }
        sh "cd ${repoName}"
        dockerImage = docker.build(dockerimagename)
    }
    stage('Deploying to Docker'){
        environment {
            registryCredential = 'dockerhublogin'
        }
        docker.withRegistry('http://registry.hub.docker.com', registryCredential) {
            dockerImage.push("latest")
        }
    }
}