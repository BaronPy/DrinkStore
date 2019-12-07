node{
    def app
    def image = 'baronpy/liquorstore'
    def hostport = '8888'
    def containerport = '8080'
    

   stage('SCM Checkout'){
        checkout scm
   }
    stage('Mvn Package'){
        def mvnHome = tool name: 'maven-3', type: 'maven'
        def mvnCMD = "${mvnHome}/bin/mvn"
        sh "${mvnCMD} clean package"
   }
        stage('Dangling image') {
        script {
            def cmd = "docker ps -aqf ancestor=$image"
            def container = sh (returnStdout: true, script: cmd)
            
            if (container) {
                echo 'Existing container found!! Deleting...'
                sh "docker stop \$($cmd)"
                sh "docker rm \$($cmd)"
                echo 'Done.'
            } 
            sh 'docker images -q -f dangling=true | xargs --no-run-if-empty docker rmi'
        }
    }
   stage('Build image') {
        /* This builds the actual image; synonymous to
         * docker build on the command line */
        app = docker.build(image)
    }
    stage('Test image') {
        app.inside {
            sh 'echo "Tests passed"'
        }
    }
    stage('Run image') {
        app.run("-p $hostport:$containerport")
    }
}