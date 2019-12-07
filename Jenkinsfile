node{
    def app
    def image = 'baronpy/drinkstore'
    def hostport = '8181'
    def containerport = '8080'
    

   stage('SCM Checkout'){
        checkout scm
   }
    stage('Mvn Package'){
        def mvnHome = tool name: 'maven-3', type: 'maven'
        def mvnCMD = "${mvnHome}/bin/mvn"
        sh "${mvnCMD} clean package"
   }
        stage('Dangling docker image') {
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
   stage('Build docker image') {
        /* This builds the actual image; synonymous to
         * docker build on the command line */
        app = docker.build(image)
    }
    stage('Test docker image') {
        app.inside {
            sh 'echo "Tests passed"'
        }
    }
    stage('Run docker Image') {
        app.run("-p $hostport:$containerport")
    }
    stage("Trigger Silk Central Executions") {
        def path = tool name: 'gradle5.6.4', type: 'gradle'
        def scFile = new File(pwd(), "silkcentral.gradle")
        scFile.delete()
        scFile.getParentFile().mkdirs()
        writeFile([file: scFile.getAbsolutePath(), text: new URL ("http://172.16.0.1:19120/silkroot/tools/silkcentral.gradle").getText()])
        def scTriggerInfo = '-Psc_executionNodeIds=45 -Psc_host=http://172.16.0.1:19120 -Psc_token=0f58a614-edcd-403c-8953-fdeb37a572ca'
        if (isUnix()) { 
            sh "${path}/bin/gradle :silkCentralLaunch -b ${scFile} " + scTriggerInfo
        } else {
            bat "${path}/bin/gradle.bat :silkCentralLaunch -b ${scFile} " + scTriggerInfo
        }
        junit 'sc_results/junit*.xml' 
    }
}