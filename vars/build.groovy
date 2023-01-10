def call(String repoName){
    def dockerImage = ""
    stage('Building'){
        def dockerimagename = "duartemcg/reactapp"
        sh "cd ${repoName}"
        dir(path: "./${repoName}"){
            dockerImage = docker.build(dockerimagename)
        }
    }
    stage('Deploying to Docker'){
        def registryCredential = 'dockerhublogin'
        docker.withRegistry('http://registry.hub.docker.com', registryCredential) {
            dockerImage.push("latest")
        }
    }
}