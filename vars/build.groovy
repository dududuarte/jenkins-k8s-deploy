def call(String repoName){
    stage('Building'){
        def dockerimagename = "duartemcg/reactapp"
        def dockerImage = ""
        sh "cd ${repoName}"
        dockerImage = docker.build(dockerimagename)
    }
    stage('Deploying to Docker'){
        def registryCredential = 'dockerhublogin'
        docker.withRegistry('http://registry.hub.docker.com', registryCredential) {
            dockerImage.push("latest")
        }
    }
}